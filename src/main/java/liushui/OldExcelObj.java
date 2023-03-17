package liushui;

public class OldExcelObj {
    //单据编号
    public String voucherNum;
    //货号
    public String artNum;

    //单品数量
    public int num;
    // 单价
    public int price;
    // 颜色
    public String color;
    // 尺码
    public String size;
    public String name;

    public String time;

    @Override
    public String toString() {
        return "OldExcelObj{" +
                "voucherNum='" + voucherNum + '\'' +
                ", artNum='" + artNum + '\'' +
                ", num=" + num +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
