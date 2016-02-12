package controllers;

import factories.KlinikaFactory;
import factories.KorisniciFactory;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import models.Korisnici;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SessionAttributes({"korisnik"})
@Controller
public class UserController {
    private final KorisniciFactory korisniciFactory;
    private final KlinikaFactory klinikaFactory;
    
    public UserController() {
        korisniciFactory = new KorisniciFactory();
        klinikaFactory = new KlinikaFactory();
    }
    
    @ModelAttribute("korisnik")
    public Korisnici createKorisnici() {
        return new Korisnici();
    }
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String loginGet(Model model, @ModelAttribute("korisnik") Korisnici korisnik) {
        if(korisnik.getId() != null) {
            return "redirect:home";
        }
        return "login";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginPost(Model model, @RequestParam String jmbg, @RequestParam String lozinka) {
        Korisnici korisnik = korisniciFactory.tryLogin(jmbg, lozinka);
        if (korisnik == null) {
            model.addAttribute("error", "Unete informacije nisu ispravne ili vam nije odobren nalog.");
            return "login";
        }
        model.addAttribute("korisnik", korisnik);
        return "redirect:home";
    }
    
    @RequestMapping(value="/logout")
    public String logout(Model model, HttpSession session, RedirectAttributes redirect) {
        session.invalidate();
        String successMsg = (String)model.asMap().get("success");
        redirect.addFlashAttribute("success", successMsg == null ? "Uspešno ste se odjavili." : successMsg);
        return "redirect:login";
    }
    
    @RequestMapping(value="/changepassword", method=RequestMethod.GET)
    public String changePasswordGet(@ModelAttribute("korisnik") Korisnici korisnik) {
        if (korisnik.getId() == null)
            return "redirect:login";
        return "changepassword";
    }
    
    @RequestMapping(value="/changepassword", method=RequestMethod.POST)
    public String changePasswordPost(@RequestParam String lozinka, 
            @RequestParam("staralozinka") String staraLozinka,
            @ModelAttribute("korisnik") Korisnici korisnik,
            Model model,
            RedirectAttributes redirect) {
        int izmena = 0;
        if(korisnik.getId() != null) {
            izmena = korisniciFactory.updatePassword(korisnik, staraLozinka, lozinka);
            if(izmena > 0) {
                redirect.addFlashAttribute("success", "Uspešno promenjena lozinka. Prijavite se opet.");
            } else {
                model.addAttribute("error", "Neuspešno izmenjeni podaci.");
            }
        }
        return izmena > 0 ? "redirect:logout" : "changepassword";
    }
    
    @RequestMapping(value="/register", method=RequestMethod.GET)
    public String registerGet(Model model, @ModelAttribute("korisnik") Korisnici korisnik) {
        if(korisnik.getId() != null)
            return "redirect:home";
        model.addAttribute("register", new Korisnici());
        model.addAttribute("klinike", klinikaFactory.getAll());
        return "register";
    }
    
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String registerPost(Model model, @ModelAttribute("register") Korisnici register, 
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        boolean jmbgTest = Pattern.matches("^(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])[0-9]{9}$", register.getJmbg());
        boolean brojKnjiziceTest = Pattern.matches("^[0-9]{11}$", register.getBrojKnjizice());
        boolean emailTest = Pattern.matches("^(\\w+).*(\\w*)@(\\w+).((com)|(org)|(rs)|(edu))$", register.getEmail());
        if(!jmbgTest || !brojKnjiziceTest || !emailTest || register.getLozinka().length() < 5) {
            model.addAttribute("error", "Format nije dobar ili informacije nisu tačno unete!");
            model.addAttribute("klinike", klinikaFactory.getAll());
            return "register";
        }
        BindingResult nesto = bindingResult;
        register.setTip("pacijent");
        int insertovan = korisniciFactory.insertKorisnik(register);
        if(insertovan <= 0)
            model.addAttribute("error", "JMBG, broj knjižice ili email adresa već postoji.");
        else
            redirectAttributes.addFlashAttribute("success", "Uspešno ste se registrovali. Administrator će uskoro pregledati vašu registraciju.");
        return insertovan > 0 ? "redirect:login" : "register";
    }
}
