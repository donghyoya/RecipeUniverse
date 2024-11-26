package com.recipe.universe.domain.attach.dto;

import com.recipe.universe.domain.attach.entity.AttachFiles;
import com.recipe.universe.domain.attach.entity.EntityType;
import lombok.Getter;

@Getter
public class AttachFileMetadataDto {
    private EntityType entityType;
    private Long entityId;
    private String storePath;
    private String originalFileName;
    private String fileType;
    private Long fileSize;

    public AttachFileMetadataDto(EntityType entityType, Long entityId, String storePath, String originalFileName, String fileType, Long fileSize) {
        this.entityType = entityType;
        this.entityId = entityId;
        this.storePath = storePath;
        this.originalFileName = originalFileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }

    public static AttachFileMetadataDto from(AttachFiles file){
        return new AttachFileMetadataDto(
                file.getEntityType(),
                file.getEntityId(),
                file.getStorePath(),
                file.getOriginalFileName(),
                file.getFileType(),
                file.getFileSize()
        );
    }
}
