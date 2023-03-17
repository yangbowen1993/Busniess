package dataanalyse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Analyse {
    public static void main(String[] args) throws IOException {
        List<KuanhaoAndShuliang> caipianList = CaipianExcel.readExcel();
        List<KuanhaoAndShuliang> diaoboList = DiaoboExcel.readExcel();
        List<KuanhaoAndShuliang> xiaoshouList = XiaoshouExcel.readExcel();
        HashMap<String, AnalyseData> keys = new HashMap<>();
        for (KuanhaoAndShuliang i : caipianList) {
            AnalyseData analyseData = new AnalyseData();
            analyseData.huohao = i.kuanhao;
            analyseData.caipian = i.shuliang;
            keys.put(i.kuanhao, analyseData);
        }
        for (KuanhaoAndShuliang i : diaoboList) {
            if (keys.containsKey(i.kuanhao)) {
                AnalyseData analyseData = keys.get(i.kuanhao);
                analyseData.diaobo = i.shuliang;
            } else {
                AnalyseData analyseData = new AnalyseData();
                analyseData.huohao = i.kuanhao;
                analyseData.diaobo = i.shuliang;
                keys.put(i.kuanhao, analyseData);
            }
        }
        for (KuanhaoAndShuliang i : xiaoshouList) {
            if (keys.containsKey(i.kuanhao)) {
                AnalyseData analyseData = keys.get(i.kuanhao);
                analyseData.xiaoshou = i.shuliang;
            } else {
                AnalyseData analyseData = new AnalyseData();
                analyseData.huohao = i.kuanhao;
                analyseData.xiaoshou = i.shuliang;
                keys.put(i.kuanhao, analyseData);
            }
        }

        ArrayList<AnalyseData> analyseData = new ArrayList<>(keys.values());
        analyseData.sort(new Comparator<AnalyseData>() {
            @Override
            public int compare(AnalyseData o1, AnalyseData o2) {
                return o2.xiaoshou - o1.xiaoshou;
            }
        });
        for (AnalyseData a : analyseData) {
            System.out.println(a);
        }
        writeExcel(analyseData);
    }

    public static void writeExcel(List<AnalyseData> dataList) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("库存分析");
        int row = 0;
        HSSFRow head = sheet.createRow(row);
        row++;
        head.createCell(0).setCellValue("款号");
        head.createCell(1).setCellValue("裁片");
        head.createCell(2).setCellValue("调拨");
        head.createCell(3).setCellValue("销售");
        head.createCell(4).setCellValue("库存余量");
        head.createCell(5).setCellValue("总余量");

        for (int i = 0; i < dataList.size(); i++) {
            HSSFRow rowData = sheet.createRow(row);
            row++;
            rowData.createCell(0).setCellValue(dataList.get(i).huohao);
            rowData.createCell(1).setCellValue(dataList.get(i).caipian);
            rowData.createCell(2).setCellValue(dataList.get(i).diaobo);
            rowData.createCell(3).setCellValue(dataList.get(i).xiaoshou);
            rowData.createCell(4).setCellValue(dataList.get(i).diaobo - dataList.get(i).xiaoshou);
            rowData.createCell(5).setCellValue(dataList.get(i).caipian - dataList.get(i).xiaoshou);
        }
//6.通过输出流写到文件里去
        FileOutputStream fos = new FileOutputStream(Path.RESULT);
        workbook.write(fos);
        fos.close();

    }

}
