package dataanalyse;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XiaoshouExcel {

    //第三行开始有 有效数据
    public static int START_ROW = 1;
    //要读取的列
    public static int[] LINES = {1, 6};

    public static void main(String[] args) {
        try {
            readExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<KuanhaoAndShuliang> readExcel() throws IOException {

        File excel = new File(Path.XIAO_SHOU);
        Workbook workbook = WorkbookFactory.create(excel);
        Sheet sheet = workbook.getSheetAt(0);
        ArrayList<KuanhaoAndShuliang> kuanhaoAndShuliangs = new ArrayList<>();
        for (int i = START_ROW; i <= sheet.getLastRowNum(); i++) {
            Row sheetRow = sheet.getRow(i);

            Cell cell1 = sheetRow.getCell(LINES[0]);
            String huohao = cell1.toString();

            int shuliang = Double.valueOf(sheetRow.getCell(LINES[1]).toString()).intValue();
            kuanhaoAndShuliangs.add(new KuanhaoAndShuliang(huohao, shuliang));
            System.out.println(huohao + "  " + shuliang);

        }
        return kuanhaoAndShuliangs;
    }

}
