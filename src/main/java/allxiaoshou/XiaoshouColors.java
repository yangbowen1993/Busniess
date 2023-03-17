package allxiaoshou;

public class XiaoshouColors {
    public int num;
    public String colorName;

    public XiaoshouColors(String colorName) {
        this.colorName = colorName;
    }

    public void addInfo(XiaoshouColorInfo colorInfo) {
        num+=colorInfo.num;
    }


    @Override
    public String toString() {
        return "Colors{" +
                "num=" + num +
                ", colorName='" + colorName + '\'' +
                '}';
    }
}
