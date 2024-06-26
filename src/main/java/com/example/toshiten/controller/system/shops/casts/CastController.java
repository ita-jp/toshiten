package com.example.toshiten.controller.system.shops.casts;

import com.example.toshiten.service.cast.CastService;
import com.example.toshiten.service.shop.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sys/shops/{shopId}/casts")
@RequiredArgsConstructor
public class CastController {

    private final CastService castService;
    private final ShopService shopService;

    @GetMapping
    public String list(@PathVariable("shopId") long shopId, Model model) {
        shopService.findById(shopId)
                .ifPresentOrElse(
                        shop -> model.addAttribute("shop", shop),
                        () -> {
                            throw new RuntimeException("Shop not found"); // TODO 404 にしたい
                        }
                );
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
                        cast -> {
                            model.addAttribute("castForm", CastForm.from(cast));
                            model.addAttribute("mode", "VIEW");
                        },
                        () -> {
                            throw new RuntimeException("Cast not found"); // TODO 404 にしたい
                        }
                );
        return "sys/shops/casts/form";
    }

    @GetMapping("/new")
    public String showCreationForm(
            @PathVariable("shopId") long shopId,
            @ModelAttribute CastForm form,
            Model model
    ) {
        model.addAttribute("shopId", shopId);
        model.addAttribute("mode", "CREATE");
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

    @GetMapping("/{castId}/edit")
    public String showEditForm(
            @PathVariable("shopId") long shopId,
            @PathVariable("castId") long castId,
            Model model
    ) {
        castService.findByCastId(shopId, castId)
                .ifPresentOrElse(
                        cast -> {
                            model.addAttribute("castForm", CastForm.from(cast));
                            model.addAttribute("mode", "EDIT");
                        },
                        () -> {
                            throw new RuntimeException("Cast not found"); // TODO 404 にしたい
                        }
                );
        return "sys/shops/casts/form";
    }
    
    @PutMapping("/{castId}")
    public String update(
            @PathVariable("shopId") long shopId,
            @PathVariable("castId") long castId,
            @Validated CastForm form,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "sys/shops/casts/form";
        }
        castService.update(form.toEntity(shopId, castId));
        return "redirect:/sys/shops/{shopId}/casts";
    }

}
