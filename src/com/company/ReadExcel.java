package com.company;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ReadExcel {
    private String inputFile;
    private File inputWorkBook;
    private Workbook workbook;
    private String [][] arrSheet ;
    ReadExcel(){

    }
    ReadExcel(String inputFile){
        this.inputFile = inputFile;

    }
    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;

    }
    public void read() throws IOException {
        inputWorkBook = new File(inputFile);
        try {
            // get the workbook.
            workbook = Workbook.getWorkbook(inputWorkBook);
            // get the first sheet.
            Sheet sheet = workbook.getSheet(0);
            ArrayList<Person> persons = new ArrayList<>();
            arrSheet = new String[sheet.getRows()][3];
            for (int i = 0; i < arrSheet.length; i++) {
                for (int j = 0; j < 3; j++) {
                    arrSheet[i][j] = "";
                }
            }
            for (int i = 0; i < sheet.getRows(); i++) {
                Cell cell0 = sheet.getCell(0,i);
                Cell cell1 = sheet.getCell(1,i);
                Cell cell2 = sheet.getCell(2,i);
                CellType type0 = cell0.getType();
                CellType type1 = cell1.getType();
                CellType type2 = cell2.getType();
                arrSheet[i][0] = cell0.getContents();
                arrSheet[i][1] = cell1.getContents();
                arrSheet[i][2] = cell2.getContents();
            }
        }catch (BiffException e){
            e.printStackTrace();
        }
    }
    public String[][] getArrSheet(){
        return arrSheet;
    }
}
