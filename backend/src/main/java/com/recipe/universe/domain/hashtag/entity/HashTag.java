package com.recipe.universe.domain.hashtag.entity;

import com.recipe.universe.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class HashTag extends BaseEntity {
    @Id
    @Column
    private String tagname;

    @OneToMany(mappedBy = "hashTag", cascade = CascadeType.ALL,orphanRemoval = true)
    List<RecipeHashTag> recipeHashTags = new ArrayList<>();

    public void addHashTag(RecipeHashTag recipeHashTag){
        this.recipeHashTags.add(recipeHashTag);
    }

    /* 생성 */

    public HashTag(String tagname) {
        this.tagname = tagname;
    }

    protected HashTag(){}
}
