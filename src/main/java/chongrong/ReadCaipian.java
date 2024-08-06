package chongrong;

import org.apache.poi.ss.usermodel.*;
import path.InPaths;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCaipian {
    public static int START_ROW = 3;
    //要读取的列
    //厂名、款号、颜色、码数、件数
    public static int[] LINES = {4, 9, 11, 12, 13};


    public static ArrayList<CaipianInfo> readCaipian() throws IOException {
        ArrayList<CaipianInfo> list = new ArrayList<>();
        //读取分颜色的裁片Excel
        File excel = new File(InPaths.getCaipianColorPath());
        Workbook workbook = WorkbookFactory.create(excel);
        Sheet sheet = workbook.getSheetAt(0);
        a:for (int i = START_ROW; i <= sheet.getLastRowNum(); i++) {


            Row sheetRow = sheet.getRow(i);
            String factoryName = sheetRow.getCell(LINES[0]).toString();
            String kuanhao = sheetRow.getCell(LINES[1]).toString();
            String color = sheetRow.getCell(LINES[2]).toString();
            String mashu = sheetRow.getCell(LINES[3]).toString();
            String jianshu = sheetRow.getCell(LINES[4]).toString();
            int num = Double.valueOf(jianshu).intValue();
            for (CaipianInfo info : list) {
                if (info.factoryName.equals(factoryName) && info.kuanhao.equals(kuanhao) && info.mashu.equals(mashu)) {
                    info.setNum(info.getNum() + num);
                    continue a;
                }
            }

            list.add(new CaipianInfo(factoryName, kuanhao, color, mashu, num));
        }

        return list;
    }

    public static class CaipianInfo {
        String factoryName;
        String kuanhao;
        String color;
        String mashu;
        int num;

        public CaipianInfo(String factoryName, String kuanhao, String color, String mashu, int num) {
            this.factoryName = factoryName;
            this.kuanhao = kuanhao;
            this.color = color;
            this.mashu = mashu;
            this.num = num;
        }

        @Override
        public String toString() {
            return "CaipianInfo{" +
                    "factoryName='" + factoryName + '\'' +
                    ", kuanhao='" + kuanhao + '\'' +
                    ", color='" + color + '\'' +
                    ", mashu='" + mashu + '\'' +
                    ", num=" + num +
                    '}';
        }

        public String getFactoryName() {
            return factoryName;
        }

        public void setFactoryName(String factoryName) {
            this.factoryName = factoryName;
        }

        public String getKuanhao() {
            return kuanhao;
        }

        public void setKuanhao(String kuanhao) {
            this.kuanhao = kuanhao;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getMashu() {
            return mashu;
        }

        public void setMashu(String mashu) {
            this.mashu = mashu;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }


}
