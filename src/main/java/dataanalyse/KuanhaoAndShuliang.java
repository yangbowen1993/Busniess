package dataanalyse;

public class KuanhaoAndShuliang {
    public String kuanhao;
    public int shuliang;

    @Override
    public String toString() {
        return "KuanhaoAndShuliang{" +
                "kuanhao='" + kuanhao + '\'' +
                ", shuliang=" + shuliang +
                '}';
    }

    public KuanhaoAndShuliang(String kuanhao, int shuliang) {
        this.kuanhao = kuanhao;
        this.shuliang = shuliang;
    }

}
