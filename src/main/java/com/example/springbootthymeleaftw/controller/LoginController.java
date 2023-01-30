package com.example.springbootthymeleaftw.controller;

import com.example.springbootthymeleaftw.model.entity.UserEntity;
import com.example.springbootthymeleaftw.service.SecurityService;
import com.example.springbootthymeleaftw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final SecurityService securityService;

    private final UserService userService;
    @GetMapping()
    public String open(Model model, String error, String logout){
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @PostMapping("/postlogin")
    public String login(String username,Model model, String error, String logout, final RedirectAttributes redirectAttributes ){
        securityService.setAuthToken(username);
        redirectAttributes.addFlashAttribute("email",username);
        return "redirect:/ShoppingCartController/OpenShoppingCart";
    }

    @GetMapping("/error")
    public String error(Model model, String error, String logout){
        return "login";
    }

}
