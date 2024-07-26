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

import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private Logger logger = Logger.getLogger(getClass().getName());

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
    public String showMyLoginPage(Model model) {

        model.addAttribute("userDto", new UserDto());

        return "user-management/register";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("userDto") UserDto userDto,
            BindingResult theBindingResult,
            HttpSession session, Model model) {

        String username = userDto.getUsername();

        // form validation
        if (theBindingResult.hasErrors()){
            return "user-management/register";
        }

        // check the database if user already exists
        User existing = userService.findByUserName(username);
        if (existing != null){
            model.addAttribute("userDto", new UserDto());
            model.addAttribute("registrationError", "Username already exists.");

            logger.warning("User name already exists.");
            return "user-management/register";
        }

        // create user account and store in the databse
        userService.save(userDto);

        logger.info("Successfully created user: " + username);

        // place user in the web http session for later use
        session.setAttribute("user", userDto);

        return "home";
    }
}
