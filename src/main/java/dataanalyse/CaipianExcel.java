package dataanalyse;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CaipianExcel {
    //第三行开始有 有效数据
    public static int START_ROW = 3;
    //要读取的列
    public static int[] LINES = {5, 6};

    public static void main(String[] args) {
        try {
            readExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<KuanhaoAndShuliang> readExcel() throws IOException {

        File excel = new File(Path.CAI_PIAN);
        Workbook workbook = WorkbookFactory.create(excel);
        Sheet sheet = workbook.getSheetAt(0);
        ArrayList<KuanhaoAndShuliang> kuanhaoAndShuliangs = new ArrayList<>();
        for (int i = START_ROW; i <= sheet.getLastRowNum(); i++) {
            Row sheetRow = sheet.getRow(i);

            Cell cell1 = sheetRow.getCell(LINES[0]);
            if (cell1 == null) {
                break;
            }
            String huohao = sheetRow.getCell(LINES[0]).toString().substring(8);
            int shuliang = Integer.valueOf(sheetRow.getCell(LINES[1]).toString());
            kuanhaoAndShuliangs.add(new KuanhaoAndShuliang(huohao, shuliang));
            System.out.println(huohao + "  " + shuliang);
        }
        return kuanhaoAndShuliangs;
    }
}
