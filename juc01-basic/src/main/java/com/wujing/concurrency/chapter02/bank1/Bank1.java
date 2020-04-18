package com.wujing.concurrency.chapter02.bank1;

/**
 * @author: wj
 */
public class Bank1 {
    public static void main(String[] args) {

        final Runnable target = new TicketWindow();
        Thread thread1 = new Thread(target, "一号柜台");
        Thread thread2 = new Thread(target, "二号柜台");
        Thread thread3 = new Thread(target, "三号柜台");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
