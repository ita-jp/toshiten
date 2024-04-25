package com.example.toshiten.controller.system.shops.casts.image;

import com.example.toshiten.service.cast.CastService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/sys/shops/{shopId}/casts/{castId}/images")
@RequiredArgsConstructor
public class ImageController {

    private final CastService castService;

    @GetMapping("/new")
    public String showCreationForm(
            @PathVariable("shopId") long shopId,
            @PathVariable("castId") long castId
    ) {
        return "sys/shops/casts/images/form";
    }

    @PostMapping
    public String create(
            @PathVariable("shopId") long shopId,
            @PathVariable("castId") long castId,
            @RequestParam MultipartFile image
    ) throws IOException {
        castService.saveImage(image.getOriginalFilename(), image.getInputStream(), image.getSize(), shopId, castId);
        return "redirect:/sys/shops/{shopId}/casts/{castId}";
    }
}
