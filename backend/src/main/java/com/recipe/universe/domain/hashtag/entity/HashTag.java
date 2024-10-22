package com.recipe.universe.domain.hashtag.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class HashTag {
    @Id
    @Column(name = "hash_tag_id")
    @GeneratedValue
    private Long id;

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
}
