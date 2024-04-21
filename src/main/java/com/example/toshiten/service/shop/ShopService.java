package com.example.toshiten.service.shop;

import com.example.toshiten.repository.shop.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public List<ShopEntity> listShop() {
        return shopRepository.select();
    }
}
