package oah.project.kuangshen;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName SaleTicketDemo02
 * @Description TODO
 * @Author _oah
 * @Date 2024.01.31 12:21
 * @Version 1.0
 */
public class SaleTicketDemo02 {

    public static void main(String[] args) {

        // 并发：多线程操作同一个资源类，把资源类丢进线程
        Ticket02 ticket02 = new Ticket02();


        // @FunctionalInterface 函数式接口，jdk1.8 lambda表达式 (参数)->{代码}
        new Thread(()->{ for(int i = 1; i < 20; i++) ticket02.sale();}, "A").start();
        new Thread(()->{ for(int i = 1; i < 20; i++) ticket02.sale();}, "B").start();
        new Thread(()->{ for(int i = 1; i < 20; i++) ticket02.sale();}, "C").start();
    }

}

// Lock锁 三部曲
// 1. new ReentrantLock()
// 2. lock.lock() 加锁
// 3. finally => lock.unlock() // 解锁
class Ticket02 {

    private int number = 30;
    Lock lock = new ReentrantLock();
    public void sale() {
        lock.lock(); // 加锁

        try {
            // 业务代码
            if(number > 0) {
                System.out.println(Thread.currentThread().getName() + "正在卖出第" + (number--) + "票，剩余" + number);
            }
        } catch (Exception e) {
             e.printStackTrace();
        } finally {
            lock.unlock(); // 解锁
        }

    }

}
