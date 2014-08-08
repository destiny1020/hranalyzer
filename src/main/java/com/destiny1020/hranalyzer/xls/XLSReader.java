package com.destiny1020.hranalyzer.xls;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class XLSReader {

    private Workbook book;
    private Sheet currentSheet;

    public XLSReader(String path) throws BiffException, IOException {
        this.book = Workbook.getWorkbook(new File(path));

        // defaults to the 2nd sheet
        this.currentSheet = book.getSheet(1);
    }

    public void setCurrentSheet(int idx) {
        this.currentSheet = book.getSheet(idx);
    }

    public HRElement getRow(int idx) {
        return new HRElement(currentSheet.getRow(idx));
    }

    public int getTotalRows() {
        return currentSheet.getRows();
    }
}
