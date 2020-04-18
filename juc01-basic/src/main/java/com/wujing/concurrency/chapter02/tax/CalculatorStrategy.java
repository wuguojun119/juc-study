package com.wujing.concurrency.chapter02.tax;

/**
 * @author: wj
 */
@FunctionalInterface
public interface CalculatorStrategy {
    double calculate(double salary, double bonus);
}
