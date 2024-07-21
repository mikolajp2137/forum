package pl.mikolajp.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForumMvcController {

    @GetMapping({"/forum"})
    public String showHome(){
        return "forum";
    }
}
