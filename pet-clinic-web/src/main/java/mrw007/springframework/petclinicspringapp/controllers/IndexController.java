package mrw007.springframework.petclinicspringapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"/", "", "index.html"})
    public String index() {
        return "index";
    }
}
