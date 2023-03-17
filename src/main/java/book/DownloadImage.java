package book;

import com.google.gson.Gson;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class DownloadImage {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        downloadLately();
        //  System.out.println(json(req(book.Apis.query("mens fudge")), book.Clazz1.class));
    }



    /**下载最近的杂志*/
    private static void downloadLately() throws Exception {
        LatelyBook latelyBook = json(req(Apis.latelyBook()), LatelyBook.class);
        latelyBook.getMagazine().forEach(magazineDTO -> {
            try {
                Clazz2 clazz2 = json(req(Apis.getDetail(magazineDTO.getMagId())), Clazz2.class);
                final String[] name = {null};
                clazz2.getContent().forEach(contentDTO -> {
                    String magPic = contentDTO.getMagPic();
                    String[] split = magPic.split("/");
                    try {
                        if (name[0] == null) {
                            name[0] = split[split.length - 2];
                        }
                        download(magPic, split[split.length - 1], PdfUtil.SAVE_PATH + split[split.length - 2]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                //下载完一期后，整合为pdf
                PdfUtil.imagesToPdf2(name[0], PdfUtil.SAVE_PATH + name[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**根据杂志名下载杂志*/
    private static void downloadByName() throws Exception {
        //模糊查询
        Clazz1 clazz1 = json(req(Apis.query("smart", 1, 24)), Clazz1.class);
        clazz1.getMagazine().forEach(magazineDTO -> {
            try {
                //某期杂志详情
                Clazz2 clazz2 = json(req(Apis.getDetail(magazineDTO.getMagId())), Clazz2.class);
                final String[] name = {null};
                clazz2.getContent().forEach(contentDTO -> {
                    //每张图的地址
                    String magPic = contentDTO.getMagPic();
                    String[] split = magPic.split("/");
                    try {
                        if (name[0] == null) {
                            name[0] = split[split.length - 2];
                        }
                        download(magPic, split[split.length - 1], PdfUtil.SAVE_PATH+ name[0]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                //下载完一期后，整合为pdf
                PdfUtil.imagesToPdf2(name[0], PdfUtil.SAVE_PATH + name[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void reqPicture() throws Exception {
        int year = 20;
        for (int m = 1; m <= 10; m++) {
            String name = "mens FUDGE";
            int flag = 0;
            int month = m;
            String monthStr = month >= 10 ? String.valueOf(month) : "0" + month;
            for (int i = 1; i < 300; i++) {
                String index = i >= 100 ? String.valueOf(i) : (i >= 10 ? "0" + i : "00" + i);
                boolean result = download("https://img2020.zazhimi.net//aazzmpic//" + year + monthStr + "//" + name + "-" + year + "-" + monthStr + "//" + index + ".jpg", name + "-" + year + monthStr + "-" + index + ".jpg", "/Users/yangbowen/save/" + name + "_" + year + "_" + monthStr + "/");
                if (!result) {
                    break;
                }
                flag++;
            }
            if (flag < 5) {
                name = name.toLowerCase();
                for (int i = 1; i < 300; i++) {
                    String index = i >= 100 ? String.valueOf(i) : (i >= 10 ? "0" + i : "00" + i);
                    boolean result = download("https://img2020.zazhimi.net//aazzmpic//" + year + monthStr + "//" + name + "-" + year + "-" + monthStr + "//" + index + ".jpg", name + "-" + year + monthStr + "-" + index + ".jpg", "/Users/yangbowen/save/" + name + "_" + year + "_" + monthStr + "/");
                    if (!result) {
                        break;
                    }
                }
            }
        }
        //download("http://avatar.csdn.net/1/3/B/1_li1325169021.jpg", "1_li1325169021.jpg", "d:\\image\\");
        System.out.println("结束虚拟机");
    }

    public static boolean download(String urlString, String filename, String savePath) throws Exception {
        System.out.println("开始下载:    " + urlString);
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        //设置请求超时为5s
        con.setConnectTimeout(5 * 1000);
        // 输入流
        InputStream is = con.getInputStream();

        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File sf = new File(savePath);
        if (!sf.exists()) {
            sf.mkdirs();
        }
        OutputStream os = new FileOutputStream(sf.getPath() + "/" + filename);
        int isEmpty = 0;
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
            if (isEmpty <= 1) {
                isEmpty++;
            }
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();

        if (isEmpty < 2) {
            new File(sf.getPath() + "/" + filename).deleteOnExit();
            return false;
        }
        return true;

    }


    public static String req(String urlString) throws Exception {
        System.out.println("开始请求:    " + urlString);
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        //设置请求超时为5s
        con.setConnectTimeout(5 * 1000);
        // 输入流
        InputStream is = con.getInputStream();

        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;


        ByteArrayOutputStream os = new ByteArrayOutputStream();

        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        String content = os.toString();
        //  System.out.println("响应内容： " + content);
        // 完毕，关闭所有链接
        os.close();
        is.close();

        return content;
    }

    public static <T> T json(String str, Class<T> tClass) {
        Gson gson = new Gson();
        return gson.fromJson(str, tClass);
    }

}