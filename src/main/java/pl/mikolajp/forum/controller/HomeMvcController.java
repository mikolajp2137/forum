package pl.mikolajp.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeMvcController {

    @GetMapping({"/","/home","/index"})
    public String showHome(){
        return "home";
    }
}
