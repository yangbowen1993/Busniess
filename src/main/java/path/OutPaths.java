package path;

public class OutPaths {
    public static String[] ONE_DAY_SALE_LIUSHUI_RESULT_PATHS = {"", "", "C:\\Users\\ybw\\Downloads\\当日流水.xlsx"};

    /**
     * 处理秦丝当日流水的结果
     */
    public static String getOneDaySaleLiushuiResult() {
        return ONE_DAY_SALE_LIUSHUI_RESULT_PATHS[Computer.FLAG];
    }


    public static String[] ALL_COLOR_KUCUN_PATHS = {"", "", "C:\\Users\\ybw\\Downloads\\分颜色库存.xlsx"};

    /**
     * 处理裁片颜色-销售颜色结果
     */
    public static String getAllColorKucun() {
        return ALL_COLOR_KUCUN_PATHS[Computer.FLAG];
    }


    public static String[] ALL_KUCUN_PATHS = {"", "", "C:\\Users\\ybw\\Downloads\\" +
            "库存.xlsx"};

    /**
     * 处理裁片-销售结果
     */
    public static String getAllKucun() {
        return ALL_KUCUN_PATHS[Computer.FLAG];
    }

}
