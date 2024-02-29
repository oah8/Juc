package oah.project.kuangshen.add;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName CountDownLatchDemo
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.01 10:27
 * @Version 1.0
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        // 总是是6，必须要执行任务的时候，在使用！
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for(int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " Go out");
                countDownLatch.countDown(); // 数量-1
            }, String.valueOf(i)).start();
        }
        countDownLatch.await(); // 等待计数器归零，然后再向下执行
        System.out.println("Close Door");
    }

}
