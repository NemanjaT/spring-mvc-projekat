package controllers;

import factories.KlinikaFactory;
import factories.KorisniciFactory;
import factories.SpecijalistaTipFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import models.Klinika;
import models.Korisnici;
import models.SpecijalistaTip;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdministratorController {
    private final KorisniciFactory korisniciFactory;
    private final KlinikaFactory klinikaFactory;
    private final SpecijalistaTipFactory specijalistaTipFactory;
    
    public AdministratorController() {
        korisniciFactory = new KorisniciFactory();
        klinikaFactory = new KlinikaFactory();
        specijalistaTipFactory = new SpecijalistaTipFactory();
    }
    
    @RequestMapping("/pregledreg")
    public String pregledReg(HttpServletRequest request) {
        if(nijeAdmin(request.getSession()))
            return "redirect:home";
        request.setAttribute("pacijenti", korisniciFactory.getAllPacijenti(false));
        return "neprihvaceni-pacijenti";
    }
    
    @RequestMapping("/odgovorregistracije")
    public String odgovorRegistracije(HttpServletRequest request) {
        if(nijeAdmin(request.getSession()) || request.getParameter("prihvacen") == null || request.getParameter("pac") == null)
            return "redirect:pregledreg";
        Korisnici korisnik = korisniciFactory.getById(Integer.parseInt(request.getParameter("pac")));
        if(request.getParameter("prihvacen").equals("1")) {
            korisniciFactory.prihvatiKorisnika(korisnik);
        } else {
            korisniciFactory.deleteKorisnik(korisnik);
        }
        return "redirect:pregledreg";
    }
    
    @RequestMapping(value="/registrujlekara", method=RequestMethod.GET)
    public String registrujLekaraGet(HttpServletRequest request) {
        if(nijeAdmin(request.getSession()))
            return "redirect:home";
        List<Korisnici> lekari = korisniciFactory.getAllLekari();
        List<Klinika> klinike = klinikaFactory.getAll();
        List<SpecijalistaTip> specijalisteTipovi = specijalistaTipFactory.getAllLow();
        HashMap<Korisnici, SpecijalistaTip> kst = new HashMap<>();
        for(Korisnici k : lekari) {
            if(k.getSpecijalistaTipId() == null) {
                kst.put(k, new SpecijalistaTip());
                continue;
            } else if(k.getSpecijalistaTipId() == 1) {
                kst.put(k, new SpecijalistaTip("Načelnik"));
                continue;
            }
            specijalisteTipovi.stream().filter((st) -> (Objects.equals(k.getSpecijalistaTipId(), st.getId()))).forEach((st) -> {
                kst.put(k, st);
            });
        }
        request.setAttribute("lekari", kst);
        request.setAttribute("specijaliste", specijalisteTipovi);
        request.setAttribute("klinike", klinike);
        return "registruj-lekara";
    }
    
    @RequestMapping(value="/registrujlekara", method=RequestMethod.POST)
    public String registrujLekaraPost(HttpServletRequest request) {
        if(nijeAdmin(request.getSession()))
            return "redirect:home";
        
        Korisnici korisnik = new Korisnici();
        korisnik.setPrihvacen(1);
        korisnik.setAdresa(request.getParameter("adresa"));
        korisnik.setBrojKnjizice(request.getParameter("broj-knjizice"));
        korisnik.setEmail(request.getParameter("email"));
        korisnik.setIme(request.getParameter("ime"));
        korisnik.setPrezime(request.getParameter("prezime"));
        korisnik.setJmbg(request.getParameter("jmbg"));
        korisnik.setKlinikaId(Integer.parseInt(request.getParameter("klinika")));
        korisnik.setLozinka(request.getParameter("lozinka"));
        korisnik.setOsiguranjeImePrezime(request.getParameter("osiguranje-ime-prezime"));
        korisnik.setOsiguranjeSrodstvo(request.getParameter("osiguranje-srodstvo"));
        korisnik.setPol(request.getParameter("pol"));
        korisnik.setTelefon(request.getParameter("telefon"));
        String tip = request.getParameter("tip");
        switch (tip) {
            case "opste":
                korisnik.setTip("lekar opste prakse");
                break;
            case "speci":
                korisnik.setTip("lekar specijalista");
                korisnik.setSpecijalistaTipId(Integer.parseInt(request.getParameter("specijalizacija")));
                break;
            default:
                request.setAttribute("error", "Nije ispravno unet tip");
                return "registruj-lekara";
        }
        
        int insert = korisniciFactory.insertKorisnik(korisnik);
        if(insert <= 0) 
            request.setAttribute("error", "Neuspešno snimljen korisnik");
        return insert > 0 ? "redirect:registrujlekara" : "registruj-lekara";
    }
    
    @RequestMapping(value="/uklonilekara")
    public String ukloniLekara(HttpServletRequest request) {
        if(nijeAdmin(request.getSession()) || request.getParameter("lek") == null)
            return "redirect:home";
        Korisnici korisnik = korisniciFactory.getById(Integer.parseInt(request.getParameter("lek")));
        korisniciFactory.deleteKorisnik(korisnik);
        return "redirect:registrujlekara";
    }
    
    @RequestMapping(value="/nacelnici", method=RequestMethod.GET)
    public String nacelniciGet(HttpServletRequest request) {
        if(nijeAdmin(request.getSession()))
            return "redirect:home";
        List<Korisnici> nacelnici = korisniciFactory.getAllNacelnici();
        List<Klinika> klinike = klinikaFactory.getAll();
        HashMap<Korisnici,Klinika> kk = new HashMap<>();
        klinike.stream().forEach((kl) -> {
            nacelnici.stream().filter((ko) -> (Objects.equals(kl.getId(), ko.getKlinikaId()))).forEach((ko) -> {
                kk.put(ko, kl);
            });
        });
        request.setAttribute("nacelnici", kk);
        request.setAttribute("lekari", korisniciFactory.getAllLekari());
        return "nacelnici";
    }
    
    @RequestMapping(value="/nacelnici", method=RequestMethod.POST)
    public String nacelniciPost(HttpServletRequest request) {
        if(nijeAdmin(request.getSession()) || request.getParameter("nacelnik") == null || request.getParameter("lekar") == null)
            return "redirect:home";
        int nacelnikId = Integer.parseInt(request.getParameter("nacelnik"));
        int lekarId = Integer.parseInt(request.getParameter("lekar"));
        Korisnici nacelnik = korisniciFactory.getById(nacelnikId);
        Korisnici lekar    = korisniciFactory.getById(lekarId);
        lekar.setTip("lekar specijalista");
        lekar.setSpecijalistaTipId(1);
        lekar.setKlinikaId(nacelnik.getKlinikaId());
        nacelnik.setSpecijalistaTipId(2);
        korisniciFactory.simpleUpdate(nacelnik);
        korisniciFactory.simpleUpdate(lekar);
        return "redirect:nacelnici";
    }
    
    @RequestMapping(value="/klinike", method=RequestMethod.GET)
    public String klinikeGet(HttpServletRequest request) {
        if(nijeAdmin(request.getSession()))
            return "redirect:home";
        request.setAttribute("klinike", klinikaFactory.getAll());
        return "klinike";
    }
    
    @RequestMapping(value="/klinike", method=RequestMethod.POST)
    public String klinikePost(HttpServletRequest request) {
        if(nijeAdmin(request.getSession()) || request.getParameter("akcija") == null)
            return "redirect:home";
        if(request.getParameter("akcija").equals("dodaj")) {
            Klinika klinika = new Klinika();
            klinika.setIme(request.getParameter("ime"));
            klinika.setAdresa(request.getParameter("telefon"));
            klinika.setTelefon(request.getParameter("telefon"));
            klinikaFactory.insertKlinika(klinika);
        } else {
            Klinika klinika = klinikaFactory.getById(Integer.parseInt(request.getParameter("akcija")));
            klinikaFactory.deleteKlinika(klinika);
        }
        return "redirect:klinike";
    }
    
    private boolean nijeAdmin(HttpSession session) {
        Korisnici korisnik = (Korisnici)session.getAttribute("korisnik");
        if(korisnik == null)
            return true;
        return !korisnik.getTip().equals("administrator");
    }
}
