package oah.project.kuangshen.cas;

/**
 * @ClassName SpinLockDemo
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.05 15:38
 * @Version 1.0
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 题目：实现一个自旋锁，复习CAS思想
 * 自旋锁好处：循环比较获取没有类似wait的阻塞
 *
 * 通过CAS操作完成自旋锁，A线程先进来调用myLock方法自己持有锁5秒钟，B线程随后进来发现
 * 当前A线程持有锁，所以只能通过自旋锁等待，直到A释放锁后B随后抢到
 */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t" + "--------come in");
        while (!atomicReference.compareAndSet(null, thread)) {

        }

    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t" + "-------task over, unlock........");
    }


    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.lock();
            // 暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            spinLockDemo.unlock();
        }, "A").start();

        // 暂停500ms，线程A先于B启动
        try {
            TimeUnit.MICROSECONDS.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            spinLockDemo.lock();

            spinLockDemo.unlock();
        }, "B").start();

    }

}
