package allxiaoshou;

import org.apache.poi.ss.usermodel.*;
import path.InPaths;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XiaoshouColorExcel {

    //第四行开始有 有效数据
    public static int START_ROW = 1;
    //要读取的列
    //厂名、款号、颜色、件数
    public static int[] LINES = {1, 3, 10};

    public static void main(String[] args) {
        try {
            readExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<XiaoshouColorInfo> readExcel() throws IOException {

        File excel = new File(InPaths.getXiaoshouColorPath());
        Workbook workbook = WorkbookFactory.create(excel);
        Sheet sheet = workbook.getSheetAt(0);
        ArrayList<XiaoshouColorInfo> colorInfos = new ArrayList<>();
        for (int i = START_ROW; i <= sheet.getLastRowNum(); i++) {
            Row sheetRow = sheet.getRow(i);

            Cell cell1 = sheetRow.getCell(LINES[0]);
            String oldhuohao = cell1.toString();
            if (filter(oldhuohao)) {
                continue;
            }


            String huohao = oldhuohao.split("-")[1];


            Cell cell2 = sheetRow.getCell(LINES[1]);
            String color = cell2.toString();


            Cell cell3 = sheetRow.getCell(LINES[2]);
            //数量
            String num = cell3.toString();
            //Double.valueOf(sheetRow.getCell(LINES[1]).toString()).intValue();

            int shuliang = Double.valueOf(num).intValue();
            colorInfos.add(new XiaoshouColorInfo(huohao, color, shuliang));
            System.out.println(huohao + "  " + shuliang);

        }
        return colorInfos;
    }

    private static boolean filter(String oldhuohao) {
        if (!oldhuohao.contains("-")) {
            return true;
        }
        //在这里可以过滤一些秦丝货号规则
        return false;
    }

}
