package com.recipe.universe.domain.attach.service;

import com.recipe.universe.domain.attach.entity.AttachFiles;
import com.recipe.universe.domain.attach.entity.EntityType;
import com.recipe.universe.domain.attach.repository.AttachRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AttachService {

    private final AttachRepository attachRepository;
    private final S3Client s3Client;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucketName;

    /**
     * 테이블정보 및 저장하는 데이터
     * @param file
     * @param entityId
     * @param entityType
     */
    @Transactional
    public Long saveImageById(MultipartFile file, Long entityId, EntityType entityType) throws IOException {

        AttachFiles files = new AttachFiles();
        files.settingMultipartFile(file);
        files.setEntityId(entityId);
        files.setEntityType(entityType);

        Long attachFileId = attachRepository.save(files).getAfid();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .contentType(file.getContentType())
                .contentLength(file.getSize())
                .key(attachFileId + "." + files.getFileType())
                .build();

        RequestBody requestBody = RequestBody.fromBytes(file.getBytes());
        s3Client.putObject(putObjectRequest, requestBody);

        return attachFileId;
    }
}
