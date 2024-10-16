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
@Setter
@NoArgsConstructor
@SQLRestriction("del_flag = false")
@Entity
public class AttachFiles extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long afid;  //attach file id

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EntityType entityType;

    @Column(nullable = false)
    private Long entityId;
    private String chgFileName;
    private String orgFileName;
    private String fileType;
    private Long fileSize;   //mb
    private String filePath;

    /**
     * 파일확장자 추출함수
     * @param filename 파일명(확장자 포함)
     * @return 파일확장자
     */
    public String getExtention(String filename){
        int doxIdx = filename.lastIndexOf('.');
        return filename.substring(doxIdx+1);
    }

    /**
     * settting = update || insert
     * @param file
     * @throws RuntimeException
     */
    public void settingMultipartFile(MultipartFile file) throws RuntimeException{
        String orgFileName = file.getOriginalFilename();
        String fileType = orgFileName.substring(orgFileName.lastIndexOf(".") + 1);
        String chgFileName = UUID.randomUUID().toString() + "_" + orgFileName;

        this.chgFileName = chgFileName;
        this.orgFileName = orgFileName;
        this.fileSize = file.getSize();
        this.fileType = fileType;
    }

    /**
     *
     * @return S3에서 저장된 파일의 경로
     */
    public String getStorePath() {
        return afid + "." + fileType;
    }
}
