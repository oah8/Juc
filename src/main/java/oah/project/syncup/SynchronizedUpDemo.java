package oah.project.syncup;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName SynchronizedUpDemo
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.13 15:18
 * @Version 1.0
 */
public class SynchronizedUpDemo {
    public static void main(String[] args) {
        // noLock();

        // biasedLock();

        // lightLock();

        // weightLock();

        /*// 先睡眠5秒，保证开启偏向锁
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Object o = new Object();
        System.out.println("本应是偏向锁");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());


        o.hashCode(); // 没有重写，一致性哈希，重写后无效, 当一个对象已经计算过identity hash code，它就无法进入偏向锁状态；

        synchronized (o) {
            System.out.println("本应是偏向锁，但由于计算过一致性哈希，会直接升级为轻量级锁");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }*/

        // 先睡眠5秒，保证开启偏向锁
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Object o = new Object();

        synchronized (o) {
            o.hashCode(); // 没有重写，一致性哈希，重写后无效
            System.out.println("偏向锁过程中遇到一致性哈希计算请求，立马撤销偏向锁模式，膨胀为重量级锁");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }



    }

    private static void weightLock() {
        Object o = new Object();

        new Thread(()->{
            synchronized (o) {
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        }, "t1").start();

        new Thread(()->{
            synchronized (o) {
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        }, "t2").start();
    }

    private static void lightLock() {
        Object o = new Object();
        new Thread(()->{
          synchronized (o) {
              System.out.println(ClassLayout.parseInstance(o).toPrintable());
          }
        }, "t1").start();
    }

    private static void biasedLock() {
        // biased
        // 偏向锁开启
        // -XX:BiasedLockingStartupDelay=0 或 直接暂停4秒钟
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        System.out.println("========");
        new Thread(()->{
            synchronized (o) {
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        }, "t1").start();
    }

    private static void noLock() {
        Object o = new Object();

        // 倒着看
        System.out.println("10进制: " + o.hashCode());
        System.out.println("16进制: " + Integer.toHexString(o.hashCode()));
        System.out.println("2进制: " + Integer.toBinaryString(o.hashCode()));

        // 2进制: 1010000010011110111010010010
        //       1010000010011110111010010010
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
