package com.zlht.pbr.algorithm.wcmp.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author zijian Wang
 */
public class ExamTableValidator {

    public static boolean validateExamTable(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            Row firstRow = sheet.getRow(0);
            int  num =6;
            // 检查第一行的标题是否符合规范
            if (firstRow.getLastCellNum() != num ||
                    !"题目".equals(getStringCellValue(firstRow.getCell(0))) ||
                    !"A".equals(getStringCellValue(firstRow.getCell(1))) ||
                    !"B".equals(getStringCellValue(firstRow.getCell(2))) ||
                    !"C".equals(getStringCellValue(firstRow.getCell(3))) ||
                    !"D".equals(getStringCellValue(firstRow.getCell(4))) ||
                    !"答案".equals(getStringCellValue(firstRow.getCell(5)))) {
                return false;
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                // 检查行的列数是否正确
                if (row.getLastCellNum() != 6) {
                    return false;
                }

                // 检查题目、选项、答案是否为空，并且答案是否在A~D之间
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null || cell.getCellType() != CellType.STRING ||
                            cell.getStringCellValue().isEmpty() ||
                            cell.getStringCellValue().length() > 300) {
                        return false;
                    }

                    // 检查答案是否在A~D之间
                    if (j == 5 && !isAnswerValid(cell.getStringCellValue())) {
                        return false;
                    }
                }
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String getStringCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((int) cell.getNumericCellValue());
        } else {
            return "";
        }
    }

    private static boolean isAnswerValid(String answer) {
        return answer.matches("[A-D]");
    }

}