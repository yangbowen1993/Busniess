package allcaipian;

import chongrong.WriteExcel;
import factory.Write;
import liushui.OutputOnedayLiushui;
import num.Result;

import java.io.IOException;

public class Start {
    public static void main(String[] args) throws IOException {
        //各厂送货情况();
        //剩余库存_按颜色分类();
        每日流水();
        //  WriteExcel.extracted();
    }

    public static void 每日流水() throws IOException {
        OutputOnedayLiushui.writeExcel();
    }

    public static void 剩余库存_按颜色分类() throws IOException {
        ColorKucunResult.wrtie();
    }

    public static void 不分颜色库存() throws IOException {
        Result.write();
    }


    public static void 各厂送货情况() throws IOException {
        Write.write();
    }
}
