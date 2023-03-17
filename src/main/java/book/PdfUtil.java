package book;


import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zhangguangze
 * @version v1.0
 * @project: zgz
 * @description: 这里描述类的用处
 * @copyright: © 2018
 * @company:
 * @date 2018/5/16 14:23
 */
public class PdfUtil {

    public static String FILEPATH = "D:\\code\\save\\pdf\\";
    public static final String SAVE_PATH="D:\\code\\save\\book\\";


    /**
     * @param fileName   生成pdf文件
     * @param imagesPath 需要转换的图片路径的数组
     */
    public static void imagesToPdf2(String fileName, String imagesPath) {
        try {
            fileName = FILEPATH + fileName + ".pdf";
            File file = new File(fileName);
            // 第一步：创建一个document对象。
            PdfWriter pdfWriter = new PdfWriter(file);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);

            Document document = new Document(pdfDocument);
            document.setMargins(0, 0, 0, 0);


            // 第四步：在文档中增加图片。
            File files = new File(imagesPath);
            String[] images = files.list();
            sort(images);
            int len = images.length;

            for (int i = 0; i < len; i++) {
                if (images[i].toLowerCase().endsWith(".bmp")
                        || images[i].toLowerCase().endsWith(".jpg")
                        || images[i].toLowerCase().endsWith(".jpeg")
                        || images[i].toLowerCase().endsWith(".gif")
                        || images[i].toLowerCase().endsWith(".png")) {
                    String temp = imagesPath + "/" + images[i];
                    Image img = new Image(ImageDataFactory.create(temp));
                  //  img.setAlignment(Image.ALIGN_CENTER);
                    // 根据图片大小设置页面，一定要先设置页面，再newPage（），否则无效
                  //  document.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
                  //  document.newPage();
                    document.add(img);
                }
            }
            // 第五步：关闭文档。
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    /**
//     * @param fileName   生成pdf文件
//     * @param imagesPath 需要转换的图片路径的数组
//     */
//    public static void imagesToPdf(String fileName, String imagesPath) {
//        try {
//            fileName = FILEPATH + fileName + ".pdf";
//            File file = new File(fileName);
//            // 第一步：创建一个document对象。
//            Document document = new Document();
//            document.setMargins(0, 0, 0, 0);
//            // 第二步：
//            // 创建一个PdfWriter实例，
//            PdfWriter.getInstance(document, new FileOutputStream(file));
//            // 第三步：打开文档。
//            document.open();
//            // 第四步：在文档中增加图片。
//            File files = new File(imagesPath);
//            String[] images = files.list();
//            sort(images);
//            int len = images.length;
//
//            for (int i = 0; i < len; i++) {
//                if (images[i].toLowerCase().endsWith(".bmp")
//                        || images[i].toLowerCase().endsWith(".jpg")
//                        || images[i].toLowerCase().endsWith(".jpeg")
//                        || images[i].toLowerCase().endsWith(".gif")
//                        || images[i].toLowerCase().endsWith(".png")) {
//                    String temp = imagesPath + "/" + images[i];
//                    Image img = Image.getInstance(temp);
//                    img.setAlignment(Image.ALIGN_CENTER);
//                    // 根据图片大小设置页面，一定要先设置页面，再newPage（），否则无效
//                    document.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
//                    document.newPage();
//                    document.add(img);
//                }
//            }
//            // 第五步：关闭文档。
//            document.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private static void sort(String[] images) {
        Arrays.sort(images, new Comparator<String>() {

            public int compare(String o1, String o2) {
                if (o1.charAt(0) > o2.charAt(0)) {
                    return 1;
                } else if (o1.charAt(0) < o2.charAt(0)) {
                    return -1;
                }

                if (o1.charAt(1) > o2.charAt(1)) {
                    return 1;
                } else if (o1.charAt(1) < o2.charAt(1)) {
                    return -1;
                }
                if (o1.charAt(2) > o2.charAt(2)) {
                    return 1;
                } else if (o1.charAt(2) < o2.charAt(2)) {
                    return -1;
                }

                return 0;
            }
        });
    }

    public static void main(String[] args) {
        String name = "mens FUDGE-21-05";
        String imagesPath = "/Users/yangbowen/save/book/mens FUDGE-21-05";
        imagesToPdf2(name, imagesPath);
    }
}