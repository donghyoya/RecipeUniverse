package com.recipe.universe.domain.attach.entity;

import com.recipe.universe.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@NoArgsConstructor
@SQLRestriction("del_flag = false")
public class AttachFiles extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long afid;  //attach file id

    @Column(nullable = false)
    private String entityType;

    @Column(nullable = false)
    private Long entityId;
    private String chgFileName;
    private String orgFileName;
    private FileEnum fileType;
    private Integer fileSize;   //mb
    private String filePath;

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
        return afid + "." + fileType;
    }
}
