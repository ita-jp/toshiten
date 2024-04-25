package com.example.toshiten.config;

public enum S3Bucket {

    CAST_IMAGE("cast-image");

    private final String bucketName;

    S3Bucket(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
