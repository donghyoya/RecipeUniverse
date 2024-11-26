package com.recipe.universe.domain.attach.entity;

import com.recipe.universe.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@NoArgsConstructor
@SQLRestriction("del_flag = false")
@Entity
public class AttachFiles extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attach_file_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EntityType entityType;

    @Column(nullable = false)
    private Long entityId;

    private String storePath;
    private String originalFileName;
    private String fileType;
    private Long fileSize;   //mb

    /* 생성 */

    public AttachFiles(EntityType entityType, Long entityId, String storePath, String originalFileName, String fileType, Long fileSize) {
        this.entityType = entityType;
        this.entityId = entityId;
        this.storePath = storePath;
        this.originalFileName = originalFileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private EntityType entityType;
        private Long entityId;
        private String storePath;
        private String originalFileName;
        private String fileType;
        private Long fileSize;


        /* 빌드 */
        public AttachFiles build(){
            return new AttachFiles(
                entityType, entityId, storePath, originalFileName, fileType, fileSize
            );
        }

        /* Util */

        /**
         * 파일확장자 추출함수
         */
        private String extractExtension(String filename){
            int dotIdx = filename.lastIndexOf('.');
            return filename.substring(dotIdx+1);
        }

        private String createStorePath(String fileType){
            return UUID.randomUUID() + "." + fileType;
        }

        /* Builder setter */

        public Builder file(MultipartFile file){
            this.originalFileName = file.getOriginalFilename();
            this.fileType = extractExtension(this.originalFileName);
            this.storePath = createStorePath(this.fileType);
            this.fileSize = file.getSize();
            return this;
        }

        public Builder entityType(EntityType entityType) {
            this.entityType = entityType;
            return this;
        }

        public Builder entityId(Long entityId) {
            this.entityId = entityId;
            return this;
        }

        public Builder storePath(String storePath) {
            this.storePath = storePath;
            return this;
        }

        public Builder originalFileName(String originalFileName) {
            this.originalFileName = originalFileName;
            return this;
        }

        public Builder fileType(String fileType) {
            this.fileType = fileType;
            return this;
        }

        public Builder fileSize(Long fileSize) {
            this.fileSize = fileSize;
            return this;
        }
    }
}
