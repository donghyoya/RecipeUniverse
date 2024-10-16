package com.recipe.universe.domain.unit.controller;


import com.recipe.universe.domain.unit.dto.CreateUnitDto;
import com.recipe.universe.domain.unit.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/unit")
public class UnitController {

    private final UnitService unitService;

    @PostMapping(value = "/save")
    public ResponseEntity<String> saveUnit(@RequestBody CreateUnitDto dto){
        Long result = null;
        try{
            result = unitService.save(dto);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(result != null){
            return new ResponseEntity<>("success", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("faile",HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
