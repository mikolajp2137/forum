package pl.mikolajp.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {
    @GetMapping("/login")
    public String login()
    {
        return "login";
    }
}
