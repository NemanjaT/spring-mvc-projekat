package controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    protected final Log logger = LogFactory.getLog(HomeController.class);
    
    @RequestMapping({"", "/", "/home"})
    public String index(Model model) {
        
        model.addAttribute("paramt", "Dobrodosli u EviMedik");
        return "index";
    }
}
