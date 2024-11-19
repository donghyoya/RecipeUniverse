package com.recipe.universe.domain.images.controller;

import com.recipe.universe.domain.images.dto.ImageFileDto;
import com.recipe.universe.domain.images.dto.ResourceDto;
import com.recipe.universe.domain.images.service.ImageFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/image")
@RequiredArgsConstructor
@RestController
public class ImageController {
    private final ImageFileService imageFileService;

    @GetMapping("/{filename}")
    public ResponseEntity<Resource> loadImage(@PathVariable("filename") String filename){
        ResourceDto resource = imageFileService.loadFileByFilename(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, resource.getHeader())
                .body(resource.getResource());
    }

    @PostMapping("/upload")
    public ImageFileDto upload(@RequestParam("file")MultipartFile file){
        ImageFileDto imageFileDto = imageFileService.saveFile(file);
        return imageFileDto;
    }
}
