package num;


import org.apache.poi.ss.usermodel.*;
import path.InPaths;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadCaipian {
    //第三行开始有 有效数据
    public static int START_ROW = 3;
    //要读取的列
    public static int[] LINES = {5, 6};

    public static Map<String, Integer> read() throws IOException {
        File excel = new File(InPaths.getCaipianColorPath());
        Workbook workbook = WorkbookFactory.create(excel);
        Sheet sheet = workbook.getSheetAt(0);
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = START_ROW; i <= sheet.getLastRowNum(); i++) {
            Row sheetRow = sheet.getRow(i);

            Cell cell1 = sheetRow.getCell(LINES[0]);
            if (cell1 == null) {
                break;
            }
            /**裁片货号 一般是TBCA-001   BBCA-001   RBCA-001
             * 其中T 代表曹婷、B代表曹彪、R代表曹蓉
             * BC代表年，每年加1
             * A代表季度 每季度加1
             *
             * */
            String huohao = sheetRow.getCell(LINES[0]).toString().split("-")[1];
            int shuliang = Integer.parseInt(sheetRow.getCell(LINES[1]).toString());
            if (!filter(huohao)) {
                map.put(huohao, shuliang);
                System.out.println(huohao + "  " + shuliang);
            }


        }
        return map;
    }

    /**
     * 过滤不需要的货号
     */
    public static boolean filter(String huohao) {
        return false;
    }
}
