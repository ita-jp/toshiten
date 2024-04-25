package com.example.toshiten.repository.storage;

import com.example.toshiten.config.S3Bucket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class FileRepository {

    private final S3Client s3Client;

    public void save(S3Bucket bucket, InputStream inputStream, long contentLength, String key) {
        // TODO retry
        s3Client.putObject(
                builder -> builder.bucket(bucket.getBucketName()).key(key).build(),
                RequestBody.fromInputStream(inputStream, contentLength)
        );
    }
}
