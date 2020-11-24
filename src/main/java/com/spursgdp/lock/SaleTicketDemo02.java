package com.spursgdp.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangdongwei
 * @create 2020-11-24-16:54
 */
public class SaleTicketDemo02 {

    public static void main(String[] args) {

        Ticket2 ticket = new Ticket2();

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
 * 使用ReentrantLock三部曲：
 * （1）new出来
 * （2）加锁
 * （3）finally中解锁
 */
class Ticket2 {

    private Integer number = 30;

    private ReentrantLock lock = new ReentrantLock();

    public synchronized void sale() {
        lock.lock();
        try {
            if(number > 0) {
                number--;
                System.out.println("线程"+Thread.currentThread().getName()+"卖出了票，剩余票数："+number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
