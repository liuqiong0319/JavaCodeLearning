package learning.self.ioDemo.fileDemo;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.*;

/**
 * Created by qiong.liu on 2018/5/18.
 */
public class ReadExcelMethod {

    public static void main(String[] args) throws IOException , BiffException{
        File srcfile =new File("/Users/qiong.liu/Downloads/实时查询数据.xls");
        File desfile =new File("/Users/qiong.liu/Downloads/runSql.txt");
        readExcel(srcfile,desfile);
    }

    private static void readExcel(File srcFile,File desfile) throws IOException, BiffException {
        FileWriter fw=new FileWriter(desfile);

        Workbook wb= Workbook.getWorkbook(srcFile);
        Sheet s=wb.getSheet(0);
        for(int i=1;i<s.getRows();i++){
            if(s.getCell(16,i).getContents().equals("adhoc")) {
                String cellinfo = s.getCell(3, i).getContents();
                fw.write(cellinfo);
                fw.write("\n\n\n");
                fw.flush();
            }
        }

        fw.close();
    }
}
