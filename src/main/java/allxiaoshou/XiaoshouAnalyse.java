package allxiaoshou;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import path.OutPaths;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class XiaoshouAnalyse {

    public static void main(String[] args) throws IOException {
        //写
     //   writeExcel(getXiaoshouList());

    }

    public static List<XiaoshouColorInfo> getXiaoshouList() throws IOException {
        List<XiaoshouColorInfo> colorInfos = XiaoshouColorExcel.readExcel();
        ArrayList<KuanInfo> kuanInfos = new ArrayList<>();
        a:
        for (int i = 0; i < colorInfos.size(); i++) {
            XiaoshouColorInfo colorInfo = colorInfos.get(i);
            b:
            for (KuanInfo kuan : kuanInfos) {
                if (colorInfo.kuanhao.equals(kuan.kuanhao)) {
                    kuan.addInfo(colorInfo);
                    continue a;
                }
            }
            //添加新的
            KuanInfo kuanInfo = new KuanInfo(colorInfo.kuanhao);
            kuanInfo.addInfo(colorInfo);
            kuanInfos.add(kuanInfo);
        }
        return dataDispose(kuanInfos);
    }


    private static List<XiaoshouColorInfo> dataDispose(ArrayList<KuanInfo> kuanInfos) {
        System.out.println(kuanInfos);
        ArrayList<XiaoshouColorInfo> colorInfos = new ArrayList<>();
        //款号，升序排
        kuanInfos.sort(new Comparator<KuanInfo>() {
            @Override
            public int compare(KuanInfo o1, KuanInfo o2) {
                return o1.kuanhao.compareTo(o2.kuanhao);
            }
        });
        for (int i = 0; i < kuanInfos.size(); i++) {
            KuanInfo kuanInfo = kuanInfos.get(i);


            kuanInfo.list.sort(new Comparator<XiaoshouColors>() {
                @Override
                public int compare(XiaoshouColors o1, XiaoshouColors o2) {
                    return o2.num - o1.num;
                }
            });
            for (int j = 0; j < kuanInfo.list.size(); j++) {
                XiaoshouColors colors = kuanInfo.list.get(j);
                colorInfos.add(new XiaoshouColorInfo(kuanInfo.kuanhao, colors.colorName, colors.num));

            }
        }
        return colorInfos;

    }


    /**秦丝分颜色销售信息直接输出，没啥用*/
    public static void writeExcel(List<XiaoshouColorInfo> dataList) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("裁片颜色");
        int row = 0;
        HSSFRow head = sheet.createRow(row);
        row++;
        head.createCell(0).setCellValue("款号");

        head.createCell(1).setCellValue("颜色");
        head.createCell(2).setCellValue("数量");

        String tempkuanhao = "";
        for (int i = 0; i < dataList.size(); i++) {
            if (!dataList.get(i).kuanhao.equals(tempkuanhao)) {
                tempkuanhao = dataList.get(i).kuanhao;
                HSSFRow rowData2 = sheet.createRow(row);
                row++;
            }
            HSSFRow rowData = sheet.createRow(row);
            row++;

            rowData.createCell(0).setCellValue(dataList.get(i).kuanhao);
            rowData.createCell(1).setCellValue(dataList.get(i).color);
            rowData.createCell(2).setCellValue(dataList.get(i).num);

        }
//6.通过输出流写到文件里去
        FileOutputStream fos = new FileOutputStream(OutPaths.getAllKucun());
        workbook.write(fos);
        fos.close();

    }
}
