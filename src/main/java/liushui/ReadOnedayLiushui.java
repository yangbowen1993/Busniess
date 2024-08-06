package liushui;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import path.InPaths;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadOnedayLiushui {

    /**
     * 货号在第2列
     */
    public static int ART_NUM_INDEX = 1;
    /**颜色在第5列*/
    public static int COLOR_INDEX = 4;
    /**码数在第6列*/
    public static int SIZE_INDEX = 5;
    /**名字在第11列*/
    public static int NAME_INDEX = 10;
    /**数量在第12列*/
    public static int NUM_INDEX = 11;
    /**价格在13列*/
    public static int PRICE_INDEX = 12;
    /**
     * 单据号在第20位
     */
    public static int VOUCHER_NUM_INDEX = 22;

/**时间是在第34列*/
    public static int TIME_INDEX = 34;


    public static Map<String, Order> readExcel() throws IOException {
        File excel = new File(InPaths.getOnedayXiaoshouLiushui());
        ArrayList<OldExcelObj> list = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(excel);
        Sheet sheet = workbook.getSheetAt(0);
        System.out.println("行数： " + (sheet.getLastRowNum() + 1));
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            OldExcelObj oldExcelObj = new OldExcelObj();
            oldExcelObj.voucherNum = row.getCell(VOUCHER_NUM_INDEX).toString();
            oldExcelObj.artNum = row.getCell(ART_NUM_INDEX).toString();
            oldExcelObj.num = Double.valueOf(row.getCell(NUM_INDEX).toString()).intValue(); //Integer.getInteger(row.getCell(NUM_INDEX).toString());
            oldExcelObj.price = Double.valueOf(row.getCell(PRICE_INDEX).toString()).intValue();//Integer.getInteger( row.getCell(PRICE_INDEX).toString());
            oldExcelObj.color = row.getCell(COLOR_INDEX).toString();
            oldExcelObj.size = row.getCell(SIZE_INDEX).toString();
            oldExcelObj.name = row.getCell(NAME_INDEX).toString();
            oldExcelObj.time = row.getCell(TIME_INDEX).getLocalDateTimeCellValue().toString().replace("T", " ");
            list.add(oldExcelObj);


        }
        Map<String, Order> map = new HashMap<>();
        for (OldExcelObj obj : list) {
            if (!map.containsKey(obj.voucherNum)) {
                Order order = new Order(obj);
                map.put(obj.voucherNum, order);
            } else {
                map.get(obj.voucherNum).addOldExcelObj(obj);
            }
            System.out.println(obj.toString());
        }
        return map;
        // WriteExcelUtil.writeExcel(map, false);


    }
}
