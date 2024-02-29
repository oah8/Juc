package oah.project.kuangshen.tvolatile;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName VolatileSeeDemo
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.04 18:44
 * @Version 1.0
 */
public class VolatileSeeDemo {
//    static boolean flag = true;
    static volatile boolean flag = true;

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t --------come in");;
            while(flag) {

            }
            System.out.println(Thread.currentThread().getName() + "\t --------flag被置为false，程序停止");
        }, "t1").start();
        // 暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        flag = false;
        System.out.println(Thread.currentThread().getName() + "\t --------修改完成flag: " + flag);
    }
}
