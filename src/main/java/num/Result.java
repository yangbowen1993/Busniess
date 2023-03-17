package num;


import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import path.InPaths;
import path.OutPaths;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class Result {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> caipian = ReadCaipian.read();
        Map<String, Integer> xiaoshou = ReadXiaoshou.read();
        ArrayList<OBJ> objs = new ArrayList<>();
        for (Map.Entry<String, Integer> s : caipian.entrySet()) {
            OBJ obj = new OBJ(s.getKey(), s.getValue(), 0);
            objs.add(obj);
            if (xiaoshou.containsKey(s.getKey())) {
                obj.xiaoshou = xiaoshou.get(s.getKey());
            }
        }

        objs.sort(new Comparator<OBJ>() {
            @Override
            public int compare(OBJ o1, OBJ o2) {
                return o1.huohao.compareTo(o2.huohao);
            }
        });

        write(objs);
    }

    private static void write(ArrayList<OBJ> dataList) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("库存分析");
        int row = 0;
        HSSFRow head = sheet.createRow(row);
        row++;
        head.createCell(0).setCellValue("款号");
        head.createCell(1).setCellValue("裁片");
        head.createCell(2).setCellValue("销售");
        head.createCell(3).setCellValue("余量");
        int caipian = 0;
        int xiaoshou = 0;
        for (int i = 0; i < dataList.size(); i++) {
            HSSFRow rowData = sheet.createRow(row);
            row++;
            rowData.createCell(0).setCellValue(dataList.get(i).huohao);
            rowData.createCell(1).setCellValue(dataList.get(i).caipian);
            rowData.createCell(2).setCellValue(dataList.get(i).xiaoshou);
            rowData.createCell(3).setCellValue(dataList.get(i).caipian - dataList.get(i).xiaoshou);
            caipian += dataList.get(i).caipian;
            xiaoshou += dataList.get(i).xiaoshou;
        }

        HSSFRow rowData = sheet.createRow(row);
        row++;
        rowData.createCell(0).setCellValue("总计");
        rowData.createCell(1).setCellValue(caipian);
        rowData.createCell(2).setCellValue(xiaoshou);
        rowData.createCell(3).setCellValue(caipian - xiaoshou);
//6.通过输出流写到文件里去
        FileOutputStream fos = new FileOutputStream(OutPaths.getAllColorKucun());
        workbook.write(fos);
        fos.close();
    }
}
