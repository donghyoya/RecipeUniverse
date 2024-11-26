package com.recipe.universe.global.file.access;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class S3AccessObject implements FileSystemAccessObject{
    private final S3Client s3Client;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucketName;



    @Override
    public void save(String path, MultipartFile file) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .contentType(file.getContentType())
                    .contentLength(file.getSize())
                    .key(path)
                    .build();
            RequestBody requestBody = RequestBody.fromBytes(file.getBytes());
            s3Client.putObject(putObjectRequest, requestBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Resource load(String path) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(path)
                .build();
        ResponseInputStream<GetObjectResponse> object = s3Client.getObject(getObjectRequest);
        Resource ret = new InputStreamResource(object);
        return ret;
    }

    @Override
    public void delete(String path) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(path)
                .build();
        s3Client.deleteObject(deleteObjectRequest);
    }
}
