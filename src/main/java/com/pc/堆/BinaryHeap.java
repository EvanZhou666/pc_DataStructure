package com.pc.堆;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * 最小堆：堆中某个节点的值总是不小于其父节点的值
 */
public class BinaryHeap<E extends Comparable<E>> {
    private int currentSize;
    private int capasity;
//    数组中第一个真正存放数据的索引
    private int front = 1;
    private Class<E> classType;
    private E[] data;
    private static final int DEFAULT_CAPASITY = 10;

    public BinaryHeap(){
        this(DEFAULT_CAPASITY);
    }

    public BinaryHeap(int capacity){
//        索引为0的位置空出来
        this.capasity =capacity;
        data = (E[]) new Comparable[capacity+1];
    }

    public BinaryHeap(E[] items){
        data = (E[])new Comparable[items.length+front];
        System.arraycopy(items,0,data,front,items.length);
        System.out.println(Arrays.asList(data));
        currentSize = items.length;
//        这种方法效率不高
        /*
        this(DEFAULT_CAPASITY);
        for (E item : items) {
            add(item);
        }*/
        buildHeap(currentSize/2);
    }

    /**
     * 从第一个非叶子节点执行“下沉”操作
     * @param index
     */
    private void buildHeap(int index){
        while (index!=0){
            siftDown(index);
            index--;
        }
    }

//    交换a，b元素
    private void swap(int a,int b){
        E temp = data[b];
        data[b] = data[a];
        data[a] = temp;
    }

    public void add(E e){
        if (currentSize+1>capasity){
            enlarge(capasity*2+front);
        }
        data[++currentSize] = e;
        siftUp(currentSize,e);
    }

    /**
     * 上浮操作，沿着根路径一直与父节点比较，如果小于父节点，则交换位置
     * @param currentSize
     * @param e
     */
    private void siftUp(int currentSize, E e) {
        int parent = currentSize/2;
        while (parent!=0){
            if (e.compareTo((E) data[parent])<0){
                swap(currentSize,parent);
                parent/=2;
            } else {
                break;
            }
        }
    }


    public  E findMin(){
        return data[front];
    }

//    将最后一个元素赋给根节点，根节点中的元素和左右孩子节点中最小的一个比较，如果比最小的大，则它们交换位置后继续与左右孩子比较，以此类推一直下沉...
    public E deleteMin(){
        E min = data[front];
        data[front] = data[currentSize];
        siftDown(front);
        data[currentSize] = null;
        currentSize--;
        return min;
    }

    private int siftDown(int front) {
//        data[front] = data[currentSize];
        int left = 2 * front <= currentSize ? 2 * front:-1;
        int right = 2 * front+1 <= currentSize ? 2 * front+1:-1;
        if (left ==-1){
            return front;
        }
        int minIndex = min(left,right);//返回左右孩子较小的节点索引
        if (data[front].compareTo(data[minIndex])>0){
            swap(front,minIndex);
            siftDown(minIndex);
        }
        return front;
    }

    private int min(int left, int right) {
        if (left==-1&&right==-1){
            throw new IllegalArgumentException("比较的索引不能都为空");
        }

        if (left==-1&&right!=-1){
            throw new IllegalArgumentException("左孩子节点不存在，右孩子存在的情况不合法");
        }
        if (left!=-1&&right!=-1){
            return data[left].compareTo(data[right])<0?left:right;
        } else {
//            right ==null
            return left;
        }
    }

    //    层序遍历二叉对
    public void levelOrder(){
        Queue queue = new LinkedList();
        queue.offer(data[front]);
        int cur = front;
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
            if (2*cur<=currentSize&&data[2*cur]!=null){
                queue.offer(data[2*cur]);
            }
            if (2*cur+1<=currentSize&&data[2*cur+1]!=null){
                queue.offer(data[2*cur+1]);
            }
            cur++;
        }
    }

    public boolean isEmpty(){
        return currentSize==0;
    }

//    清空二叉堆
    public void makeEmpty(){
        for (int i=0;i<=currentSize;i++){
            data[i]=null;
        }
        currentSize=0;
        capasity=DEFAULT_CAPASITY;
    }

    private void enlarge(int i) {
        E[] newData = (E[])new Comparable[i];
        System.arraycopy(data,0,newData,0,data.length);
        capasity = i-1;
        data=newData;
    }

    public void testHasError(){
        int end = currentSize/2;
        for (int i =front;i<=end;i++){

            if (2*i<=currentSize&&data[i].compareTo(data[2*i])>0){
                throw new IllegalArgumentException(String.format("最小堆结构不正确,%s,%s",data[i],data[2*i]));
            }
            if (2*i+1<=currentSize&&data[i].compareTo(data[2*i+1])>0){
                throw new IllegalArgumentException(String.format("最小堆结构不正确,%s,%s",data[i],data[2*i+1]));
            }
        }
    }

    public static Integer[] testHeapfy(int nums ){
        Integer[] rands = new Integer[nums];
        for(int i=0;i<nums;i++){
            int rand = new Random().nextInt(1000)+1;
            rands[i]=rand;
        }
        return rands;
    }

    public static void main(String[] args) {

     /*   BinaryHeap<Integer> eBinaryHeap = new BinaryHeap<>();
        Integer[] rands = testHeapfy(10);
        System.out.println(Arrays.toString(rands));
        for (Integer rand : rands) {
            eBinaryHeap.add(rand);
        }
//           eBinaryHeap.testHasError();
         eBinaryHeap.levelOrder();
        for (int i =0;i<10;i++){
            System.out.println();
            eBinaryHeap.deleteMin();
            eBinaryHeap.levelOrder();
            eBinaryHeap.testHasError();
        }*/
     /*  Integer[] nums = new Integer[]{17,43,33,94,56,76,48,71};
        BinaryHeap<Integer> eBinaryHeap = new BinaryHeap<>(nums);
        eBinaryHeap.levelOrder();*/
//        new Integer[]{88, 400, 160, 153, 485}
//        测试构建堆的算法
        BinaryHeap<Integer> eBinaryHeap = new BinaryHeap<>(testHeapfy(100));
        eBinaryHeap.levelOrder();
        eBinaryHeap.testHasError();
    }
}
