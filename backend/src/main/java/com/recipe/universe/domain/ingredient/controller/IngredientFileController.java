package com.recipe.universe.domain.ingredient.controller;

import com.recipe.universe.domain.ingredient.dto.CreateIngredientDto;
import com.recipe.universe.domain.ingredient.dto.CreateIngUnitDto;
import com.recipe.universe.domain.ingredient.entity.SUnit;
import com.recipe.universe.domain.ingredient.service.IngredientService;
import com.recipe.universe.domain.nutrition.dto.CreateNutritionDto;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ing/file")
@CrossOrigin(origins = "*")
public class IngredientFileController {

    private final IngredientService ingredientService;

    @PostMapping(value = "/read/excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<String[]> excelData = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);  // 첫 번째 시트만 처리

            for (Row row : sheet) {
                List<String> rowData = new ArrayList<>();
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            rowData.add(String.valueOf(cell.getNumericCellValue()));
                            break;
                        default:
                            rowData.add("");  // 다른 데이터 타입은 공백으로 처리
                    }
                }
                excelData.add(rowData.toArray(new String[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        for (int y = 1; y < excelData.size(); y++) {
            System.out.println("Row " + y + ":");
            String[] row = excelData.get(y);

            // 재료
            CreateIngredientDto ingredientDto = new CreateIngredientDto();
            ingredientDto.setIngredientName(row[1]);
            ingredientDto.setCategory(row[0]);
            ingredientDto.setUnit(new CreateIngUnitDto(null, SUnit.g));

            CreateNutritionDto nutritionDto = new CreateNutritionDto();
            nutritionDto.setCalories(safeParseDouble(row[3]));  //칼로리
            nutritionDto.setMoisture(safeParseDouble(row[4]));  //수분
            nutritionDto.setProtein(safeParseDouble(row[5]));   //단백질
            nutritionDto.setFat(safeParseDouble(row[6]));       //지방
            nutritionDto.setCarbs(safeParseDouble(row[8]));     //탄수화물
            nutritionDto.setSugar(safeParseDouble(row[9]));     //당류
            nutritionDto.setSodium(safeParseDouble(row[10]));   //나트륩
            nutritionDto.setCalcium(safeParseDouble(row[11]));  //칼슘
            nutritionDto.setPotassium(safeParseDouble(row[12]));//칼륨
            nutritionDto.setNAmount(100.0);     //기준양(단위는 재료쪽)

            ingredientService.saveWithNutrition(ingredientDto, nutritionDto);

            System.out.println("ingredientDto = " + ingredientDto+ " | nutritionDto = " + nutritionDto);
        }



        // 읽은 데이터를 JSON 형식으로 반환
        return new ResponseEntity<>("Success Read Excel", HttpStatus.OK);

    }

    @PostMapping("/create/excel")
    public ResponseEntity<String> uploadCSVFile(@RequestParam("file")MultipartFile file){
        if (file.isEmpty()) {
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Sample Data");

            // Sample 데이터 추가
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("Name");
            row.createCell(1).setCellValue("Age");

            row = sheet.createRow(1);
            row.createCell(0).setCellValue("John Doe");
            row.createCell(1).setCellValue(30);

            // 파일을 ByteArray로 변환
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);

            // HTTP 응답으로 파일 전송
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "sample.xlsx");

            return new ResponseEntity<>("success", HttpStatus.OK);

        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }

    }



    private Double safeParseDouble(String value) {
        try {
            return Double.valueOf(value);
        } catch (Exception e) {
            return -0.1;  // 예외 발생 시 기본값
        }
    }
}
