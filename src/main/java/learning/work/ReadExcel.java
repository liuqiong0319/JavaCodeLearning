package java.learning.work;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * Created by qiong.liu on 2017/11/28.
 */
public class ReadExcel {

    ArrayList<ArrayList<String>> getCellValuesH(String filePath, String sheetName, int tr1, int tr2, int td1, int td2){
        ArrayList<ArrayList<String>> cellValues = new ArrayList<ArrayList<String>>();
        try{
            File file = new File(filePath);
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(filePath));
            XSSFSheet sheet = wb.getSheet(sheetName);
            int trLength = sheet.getLastRowNum();
            Row row = sheet.getRow(0);
            int tdLength = row.getLastCellNum();
            Cell cellValue;
            if(sheet != null){
                for(int i=tr1; i<=tr2; i++){
                    ArrayList<String> rowCellValue = new ArrayList<String>();
                    Row row1 = sheet.getRow(i);
                    for(int j=td1; j<=td2; j++){
                        cellValue = row1.getCell(j);
                        rowCellValue.add(cellValue.getStringCellValue().trim());
                    }
                    cellValues.add(rowCellValue);
                }
            }
            return cellValues;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
