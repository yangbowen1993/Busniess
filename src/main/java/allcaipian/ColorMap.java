package allcaipian;

import org.apache.poi.ss.usermodel.*;
import path.InPaths;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * 管家婆与秦丝颜色映射
 */
public class ColorMap {

    //第二行开始有 有效数据
    public static int START_ROW = 1;
    //要读取的列
    //款号、管家婆颜色、秦丝颜色
    public static int[] LINES = {0, 1, 2};

    public static HashMap<String, String> readColorExcelGetMap() throws IOException {
        File excel = new File(InPaths.getColorMapInfo());
        Workbook workbook = WorkbookFactory.create(excel);
        Sheet sheet = workbook.getSheetAt(0);
        String tempKuanhao = null;
        HashMap<String, String> map = new HashMap<>();
        for (int i = START_ROW; i <= sheet.getLastRowNum(); i++) {
            Row sheetRow = sheet.getRow(i);
            Cell cell1 = sheetRow.getCell(LINES[0]);
            if (cell1 != null) {
                //款号
                String kuanhao = cell1.toString();
                if (kuanhao != null && !kuanhao.equals("")) {
                    tempKuanhao = kuanhao;
                }
            }

            Cell cell2 = sheetRow.getCell(LINES[1]);
            //款号
            String guanjiapoColor = cell2.toString();

            Cell cell3 = sheetRow.getCell(LINES[2]);
            //款号
            String qinsiColor = cell3.toString();

            map.put(tempKuanhao + "-" + guanjiapoColor, tempKuanhao + "-" + qinsiColor);
        }
        return map;

    }
}
