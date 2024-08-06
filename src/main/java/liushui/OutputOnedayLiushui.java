package liushui;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import path.OutPaths;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class OutputOnedayLiushui {
    public static int[] HE_BING = {0, 1, 6, 7, 8, 9, 10, 11};


    public static void main(String[] args) throws IOException {
        writeExcel();
    }

    public static void writeExcel() throws IOException {
        Map<String, Order> map = ReadOnedayLiushui.readExcel();
        Collection<Order> values = map.values();
        ArrayList<Order> orderArrayList = new ArrayList<>(values);
        Collections.sort(orderArrayList, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                String time1 = o1.time;
                String time2 = o2.time;

                return time1.compareTo(time2);
            }
        });

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("今日流水");
        sheet.setColumnWidth(0, 16 * 256);
        sheet.setColumnWidth(1, 20 * 256);


        int rowIndex = 0;

        /**先写个标题*/
        rowIndex = getHeadRow(workbook, sheet, rowIndex);

        /**写个时间**/
        rowIndex = getDateRow(workbook, sheet, rowIndex, orderArrayList.get(0).time);


        /**设置主体样式*/
        HSSFCellStyle mainStyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontHeight((short) 200);
        mainStyle.setFont(font);
        mainStyle.setAlignment(HorizontalAlignment.CENTER);
        mainStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        mainStyle.setBorderBottom(BorderStyle.MEDIUM); //下边框
        mainStyle.setBorderLeft(BorderStyle.MEDIUM);//左边框
        mainStyle.setBorderTop(BorderStyle.MEDIUM);//上边框
        mainStyle.setBorderRight(BorderStyle.MEDIUM);//右边框


        /**写表头*/
        HSSFRow rowHead = sheet.createRow(rowIndex);
        rowIndex++;
        rowHead.createCell(0).setCellValue("姓名");
        rowHead.createCell(1).setCellValue("时间");
        rowHead.createCell(2).setCellValue("货号");
        rowHead.createCell(3).setCellValue("数量");
        rowHead.createCell(4).setCellValue("价格");
        rowHead.createCell(5).setCellValue("金额");
        rowHead.createCell(6).setCellValue("总数量");
        rowHead.createCell(7).setCellValue("总金额");
        rowHead.createCell(8).setCellValue("已付款");
        rowHead.createCell(9).setCellValue("未付款");
        rowHead.createCell(10).setCellValue("物流信息");
        rowHead.createCell(11).setCellValue("备注");

        for (int i = 0; i <= 11; i++) {
            HSSFCell cell = rowHead.getCell(i);
            if (cell == null) {
                cell = rowHead.createCell(i);
            }
            cell.setCellStyle(mainStyle);
        }

        short a = 0xfff;
        short b = 0xccc;
        short c = 0x888;


        boolean colorFlag = true;
        /**循环写入内容*/
        for (Order order : orderArrayList) {
            //是否是单据开头
            boolean isFirstOrder = true;
            int startRow = rowIndex;
            for (Article article : order.articles.values()) {
                int allMoney = order.allMoney();
                //某一单
                Article article1 = order.articles.get(article.artNmu);

                HSSFRow row = sheet.createRow(rowIndex);
                rowIndex++;
                if (isFirstOrder) {
                    //写姓名
                    HSSFCell cell1 = row.createCell(0);
                    cell1.setCellValue(order.name);
                    cell1.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
                    cell1.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
                    //写开单时间

                    //  row.getCell(TIME_INDEX).getLocalDateTimeCellValue().toString().replace("T"," ");
                    row.createCell(1).setCellValue(order.time);
                    row.createCell(7).setCellValue(allMoney);
                    row.createCell(6).setCellValue(order.allNum());
                    isFirstOrder = false;
                }
                row.createCell(2).setCellValue(article1.artNmu);
                row.createCell(3).setCellValue(article1.allNum());
                row.createCell(4).setCellValue(article1.colorInfos.values().toArray(new ColorInfo[1])[0].price);
                row.createCell(5).setCellValue(article1.allMoney());

                for (int i = 0; i <= 11; i++) {
                    HSSFCell cell = row.getCell(i);
                    if (cell == null) {
                        cell = row.createCell(i);
                    }
                    cell.setCellStyle(mainStyle);
                }

            }


            if (order.articles.size() > 1) {
                for (int i : HE_BING) {
                    CellRangeAddress region = new CellRangeAddress(startRow, startRow + order.articles.size() - 1, i, i);
                    sheet.addMergedRegion(region);
                }
            }
        }

        //6.通过输出流写到文件里去
        FileOutputStream fos = new FileOutputStream(OutPaths.getOneDaySaleLiushuiResult());
        workbook.write(fos);
        fos.close();

    }

    private static int getHeadRow(HSSFWorkbook workbook, HSSFSheet sheet, int rowIndex) {
        HSSFRow row = sheet.createRow(rowIndex);
        row.setHeight((short) 1000);

        HSSFCell head1Cell = row.createCell(0);
        head1Cell.setCellValue("明细表");
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontHeight((short) 800);
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM); //下边框
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);//左边框
        cellStyle.setBorderTop(BorderStyle.MEDIUM);//上边框
        cellStyle.setBorderRight(BorderStyle.MEDIUM);//右边框
        // head1Cell.setCellStyle(cellStyle);

        CellRangeAddress cellAddresses = new CellRangeAddress(rowIndex, rowIndex, 0, 11);
        sheet.addMergedRegion(cellAddresses);


        for (int i = 0; i <= 11; i++) {
            HSSFCell cell = row.getCell(i);
            if (cell == null) {
                cell = row.createCell(i);
            }
            cell.setCellStyle(cellStyle);
        }
        rowIndex++;
        return rowIndex;
    }

    private static int getDateRow(HSSFWorkbook workbook, HSSFSheet sheet, int rowIndex, String time) {
        HSSFRow row = sheet.createRow(rowIndex);
        row.setHeight((short) 400);

        HSSFCell head1Cell = row.createCell(0);
        head1Cell.setCellValue("日期：" + time.substring(0, 10));


        HSSFCellStyle cellStyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontHeight((short) 300);
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM); //下边框
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);//左边框
        cellStyle.setBorderTop(BorderStyle.MEDIUM);//上边框
        cellStyle.setBorderRight(BorderStyle.MEDIUM);//右边框
        //  head1Cell.setCellStyle(cellStyle);

        CellRangeAddress cellAddresses = new CellRangeAddress(rowIndex, rowIndex, 0, 11);
        sheet.addMergedRegion(cellAddresses);

        for (int i = 0; i <= 11; i++) {
            HSSFCell cell = row.getCell(i);
            if (cell == null) {
                cell = row.createCell(i);
            }
            cell.setCellStyle(cellStyle);
        }
        rowIndex++;
        return rowIndex;
    }
}
