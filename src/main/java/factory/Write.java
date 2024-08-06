package factory;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import path.InPaths;
import path.OutPaths;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.*;

public class Write {
    public static void main(String[] args) throws IOException {
        write();
    }

    public static void write() throws IOException {


        ArrayList<FactoryCloseInfo> caipian = read(InPaths.getCaipianFactoryInfo());
        ArrayList<FactoryCloseInfo> xiaoshou = read(InPaths.getDiaoboFactoryInfo());

        System.out.println(caipian);
        System.out.println(xiaoshou);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("到货情况");
        int row = 0;
        HSSFRow head = sheet.createRow(row);
        row++;
        head.createCell(0).setCellValue("厂名");
        head.createCell(1).setCellValue("款号");
        head.createCell(2).setCellValue("裁片");
        head.createCell(3).setCellValue("调拨");
        head.createCell(4).setCellValue("剩余");

        //排序
        caipian.sort(new Comparator<FactoryCloseInfo>() {
            @Override
            public int compare(FactoryCloseInfo o1, FactoryCloseInfo o2) {
                int i = o1.factoryName.compareTo(o2.factoryName);
                if (i != 0) {
                    return i;
                }

                return o1.huohao.compareTo(o2.huohao);
            }
        });


        HashSet<String> set = new HashSet<>();

        for (FactoryCloseInfo info : caipian) {
            set.add(info.factoryName + info.huohao);
        }

        for (FactoryCloseInfo info : xiaoshou) {
            set.add(info.factoryName + info.huohao);
        }

        ArrayList<String> arrayList = new ArrayList<>(set);

        arrayList.sort(String::compareTo);

        for (String s : arrayList) {
            HSSFRow rowData = sheet.createRow(row);
            row++;
            FactoryCloseInfo info1 = null;
            FactoryCloseInfo info2 = null;
         //   System.out.println("s = " + s);
            for (FactoryCloseInfo info : caipian) {
                if (s.equals(info.factoryName + info.huohao)) {
                    info1 = info;
                }
            }
            for (FactoryCloseInfo info : xiaoshou) {
                if (s.equals(info.factoryName + info.huohao)) {
                    info2 = info;
                }
            }
           System.out.println("s = " + s + "  info1 = " + info1 + " info2 = " + info2);
            writeExcel(rowData, info1, info2);

        }

//        for (FactoryCloseInfo info : caipian) {
//
//            boolean flag = false;
//            for (FactoryCloseInfo info2 : xiaoshou) {
//                if (info.factoryName.equals(info2.factoryName) && info.huohao.equals(info2.huohao)) {
//                    //
//                    System.out.println("裁片信息:" + info + ",调拨信息：" + info2);
//                    HSSFRow rowData = sheet.createRow(row);
//                    row++;
//                    writeExcel(rowData, info, info2);
//                    flag = true;
//                }
//            }
//            if (!flag) {
//                HSSFRow rowData = sheet.createRow(row);
//                row++;
//                writeExcel(rowData, info, null);
//            }
//
//
//        }


        //6.通过输出流写到文件里去
        FileOutputStream fos = new FileOutputStream(OutPaths.getFactoryKucunn());
        workbook.write(fos);
        fos.close();
    }


    public static void writeExcel(HSSFRow rowData, FactoryCloseInfo caipian, FactoryCloseInfo diaobo) {

        rowData.createCell(0).setCellValue(caipian == null ? diaobo.factoryName : caipian.factoryName);
        rowData.createCell(1).setCellValue(caipian == null ? diaobo.huohao : caipian.huohao);
        rowData.createCell(2).setCellValue(caipian == null ? 0 : caipian.shuliang);
        rowData.createCell(3).setCellValue(diaobo == null ? 0 : diaobo.shuliang);
        rowData.createCell(4).setCellValue(diaobo == null ? caipian.shuliang : caipian == null ? diaobo.shuliang : caipian.shuliang + diaobo.shuliang);

    }


    //第三行开始有 有效数据
    public static int START_ROW = 3;
    //要读取的列
    public static int[] LINES = {3, 5, 6};

    public static ArrayList<FactoryCloseInfo> read(String path) throws IOException {
        File excel = new File(path);
        Workbook workbook = WorkbookFactory.create(excel);
        Sheet sheet = workbook.getSheetAt(0);
        ArrayList<FactoryCloseInfo> map = new ArrayList<>();
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
            String factoryName = sheetRow.getCell(LINES[0]).toString();
            if (factoryName == null || factoryName.equals("")) {
                return map;
            }
            String huohao = sheetRow.getCell(LINES[1]).toString().split("-")[1];
            int shuliang = Integer.parseInt(sheetRow.getCell(LINES[2]).toString());
            if (!filter(huohao)) {
                map.add(new FactoryCloseInfo(factoryName, huohao, shuliang));
                System.out.println(huohao + "  " + shuliang);
            }
        }
        return map;
    }

    private static boolean filter(String huohao) {
        return false;
    }

    public static class FactoryCloseInfo {
        public String factoryName;
        public String huohao;
        public int shuliang;

        public FactoryCloseInfo(String factoryName, String huohao, int shuliang) {
            this.factoryName = factoryName;
            this.huohao = huohao;
            this.shuliang = shuliang;
        }

        public String getFactoryName() {
            return factoryName;
        }

        public void setFactoryName(String factoryName) {
            this.factoryName = factoryName;
        }

        public String getHuohao() {
            return huohao;
        }

        public void setHuohao(String huohao) {
            this.huohao = huohao;
        }

        public int getShuliang() {
            return shuliang;
        }

        public void setShuliang(int shuliang) {
            this.shuliang = shuliang;
        }

        @Override
        public String toString() {
            return "FactoryCloseInfo{" +
                    "factoryName='" + factoryName + '\'' +
                    ", huohao='" + huohao + '\'' +
                    ", shuliang=" + shuliang +
                    '}';
        }
    }
}
