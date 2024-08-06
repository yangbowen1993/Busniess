package path;

public class OutPaths {


    /**
     * 处理秦丝当日流水的结果
     */
    public static String getOneDaySaleLiushuiResult() {
        return Computer.PATH[Computer.FLAG] + "当日流水.xlsx";
    }


    /**
     * 处理裁片颜色-销售颜色结果
     */
    public static String getAllColorKucun() {
        return Computer.PATH[Computer.FLAG] + "分颜色库存.xlsx";
    }


    /**
     * 处理裁片-销售结果
     */
    public static String getAllKucun() {
        return Computer.PATH[Computer.FLAG] + "库存.xlsx";
    }

    public static String getFactoryKucunn(){
        return Computer.PATH[Computer.FLAG] + "工厂剩余库存.xlsx";
    }


    public static String getFactoryChongrong(){
        return Computer.PATH[Computer.FLAG] + "工厂充绒情况.xlsx";
    }

}
