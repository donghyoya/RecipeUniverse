package com.recipe.universe.domain.images.dto;

import com.recipe.universe.domain.images.entity.ImageFiles;
import lombok.Data;
import lombok.Getter;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

@Data
public class ImageFileDto {
    private String fileStorePath;
    private String originalFileName;

    public ImageFileDto(ImageFiles imageFiles){
        this.fileStorePath = imageFiles.getStorePath();
        this.originalFileName = imageFiles.getOriginalFileName();
    }
}
