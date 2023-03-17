package path;

import static path.Computer.PATH;

public class InPaths {


//    public static String[] CAIPIAN_COLOR_PATHS = {"",
//            "C:\\Users\\12713\\Downloads\\caipian.xlsx",
//            "C:\\Users\\ybw\\Downloads\\caipian.xlsx"};

    /**
     * 管家婆导出的分颜色的裁片信息
     */
    public static String getCaipianColorPath() {
        return PATH[Computer.FLAG] + "caipian.xlsx";
    }


    /**
     * 秦丝分颜色导出的总销售数据
     */
    public static String getXiaoshouColorPath() {
        return PATH[Computer.FLAG] + "xiaoshou.xlsx";
    }


    /**
     * 秦丝导出的当日流水信息
     */
    public static String getOnedayXiaoshouLiushui() {
        return PATH[Computer.FLAG] + "liushui.xlsx";
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
        return PATH[Computer.FLAG] + "color.xlsx";
    }
}
