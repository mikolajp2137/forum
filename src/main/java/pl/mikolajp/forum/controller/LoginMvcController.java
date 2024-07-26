package pl.mikolajp.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginMvcController {
    @GetMapping("/login")
    public String showLoginPage(){
        return "user-management/login";
    }

    @GetMapping("/access-denied")
    public String showAccessDeniedPage(){
        return "access-denied";
    }
}
