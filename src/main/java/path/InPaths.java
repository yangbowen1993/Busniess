package path;

import static path.Computer.PATH;

public class InPaths {




    public static String getDownloadPath() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return System.getProperty("user.home") + "\\Downloads\\";
        } else if (os.contains("mac")) {
            return System.getProperty("user.home") + "/Download/";
        }
        System.out.println("找不到系统");
        return "";
    }

    /**
     * 管家婆导出的分颜色的裁片信息
     */
    public static String getCaipianColorPath() {
        return getDownloadPath()  + "caipian.xlsx";
    }


    public static String getChongrongPath() {
        return getDownloadPath()  + "chongrong.xlsx";
    }

    /**
     * 秦丝分颜色导出的总销售数据
     */
    public static String getXiaoshouColorPath() {
        return getDownloadPath()  + "xiaoshou.xlsx";
    }


    /**
     * 秦丝导出的当日流水信息
     */
    public static String getOnedayXiaoshouLiushui() {
     //   System.out.println(getDownloadPath() + "ls.xlsx");
        return getDownloadPath() + "ls.xlsx";
    }


    /**
     * 从管家婆单据综合统计中导出的总裁片信息
     */
    public static String getAllCaipianInfo() {
        return PATH[Computer.FLAG];
    }


    /**
     * 从秦丝中导出的总销售信息
     */
    public static String getAllXiaoshouInfo() {
        return PATH[Computer.FLAG];
    }


    /**
     * 管家婆与秦丝同款号 颜色映射信息
     */
    public static String getColorMapInfo() {
        return getDownloadPath()  + "color3.xlsx";
    }

    public static String getCaipianFactoryInfo() {
        return getDownloadPath()  + "caipian.xlsx";
    }

    public static String getDiaoboFactoryInfo() {
        return getDownloadPath() + "diaobo.xlsx";
    }


}
