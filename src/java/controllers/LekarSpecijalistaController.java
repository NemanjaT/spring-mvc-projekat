package controllers;

import factories.KlinikaFactory;
import factories.KorisniciFactory;
import factories.NalazFactory;
import factories.PregledFactory;
import factories.SpecijalistaTipFactory;
import factories.UputFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import models.Korisnici;
import models.Nalaz;
import models.Pregled;
import models.Uput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LekarSpecijalistaController {
    private final KorisniciFactory korisniciFactory;
    private final UputFactory uputFactory;
    private final PregledFactory pregledFactory;
    private final SpecijalistaTipFactory specijalistaTipFactory;
    private final KlinikaFactory klinikaFactory;
    private final NalazFactory nalazFactory;
    
    public LekarSpecijalistaController() {
        korisniciFactory = new KorisniciFactory();
        uputFactory = new UputFactory();
        pregledFactory = new PregledFactory();
        specijalistaTipFactory = new SpecijalistaTipFactory();
        klinikaFactory = new KlinikaFactory();
        nalazFactory = new NalazFactory();
    }
    
    @RequestMapping("/pacijentiklinike")
    public String pacijentiKlinike(HttpServletRequest request) {
        if(nijeLekarSpecijalista(request.getSession()))
            return "redirect:home";
        Korisnici korisnik = (Korisnici)request.getSession().getAttribute("korisnik");
        List<Uput> uputi = uputFactory.getAllPresentUniqueForLekar(korisnik);
        HashMap<Korisnici, Uput> kdp = new HashMap<>();
        for(Uput uput : uputi) {
            int pacId = uput.getPacijentId();
            kdp.put(korisniciFactory.getById(pacId), uput);
        }
        
        String query = request.getParameter("q");
        if(query != null) {
            HashMap<Korisnici, Uput> temp = new HashMap<>();
            for(HashMap.Entry<Korisnici, Uput> ku : kdp.entrySet()) {
                String imePrezime = ku.getKey().getIme().toLowerCase() + " " + ku.getKey().getPrezime().toLowerCase();
                String prezimeIme = ku.getKey().getPrezime().toLowerCase() + " " + ku.getKey().getIme().toLowerCase();
                if((imePrezime.contains(query.toLowerCase())) || (prezimeIme.contains(query.toLowerCase()))) {
                    temp.put(ku.getKey(), ku.getValue());
                }
            }
            kdp = temp;
        }
        request.setAttribute("pacijenti", kdp);
        return "pacijentiklinike";
    }
    
    @RequestMapping("/karton")
    public String karton(HttpServletRequest request) {
        String pacijentId = request.getParameter("pac");
        if(nijeLekarSpecijalista(request.getSession()) || pacijentId == null)
            return "redirect:home";
        Korisnici pacijent = korisniciFactory.getById(Integer.parseInt(pacijentId));
        request.setAttribute("pacijent", pacijent);
        List<Pregled> pregledi = pregledFactory.getForKorisnik(pacijent.getId());
        request.setAttribute("pregledi", pregledi);
        List<Uput> uputi = uputFactory.getAllForPacijent(pacijent.getId());
        request.setAttribute("uputi", uputi);
        request.setAttribute("directpage", "specpregled");
        return "pregledi";
    }
    
    @RequestMapping(value="/detaljnipregled", method=RequestMethod.GET)
    public String detaljniPregledGet(HttpServletRequest request) {
        String uputId = request.getParameter("uput");
        if(nijeLekarSpecijalista(request.getSession()) || uputId == null)
            return "redirect:home";
        request.setAttribute("uput", uputId);
        request.setAttribute("specijalisti", specijalistaTipFactory.getAllLow());
        request.setAttribute("klinike", klinikaFactory.getAll());
        return "detaljnipregled";
    }
    
    @RequestMapping(value="/detaljnipregled", method=RequestMethod.POST)
    public String detaljniPregledPost(HttpServletRequest request) {
        int nalazInsert, pregledInsert;
        nalazInsert = pregledInsert = 0;
        try {
            Date date = new Date();
            
            Uput uput = uputFactory.getById(Integer.parseInt(request.getParameter("uput-id")));
            
            Nalaz nalaz = new Nalaz();
            nalaz.setDatumNalaza(date);
            nalaz.setDisanje(Integer.parseInt(request.getParameter("disanje")));
            nalaz.setKrvnaSlika(request.getParameter("krvna-slika"));
            nalaz.setKrvniPritisak(request.getParameter("krvni-pritisak"));
            nalaz.setMokraca(request.getParameter("mokraca"));
            nalaz.setPuls(Integer.parseInt(request.getParameter("puls")));
            nalaz.setSpecificanPregled(request.getParameter("specifican-pregled"));
            nalaz.setStolica(request.getParameter("stolica"));
            nalaz.setTelesnaTemperatura(Integer.parseInt(request.getParameter("telesna-temperatura")));
            
            String string = request.getParameter("datum-sledece-kontrole");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date dateSlKontrole = format.parse(string);
            Pregled pregled = new Pregled();
            pregled.setCuvajPacijenta(request.getParameter("cuvaj-pacijenta") == null ? 0 : 1);
            pregled.setDatumPregleda(date);
            pregled.setDatumSledeceKontrole(dateSlKontrole);
            pregled.setDijagnoza(request.getParameter("dijagnoza"));
            pregled.setNazivBolesti(request.getParameter("naziv-bolesti"));
            pregled.setPacijentId(uput.getPacijentId());
            pregled.setPropisanaTerapija(request.getParameter("propisana-terapija"));
            pregled.setTegobe(request.getParameter("tegobe"));
            
            nalazInsert = nalazFactory.insertNalaz(nalaz);
            pregled.setNalazId(nalaz.getId());
            pregledInsert = pregledFactory.insertPregled(pregled);
            uputFactory.updateUputPregledan(uput);
        } catch (ParseException ex) {
            Logger.getLogger(LekarSpecijalistaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("error", "Nije uspešno snimanje nalaz-a ili pregleda");
        return (nalazInsert > 0 && pregledInsert > 0) ? "redirect:pacijentiklinike" : "detaljnipregled";
    }
    
    private boolean nijeLekarSpecijalista(HttpSession session) {
        Korisnici k = (Korisnici) session.getAttribute("korisnik");
        if(k == null)
            return true;
        return !k.getTip().equals("lekar specijalista");
    }
}
