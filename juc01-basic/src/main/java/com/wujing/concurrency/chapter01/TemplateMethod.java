package com.wujing.concurrency.chapter01;

/**
 * @author wj
 */
public class TemplateMethod {

    public final void templateMethod() {
        System.out.println("==============================");
        wrapPrint();
        System.out.println("==============================");
    }

    protected void wrapPrint() {
        // 需实现逻辑
    }

    public static void main(String[] args) {
        TemplateMethod templateMethod = new TemplateMethod(){
            @Override
            protected void wrapPrint() {
                System.out.println("template method");
            }
        };
        templateMethod.templateMethod();

    }

}
