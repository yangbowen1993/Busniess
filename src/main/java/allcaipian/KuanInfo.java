package allcaipian;

import java.util.ArrayList;

public class KuanInfo {
    public String kuanhao;
    public ArrayList<FactoryInfo> list = new ArrayList<>();

    public KuanInfo(String kuanhao) {
        this.kuanhao = kuanhao;
    }

    @Override
    public String toString() {
        return "KuanInfo{" +
                "kuanhao='" + kuanhao + '\'' +
                ", list=" + list +
                '}';
    }

    public void addInfo(CaipianColorInfo colorInfo) {
        for (int i = 0; i < list.size(); i++) {
            FactoryInfo factoryInfo = list.get(i);
            System.out.println(colorInfo.factoryName);
            if (factoryInfo.factoryName.equals(colorInfo.factoryName)) {
                factoryInfo.addInfo(colorInfo);
                return;
            }
        }
        FactoryInfo factoryInfo1 = new FactoryInfo(colorInfo.factoryName);
        factoryInfo1.addInfo(colorInfo);
        list.add(factoryInfo1);
    }

    /**
     * 每款总数量
     */
    public int allNum() {
        int num = 0;
        for (FactoryInfo c : list) {
            num += c.allNum();
        }
        return num;
    }
}
