package com.wujing.concurrency.chapter02.tax;

/**
 * 策略模式
 * @author: wj
 */
public class TaxCalculatorMain {
    public static void main(String[] args) {
//        TaxCalculator taxCalculator = new TaxCalculator(10000d,2000d){
//			@Override
//			protected double calcTax() {
//				return getSalary()*0.1 + getBonus()*0.15;
//			}
//		};
//		System.out.println(taxCalculator.calculate());


        TaxCalculator taxCalculator = new TaxCalculator(10000d, 2000d);
        CalculatorStrategy calculatorStrategy = new SimpleCalculatorStrategy();
        taxCalculator.setCalculatorStrategy(calculatorStrategy);
        double tax = taxCalculator.calculate();
        System.out.println(tax);


        taxCalculator.setCalculatorStrategy((s, b) -> s * 0.1 + b * 0.15);
        tax = taxCalculator.calculate();
        System.out.println(tax);
    }
}
