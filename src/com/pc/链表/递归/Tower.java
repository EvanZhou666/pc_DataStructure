package com.pc.链表.递归;

/**
 * @author zhouzixiang 递归思想解决河内塔问题
 */
public class Tower {
	public static int count=0;
	
	public static void move(int dish,String from,String temp,String to,int dept){
	    if(dish == 1){
	        System.out.println("将盘子"+dish+"从塔座"+from+"移动到目标塔座"+to);
	        count++;
	    }else{
	    	for(int i =0;i<dept;i++) {
				System.out.print("--");
			}
			System.out.println("Call move ("+(dish-1)+")");
	        move(dish-1,from,to,temp,dept+1);//A为初始塔座，B为目标塔座，C为中介塔座
	        System.out.println("将盘子"+dish+"从塔座"+from+"移动到目标塔座"+to);
	        count++;
	        for(int i =0;i<dept;i++) {
				System.out.print("--");
			}
			System.out.println("Call move ("+(dish-1)+")");
	        move(dish-1,temp,from,to,dept+1);//B为初始塔座，C为目标塔座，A为中介塔座
	        for(int i =0;i<dept;i++) {
				System.out.print("--");
			}
	        System.out.println("Return");
	    }
	}
	
	public static void main(String[] args) {
//		move(3, "A","B","C",0);
		move(4,"A","B","C",0);
		System.out.println(count);
	}
}
