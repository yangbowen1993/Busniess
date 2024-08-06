package allcaipian;

import org.apache.poi.ss.usermodel.*;
import path.InPaths;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * 裁片按颜色分的 excel读取
 */
public class CaipianColorExcel {

    //第四行开始有 有效数据
    public static int START_ROW = 3;
    //要读取的列
    //厂名、款号、颜色、件数
    public static int[] LINES = {4, 9, 11, 13};

    public static void main(String[] args) {
        try {
            readExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<CaipianColorInfo> readExcel() throws IOException {

        //读取分颜色的裁片Excel
        File excel = new File(InPaths.getCaipianColorPath());
        Workbook workbook = WorkbookFactory.create(excel);
        Sheet sheet = workbook.getSheetAt(0);
        ArrayList<CaipianColorInfo> colorInfos = new ArrayList<>();
        for (int i = START_ROW; i <= sheet.getLastRowNum(); i++) {
            Row sheetRow = sheet.getRow(i);

            Cell cell1 = sheetRow.getCell(LINES[0]);
            //厂名
            String factoryName = cell1.toString();
            if (factoryName.equals("")) {
                break;
            }

            Cell cell2 = sheetRow.getCell(LINES[1]);
            String oldhuohao = cell2.toString();
            if (filter(oldhuohao)) {
                continue;
            }
            //货号
            String huohao = oldhuohao.substring(5);

            Cell cell3 = sheetRow.getCell(LINES[2]);
            //颜色
            String color = cell3.toString();


            Cell cell4 = sheetRow.getCell(LINES[3]);
            //数量
            String num = cell4.toString();

            int shuliang = Double.valueOf(num).intValue();
            colorInfos.add(new CaipianColorInfo(factoryName, huohao, color, shuliang));
            System.out.println(huohao + "  " + shuliang);

        }
        return colorInfos;
    }

    /**
     * 对想要输出的货号进行选择
     */
    private static boolean filter(String huohao) {
        return false;
    }

}
