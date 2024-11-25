package com.recipe.universe.domain.ingredient.service;

import com.recipe.universe.domain.ingredient.dto.CreateIngredientDto;
import com.recipe.universe.domain.ingredient.dto.ReadIngredientDto;
import com.recipe.universe.domain.ingredient.entity.IngUnit;
import com.recipe.universe.domain.ingredient.entity.Ingredient;
import com.recipe.universe.domain.ingredient.entity.SUnit;
import com.recipe.universe.domain.ingredient.repository.IngUnitRepository;
import com.recipe.universe.domain.ingredient.repository.IngredientRepository;
import com.recipe.universe.domain.nutrition.dto.CreateNutritionDto;
import com.recipe.universe.domain.nutrition.entity.Nutrition;
import com.recipe.universe.domain.nutrition.repository.NutritionRepository;
import com.recipe.universe.domain.unit.dto.CreateUnitDto;
import com.recipe.universe.domain.unit.dto.ReadUnitDto;
import com.recipe.universe.domain.unit.dto.UnitDto;
import com.recipe.universe.domain.unit.entity.Unit;
import com.recipe.universe.domain.unit.repository.UnitRepository;
import com.recipe.universe.domain.unit.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngUnitRepository ingUnitRepository;
    private final NutritionRepository nutritionRepository;
    private final UnitRepository unitRepository;

    /**
     * 재료 저장
     * @param dto
     * @return
     */
    @Transactional
    public Long save(CreateIngredientDto dto){
        Ingredient ingredient = new Ingredient(dto);

        Long ingId = ingredientRepository.save(ingredient).getIngId();
        return ingId;
    }

    /**
     * 재료, 영양정보, 단위 동시 저장
     * 위처럼 둔 이유는 사용자가 직접 추가할수잇는 기능도 구현하기 위함이다
     * -> 없으면 새로만든다 개념이 아닌 사용자가 확인후 없으면 추가한다는 로직이다
     * @param ingredientDto
     * @param nutritionDto
     * @return
     */
    @Transactional
    public Long saveWithNU(CreateIngredientDto ingredientDto, CreateNutritionDto nutritionDto, UnitDto unitDto){


        //재료 입력후
        Ingredient ingredient = new Ingredient(ingredientDto);
        Ingredient saveIngredient = ingredientRepository.save(ingredient);

        //재료 영양정보 저장
        Nutrition nutrition = new Nutrition(nutritionDto);
        nutrition.setIngredient(saveIngredient);

        if(unitDto instanceof CreateUnitDto createUnitDto){
            Unit unit = new Unit(createUnitDto);

            // 이렇게 해야 트랜잭션이 걸림
            Unit saveUnit = unitRepository.save(unit);

            List<IngUnit> ingUnits = saveIngredient.getIngUnits();

            //연관간계 생성
            ingUnits.forEach(data -> {
                data.setForeignKey(saveIngredient,saveUnit);
                ingUnitRepository.save(data);
            });
        }
        else if (unitDto instanceof ReadUnitDto readUnitDto) {
            Unit saveUnit = unitRepository.findByName(SUnit.g.name()).orElseThrow(
                    () -> new IllegalArgumentException("no Unit")
            );//처음 데이터 넣을때만 설정
//            unitRepository.findByName(readUnitDto.getName());

            List<IngUnit> ingUnits = saveIngredient.getIngUnits();

            ingUnits.forEach(data ->{
                data.setForeignKey(saveIngredient, saveUnit);
                ingUnitRepository.save(data);
            });

        }else{
            throw new IllegalArgumentException("Unknown Dto Type");
        }

        nutritionRepository.save(nutrition);

        return saveIngredient.getIngId();
    }

    /**
     * 재료, 영양정보 동시 저장
     * @param ingredientDto
     * @param nutritionDto
     * @return
     */
    @Transactional
    public Long saveWithNutrition(CreateIngredientDto ingredientDto, CreateNutritionDto nutritionDto){


        //재료 입력후
        Ingredient ingredient = new Ingredient(ingredientDto);
        Ingredient saveIngredient = ingredientRepository.save(ingredient);

        //재료 영양정보 저장
        Nutrition nutrition = new Nutrition(nutritionDto);
        nutrition.setIngredient(saveIngredient);

        nutritionRepository.save(nutrition);

        return saveIngredient.getIngId();
    }


    /*READ*/
    /**
     * 제료 이름 검색 함수
     * @param ingName 제료이름
     * @return dto
     */
    public ReadIngredientDto findByIngName(String ingName){
        Ingredient ingredient = ingredientRepository.findByIngName(ingName).orElseThrow();
        ReadIngredientDto dto = new ReadIngredientDto(ingredient);
        return dto;
    }

    public List<ReadIngredientDto> findByLikeIngName(String ingName){
        return ingredientRepository.findByIngNameContaining(ingName).stream().map(ReadIngredientDto::new).toList();
    }

}
