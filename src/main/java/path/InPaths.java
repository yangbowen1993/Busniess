package path;

public class InPaths {

    public static String[] CAIPIAN_COLOR_PATHS = {"",
            "",
            "C:\\Users\\ybw\\Downloads\\caipian.xlsx"};

    /**
     * 管家婆导出的分颜色的裁片信息
     */
    public static String getCaipianColorPath() {
        return CAIPIAN_COLOR_PATHS[Computer.FLAG];
    }

    public static String[] XIAOSHOU_COLOR_PATHS = {"",
            "",
            "C:\\Users\\ybw\\Downloads\\xiaoshou.xlsx"};

    /**
     * 秦丝分颜色导出的总销售数据
     */
    public static String getXiaoshouColorPath() {
        return XIAOSHOU_COLOR_PATHS[Computer.FLAG];
    }




    public static String[] ONE_DAY_XIAOSHOU_LIUSHUI = {"",
            "",
            "C:\\Users\\ybw\\Downloads\\liushui.xlsx"};

    /**
     * 秦丝导出的当日流水信息
     */
    public static String getOnedayXiaoshouLiushui() {
        return ONE_DAY_XIAOSHOU_LIUSHUI[Computer.FLAG];
    }





    public static String[] ALL_CAIPIAN_INFO_PATHS = {"", "", ""};

    /**
     * 从管家婆单据综合统计中导出的总裁片信息
     */
    public static String getAllCaipianInfo() {
        return ALL_CAIPIAN_INFO_PATHS[Computer.FLAG];
    }

    public static String[] ALL_XIAOSHOU_INFO_PATH = {"", "", ""};

    /**
     * 从秦丝中导出的总销售信息
     */
    public static String getAllXiaoshouInfo() {
        return ALL_XIAOSHOU_INFO_PATH[Computer.FLAG];
    }






    public static String[] COLOR_MAP = {"", "", "C:\\Users\\ybw\\Downloads\\color.xlsx"};

    /**
     * 管家婆与秦丝同款号 颜色映射信息
     */
    public static String getColorMapInfo() {
        return COLOR_MAP[Computer.FLAG];
    }
}
