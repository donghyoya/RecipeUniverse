package com.recipe.universe.domain.hashtag.entity;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class RecipeHashTag extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "recipe_hash_tag")
    private Long id;


    /* HashTag */
    public void addHashTag(HashTag hashTag){
        hashTag.addHashTag(this);
        this.hashTag = hashTag;
    }

    @Column(name = "tagname", insertable = false, updatable = false)
    private String tagname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tagname")
    private HashTag hashTag;

    /* 관계 */
    @Column(name = "recipe_id", insertable = false, updatable = false)
    private Long recipeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    /* 생성 */

    public RecipeHashTag(HashTag hashTag, Recipe recipe) {
        addHashTag(hashTag);
        this.recipe = recipe;
    }

    protected RecipeHashTag(){}
}
