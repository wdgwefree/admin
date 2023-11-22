package com.wdg.Test;

/**
 * @description:
 * @author: wdg
 * @create: 2023-11-21 18:07
 */
public class MyTest {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new A("11111111111"),"线程1");
        thread1.start();
        Thread thread2= new Thread(new A("2222222222222"),"线程2");
        thread2.start();
    }
}
