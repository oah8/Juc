package oah.project.kuangshen.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @ClassName Test
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.01 18:04
 * @Version 1.0
 */
// 3000 6000(ForkJoin) 9000 (Stream并行流)
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();
        test2();
        test3();
    }

    // sum = 500000000500000000 时间: 7123
    public static void test1() {
        Long sum = 0L;
        long start = System.currentTimeMillis();
        for(Long i = 1L; i <= 10_0000_0000L; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + " 时间: " + (end - start));
    }
    // 会使用ForkJoin
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinDemo task = new ForkJoinDemo(1L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);// 提交任务
        Long sum = submit.get();

        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + " 时间: " + (end - start));
    }

    public static void test3() {
        long start = System.currentTimeMillis();
        // Stream并行流
        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + " 时间: " + (end - start));
    }
}
