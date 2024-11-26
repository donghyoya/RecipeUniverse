package com.recipe.universe.domain.attach.service;

import com.recipe.universe.domain.attach.entity.AttachFiles;
import com.recipe.universe.domain.attach.entity.EntityType;
import com.recipe.universe.domain.attach.repository.AttachRepository;
import com.recipe.universe.global.file.access.FileSystemAccessObject;
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
    private final FileSystemAccessObject fileAO;

    @Transactional
    public String saveFile(MultipartFile file){
        AttachFiles files = AttachFiles.builder()
                .file(file)
                .build();
        fileAO.save(files.getStorePath(), file);
        return files.getStorePath();
    }

    /**
     * 테이블정보 및 저장하는 데이터
     */
    @Transactional
    public String saveImageWithEntityId(MultipartFile file, Long entityId, EntityType entityType){
        AttachFiles files = AttachFiles.builder()
                .file(file)
                .entityId(entityId)
                .entityType(entityType)
                .build();

        fileAO.save(files.getOriginalFileName(), file);
        return files.getStorePath();
    }

    /**
     * 파일 ID로 파일 조회
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
            fileAO.delete(file.getStorePath());
            attachRepository.deleteById(id);
        }
    }

}
