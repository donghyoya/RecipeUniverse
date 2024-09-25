package com.recipe.universe.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    // 삭제여부
    @Column(name = "delFlag")
    private boolean delFlag;

    //새성날짜
    @Column(name = "regDate")
    private LocalDateTime regDate;
}
