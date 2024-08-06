package chongrong;

import org.apache.poi.ss.usermodel.*;
import path.InPaths;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadChongrong {
    public static int START_ROW = 2;
    //要读取的列


    public static HashMap<String, Chongrong> readChongrong() throws IOException {
        ArrayList<ReadCaipian.CaipianInfo> list = new ArrayList<>();
        //读取充绒表Excel
        File excel = new File(InPaths.getChongrongPath());
        Workbook workbook = WorkbookFactory.create(excel);
        Sheet sheet = workbook.getSheetAt(0);
        HashMap<String, Chongrong> map = new HashMap<>();
        for (int i = START_ROW; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            String yuanshihuohao = row.getCell(0).toString();
            if ("".equals(yuanshihuohao)) {
                break;
            }
            String kuanhao = yuanshihuohao.substring(4);
            String s1 = row.getCell(1).toString();
            double s = "".equals(s1) ? 0 : Double.valueOf(s1);
            double m = Double.valueOf(row.getCell(2).toString());
            double l = Double.valueOf(row.getCell(3).toString());
            double xl = Double.valueOf(row.getCell(4).toString());
            String xl21 = row.getCell(5).toString();
            double xl2 = "".equals(xl21) ? 0 : Double.valueOf(xl21);
            map.put(kuanhao, new Chongrong(kuanhao, s, m, l, xl, xl2));
        }
        return map;
    }

    public static class Chongrong {
        String kuanhao;
        double s;
        double m;
        double l;
        double xl;
        double xl2;

        public Chongrong(String kuanhao, double s, double m, double l, double xl, double xl2) {
            this.kuanhao = kuanhao;
            this.s = s;
            this.m = m;
            this.l = l;
            this.xl = xl;
            this.xl2 = xl2;
        }

        public String getKuanhao() {
            return kuanhao;
        }

        public void setKuanhao(String kuanhao) {
            this.kuanhao = kuanhao;
        }

        public double getS() {
            return s;
        }

        public void setS(double s) {
            this.s = s;
        }

        public double getM() {
            return m;
        }

        public void setM(double m) {
            this.m = m;
        }

        public double getL() {
            return l;
        }

        public void setL(double l) {
            this.l = l;
        }

        public double getXl() {
            return xl;
        }

        public void setXl(double xl) {
            this.xl = xl;
        }

        public double getXl2() {
            return xl2;
        }

        public void setXl2(double xl2) {
            this.xl2 = xl2;
        }
    }
}
