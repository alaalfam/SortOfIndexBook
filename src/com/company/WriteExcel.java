package com.company;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import java.io.File;
import java.io.IOException;

public class WriteExcel {
    private String EXCEL_FILE_LOCATION ;
    private WritableWorkbook writableWorkbook = null;
    private WritableSheet writableSheet ;
    WriteExcel(){

    }
    WriteExcel(String fileLocation){
        this.EXCEL_FILE_LOCATION = fileLocation;
    }
    public void write(String [][] arrSheet) {

        try {
            writableWorkbook = Workbook.createWorkbook(new File(EXCEL_FILE_LOCATION));
            WritableSheet writableSheet = writableWorkbook.createSheet("sheet1",0);
            for (int i = 0; i < arrSheet.length; i++) {
                Label labelName = new Label(0,i,arrSheet[i][0]);
                writableSheet.addCell(labelName);
                Label labelJournalName = new Label(1,i,arrSheet[i][1]);
                writableSheet.addCell(labelJournalName);
                Label labelPage = new Label(2,i,arrSheet[i][2]);
                writableSheet.addCell(labelPage);
            }

            writableWorkbook.write();
        }catch (IOException | WriteException e){
            e.printStackTrace();
        } finally {
            if (writableWorkbook != null){
                try {
                    writableWorkbook.close();
                }catch (IOException | WriteException e){
                    e.printStackTrace();
                }
            }
        }

    }
}
