package utilities;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtils {
    public static String[][] readExcel(String filename,String sheetName) throws Exception{
        XSSFWorkbook book=new XSSFWorkbook(new FileInputStream(filename));
        XSSFSheet sheet=book.getSheet(sheetName);
        int rows=sheet.getLastRowNum();
        int cols=sheet.getRow(0).getLastCellNum();
        String[][] data=new String[rows+1][cols];
        for(int i=0;i<=rows;i++){
            XSSFRow row=sheet.getRow(i);
            for(int j=0;j<cols;j++){
                data[i][j]=row.getCell(j).toString();
            }
        }
        return data;
    }

    public static String[] readExcelByRow(String rownum) throws Exception{
        XSSFWorkbook book=new XSSFWorkbook(new FileInputStream("C:\\Users\\2408719\\IdeaProjects\\CarWashServices\\src\\test\\resources\\excel.xlsx"));
        XSSFSheet sheet=book.getSheet("Sheet1");
        XSSFRow row=sheet.getRow(Integer.parseInt(rownum));
        int cols=row.getLastCellNum();
        String[] data=new String[cols];
        for(int i=0;i<cols;i++){
            data[i]=row.getCell(i).toString();
        }
        return data;
    }

    public static void writeIntoExcel(List<String> data, String path, String sheetName) throws IOException {
        try(FileInputStream stream = new FileInputStream(path);
            XSSFWorkbook book = new XSSFWorkbook(stream);){
            XSSFSheet sheet=book.getSheet(sheetName);
            if(sheet==null){
                sheet=book.createSheet(sheetName);
            }
            int lastRow= sheet.getLastRowNum()+3;
            for(int i=0;i<data.size();i++){
                XSSFRow row=sheet.createRow(lastRow+i+1);
                row.createCell(0).setCellValue(data.get(i));
            }
            stream.close();
            try(FileOutputStream ostream = new FileOutputStream(path);){
                book.write(ostream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

