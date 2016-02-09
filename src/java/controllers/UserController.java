package controllers;

import factories.KlinikaFactory;
import factories.KorisniciFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import models.Korisnici;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private final KorisniciFactory korisniciFactory;
    private final KlinikaFactory klinikaFactory;
    
    public UserController() {
        korisniciFactory = new KorisniciFactory();
        klinikaFactory = new KlinikaFactory();
    }
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String loginGet(HttpSession session) {
        if(session.getAttribute("korisnik") != null)
            return "redirect:home";
        return "login";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginPost(HttpServletRequest request) {
        Korisnici korisnici = korisniciFactory.tryLogin(request.getParameter("jmbg"), request.getParameter("lozinka"));
        if(korisnici != null) {
            request.getSession().setAttribute("korisnik", korisnici);
            return "redirect:home";
        } else {
            request.setAttribute("error", "Unete informacije nisu ispravne ili vam nije odobren nalog.");
        }
        return "login";
    }
    
    @RequestMapping(value="/logout")
    public String logout(HttpSession session) {
        session.setAttribute("korisnik", null);
        return "redirect:login";
    }
    
    @RequestMapping(value="/changepassword", method=RequestMethod.GET)
    public String changePasswordGet(HttpSession session) {
        if(session.getAttribute("korisnik") == null)
            return "redirect:login";
        return "changepassword";
    }
    
    @RequestMapping(value="/changepassword", method=RequestMethod.POST)
    public String changePasswordPost(HttpServletRequest request, RedirectAttributes redirect) {
        Korisnici korisnik = (Korisnici)request.getSession().getAttribute("korisnik");
        int izmena = 0;
        if(korisnik != null) {
            izmena = korisniciFactory.updatePassword(korisnik, request.getParameter("staralozinka"), request.getParameter("lozinka"));
            request.setAttribute("error", "Neuspešno izmenjeni podaci.");
            redirect.addAttribute("success", "Uspešno izmenjeni podaci. Prijavite se opet.");
            request.getSession().setAttribute("korisnik", null);
        }
        return izmena > 0 ? "redirect:login" : "changepassword";
    }
    
    @RequestMapping(value="/register", method=RequestMethod.GET)
    public String registerGet(HttpServletRequest request) {
        if(request.getSession().getAttribute("korisnik") != null)
            return "redirect:home";
        request.setAttribute("klinike", klinikaFactory.getAll());
        return "register";
    }
    
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String registerPost(HttpServletRequest request, RedirectAttributes redirect) {
        String jmbg = request.getParameter("jmbg");
        String brojKnjizice = request.getParameter("broj-knjizice");
        String email = request.getParameter("email");
        
        boolean jmbgTest = Pattern.matches("^(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])[0-9]{9}$", jmbg);
        boolean brojKnjiziceTest = Pattern.matches("^[0-9]{11}$", brojKnjizice);
        boolean emailTest = Pattern.matches("^(\\w+).*(\\w*)@(\\w+).((com)|(org)|(rs)|(edu))$", email);
        
        if(!jmbgTest || !brojKnjiziceTest || !emailTest || request.getParameter("lozinka").length() < 5) {
            request.setAttribute("error", "Format nije dobar ili informacije nisu tačno unete!");
            request.setAttribute("klinike", klinikaFactory.getAll());
            return "register";
        }
        
        Korisnici korisnik = new Korisnici();
        korisnik.setAdresa(request.getParameter("adresa"));
        korisnik.setBrojKnjizice(brojKnjizice);
        korisnik.setEmail(email);
        korisnik.setIme(request.getParameter("ime"));
        korisnik.setPrezime(request.getParameter("prezime"));
        korisnik.setJmbg(jmbg);
        korisnik.setKlinikaId(Integer.parseInt(request.getParameter("klinika")));
        korisnik.setLozinka(request.getParameter("lozinka"));
        korisnik.setOsiguranjeImePrezime(request.getParameter("osiguranje-ime-prezime"));
        korisnik.setOsiguranjeSrodstvo(request.getParameter("osiguranje-srodstvo"));
        korisnik.setPol(request.getParameter("pol"));
        korisnik.setTelefon(request.getParameter("telefon"));
        korisnik.setTip("pacijent");
        korisnik.setPrihvacen(0);
        
        int registrovanStatus = korisniciFactory.insertKorisnik(korisnik);
        if(registrovanStatus > 0) {
            redirect.addAttribute("success", "Uspešno ste se prijavili. Administrator će proveriti vašu registraciju uskoro.");
        } else {
            request.setAttribute("error", "JMBG, broj knizice ili e-mail adresa već postoji!");
        }
        return registrovanStatus > 0 ? "redirect:login" : "register";
    }
}
