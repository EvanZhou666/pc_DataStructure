package com.pc;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description
 * @Author zhouzixiang
 * @Date 2019/4/4 12:13
 **/
public class RegTest {

//    正常捕获0xx分组
    public static void test() {
        String test = "020-85653333";
        String reg = "(0\\d{2})-(\\d{8})";
        Pattern pattern = Pattern.compile(reg);
        Matcher mc = pattern.matcher(test);
        if (mc.find()) {
            System.out.println("分组的个数有：" + mc.groupCount());
            for (int i = 0; i <= mc.groupCount(); i++) {
                System.out.println("第" + i + "个分组为：" + mc.group(i));
            }
        }
    }

//    不捕获0xx分组
    public static void test2(){
        System.out.println("==========分界线=====");
        String test = "020-85653333";
        String reg = "(?:0\\d{2})-(\\d{8})";
        Pattern pattern = Pattern.compile(reg);
        Matcher mc = pattern.matcher(test);
        if (mc.find()) {
            System.out.println("=======分组的个数有：=====" + mc.groupCount());
            for (int i = 0; i <= mc.groupCount(); i++) {
                System.out.println("第" + i + "个分组为：" + mc.group(i));
            }
        }
    }

    public static void test3(String str){
        String reg = "(\\[img\\])(https??://.*?)(\\[/img\\])";
        if (str==null)
         str = "平时到餐厅吃饭的时候，人们的目光大多被美食吸收，可曾留意过伺候你们的碗、碟、羹甚至餐巾？估计真正在意这些的还是归入少数吧。不过今天它们也要在我们面前露一手咯，让你知道原来餐巾也能七十二变的！\n" +
                 "\n" +
                 "[url]aaa://www.pclady.com.cn/home/jzjj/0505/40694.html[/url]\n" +
                 "\n" +
                 "\n" +
                 "[img]aaa://img.pconline.com.cn/images/images/upload/bbs/2005/5/12/1/3/207/139/65/4/111587853952446.jpg[/img]\n" +
                 "\n" +
                 "\n" +
                 "\n" +
                 "[img]https://img.pconline.com.cn/images/images/upload/bbs/2005/5/12/1/3/207/140/17/59/1115878592827951.jpg[/img]\n" +
                 "\n" +
                 "\n" +
                 "\n" +
                 "[img]http://img.pconline.com.cn/images/images/upload/bbs/2005/5/12/1/3/207/140/157/71/1115878628679496.jpg[/img]\n" +
                 "\n" +
                 "\n" +
                 "\n" +
                 "[img]http://img.pconline.com.cn/images/images/upload/bbs/2005/5/12/1/3/207/141/66/187/1115878671035140.jpg[/img]\n";
        Pattern pattern = Pattern.compile(reg);
        Matcher mc = pattern.matcher(str);
        while (mc.find()) {
            System.out.println("=======分组的个数有：=====" + mc.groupCount());
            for (int i = 0; i <= mc.groupCount(); i++) {
                System.out.println("第" + i + "个分组为：" + mc.group(i));
            }
        }
    }
    public static void main(String[] args) {
        test3(null);

        List<String> imgs = new LinkedList<String>();
        System.out.println(imgs.size());
        System.out.println(1104183);

    }
}
