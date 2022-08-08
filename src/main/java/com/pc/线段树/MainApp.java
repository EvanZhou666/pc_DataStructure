package com.pc.线段树;

import java.util.Arrays;
import java.util.Random;

/**
 * @Description
 * @Author EvanZhou
 * @Date 2019/4/1 17:00
 **/
public class MainApp {
    public static Integer[] rands(int num,int bound){
        Integer[] rands = new Integer[num];
        for (int i = 0; i <num; i++) {
            int rand = new Random().nextInt(bound)+1;
            rands[i] = rand;
        }
        return rands;
    }
    public static void main(String[] args) {
//        Integer[] rands = rands(5, 10);
        Integer[] rands = new Integer[]{2, 3, 8, 1, 10,6};
        System.out.println("arrays:"+Arrays.toString(rands));
        SegmentTree<Integer> segmentTree = new SegmentTree<>(rands, new Merger() {
            public Object merge(Object a, Object b) {
                return (Integer)a+ (Integer)b;
            }
        });
        System.out.println(segmentTree.toString());
        segmentTree.levelOrder();
        System.out.println( segmentTree.query(2,3));
        segmentTree.set(2,5);
        System.out.println( segmentTree.query(2,3));
        System.out.println( segmentTree.query(1,4));
    }
}
