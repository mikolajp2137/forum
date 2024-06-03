package pl.mikolajp.forum.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pl.mikolajp.forum.user.UserDto;
import pl.mikolajp.forum.user.UserService;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());

        return "signup";
    }

    @PostMapping("/process_register")
    public String processRegister(UserDto user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userService.addUser(user);

        return "home";
    }
}
