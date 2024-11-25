package com.recipe.universe.domain.unit.controller;

import com.recipe.universe.domain.ingredient.entity.SUnit;
import com.recipe.universe.domain.unit.dto.CreateUnitDto;
import com.recipe.universe.domain.unit.service.UnitService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitDefaltUnit {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlStatus;

    private final InitUnitService initUnitService;

    @PostConstruct
    public void init(){
        if(ddlStatus.equals("create")){
            log.info("======Insert Default UnitData======");
            initUnitService.init();
        }
    }

    @Component
    static class InitUnitService{

        @Autowired
        private UnitService unitService;

        @Transactional
        public void init(){
            CreateUnitDto unitGram = new CreateUnitDto();
            unitGram.setName(SUnit.g.name());

            CreateUnitDto unitKg = new CreateUnitDto();
            unitKg.setName(SUnit.kg.name());

            CreateUnitDto unitMl = new CreateUnitDto();
            unitMl.setName(SUnit.mL.name());

            CreateUnitDto unitL = new CreateUnitDto();
            unitL.setName(SUnit.L.name());

            unitService.save(unitGram);
            unitService.save(unitKg);
            unitService.save(unitMl);
            unitService.save(unitL);
        }
    }
}
