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
@RequestMapping("/file")
@RequiredArgsConstructor
public class AttachFileController {

    private final AttachService attachService;

    /**
     * 파일 업로드
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("entityId") Long entityId,
            @RequestParam("entityType") EntityType entityType) {
        return null;
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
        return null;
    }

}
