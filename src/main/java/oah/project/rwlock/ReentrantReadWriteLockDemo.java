package oah.project.rwlock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName ReentrantReadWriteLockDemo
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.15 8:52
 * @Version 1.0
 */

// 资源类，模拟一个简单的缓存
class MyResource {
    Map<String, String> map = new HashMap<>();
    // =======ReentrantLock 等价于 ======== synchronized
    Lock lock = new ReentrantLock();
    // ========ReentrantReadWriteLock 一体两面，读写互斥，读读共享
    ReadWriteLock rwLock = new ReentrantReadWriteLock();


    public void write(String key, String value) {
        // lock.lock();
        rwLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "正在写入");
            map.put(key, value);
            // 暂停500毫秒
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "完成写入");
        } finally {
            // lock.unlock();
            rwLock.writeLock().unlock();
        }
    }

    public void read(String key) {
        // lock.lock();
        rwLock.readLock().lock();


        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "正在读取");
            String result = map.get(key);
            // 暂停200毫秒
//            try {
//                TimeUnit.MILLISECONDS.sleep(200);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            // 暂停2000毫秒，演示读锁没有完成之前，写锁无法获得
            try {
                TimeUnit.MICROSECONDS.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "完成读取" + "\t" + result);
        } finally {
            // lock.unlock();
            rwLock.readLock().unlock();
        }
    }
}



public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) {
        MyResource myResource = new MyResource();
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(()->{
                myResource.write(finalI + "", finalI + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(()->{
                myResource.read(finalI + "");
            }, String.valueOf(i)).start();
        }

        // 暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 1; i <= 3; i++) {
            int finalI = i;
            new Thread(()->{
                myResource.write(finalI + "", finalI + "");
            }, "新写锁线程->" + String.valueOf(i)).start();
        }

    }
}



































