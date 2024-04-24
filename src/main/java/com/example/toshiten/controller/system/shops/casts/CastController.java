package com.example.toshiten.controller.system.shops.casts;

import com.example.toshiten.service.cast.CastService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/new")
    public String showCreationForm(@PathVariable("shopId") long shopId, Model model) {
        model.addAttribute("shopId", shopId);
        return "sys/shops/casts/form";
    }

    @PostMapping
    public String create(
            @PathVariable("shopId") long shopId,
            @Validated CastForm form,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "sys/shops/casts/form";
        }
        castService.create(form.toEntity(shopId));
        return "redirect:/sys/shops/{shopId}/casts";
    }

}