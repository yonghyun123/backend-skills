package com.company.ex02;

public class AnonymousTest {

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(" inner thread ");
            }
        });

        t.run();

        // 의미없는 코드
        new Runnable() {
            @Override
            public void run() {
                System.out.println("outer thread");
            }
        };
    }
}
