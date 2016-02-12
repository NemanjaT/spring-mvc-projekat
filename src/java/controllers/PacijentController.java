package controllers;

import factories.PregledFactory;
import models.Korisnici;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({"korisnik"})
@Controller
public class PacijentController {
    private final PregledFactory pregledFactory;
    
    public PacijentController() {
        pregledFactory = new PregledFactory();
    }
    
    @ModelAttribute("korisnik")
    public Korisnici createKorisnici() {
        return new Korisnici();
    }
    
    @RequestMapping("/mojkarton")
    public String mojKarton(Model model, @ModelAttribute("korisnik") Korisnici korisnik) {
        if(null == korisnik.getTip() || !korisnik.getTip().equals("pacijent"))
            return "redirect:home";
        model.addAttribute("pacijent", korisnik);
        model.addAttribute("pregledi", pregledFactory.getForKorisnik(korisnik.getId()));
        return "pregledi";
    }
}
