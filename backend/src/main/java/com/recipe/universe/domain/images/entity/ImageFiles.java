package com.recipe.universe.domain.images.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
public class ImageFiles {
    @Id @GeneratedValue
    private Long id;

    @Column
    private String storePath;

    @Column
    private String originalFileName;

    @Column
    private String extension;

    public ImageFiles(String originalFileName) {
        this.originalFileName = originalFileName;
        this.extension = this.getExtension(originalFileName);
        storePath = UUID.randomUUID().toString() + "." + extension;
    }

    /**
     * 파일확장자 추출함수
     * @param filename 파일명(확장자 포함)
     * @return 파일확장자
     */
    private String getExtension(String filename){
        if(this.extension == null){
            int dotIdx = filename.lastIndexOf('.');
            return filename.substring(dotIdx+1);
        }else {
            return this.extension;
        }
    }
}
