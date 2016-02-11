package controllers;

import factories.KorisniciFactory;
import factories.NalazFactory;
import factories.PregledFactory;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import models.Korisnici;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SharedController {
    private final NalazFactory nalazFactory;
    private final PregledFactory pregledFactory;
    private final KorisniciFactory korisniciFactory;
    
    public SharedController() {
        nalazFactory = new NalazFactory();
        pregledFactory = new PregledFactory();
        korisniciFactory = new KorisniciFactory();
    }
    
    @RequestMapping("/nalaz")
    public String nalaz(HttpServletRequest request) {
        if(request.getSession().getAttribute("korisnik") == null || request.getParameter("nal") == null)
            return "redirect:home";
        request.setAttribute("nalaz", nalazFactory.getById(Integer.parseInt(request.getParameter("nal"))));
        request.setAttribute("lastUrl", request.getHeader("referer"));
        return "nalaz";
    }
    
    @RequestMapping("/pregledi")
    public String pregledi(HttpServletRequest request) {
        String pac = request.getParameter("pac");
        Korisnici korisnik = (Korisnici)request.getSession().getAttribute("korisnik");
        if(korisnik == null || !korisnik.getTip().contains("lekar") || pac == null)
            return "redirect:home";
        int pacijentId = Integer.parseInt(pac);
        request.setAttribute("pregledi", pregledFactory.getForKorisnik(pacijentId));
        request.setAttribute("pacijent", korisniciFactory.getById(pacijentId));
        return "pregledi";
    }
    
    @RequestMapping("/svipacijenti")
    public String sviPacijenti(HttpServletRequest request) {
        Korisnici korisnik = (Korisnici) request.getSession().getAttribute("korisnik");
        if(korisnik == null || !korisnik.getTip().contains("lekar"))
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
}
