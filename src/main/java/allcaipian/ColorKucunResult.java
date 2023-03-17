package allcaipian;

import allxiaoshou.XiaoshouAnalyse;
import allxiaoshou.XiaoshouColorInfo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import path.OutPaths;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 总裁片减去总销售，计算颜色
 */
public class ColorKucunResult {
    public static void main(String[] args) throws IOException {
        //读裁片
        List<CaipianColorInfo> dataList = CaipianAnalyse.getCaipianList();
        //读销售
        List<XiaoshouColorInfo> xiaoshouList = XiaoshouAnalyse.getXiaoshouList();


        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("裁片颜色");
        int row = 0;
        HSSFRow head = sheet.createRow(row);
        row++;
        head.createCell(0).setCellValue("款号");
        head.createCell(1).setCellValue("颜色");
        head.createCell(2).setCellValue("裁片数量");
        head.createCell(3).setCellValue("销售数量");
        head.createCell(4).setCellValue("剩余数量");


        String tempkuanhao = "";

        //读取颜色颜色映射关系
        HashMap<String, String> map = ColorMap.readColorExcelGetMap();
        System.out.print("测试  "+map.toString());
        int tempCaipian = 0;
        int tempXiaoshou = 0;
        for (int i = 0; i < dataList.size(); i++) {
            CaipianColorInfo caipianColorInfo = dataList.get(i);


            tempkuanhao = caipianColorInfo.kuanhao;
            HSSFRow rowData = sheet.createRow(row);
            row++;

            rowData.createCell(0).setCellValue(caipianColorInfo.kuanhao);
            rowData.createCell(1).setCellValue(caipianColorInfo.color);
            rowData.createCell(2).setCellValue(caipianColorInfo.num);
            tempCaipian += caipianColorInfo.num;

            for (int j = 0; j < xiaoshouList.size(); j++) {
                XiaoshouColorInfo xiaoshouColorInfo = xiaoshouList.get(j);
                String key = caipianColorInfo.kuanhao + "-" + caipianColorInfo.color;
                String value = map.get(key);
                if (value != null && value.equals(xiaoshouColorInfo.kuanhao + "-" + xiaoshouColorInfo.color)) {
                    rowData.createCell(3).setCellValue(xiaoshouColorInfo.num);
                    tempXiaoshou += xiaoshouColorInfo.num;
                    rowData.createCell(4).setCellValue(caipianColorInfo.num - xiaoshouColorInfo.num);
                    break;
                }
            }


            if (i + 1 >= dataList.size() || !dataList.get(i + 1).kuanhao.equals(tempkuanhao)) {
                HSSFRow rowData2 = sheet.createRow(row);
                rowData2.createCell(1).setCellValue("总数");
                rowData2.createCell(2).setCellValue(tempCaipian);
                rowData2.createCell(3).setCellValue(tempXiaoshou);
                rowData2.createCell(4).setCellValue(tempCaipian - tempXiaoshou);

                row++;

                tempCaipian = 0;
                tempXiaoshou = 0;
                HSSFRow rowData3 = sheet.createRow(row);
                row++;
            }


        }
//6.通过输出流写到文件里去
        FileOutputStream fos = new FileOutputStream(OutPaths.getAllColorKucun());
        workbook.write(fos);
        fos.close();
    }
}
