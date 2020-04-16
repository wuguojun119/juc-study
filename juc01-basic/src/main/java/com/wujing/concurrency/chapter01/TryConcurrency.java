package com.wujing.concurrency.chapter01;

/**
 * @author wj
 */
public class TryConcurrency {

    public static void main(String[] args) {
        // 单线程执行
//        readFromDatabase();
//        writeDataToFile();

        // 多线程执行
        new Thread(() -> {
            readFromDatabase();
        }, "read-thread").start();

        new Thread(() -> {
            writeDataToFile();
        }, "write-thread").start();
    }

    private static void readFromDatabase() {
        // read data from database and handle it.
        try {
            println("Begin read data from db.");
            Thread.sleep(1000 * 10L);
            println("Read data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("The data handle finish and successfully.");
    }

    private static void writeDataToFile() {
        try {
            println("Begin write data to file.");
            Thread.sleep(2000 * 10L);
            println("Write data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void println(String message) {
        System.out.println(message);
    }
}
