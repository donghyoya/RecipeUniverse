package com.recipe.universe.domain.ingredient.entity;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.ingredient.dto.CreateUnitDto;
import com.recipe.universe.domain.unit.entity.Unit;
import jakarta.persistence.*;
import lombok.Data;
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

    public IngUnit (CreateUnitDto dto){
        this.sUnit = dto.getSUnit();
    }
}
