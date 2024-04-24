package com.example.toshiten.controller.system;

import com.example.toshiten.service.shop.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("shopList", shopService.listShop());
        return "sys/shops/list";
    }
}
