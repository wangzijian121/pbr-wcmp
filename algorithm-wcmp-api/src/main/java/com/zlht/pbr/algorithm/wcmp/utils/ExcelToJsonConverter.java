package com.zlht.pbr.algorithm.wcmp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zlht.pbr.algorithm.wcmp.model.Question;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 解析试题为json
 *
 * @author zijian Wang
 */
public class ExcelToJsonConverter {
    public static String getQuestionJson(String excelFilePath) {

        List<Question> questions = new ArrayList<>();
        String json = "";
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                Cell questionCell = row.getCell(0);
                Cell optionACell = row.getCell(1);
                Cell optionBCell = row.getCell(2);
                Cell optionCCell = row.getCell(3);
                Cell optionDCell = row.getCell(4);
                Cell answerCell = row.getCell(5);

                String question = questionCell.getStringCellValue();
                String optionA = optionACell.getStringCellValue();
                String optionB = optionBCell.getStringCellValue();
                String optionC = optionCCell.getStringCellValue();
                String optionD = optionDCell.getStringCellValue();
                String answer = answerCell.getStringCellValue();

                Question q = new Question(question, optionA, optionB, optionC, optionD, answer);
                questions.add(q);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            json = objectMapper.writeValueAsString(questions);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}

