package com.recipe.universe.domain.unit.entity;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.ingredient.entity.IngUnit;
import com.recipe.universe.domain.unit.dto.CreateUnitDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SQLRestriction("del_flag = false")
public class Unit extends BaseEntity {

    @Id
    @Column(name = "unitId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unitName", unique = true)
    private String name;

    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL)
    private List<IngUnit> ingUnits = new ArrayList<>();

    public Unit (CreateUnitDto dto){
        this.name = dto.getName();
    }
}
