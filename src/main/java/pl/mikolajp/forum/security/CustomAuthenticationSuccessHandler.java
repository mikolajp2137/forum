package pl.mikolajp.forum.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import pl.mikolajp.forum.model.entity.User;
import pl.mikolajp.forum.service.UserService;

import java.io.IOException;

@Controller
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private UserService userService;
    public CustomAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse
            response, Authentication authentication)
            throws IOException, ServletException {
        String username = authentication.getName();
        User user = userService.findByUserName(username);
// now place in the session
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
// forward to home page
        response.sendRedirect(request.getContextPath() + "/");
    }
}

