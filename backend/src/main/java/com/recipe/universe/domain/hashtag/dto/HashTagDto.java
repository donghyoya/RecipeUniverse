package com.recipe.universe.domain.hashtag.dto;

import com.recipe.universe.domain.hashtag.entity.HashTag;
import lombok.Getter;

@Getter
public class HashTagDto {
    private Long id;
    private String name;

    public HashTagDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static HashTagDto from(HashTag entity){
        return new HashTagDto(entity.getId(), entity.getTagname());
    }
}
