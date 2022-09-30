package com.pc.LeetCode.题1115;

public class FooBar2 {

    private int n;

    private Object lock = new Object();
    private boolean enableFoo = true;
    private boolean enableBar = false;

    public FooBar2(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        // printFoo.run() outputs "foo". Do not change or remove this line.
        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (!enableFoo) {
                    lock.wait();
                }
                printFoo.run();
                enableFoo = false;
                enableBar = true;
                lock.notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        synchronized (lock) {
            for (int i = 0; i < n; i++) {
                // printBar.run() outputs "bar". Do not change or remove this line.
                while (!enableBar) {
                    lock.wait();
                }
                printBar.run();
                enableFoo = true;
                enableBar = false;
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        FooBar2 fooBar2 = new FooBar2(5);

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
