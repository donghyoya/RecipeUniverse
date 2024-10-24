package com.recipe.universe.domain.hashtag.dto;

import com.recipe.universe.domain.hashtag.entity.HashTag;
import lombok.Getter;

@Getter
public class HashTagDto {
    private String tagname;

    public HashTagDto(String tagname) {
        this.tagname = tagname;
    }

    public static HashTagDto from(HashTag entity){
        return new HashTagDto( entity.getTagname());
    }
}
