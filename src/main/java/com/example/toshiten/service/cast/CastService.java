package com.example.toshiten.service.cast;

import com.example.toshiten.controller.system.shops.casts.CastForm;
import com.example.toshiten.repository.cast.CastMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CastService {

    private final CastMapper castMapper;

    public List<CastEntity> findAllByShopId(long shopId) {
        return castMapper.selectByShopId(shopId);
    }

    public Optional<CastEntity> findByCastId(long shopId, long castId) {
        return castMapper.selectByCastId(shopId, castId);
    }

    public void create(CastEntity entity) {
        castMapper.insert(entity);
    }

    public void update(CastEntity entity) {
        castMapper.update(entity);
    }
}
