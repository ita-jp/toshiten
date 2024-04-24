package com.example.toshiten.service.shop;

import com.example.toshiten.repository.shop.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public List<ShopEntity> listShop() {
        return shopRepository.select();
    }

    public Optional<ShopEntity> findById(long id) {
        return shopRepository.selectById(id);
    }
}
