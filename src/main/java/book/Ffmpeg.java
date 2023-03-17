package book;

import java.io.File;

public class Ffmpeg {
    /*
     * 压缩视频
     * @param convertFile  待转换的文件
     * @param targetFile  转换后的目标文件
     */
    private static  void toCompressFile(String convertFile,String targetFile){
        try{
            Runtime runtime = Runtime.getRuntime();
            /**将视频压缩为 每秒15帧 平均码率600k 画面的宽与高 为1280*720*/
            String cutCmd="ffmpeg -i " + convertFile + " -r 15 -b:v 600k  -s 1280x720 "+ targetFile;
            System.out.println("cutCmd:"+cutCmd);
            runtime.exec(cutCmd);
            System.out.println("文件："+convertFile+" 正在转换中。。。");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("压缩文件出现异常："+e.getMessage());
        }
    }

    /**加水印*/
    private static  void jiashuiyin(String convertFile,String targetFile,String shuiyin){
        try{
            Runtime runtime = Runtime.getRuntime();
            /**将视频压缩为 每秒15帧 平均码率600k 画面的宽与高 为1280*720*/
            String cutCmd="ffmpeg -i " + convertFile + " -r 15 -b:v 600k  -s 1280x720 "+ targetFile;
            System.out.println("cutCmd:"+cutCmd);
            runtime.exec(cutCmd);
            System.out.println("文件："+convertFile+" 正在转换中。。。");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("压缩文件出现异常："+e.getMessage());
        }
    }



    public static void main(String[] ars ){
        long startTime = System.currentTimeMillis();
        //目标文件夹地址
         String convertFile="C:\\Users\\issuser\\Desktop\\a\\";
         //处理完存放地址
        String resultFile="";
        //目标根目录
        File rootFile=new File(convertFile);
        //款号文件列表
        File[] numList=rootFile.listFiles();
        for(File num:numList){
            //款号文件夹名
            String numName = num.getName();
            File[] files = num.listFiles();
            //遍历视频
            for(int i=0;i<files.length;i++){
                //加水印，存储到新的根目录
                jiashuiyin(files[i].getAbsolutePath(),resultFile+File.separator+numName+File.separator+files[i].getName(),numName);
            }

//            if(ff.toPath().toString().toLowerCase().endsWith(".mp4")){
//                String f1=convertFile+ff.getName();
//                String f2=convertFile+"压缩后_"+ff.getName();
//                toCompressFile(f1,f2);
//            }
        }
        System.out.println("耗时："+(System.currentTimeMillis()-startTime));
    }
}
