package oah.project.kuangshen.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName C
 * @Description TODO
 * @Author _oah
 * @Date 2024.01.31 17:05
 * @Version 1.0
 */
// A 执行完调用 B, B 执行完调用 C, C 执行完调用 A
public class C {
    public static void main(String[] args) {
        Data3 data = new Data3();
        new Thread(()->{for(int i = 0; i < 10; i++) data.printA();}, "A").start();
        new Thread(()->{for(int i = 0; i < 10; i++) data.printB();}, "B").start();
        new Thread(()->{for(int i = 0; i < 10; i++) data.printC();}, "C").start();
    }
}

// 资源类
class Data3 {
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    private int number = 1; // number = 1 A执行，number = 2 B执行，number = 3 C执行

    public void printA() {
        lock.lock();
        try {
            // 业务：判断等待 -> 执行 -> 通知
            while(number != 1) {
                // 等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>AAAAAAAA");
            // 唤醒，唤醒指定的人，B
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    public void printB() {
        lock.lock();
        try {
            // 业务：判断等待 -> 执行 -> 通知
            while(number != 2) {
                // 等待
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>BBBBBBBB");
            // 唤醒，唤醒指定的人，C
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            // 业务：判断等待 -> 执行 -> 通知
            while(number != 3) {
                // 等待
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>CCCCCCCC");
            // 唤醒，唤醒指定的人，C
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }



}