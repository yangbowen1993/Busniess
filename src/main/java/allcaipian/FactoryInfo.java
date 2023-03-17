package allcaipian;

import java.util.ArrayList;

public class FactoryInfo {
    public String factoryName;
    public ArrayList<CaipianColors> list = new ArrayList<>();

    public FactoryInfo(String factoryName) {
        this.factoryName = factoryName;
    }

    @Override
    public String toString() {
        return "FactoryInfo{" +
                "factoryName='" + factoryName + '\'' +
                ", list=" + list +
                '}';
    }

    public void addInfo(CaipianColorInfo colorInfo) {
        for (int i = 0; i < list.size(); i++) {
            CaipianColors color2 = list.get(i);
            if (colorInfo.color.equals(color2.colorName)) {
                color2.addInfo(colorInfo);
                return;
            }

        }
        CaipianColors color1 = new CaipianColors(colorInfo.color);
        color1.addInfo(colorInfo);
        list.add(color1);
    }

    public int allNum() {
        int num = 0;
        for (CaipianColors c : list) {
            num += c.num;
        }
        return num;
    }
}
