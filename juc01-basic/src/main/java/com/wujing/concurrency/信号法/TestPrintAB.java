package com.wujing.concurrency.信号法;

/**
 * 生产者消费者模式 -> 信号法
 *
 * 需要 : 轮流打印 AB 内容, 生产者, 消费者, 信号资源
 * @author: wj
 */
public class TestPrintAB {
    public static void main(String[] args) {
        Product product = new Product();
        Producer producer = new Producer(product);
        Consumer consumer = new Consumer(product);
        producer.start();
        consumer.start();
    }
}

/**
 * 产品, 信号资源类
 */
class Product {
    // 是否有产品信号标记
    private boolean flag = false;

    /*
    生产, 打印字母A
     */
    public synchronized void produce() {
        // 1. 如果有产品
        while (flag) {
            // 1.1 等待消费者消费
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 2. 如果没有产品
        // 2.1 生产者生产产品
        System.out.println("---生产者生产: AAAA---");
        this.flag = true;
        // 1.2 通知消费者消费
        this.notify();
    }

    /*
    消费, 打印字母B
     */
    public synchronized void consume() {
        // 1. 如果没有有产品
        while (!flag) {
            // 1.1 等待生产者生产
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 2. 如果有产品
        // 2.1 消费者消费产品
        System.out.println("===消费者消费: BBBB===");
        this.flag = false;
        // 1.2 通知生产者生产
        this.notifyAll();
    }
}

/**
 * 生产者
 */
class Producer extends Thread {
    Product product;

    public Producer(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            product.produce();
        }
    }
}

/**
 * 消费者
 */
class Consumer extends Thread {
    Product product;

    public Consumer(Product product) {
        this.product = product;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            product.consume();
        }
    }
}

