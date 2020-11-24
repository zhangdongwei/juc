package com.spursgdp.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangdongwei
 * @create 2020-11-24-16:54
 */
public class SaleTicketDemo01 {

    public static void main(String[] args) {

        Ticket ticket = new Ticket();

        //3个线程卖票
        new Thread(()->{
            for (int i = 1; i <= 40 ; i++) {
                try {
                    ticket.sale();
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 1; i <= 40 ; i++) {
                try {
                    ticket.sale();
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 1; i <= 40 ; i++) {
                try {
                    ticket.sale();
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }


}

/**
 * 线程资源类
 * 使用Synchonized关键字
 */
class Ticket{

    private Integer number = 30;

    public synchronized void sale() {
        if(number > 0) {
            number--;
            System.out.println("线程"+Thread.currentThread().getName()+"卖出了票，剩余票数："+number);
        }
    }

}
