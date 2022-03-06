package com.Spring.ExamCheatingDetection.controller;

import com.Spring.ExamCheatingDetection.config.UserPrincipal;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {

//    @GetMapping("/login")
//    String login() {
//        return "login";
//    }

    @GetMapping("/login")
    public String showLoginForm() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }

        return "redirect:/";
    }



    @RequestMapping("/")
    public String mainPage()
    {
        return "index";
    }

    @RequestMapping("/success")
    public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {

        String role =  authResult.getAuthorities().toString();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal user = (UserPrincipal)auth.getPrincipal();
        int userId = user.getId();
        if(role.contains("ROLE_ADMIN")){
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/admin/index"));
        }
        else if(role.contains("ROLE_INSTRUCTOR")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/instructor/index/"));
        }
        else
        {

            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/student/index/"));
        }
    }

}