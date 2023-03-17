package allxiaoshou;

import java.util.ArrayList;

public class KuanInfo {
    public String kuanhao;
    public ArrayList<XiaoshouColors> list = new ArrayList<>();

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

    public void addInfo(XiaoshouColorInfo colorInfo) {
        for (int i = 0; i < list.size(); i++) {
            XiaoshouColors color = list.get(i);

            if (color.colorName.equals(colorInfo.color)) {
                color.addInfo(colorInfo);
                return;
            }
        }
        XiaoshouColors colors = new XiaoshouColors(colorInfo.color);
        colors.addInfo(colorInfo);
        list.add(colors);
    }


}
