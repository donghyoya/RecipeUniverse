package com.recipe.universe.domain.ingredient.entity;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.ingredient.dto.CreateIngUnitDto;
import com.recipe.universe.domain.unit.entity.Unit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;


@Entity
@Getter
@Setter
@NoArgsConstructor
@SQLRestriction("del_flag = false")
public class IngUnit extends BaseEntity {

    @Id
    @Column(name = "ingUnitId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "standardUnit")
    private SUnit sUnit;

    @Column(name = "standUnitAmt")
    private Long sAmt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingId", nullable = false)
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Unit unit;

    public IngUnit (SUnit sunit){
        this.sUnit = sunit;
    }

    public IngUnit (CreateIngUnitDto dto){
        this.sUnit = dto.getSUnit();
    }

    /**
     * 외래키 설정
     * @param ingredient
     * @param unit
     */
    public void setForeignKey(Ingredient ingredient, Unit unit){
        this.ingredient = ingredient;
        this.unit = unit;
    }
}
