package oah.project.kuangshen.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName B
 * @Description TODO
 * @Author _oah
 * @Date 2024.01.31 16:35
 * @Version 1.0
 */
public class B {
    public static void main(String[] args) {
        Data2 data = new Data2();
        new Thread(()->{
            for(int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }, "A").start();

        new Thread(()->{
            for(int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "B").start();

        new Thread(()->{
            for(int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "C").start();


        new Thread(()->{
            for(int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "D").start();

    }
}

// 判断等待，业务，通知
class Data2 { // 数字，资源类
    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    // condition.await(); // 等待
    // condition.signalAll(); // 唤醒全部

    // +1
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            // 业务代码
            while(number != 0) {
                // 等待
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            // 通知其他线程，我 -1 完毕了
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    // -1
    public void decrement() throws InterruptedException {
       lock.lock();
       try {
           while(number == 0) {
               // 等待
               condition.await();
           }
           number--;
           System.out.println(Thread.currentThread().getName() + "=>" + number);
           // 通知其他线程，我 -1 完毕了
           condition.signalAll();
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           lock.unlock();
       }
    }

}

