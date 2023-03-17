package liushui;

public class ColorInfo {
    //颜色
    public String color;
    //价格
    public int price;

    public int mNum;
    public int lNmu;
    public int xlNum;
    public int xl2Num;

    public ColorInfo(OldExcelObj oldExcelObj) {
        color = oldExcelObj.color;
        price = oldExcelObj.price;
        addOldExcelObj(oldExcelObj);
    }

    public void addOldExcelObj(OldExcelObj oldExcelObj) {
        switch (oldExcelObj.size) {
            case "M":
                mNum += oldExcelObj.num;
                break;
            case "L":
                lNmu += oldExcelObj.num;
                break;
            case "XL":
                xlNum += oldExcelObj.num;
                break;
            case "XXL":
                xl2Num += oldExcelObj.num;
                break;
        }
    }

    @Override
    public String toString() {
        return "ColorInfo{" +
                "color='" + color + '\'' +
                ", price=" + price +
                ", mNum=" + mNum +
                ", lNmu=" + lNmu +
                ", xlNum=" + xlNum +
                ", xl2Num=" + xl2Num +
                '}';
    }
}
