package controllers;

import factories.KorisniciFactory;
import factories.UputFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import models.Korisnici;
import models.Uput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LekarSpecijalistaController {
    private final KorisniciFactory korisniciFactory;
    private final UputFactory uputFactory;
    
    public LekarSpecijalistaController() {
        korisniciFactory = new KorisniciFactory();
        uputFactory = new UputFactory();
    }
    
    @RequestMapping("/pacijentiklinike")
    public String pacijentiKlinike(HttpServletRequest request) {
        if(nijeLekarSpecijalista(request.getSession()))
            return "redirect:home";
        List<Uput> uputi = uputFactory.getAllPresent();
        HashMap<Korisnici, String> kdp = new HashMap<>();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        for(Uput uput : uputi) {
            int pacId = uput.getPacijentId();
            kdp.put(korisniciFactory.getById(pacId), df.format(uput.getDatumPregleda()));
        }
        
        String query = request.getParameter("q");
        if(query != null) {
            HashMap<Korisnici, String> temp = new HashMap<>();
            for(HashMap.Entry<Korisnici, String> ks : kdp.entrySet()) {
                String imePrezime = ks.getKey().getIme().toLowerCase() + " " + ks.getKey().getPrezime().toLowerCase();
                String prezimeIme = ks.getKey().getPrezime().toLowerCase() + " " + ks.getKey().getIme().toLowerCase();
                if((imePrezime.contains(query.toLowerCase())) || (prezimeIme.contains(query.toLowerCase()))) {
                    temp.put(ks.getKey(), ks.getValue());
                }
            }
            kdp = temp;
        }
        request.setAttribute("pacijenti", kdp);
        return "pacijentiklinike";
    }
    
    @RequestMapping("/karton")
    public String karton(HttpServletRequest request) {
        return "";
    }
    
    private boolean nijeLekarSpecijalista(HttpSession session) {
        Korisnici k = (Korisnici) session.getAttribute("korisnik");
        if(k == null)
            return true;
        return !k.getTip().equals("lekar specijalista");
    }
}
