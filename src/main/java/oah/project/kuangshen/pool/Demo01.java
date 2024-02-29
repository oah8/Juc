package oah.project.kuangshen.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Demo01
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.01 13:15
 * @Version 1.0
 */
// Executors 工具类、3大方法
// 使用了线程池之后，使用线程池来创建线程
// new ThreadPoolExecutor.AbortPolicy() // 银行满了，还有人进来，不处理这个人的，抛出异常
// new ThreadPoolExecutor.CallerRunsPolicy() // 哪来的去哪里
// new ThreadPoolExecutor.DiscardPolicy() // 队列满了，不会抛出异常！
// new ThreadPoolExecutor.DiscardOldestPolicy() // 队列满了，尝试去和最早的竞争，也不会抛出异常！
public class Demo01 {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();// 单个线程
//        ExecutorService threadPool = Executors.newFixedThreadPool(5); // 创建一个固定的线程池大小
//        ExecutorService threadPool = Executors.newCachedThreadPool(); // 可伸缩的，遇强则强，遇弱则弱

        // 自定义线程池！工作 ThreadPoolExecutor

        // 最大线程到底该如何定义
        // 1.CPU 密集型，几核，就是几，可以保持CPU的效率最高！
        // 2.I/O 密集型，判断你程序中十分耗I/O的线程，
        //   程序    15个大型任务 io十分占用资源！
        // 获取 CPU 的核数
        System.out.println(Runtime.getRuntime().availableProcessors());


        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()); // 队列满了，尝试去和最早的竞争，也不会抛出异常！

        try {
            // 最大承载：Deque + max
            // 超过 RejectedExecutionException
            for(int i = 1; i <= 9; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池用完，程序结束，关闭线程池
            threadPool.shutdown();
        }
    }
}
