package com.example.toshiten.service.cast;

import com.example.toshiten.config.S3Bucket;
import com.example.toshiten.repository.cast.CastMapper;
import com.example.toshiten.repository.storage.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CastService {

    private final CastMapper castMapper;
    private final FileRepository fileRepository;

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

    @Transactional
    public void saveImage(String fileName, InputStream image, long size, long shopId, long castId) {
        var fileExtension = fileName.substring(fileName.lastIndexOf("."));
        var generatedFilename = UUID.randomUUID().toString().replace("-", "").substring(0, 5);
        var objectKey = String.format("shop-%s/cast-%s/%s%s", shopId, castId, generatedFilename, fileExtension);
        fileRepository.save(S3Bucket.CAST_IMAGE, image, size, objectKey);

        castMapper.insertImage(castId, objectKey, 0); // TODO displayOrder
    }
}
