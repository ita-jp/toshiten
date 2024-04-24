package com.example.toshiten.controller.system.shops.casts;

import com.example.toshiten.service.cast.CastService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys/shops/{shopId}/casts")
@RequiredArgsConstructor
public class CastController {

    private final CastService castService;

    @GetMapping
    public String list(@PathVariable("shopId") long shopId, Model model) {
        model.addAttribute("castList", castService.findAllByShopId(shopId));
        return "sys/shops/casts/list";
    }

    @GetMapping("/{castId}")
    public String detail(
            @PathVariable("shopId") long shopId,
            @PathVariable("castId") long castId,
            Model model
    ) {
        // TODO ログインユーザーの shopId とチェックしないとだめぽ
        castService.findByCastId(shopId, castId)
                .ifPresentOrElse(
                        cast -> model.addAttribute("cast", cast),
                        () -> {
                            throw new RuntimeException("Cast not found"); // TODO 404 にしたい
                        }
                );
        return "sys/shops/casts/detail";
    }
}
