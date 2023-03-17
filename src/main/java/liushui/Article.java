package liushui;

import java.util.HashMap;
import java.util.Map;

public class Article {
    //货号
    public String artNmu;
    //货品尺码
    public Map<String, ColorInfo> colorInfos = new HashMap<>();


    public Article(OldExcelObj oldExcelObj) {
        artNmu = oldExcelObj.artNum;
        addOldExcelObj(oldExcelObj);
    }

    public void addOldExcelObj(OldExcelObj oldExcelObj) {
        if (!colorInfos.containsKey(oldExcelObj.color)) {
            //没有颜色信息，先创建颜色
            ColorInfo colorInfo = new ColorInfo(oldExcelObj);
            colorInfos.put(colorInfo.color, colorInfo);
        } else {
            //更新颜色
            colorInfos.get(oldExcelObj.color).addOldExcelObj(oldExcelObj);
        }
    }

    public int allNum() {
        int num = 0;

        for (ColorInfo colorInfo : colorInfos.values()) {
            num += colorInfo.mNum;
            num += colorInfo.lNmu;
            num += colorInfo.xlNum;
            num += colorInfo.xl2Num;
        }

        return num;
    }

    public int allMoney() {
        int money = 0;
            for (ColorInfo colorInfo : colorInfos.values()) {
                money += colorInfo.price * colorInfo.mNum;
                money += colorInfo.price * colorInfo.lNmu;
                money += colorInfo.price * colorInfo.xlNum;
                money += colorInfo.price * colorInfo.xl2Num;
            }
        return money;
    }

    @Override
    public String toString() {
        return "Article{" +
                "artNmu='" + artNmu + '\'' +
                ", colorInfos=" + colorInfos +
                '}';
    }
}
