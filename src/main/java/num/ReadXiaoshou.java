package num;


import org.apache.poi.ss.usermodel.*;
import path.InPaths;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadXiaoshou {


    //第三行开始有 有效数据
    public static int START_ROW = 1;
    //要读取的列
    public static int[] LINES = {1, 6};

    public static Map<String,Integer> read() throws IOException {
        File excel = new File(InPaths.getXiaoshouColorPath());
        Workbook workbook = WorkbookFactory.create(excel);
        Sheet sheet = workbook.getSheetAt(0);

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = START_ROW; i <= sheet.getLastRowNum(); i++) {
            Row sheetRow = sheet.getRow(i);

            Cell cell1 = sheetRow.getCell(LINES[0]);
            String huohao = cell1.toString();
            int shuliang = Double.valueOf(sheetRow.getCell(LINES[1]).toString()).intValue();
            map.put(huohao, shuliang);
            System.out.println(huohao + "  " + shuliang);

        }
        return map;
    }
}
