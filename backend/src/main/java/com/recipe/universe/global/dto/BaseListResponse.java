package com.recipe.universe.global.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class BaseListResponse<T>{
    private List<T> data;
    private Integer count;

    public BaseListResponse(List<T> data) {
        this.data = data;
        this.count = data.size();
    }
}
