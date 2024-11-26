package com.recipe.universe.domain.attach.controller;

import com.recipe.universe.domain.attach.dto.AttachFileMetadataDto;
import com.recipe.universe.domain.attach.entity.EntityType;
import com.recipe.universe.domain.attach.service.AttachService;
import com.recipe.universe.domain.images.dto.ResourceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class AttachFileController {

    private final AttachService attachService;

    /**
     * 파일 업로드
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AttachFileMetadataDto uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "entityId", required = false) Long entityId,
            @RequestParam(value = "entityType", required = false) EntityType entityType) {
        return attachService.saveFile(file, entityId, entityType);
    }

    /**
     * 파일 다운로드
     */
    @GetMapping("/{filepath}")
    public ResponseEntity<Resource> downloadFile(@PathVariable(name = "filepath") String filepath) {
        ResourceDto resource = attachService.loadFileByFilePath(filepath);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, resource.getHeader())
                .body(resource.getResource());
    }

    @PostMapping("/{filepath}/delete")
    public ResponseEntity<Void> deleteFile(@PathVariable(name = "filepath") String filepath){
        attachService.deleteByFilepath(filepath);
        return ResponseEntity.ok().build();
    }

}
