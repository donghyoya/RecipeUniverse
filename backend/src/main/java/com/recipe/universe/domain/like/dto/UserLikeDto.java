package com.recipe.universe.domain.like.dto;

import lombok.Getter;

@Getter
public class UserLikeDto {
    private boolean isLike;
    private int counts;

    public UserLikeDto(boolean isLike, int counts) {
        this.isLike = isLike;
        this.counts = counts;
    }
}
