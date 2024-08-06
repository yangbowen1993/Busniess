package chongrong;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import path.InPaths;
import path.OutPaths;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class WriteExcel {
    public static void main(String[] args) throws IOException {
        extracted();

    }

    public static void extracted() throws IOException {
        //先读取分厂  分颜色 码数的裁片
        //读取各款分码数充绒量
        //计算分别各厂  各款

        ArrayList<ReadCaipian.CaipianInfo> caipianInfos = ReadCaipian.readCaipian();
        HashMap<String, ReadChongrong.Chongrong> chongrongHashMap = ReadChongrong.readChongrong();
        caipianInfos.sort(new Comparator<ReadCaipian.CaipianInfo>() {
            @Override
            public int compare(ReadCaipian.CaipianInfo o1, ReadCaipian.CaipianInfo o2) {
                if (o1.factoryName != o2.factoryName) {
                    return o2.factoryName.compareTo(o1.factoryName);
                }

                if (o1.kuanhao != o2.kuanhao) {
                    return o1.kuanhao.compareTo(o2.kuanhao);
                }

                return o1.mashu.compareTo(o2.mashu);
            }
        });
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("各厂羽绒理论用量");
        sheet.setColumnWidth(0, 16 * 256);
        sheet.setColumnWidth(1, 20 * 256);

        int row = 0;
        HSSFRow head = sheet.createRow(row);
        row++;
        head.createCell(0).setCellValue("工厂");
        head.createCell(1).setCellValue("款号");
        head.createCell(2).setCellValue("尺码");
        head.createCell(3).setCellValue("裁片数量");
        head.createCell(4).setCellValue("充绒量");
        head.createCell(5).setCellValue("单码充绒量");
        head.createCell(6).setCellValue("单款充绒量");
        head.createCell(6).setCellValue("厂里总用量");


        double kuanZhonngChongrong = 0;
        double factoryZhongChongrong = 0;
        int startFactoryRow = 1;
        int startKuanhaoRow = 1;
        for (int i = 0; i < caipianInfos.size(); i++) {
            ReadCaipian.CaipianInfo caipianInfo = caipianInfos.get(i);
            ReadCaipian.CaipianInfo nextCaipianInfo = null;
            if (i == caipianInfos.size() - 1) {
                //最后一行
            } else {
                nextCaipianInfo = caipianInfos.get(i + 1);
            }


            HSSFRow row1 = sheet.createRow(row);
            row++;


            row1.createCell(2).setCellValue(caipianInfo.mashu);
            row1.createCell(3).setCellValue(caipianInfo.num);
            double chongrong = getChongrong(caipianInfo, chongrongHashMap);
            row1.createCell(4).setCellValue(chongrong);
            double danMaChongrong = caipianInfo.num * chongrong;
            row1.createCell(5).setCellValue(danMaChongrong);
            kuanZhonngChongrong += danMaChongrong;
            factoryZhongChongrong += danMaChongrong;


            if (nextCaipianInfo == null || !caipianInfo.factoryName.equals(nextCaipianInfo.factoryName)) {
                //厂名有变化，或者最后一排，写厂名
                HSSFCell cell1 = row1.createCell(0);
                cell1.setCellValue(caipianInfo.factoryName);
                cell1.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
                cell1.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);

                HSSFCell cell2 = row1.createCell(7);
                cell2.setCellValue(factoryZhongChongrong);
                cell2.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
                cell2.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
                //写完重置
                factoryZhongChongrong = 0;

//                //合并厂名单元格
//                CellRangeAddress region = new CellRangeAddress(startFactoryRow, row - 1, 0, 0);
//                sheet.addMergedRegion(region);
//                CellRangeAddress region1 = new CellRangeAddress(startFactoryRow, row - 1, 7, 7);
//                sheet.addMergedRegion(region1);

                //将开始列更新
                startFactoryRow = row;
            }
            if (nextCaipianInfo == null || !caipianInfo.kuanhao.equals(nextCaipianInfo.kuanhao)) {
                //款号有变化，或者最后一排，写款号
                HSSFCell cell1 = row1.createCell(1);
                cell1.setCellValue(caipianInfo.kuanhao);
                cell1.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
                cell1.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);


                HSSFCell cell2 = row1.createCell(6);
                cell2.setCellValue(kuanZhonngChongrong);
                cell2.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
                cell2.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
                //写完重置
                kuanZhonngChongrong = 0;

//                //合并厂名单元格
//                CellRangeAddress region = new CellRangeAddress(startKuanhaoRow, row - 1, 0, 0);
//                sheet.addMergedRegion(region);
//                CellRangeAddress region1 = new CellRangeAddress(startKuanhaoRow, row - 1, 6, 6);
//                sheet.addMergedRegion(region1);

                //将开始列更新
                startKuanhaoRow = row;
            }


//            if (order.articles.size() > 1) {
//                for (int i : HE_BING) {
//                    CellRangeAddress region = new CellRangeAddress(startRow, startRow + order.articles.size() - 1, i, i);
//                    sheet.addMergedRegion(region);
//                }
//            }

        }

        FileOutputStream fos = new FileOutputStream(OutPaths.getFactoryChongrong());
        workbook.write(fos);
        fos.close();
    }

    private static double getChongrong(ReadCaipian.CaipianInfo caipianInfo, HashMap<String, ReadChongrong.Chongrong
            > map) {
        ReadChongrong.Chongrong chongrong = map.get(caipianInfo.kuanhao);
        if (chongrong == null) {
            return 0;
        }
        switch (caipianInfo.mashu) {
            case "S":
                return chongrong.s;
            case "M":
                return chongrong.m;
            case "L":
                return chongrong.l;
            case "XL":
                return chongrong.xl;
            case "2XL":
                return chongrong.xl2;
        }
        return 0;
    }

    private static void writeExcel() {
    }


    private static void calculate() {

    }
}
