package com.recipe.universe.domain.hashtag.entity;

import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class RecipeHashTag {
    @Id
    @GeneratedValue
    @Column(name = "recipe_hash_tag")
    private Long id;


    /* HashTag */
    public void addHashTag(HashTag hashTag){
        hashTag.addHashTag(this);
        this.hashTag = hashTag;
    }

    @Column(name = "hash_tag_id", insertable = false, updatable = false)
    private Long hashTagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hash_tag_id")
    private HashTag hashTag;

    /* 관계 */
    @Column(name = "recipe_id", insertable = false, updatable = false)
    private Long recipeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    /* 생성 */

    public RecipeHashTag(HashTag hashTag, Recipe recipe) {
        this.hashTag = hashTag;
        this.recipe = recipe;
    }

    public RecipeHashTag(){}
}
