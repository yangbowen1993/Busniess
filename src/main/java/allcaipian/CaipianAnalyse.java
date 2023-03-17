package allcaipian;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import path.OutPaths;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

public class CaipianAnalyse {

    public static void main(String[] args) throws IOException {
        //写
        writeExcel(getCaipianList());
    }

    /**
     * 读裁片、分颜色
     */
    public static List<CaipianColorInfo> getCaipianList() throws IOException {
        List<CaipianColorInfo> colorInfos = CaipianColorExcel.readExcel();
        ArrayList<KuanInfo> kuanInfos = new ArrayList<>();
        a:
        for (int i = 0; i < colorInfos.size(); i++) {
            CaipianColorInfo colorInfo = colorInfos.get(i);
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
        List<CaipianColorInfo> caipianColorInfos = dataDispose2(kuanInfos);


        return caipianColorInfos;
    }

    private static List<CaipianColorInfo> dataDispose2(ArrayList<KuanInfo> kuanInfos) {
        System.out.println(kuanInfos);
        ArrayList<CaipianColorInfo> colorInfos = new ArrayList<>();
        //款号，升序排
        kuanInfos.sort(new Comparator<KuanInfo>() {
            @Override
            public int compare(KuanInfo o1, KuanInfo o2) {
                return o1.kuanhao.compareTo(o2.kuanhao);
            }
        });
        for (int i = 0; i < kuanInfos.size(); i++) {
            KuanInfo kuanInfo = kuanInfos.get(i);

            HashMap<String, Integer> colorMap = new HashMap<>();

            for (int j = 0; j < kuanInfo.list.size(); j++) {
                FactoryInfo factoryInfo = kuanInfo.list.get(j);

                for (int k = 0; k < factoryInfo.list.size(); k++) {
                    CaipianColors colors = factoryInfo.list.get(k);
                    if (colorMap.containsKey(colors.colorName)) {
                        colorMap.put(colors.colorName, colorMap.get(colors.colorName) + colors.num);
                    } else {
                        colorMap.put(colors.colorName, colors.num);
                    }

                }
            }

            ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>(colorMap.entrySet());
            Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o2.getValue() - o1.getValue();
                }
            });
            // LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
            entries.forEach(new Consumer<Map.Entry<String, Integer>>() {
                @Override
                public void accept(Map.Entry<String, Integer> stringIntegerEntry) {
                    colorInfos.add(new CaipianColorInfo("", kuanInfo.kuanhao, stringIntegerEntry.getKey(), stringIntegerEntry.getValue()));
                }
            });
        }
        return colorInfos;

    }


    private static List<CaipianColorInfo> dataDispose(ArrayList<KuanInfo> kuanInfos) {
        System.out.println(kuanInfos);
        ArrayList<CaipianColorInfo> colorInfos = new ArrayList<>();
        //款号，升序排
        kuanInfos.sort(new Comparator<KuanInfo>() {
            @Override
            public int compare(KuanInfo o1, KuanInfo o2) {
                return o1.kuanhao.compareTo(o2.kuanhao);
            }
        });
        for (int i = 0; i < kuanInfos.size(); i++) {
            KuanInfo kuanInfo = kuanInfos.get(i);
            //厂
            kuanInfo.list.sort(new Comparator<FactoryInfo>() {
                @Override
                public int compare(FactoryInfo o1, FactoryInfo o2) {
                    return o2.allNum() - o1.allNum();
                }
            });
            for (int j = 0; j < kuanInfo.list.size(); j++) {
                FactoryInfo factoryInfo = kuanInfo.list.get(j);
                factoryInfo.list.sort(new Comparator<CaipianColors>() {
                    @Override
                    public int compare(CaipianColors o1, CaipianColors o2) {
                        return o2.num - o1.num;
                    }
                });
                for (int k = 0; k < factoryInfo.list.size(); k++) {
                    CaipianColors colors = factoryInfo.list.get(k);
                    colorInfos.add(new CaipianColorInfo(factoryInfo.factoryName, kuanInfo.kuanhao, colors.colorName, colors.num));
                }
            }
        }
        return colorInfos;

    }


    /**
     * 仅分析每个款每个厂每个色
     */
    public static void writeExcel(List<CaipianColorInfo> dataList) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("裁片颜色");
        int row = 0;
        HSSFRow head = sheet.createRow(row);
        row++;
        head.createCell(0).setCellValue("款号");
        head.createCell(1).setCellValue("厂名");
        head.createCell(2).setCellValue("颜色");
        head.createCell(3).setCellValue("数量");

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
            rowData.createCell(1).setCellValue(dataList.get(i).factoryName);
            rowData.createCell(2).setCellValue(dataList.get(i).color);
            rowData.createCell(3).setCellValue(dataList.get(i).num);

        }
//6.通过输出流写到文件里去
        FileOutputStream fos = new FileOutputStream(OutPaths.getAllKucun());
        workbook.write(fos);
        fos.close();

    }
}
