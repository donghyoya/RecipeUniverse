package com.recipe.universe.domain.ingredient.entity;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.ingredient.dto.CreateIngredientDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@NoArgsConstructor
@SQLRestriction("del_flag = false")
public class Ingredient extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingId;

    @Column(name = "ingredientName")
    private String ingName;

    @Column(name = "category")
    private String category;

    @Column(name = "unit")
    private String unit;

    @Builder
    public Ingredient(String ingName, String category, String unit){
        this.ingName = ingName;
        this.category = category;
        this.unit = unit;
    }

    public Ingredient(CreateIngredientDto dto){
        this.ingName = dto.getIngredientName();
        this.category = dto.getCategory();
        this.unit = dto.getUnit();
    }
}
