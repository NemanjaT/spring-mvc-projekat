package controllers;

import factories.KorisniciFactory;
import factories.NalazFactory;
import factories.PregledFactory;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import models.Korisnici;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("korisnik")
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
    
    @ModelAttribute("korisnik")
    public Korisnici createKorisnici() {
        return new Korisnici();
    }
    
    @RequestMapping("/nalaz")
    public String nalaz(Model model, HttpServletRequest request, 
            @RequestParam Integer nal, @ModelAttribute("korisnik") Korisnici korisnik) {
        if(korisnik.getId() == null || nal == null)
            return "redirect:home";
        model.addAttribute("nalaz", nalazFactory.getById(nal));
        model.addAttribute("lastUrl", request.getHeader("referer"));
        return "nalaz";
    }
    
    @RequestMapping("/pregledi")
    public String pregledi(Model model, @ModelAttribute("korisnik") Korisnici korisnik, @RequestParam Integer pac) {
        if(korisnik.getTip() == null || !korisnik.getTip().contains("lekar") || pac == null)
            return "redirect:home";
        model.addAttribute("pregledi", pregledFactory.getForKorisnik(pac));
        model.addAttribute("pacijent", korisniciFactory.getById(pac));
        return "pregledi";
    }
    
    @RequestMapping("/svipacijenti")
    public String sviPacijenti(Model model, @ModelAttribute("korisnik") Korisnici korisnik, @RequestParam(required = false) String q) {
        if(korisnik.getTip() == null || !korisnik.getTip().contains("lekar"))
            return "redirect:home";
        List<Korisnici> korisnici = korisniciFactory.getAllPacijenti(true);
        if(q != null && !q.equals("")) {
            List<Korisnici> temp = new ArrayList<>();
            for(Korisnici k : korisnici) {
                String query = q.toLowerCase();
                String imePrezime = k.getIme().toLowerCase() + " " + k.getPrezime().toLowerCase();
                String prezimeIme = k.getPrezime().toLowerCase() + " " + k.getIme().toLowerCase();
                if(imePrezime.contains(query) || prezimeIme.contains(query))
                    temp.add(k);
            }
            korisnici = temp;
        }
        model.addAttribute("pacijenti", korisnici);
        return "svipacijenti";
    }
}
