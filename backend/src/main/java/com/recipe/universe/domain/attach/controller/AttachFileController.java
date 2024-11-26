package com.recipe.universe.domain.attach.controller;

import com.recipe.universe.domain.attach.entity.AttachFiles;
import com.recipe.universe.domain.attach.entity.EntityType;
import com.recipe.universe.domain.attach.service.AttachService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

@RestController
@RequestMapping("/attach")
@RequiredArgsConstructor
public class AttachFileController {

    private final AttachService attachService;
    private final S3Client s3Client;

    /**
     * 파일 업로드
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("entityId") Long entityId,
            @RequestParam("entityType") EntityType entityType) {
        try {
            Long fileId = attachService.saveImageById(file, entityId, entityType);
            return ResponseEntity.ok(fileId);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 파일 다운로드
     */
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        Optional<AttachFiles> optionalFile = attachService.findById(id);
        if (optionalFile.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        AttachFiles file = optionalFile.get();
        String key = file.getStorePath();

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(attachService.getBucketName())
                .key(key)
                .build();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        s3Client.getObject(getObjectRequest, (Path) outputStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getOrgFileName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(outputStream.toByteArray());
    }

}
