package com.spursgdp.pc;

/**
 * @author zhangdongwei
 * @create 2020-11-24-17:54
 */
public class PC {

    public static void main(String[] args) {
        Data data = new Data();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 30 ; i++) {
                    data.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(() -> {
            try {
                for (int i = 1; i <= 30; i++) {
                    data.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
        new Thread(() -> {
            try {
                for (int i = 1; i <= 30 ; i++) {
                    data.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();
        new Thread(() -> {
            try {
                for (int i = 1; i <= 30; i++) {
                    data.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"D").start();
    }
}

/**
 * 线程资源类
 * 判断等待、业务、通知
 */
class Data {

    private Integer number = 0;

    public synchronized void increment() throws InterruptedException {
//        if(number != 0) {  //用if存在虚假唤醒
        while (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
//        if(number == 0) {  //用if存在虚假唤醒
        while(number == 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        this.notifyAll();
    }

}
