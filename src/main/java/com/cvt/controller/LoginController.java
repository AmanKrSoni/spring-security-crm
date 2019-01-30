package com.cvt.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {

   @GetMapping("/login")
    public String login(){

       Authentication auth = SecurityContextHolder.getContext().getAuthentication();

       if (!(auth instanceof AnonymousAuthenticationToken))
       {
           return "redirect:/home";
       }

       return "fancy-login";
   }

   @GetMapping("/home")
   public String home(){
        return "home";
   }

   @GetMapping("/access-denied")
    public String accessDenied(){
        return "access-denied";
   }
}
