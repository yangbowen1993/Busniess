package book;

public class Apis {

    /**
     * 获取杂志详情
     */
    public static String getDetail(String magId) {
        String base = "https://app2020.zazhimi.net/api/show.php?a=";
        return base + magId;
    }


    public static String query(String str, int page, int length) {
        return "https://app2020.zazhimi.net/api/search.php?k=" + str + "&p=" + page + "&s=" + length;
    }

    public static String query(String str) {
        return query(str, 1, 36);
    }

    public static String latelyBook() {
        return "https://app2020.zazhimi.net/api/lists.php?c=9&p=1&s=20";
    }

    //时尚男士页面  https://app2020.zazhimi.net/api/lists.php?c=9&p=1&s=36
    // 6是女装，8是美妆，9是男装，10是娱乐明星

}
