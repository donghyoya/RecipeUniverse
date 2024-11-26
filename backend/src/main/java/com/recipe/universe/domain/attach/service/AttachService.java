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
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.Optional;

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

    /**
     * 파일 ID로 파일 조회
     * @param id 파일 ID
     * @return 파일 정보 (Optional)
     */
    public Optional<AttachFiles> findById(Long id) {
        return attachRepository.findById(id);
    }

    /**
     * 파일 삭제 (S3와 데이터베이스 모두에서 삭제)
     * @param id 파일 ID
     */
    @Transactional
    public void deleteFileById(Long id) {
        Optional<AttachFiles> optionalFile = attachRepository.findById(id);
        if (optionalFile.isPresent()) {
            AttachFiles file = optionalFile.get();
            // S3에서 파일 삭제
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(file.getStorePath())
                    .build();
            s3Client.deleteObject(deleteObjectRequest);

            // 데이터베이스에서 파일 정보 삭제
            attachRepository.deleteById(id);
        }
    }

    /**
     * S3 버킷 이름 반환
     * @return 버킷 이름
     */
    public String getBucketName() {
        return bucketName;
    }
}
