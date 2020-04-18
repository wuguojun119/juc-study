package com.wujing.concurrency.管程法;

/**
 * 生产者、消费者模型 -> 利用缓冲区解决: 管程法
 * <p>
 * 需要: 生产者, 消费者, 产品, 缓冲区
 *
 * @author: wj
 */
public class TestPC {
    public static void main(String[] args) {
        // 容器
        SynContainer container = new SynContainer();
        // 生产者
        new Thread(new Producer(container)).start();
        // 消费者
        new Thread(new Consumer(container)).start();
    }

}

/**
 * 生成者
 */
class Producer implements Runnable {
    SynContainer container;

    public Producer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            container.push(new Chicken(i));
            System.out.println(Thread.currentThread().getName() + " 生产了第 " + (i+1) + " 只鸡");
        }
    }
}

/**
 * 消费者
 */
class Consumer implements Runnable {
    SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        Chicken chicken;
        while (true) {
            chicken = container.pop();
            System.out.println("------" + Thread.currentThread().getName() + " 消费了第 " + (chicken.getId()+1) + " 只鸡");
        }
    }
}

// 产品, 鸡肉
class Chicken {
    private int id;

    public Chicken(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

// 缓冲区
class SynContainer {
    // 需要一个容器
    Chicken[] chickens = new Chicken[1];

    // 容器计数器
    private int count = 0;

    // 生产者生产产品
    public synchronized void push(Chicken chicken) {

        // 1. 如果容器满了
        while (count == chickens.length) {
            try {
                // 1.1 需要等待消费者消费
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 2. 如果容器没满
        // 2.1 则生产者生产产品
        chickens[count] = chicken;
        count++;
        // 2.2 通知消费者消费
        this.notifyAll();
    }


    // 消费者消费产品
    public synchronized Chicken pop() {
        // 1. 如果容器空了
        while (count == 0) {
            try {
                // 1.1 需要等待生产者生产
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 2. 如果容器没空

        // 2.1 则消费者消费产品

        count--;
        Chicken chicken = chickens[count];
        // 2.2 通知生产者生产
        this.notifyAll();
        return chicken;
    }
}

















