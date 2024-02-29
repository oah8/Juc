package oah.project.kuangshen.tvolatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName volatileDemo
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.01 18:58
 * @Version 1.0
 */
// volatile 不保证原子性
// 使用原子类解决原子性问题
public class volatileDemo {

    // volatile 不保证原子性
    // 原子类的 Integer
    private volatile static AtomicInteger num = new AtomicInteger();

    public static void add() {
//        num++; // 不是一个原子性操作
        num.getAndIncrement(); // AtomicInteger 的 + 1 方法，CAS
    }


    public static void main(String[] args) {
        for(int i = 1; i <= 20; i++) {
            new Thread(()->{
                for(int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        while(Thread.activeCount() > 2) { // main gc
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
