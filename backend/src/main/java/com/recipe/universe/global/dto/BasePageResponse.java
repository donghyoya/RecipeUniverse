package com.recipe.universe.global.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class BasePageResponse<T>{
    private List<T> content;
    private Integer size;
    private Integer pageSize;
    private Integer page;


    public BasePageResponse(Page<T> data) {
        this.content = data.getContent();
        this.size = data.getNumberOfElements();
        this.page = data.getNumber();
        this.pageSize = data.getTotalPages();
    }

    public static <T> BasePageResponse<T> of(Page<T> data){
        return new BasePageResponse<>(data);
    }
}
