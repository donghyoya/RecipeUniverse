package com.recipe.universe.domain.unit.service;

import com.recipe.universe.domain.unit.dto.CreateUnitDto;
import com.recipe.universe.domain.unit.entity.Unit;
import com.recipe.universe.domain.unit.repository.UnitRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository unitRepository;

    @Transactional
    public Long save(CreateUnitDto dto){
        Unit unit = new Unit(dto);
        return unitRepository.save(unit).getId();
    }

}
