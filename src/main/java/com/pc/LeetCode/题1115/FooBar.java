package com.pc.LeetCode.题1115;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FooBar {

    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    ReentrantLock lock = new ReentrantLock();
    private boolean enableFoo = true;
    private boolean enableBar = false;
    Condition fooCondition = lock.newCondition();
    Condition barCondition = lock.newCondition();

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            lock.lock();
            while (!enableFoo) {
                fooCondition.await();
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            enableBar = true;
            enableFoo = false;
            barCondition.signal();
            lock.unlock();
        }

    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            lock.lock();
            while (!enableBar) {
                barCondition.await();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            enableBar = false;
            enableFoo = true;
            fooCondition.signal();
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        FooBar fooBar2 = new FooBar(5);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fooBar2.bar(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("bar");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fooBar2.foo(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("foo");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
