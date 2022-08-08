package com.pc.平衡二叉树;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Test AVL
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("./pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());
            long startTime = System.nanoTime();

            AVL<String, Integer> avl = new AVL<>();
            for (String word : words) {
                if (avl.contains(word))
                    avl.add(word, avl.get(word) + 1);
                else
                    avl.add(word, 1);
            }

            for (String word : words)
                avl.contains(word);

            long endTime = System.nanoTime();

            double  time = (endTime - startTime) / 1000000000.0;
            System.out.println("AVL: " + time + " s");
            System.out.println(avl.isAVLTree());
            System.out.println("Total different words: " + avl.getSize());
            System.out.println("Frequency of PRIDE: " + avl.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + avl.get("prejudice"));
            int i =0;
            for(String word: words){
                i++;
                avl.remove(word);
                if(!avl.isAVLTree()){
                    System.out.println("i:"+i);
                    throw new RuntimeException();
                }
            }
        }

    }
}
