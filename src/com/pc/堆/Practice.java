package com.pc.堆;

import java.util.*;

/**
 * @Description
 * 给定一组数，找出第k大的数
 * @Author EvanZhou
 * @Date 2019/4/1 10:57
 **/
public class Practice {


    public static void main(String[] args) {
        Integer[] integers = BinaryHeap.testHeapfy(500);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new SortCompator());
        for (Integer integer : integers) {
            priorityQueue.add(integer);
        }
     /*   Iterator<Integer> iterator = priorityQueue.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }*/

        Integer kvalue =null;
        for (int i=0;i<3;i++){
            Integer poll = priorityQueue.poll();
            kvalue = poll;
        }
        System.out.println(Arrays.toString(integers)+"中第三大的元素是:"+kvalue);
    }

    private static class SortCompator implements Comparator<Integer>{

        @Override
        public int compare(Integer a, Integer b) {
            return ~(a.compareTo(b))+1;
        }
    }

}
