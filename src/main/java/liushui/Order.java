package liushui;

import java.util.HashMap;
import java.util.Map;

/**
 * 单据
 */
public class Order {
    //单号，唯一值
    public String voucherNum;
    //客户姓名
    public String name;
    //开单时间
    public String time;
    //货品列表
    public Map<String, Article> articles = new HashMap<>();

    public Order(OldExcelObj oldExcelObj) {
        voucherNum = oldExcelObj.voucherNum;
        name = oldExcelObj.name;
        time = oldExcelObj.time;
        addOldExcelObj(oldExcelObj);

    }

    public void addOldExcelObj(OldExcelObj oldExcelObj) {
        if (!articles.containsKey(oldExcelObj.artNum)) {
            Article article = new Article(oldExcelObj);
            //article.addOldExcelObj(oldExcelObj);
            articles.put(oldExcelObj.artNum, article);
        } else {
            articles.get(oldExcelObj.artNum).addOldExcelObj(oldExcelObj);
        }
    }

    public int allMoney() {
        int money = 0;
        for (Article article : articles.values()) {
            for (ColorInfo colorInfo : article.colorInfos.values()) {
                money += colorInfo.price * colorInfo.mNum;
                money += colorInfo.price * colorInfo.lNmu;
                money += colorInfo.price * colorInfo.xlNum;
                money += colorInfo.price * colorInfo.xl2Num;
            }
        }
        return money;
    }

    public int allNum() {
        int num = 0;
        for (Article article : articles.values()) {
            for (ColorInfo colorInfo : article.colorInfos.values()) {
                num += colorInfo.mNum;
                num += colorInfo.lNmu;
                num += colorInfo.xlNum;
                num += colorInfo.xl2Num;
            }
        }
        return num;
    }

    @Override
    public String toString() {
        return "Order{" +
                "voucherNum='" + voucherNum + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", articles=" + articles +
                '}';
    }
}
