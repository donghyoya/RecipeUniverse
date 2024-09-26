package com.recipe.universe.domain.ingredient.entity;

import com.recipe.universe.domain.dish.dish.entity.Dish;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class DishIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Dish dish;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingId")
    private Ingredient ingredient;

    @Column(name = "dishQuantity")
    private Integer dishQuantity;
}
