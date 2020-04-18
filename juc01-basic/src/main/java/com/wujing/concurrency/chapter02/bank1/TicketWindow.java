package com.wujing.concurrency.chapter02.bank1;

/**
 * @author: wj
 */
public class TicketWindow implements Runnable {
    private final int MAX = 50;

    private static int index = 0;
    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println(Thread.currentThread().getName() + ", 当前的号码是 ： " + index++);
        }
    }
}
