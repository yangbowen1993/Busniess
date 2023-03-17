package allxiaoshou;

public class XiaoshouColorInfo {

    public String kuanhao;
    public String color;
    public int num;

    public XiaoshouColorInfo(String kuanhao, String color, int num) {

        this.kuanhao = kuanhao;
        this.color = color;
        this.num = num;
    }

    @Override
    public String toString() {
        return "ColorInfo{" +
                "kuanhao='" + kuanhao + '\'' +
                ", color='" + color + '\'' +
                ", num=" + num +
                '}';
    }
}
