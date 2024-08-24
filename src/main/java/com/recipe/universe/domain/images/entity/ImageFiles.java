package com.recipe.universe.domain.images.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class ImageFiles {
    @Id @GeneratedValue
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String extension;

    public ImageFiles(String originalFileName) {
        this.originalFileName = originalFileName;
        this.extension = this.getExtention(originalFileName);
    }

    /**
     * 파일확장자 추출함수
     * @param filename 파일명(확장자 포함)
     * @return 파일확장자
     */
    private String getExtention(String filename){
        int doxIdx = filename.lastIndexOf('.');
        return filename.substring(doxIdx+1);
    }

    /**
     *
     * @return S3에서 저장된 파일의 경로
     */
    public String getStorePath() {
        return id + "." + extension;
    }
}
