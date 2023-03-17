package allcaipian;

public class CaipianColors {
    public int num;
    public String colorName;

    public CaipianColors(String colorName) {
        this.colorName = colorName;
    }

    public void addInfo(CaipianColorInfo colorInfo) {
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
