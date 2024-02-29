package oah.project.kuangshen.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName CallableTest
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.01 9:44
 * @Version 1.0
 */
public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // new Thread(new Runnable()).start();
        // new Thread(new FutureTask<V>()).start();
        // new Thread(new FutureTask<V>(Callable)).start();
        // new Thread().start(); // 怎么启动 Callable

        MyThread thread = new MyThread();
        FutureTask futureTask = new FutureTask(thread); // 适配类
        // 适配类
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start(); // 结果会被缓存，效率高
        Integer o = (Integer) futureTask.get(); // 获取 Callable 的返回结果，这个get方法可能产生阻塞，把他放到最后
        // 或者使用异步通信来处理
        System.out.println(o);

    }
}

class MyThread implements Callable<Integer> {
    @Override
    public Integer call() {
        System.out.println("call()"); // 会打印几个call
        // 耗时的操作
        return 1024;
    }
}
