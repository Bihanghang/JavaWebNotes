package com.bihang.seaya.own;

/**
 * Created By bihang
 * 2018/12/26 9:08
 */
public class RuntimeTest {

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println("hello");
        }).start();
    }

}
