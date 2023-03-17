package allcaipian;

public class CaipianColorInfo {
    public String factoryName;
    public String kuanhao;
    public String color;
    public int num;

    public CaipianColorInfo(String factoryName, String kuanhao, String color, int num) {
        this.factoryName = factoryName;
        this.kuanhao = kuanhao;
        this.color = color;
        this.num = num;
    }

    @Override
    public String toString() {
        return "ColorInfo{" +
                "factoryName='" + factoryName + '\'' +
                ", kuanhao='" + kuanhao + '\'' +
                ", color='" + color + '\'' +
                ", num=" + num +
                '}';
    }
}
