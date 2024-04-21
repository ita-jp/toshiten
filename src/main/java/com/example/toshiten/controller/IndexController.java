package com.example.toshiten.controller;

import com.example.toshiten.service.shop.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {

    private final ShopService shopService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("shopList", shopService.listShop());
        return "index";
    }
}
