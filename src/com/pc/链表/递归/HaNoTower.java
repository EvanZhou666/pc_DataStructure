package com.pc.链表.递归;

/**
 * @Description
 * 汉诺塔实现
 * 问题描述：汉诺塔（又称河内塔）问题是源于印度一个古老传说的益智玩具。大梵天创造世界的时候做了三根金刚石柱子，
 * 在一根柱子上从下往上按照大小顺序摞着64片黄金圆盘。大梵天命令婆罗门把圆盘从下面开始按大小顺序重新摆放在另
 * 一根柱子上。并且规定，在小圆盘上不能放大圆盘，在三根柱子之间一次只能移动一个圆盘，求移动的次数及顺序。
 * 逻辑抽象过程：当圆盘的数量为3时，需要移动的次数是7，当圆盘数量为4时，因为小圆盘上不能放大圆盘，
 * 因此必然存在只有将最上面的三个盘子移动到中间柱子上，再将最后一个盘子移动到目标柱上，
 * 最后将三个盘子从中间柱子移动到目标柱上，在这个过程中，又可以将上一过程的中间柱看做源柱，上一个过程的源柱看为中间柱。
 *很明显，搬动n个盘子的次数等于搬动前n-1个盘子的次数+1+搬动前n-1个盘子的次数。因此：设f（n）表示搬动n个柱子的次数则：
 *f（n）= 2f（n-1）+1 且又根据f（3）=7 ，f（2）=3，f（1）=1可知 f（1），f（2）,f(3)都满足f（n）=2f(n-1)+1 ==》
 * 有如下式子：
 *  f(n)= 2f（n-1）+1
 *  f(n-1)= 2f(n-2)+1
 *  f(n-2)= 2f(n-3)+1
 *  ....
 *  f(2)=2f(1)+1
 *  f(1)=1
 *
 * 将上述式子变形得：
 *  f(n)+1= 2(f(n-1)+1)
 *  f(n-1)+1= 2(f(n-2)+1)
 *  .......
 *  f(2)+1=2(f(1)+1)
 *  f(1)+1=1+1=2
 *  将上述式子左右相乘得消项得：
 * f(n) +1 = 2^n  ---> f(n)=2^n-1;
 *
 * @Author EvanZhou
 * @Date 2019/3/28 12:30
 **/
public class HaNoTower {
    public static void process(int n,String source,String temp,String target) {
        if (n==1){
            System.out.println(String.format("将%s从盘%s移动(中间盘%s)到盘%s",1,source,temp,target));
        } else {
//            todo：移动前n-1个盘子到中间柱子上
            process(n-1,source,target,temp);
//            将盘子N从source柱移动到目标柱
            move(n,source,temp,target);
//            todo：最后将n-1个盘子从中间柱子移动到目标柱
            process(n-1,temp,source,target);
        }



    }

    private static void move(int n,String source,String temp,String target) {
        System.out.println(String.format("将%s从盘%s移动(中间盘%s)到盘%s",n,"A","B","C"));
    }
    public static void main(String[] args) {
//        process(1,"A","B","C");
//        process(2,"A","B","C");
        process(3,"A","B","C");
    }
}
