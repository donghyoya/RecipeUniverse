package com.recipe.universe.global.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class BaseListResponse<T>{
    private List<T> content;
    private Integer size;

    public BaseListResponse(List<T> data) {
        this.content = data;
        this.size = data.size();
    }

    public static <T> BaseListResponse<T> of(List<T> data){
        return new BaseListResponse<>(data);
    }

}
