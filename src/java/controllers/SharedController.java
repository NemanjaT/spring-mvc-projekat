package controllers;

import factories.NalazFactory;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SharedController {
    private final NalazFactory nalazFactory;
    
    public SharedController() {
        nalazFactory = new NalazFactory();
    }
    
    @RequestMapping("/nalaz")
    public String nalaz(HttpServletRequest request) {
        if(request.getSession().getAttribute("korisnik") == null || request.getParameter("nal") == null)
            return "redirect:home";
        request.setAttribute("nalaz", nalazFactory.getById(Integer.parseInt(request.getParameter("nal"))));
        request.setAttribute("lastUrl", request.getHeader("referer"));
        return "nalaz";
    }
}
