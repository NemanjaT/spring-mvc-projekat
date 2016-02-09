package controllers;

import factories.KlinikaFactory;
import factories.KorisniciFactory;
import factories.PregledFactory;
import factories.SpecijalistaTipFactory;
import factories.UputFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import models.Korisnici;
import models.Pregled;
import models.Uput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LekarOpstePrakseController {
    private final KorisniciFactory korisniciFactory;
    private final PregledFactory pregledFactory;
    private final KlinikaFactory klinikaFactory;
    private final UputFactory uputFactory;
    private final SpecijalistaTipFactory specijalistaTipFactory;
    
    public LekarOpstePrakseController() {
        korisniciFactory = new KorisniciFactory();
        pregledFactory = new PregledFactory();
        klinikaFactory = new KlinikaFactory();
        uputFactory = new UputFactory();
        specijalistaTipFactory = new SpecijalistaTipFactory();
    }
    
    @RequestMapping("/svipacijenti")
    public String sviPacijenti(HttpServletRequest request) {
        if(nijeLekarOpstePrakse(request.getSession()))
            return "redirect:home";
        List<Korisnici> korisnici = korisniciFactory.getAllPacijenti(true);
        String query = request.getParameter("q");
        if(query != null) {
            List<Korisnici> temp = new ArrayList<>();
            for(Korisnici k : korisnici) {
                String imePrezime = k.getIme().toLowerCase() + " " + k.getPrezime().toLowerCase();
                String prezimeIme = k.getPrezime().toLowerCase() + " " + k.getIme().toLowerCase();
                if((imePrezime.contains(query.toLowerCase())) || (prezimeIme.contains(query.toLowerCase()))) {
                    temp.add(k);
                }
            }
            korisnici = temp;
        }
        request.setAttribute("pacijenti", korisnici);
        return "svipacijenti";
    }
    
    @RequestMapping("/pregledi")
    public String pregledi(HttpServletRequest request) {
        String pac = request.getParameter("pac");
        if(nijeLekarOpstePrakse(request.getSession()) || pac == null)
            return "redirect:home";
        int pacijentId = Integer.parseInt(pac);
        request.setAttribute("pregledi", pregledFactory.getForKorisnik(pacijentId));
        request.setAttribute("pacijent", korisniciFactory.getById(pacijentId));
        return "pregledi";
    }
    
    @RequestMapping(value="/noviizvestaj", method=RequestMethod.GET)
    public String noviIzvestajGet(HttpServletRequest request) {
        String pac = request.getParameter("pac");
        if(nijeLekarOpstePrakse(request.getSession()) || pac == null)
            return "redirect:home";
        request.setAttribute("pac", pac);
        request.setAttribute("klinike", klinikaFactory.getAll());
        request.setAttribute("specijalisti", specijalistaTipFactory.getAllLow());
        return "noviizvestaj";
    }
    
    @RequestMapping(value="/noviizvestaj", method=RequestMethod.POST)
    public String noviIzvestajPost(HttpServletRequest request) {
        int pregledano = 0;
        int upuceno    = 0;
        try {
            Pregled pregled = new Pregled();
            Uput uput = new Uput();
            String cuvajPacijenta = request.getParameter("cuvaj-pacijenta");
            String string = request.getParameter("datum-sledece-kontrole");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = format.parse(string);
            int pacijentId = Integer.parseInt(request.getParameter("pacijent"));
            int specijalistaTipId = Integer.parseInt(request.getParameter("specijalista"));
            
            pregled.setCuvajPacijenta(cuvajPacijenta == null ? 0 : 1);
            pregled.setDatumPregleda(new Date());
            pregled.setDatumSledeceKontrole(date);
            pregled.setPacijentId(pacijentId);
            pregled.setDijagnoza(request.getParameter("dijagnoza"));
            pregled.setNazivBolesti(request.getParameter("naziv-bolesti"));
            pregled.setPropisanaTerapija(request.getParameter("propisana-terapija"));
            pregled.setTegobe(request.getParameter("tegobe"));
            
            pregledano = pregledFactory.insertPregled(pregled);
            if(request.getParameter("uput") != null) {
                uput.setPacijentId(pregled.getPacijentId());
                uput.setDatumPregleda(date);
                uput.setKlinikaId(Integer.parseInt(request.getParameter("klinika")));
                uput.setSpecijalistaTipId(specijalistaTipId);
                upuceno = uputFactory.insertUput(uput);
            }
            
            if(pregledano < 0 && upuceno < 0) {
                request.setAttribute("error", "Neka informacija nije ispravno uneta!");
            }
        } catch (ParseException ex) {
            Logger.getLogger(LekarOpstePrakseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pregledano > 0 && upuceno >= 0 ? "redirect:svipacijenti" : "noviizvestaj";
    }
    
    private boolean nijeLekarOpstePrakse(HttpSession session) {
        Korisnici k = (Korisnici) session.getAttribute("korisnik");
        if(k == null)
            return true;
        return !k.getTip().equals("lekar opste prakse");
    }
}
