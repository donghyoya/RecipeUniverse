package com.recipe.universe.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    // 삭제여부
    @Column(name = "delFlag")
    private boolean delFlag = false;

    //새성날짜
    @Column(name = "regDate", nullable = false)
    private LocalDateTime regDate;

    @PrePersist
    protected void perPersist() {
        this.regDate = LocalDateTime.now();
    }

}
