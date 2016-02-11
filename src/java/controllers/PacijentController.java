package controllers;

import factories.KorisniciFactory;
import factories.PregledFactory;
import javax.servlet.http.HttpServletRequest;
import models.Korisnici;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PacijentController {
    private final PregledFactory pregledFactory;
    
    public PacijentController() {
        pregledFactory = new PregledFactory();
    }
    
    @RequestMapping("/mojkarton")
    public String mojKarton(HttpServletRequest request) {
        Korisnici korisnik = (Korisnici) request.getSession().getAttribute("korisnik");
        if(korisnik == null || !korisnik.getTip().equals("pacijent"))
            return "redirect:home";
        request.setAttribute("pacijent", korisnik);
        request.setAttribute("pregledi", pregledFactory.getForKorisnik(korisnik.getId()));
        return "pregledi";
    }
}
