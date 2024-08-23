package pl.mikolajp.forum.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.mikolajp.forum.model.dto.UserDto;
import pl.mikolajp.forum.model.entity.User;
import pl.mikolajp.forum.service.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showRegisterPage(Model model) {

        model.addAttribute("userDto", new UserDto());

        return "user-management/register";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("userDto") UserDto userDto,
            BindingResult bindingResult,
            HttpSession session, Model model) {

        String username = userDto.getUsername();

        if (bindingResult.hasErrors()){
            return "user-management/register";
        }

        User existing = userService.findByUsername(username);
        if (existing != null){
            model.addAttribute("userDto", new UserDto());
            model.addAttribute("registrationError", "Username already exists.");

            return "user-management/register";
        }

        userService.save(userDto);

        session.setAttribute("user", userDto);

        return "home";
    }
}
