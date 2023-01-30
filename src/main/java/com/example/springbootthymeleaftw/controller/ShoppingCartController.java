package com.example.springbootthymeleaftw.controller;

import com.example.springbootthymeleaftw.model.entity.UserEntity;
import com.example.springbootthymeleaftw.model.entity.UserProductEntity;
import com.example.springbootthymeleaftw.service.UserProductService;
import com.example.springbootthymeleaftw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ShoppingCartController")
public class ShoppingCartController {

    private final UserService userService;
    private static UserEntity loggedUser;
    private final UserProductService userProductService;
    @GetMapping("/OpenShoppingCart")
    public String open(@ModelAttribute("email") String email, Model model)
    {
        loggedUser=userService.getByEmail(email).get();
        double total_cart=0;
        List<UserProductEntity> userProductEntityList = userProductService.getAllByUser(loggedUser.getId());
        for (UserProductEntity upe: userProductEntityList) {
            upe.setTotalValoare(upe.getProduct().getPrice() * upe.getQuantity());
            total_cart+=upe.getTotalValoare();
        }
        model.addAttribute("userProductEntityList",userProductEntityList);
        model.addAttribute("total_cart",total_cart);
        model.addAttribute("disabled",true);
        return "ShoppingCart";
    }

    private UserProductEntity split (String key) {
        String[] subStrings = key.split("--");
        UserProductEntity upe = userProductService.getByUserAndProduct(Long.valueOf(subStrings[0]),Long.valueOf(subStrings[1]));
        return upe;
    }

    @PostMapping("/updateShoppingCart")
    public String update(@RequestParam("userProductEntityList") List<String> selectedOptions , final RedirectAttributes redirectAttributes )
    {
        if(selectedOptions.size()!=0)
        {
            for(String option: selectedOptions){
                System.out.println(option);
                userProductService.delete(split(option));
            }
        }
        redirectAttributes.addFlashAttribute("email",loggedUser.getEmail());
        return "redirect:/ShoppingCartController/OpenShoppingCart";
    }
}
