package liushui;

import org.apache.poi.hssf.usermodel.*;
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

        int row = 0;
        HSSFRow head = sheet.createRow(row);
        row++;
        head.createCell(0).setCellValue("姓名");
        head.createCell(1).setCellValue("时间");
        head.createCell(2).setCellValue("货号");
        head.createCell(3).setCellValue("数量");
        head.createCell(4).setCellValue("价格");
        head.createCell(5).setCellValue("金额");
        head.createCell(6).setCellValue("总数量");
        head.createCell(7).setCellValue("总金额");
        head.createCell(8).setCellValue("已付款");
        head.createCell(9).setCellValue("未付款");
        head.createCell(10).setCellValue("物流信息");
        head.createCell(11).setCellValue("备注");

        short a = 0xfff;
        short b = 0xccc;
        short c = 0x888;


        boolean colorFlag = true;
        for (Order order : orderArrayList) {
            //是否是单据开头
            boolean isFirstOrder = true;
            int startRow = row;
            for (Article article : order.articles.values()) {
                int allMoney = order.allMoney();
                //某一单
                Article article1 = order.articles.get(article.artNmu);

                HSSFRow row1 = sheet.createRow(row);
                row++;
                if (isFirstOrder) {
                    //写姓名
                    HSSFCell cell1 = row1.createCell(0);
                    cell1.setCellValue(order.name);
                    cell1.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
                    cell1.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
                    //写开单时间
                    row1.createCell(1).setCellValue(order.time);
                    row1.createCell(7).setCellValue(allMoney);
                    row1.createCell(6).setCellValue(order.allNum());
                    isFirstOrder = false;
                }
                row1.createCell(2).setCellValue(article1.artNmu);
                row1.createCell(3).setCellValue(article1.allNum());
                row1.createCell(4).setCellValue(article1.colorInfos.values().toArray(new ColorInfo[1])[0].price);
                row1.createCell(5).setCellValue(article1.allMoney());
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
}
