package oah.project.kuangshen.tvolatile;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName JMM
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.01 18:52
 * @Version 1.0
 */
public class JMM {

    // 不加 volatile 程序就会死循环！
    // 加 volatile 可以保证可见性！
    private volatile static int num = 0;

    public static void main(String[] args) { // main

        new Thread(()->{ // 线程 1 对主内存的变化不知道的
            while(num == 0) {

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        num = 1;
        System.out.println(1);

    }
}
