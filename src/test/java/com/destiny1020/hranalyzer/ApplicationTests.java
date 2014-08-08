package com.destiny1020.hranalyzer;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.junit.Test;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
//@WebAppConfiguration
public class ApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testReadingXLS() throws IOException, BiffException {
        Workbook book = Workbook.getWorkbook(new File("data/201406.xls"));

        System.out.println(book.getNumberOfSheets());

        Sheet listSheet = book.getSheet(1);
        System.out.println(listSheet.getName());

        System.out.println(listSheet.getRows());

        for (int i = 0; i < listSheet.getRows(); i++) {
            Cell[] row = listSheet.getRow(i);
            for (int j = 0; j < row.length; j++) {
                if (j < row.length - 1) {
                    System.out.print((j + 1) + ": " + row[j].getContents()
                            + " --- ");
                } else {
                    System.out.print((j + 1) + ": " + row[j].getContents());
                }
            }
            System.out.println();
            //            }
        }

    }
}
