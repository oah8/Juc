package oah.project.kuangshen.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName CASDemo
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.02 11:02
 * @Version 1.0
 */
// Java无法操作内存
// Java可以调用C++    native
// C++可以操作内存
// Unsafe是Java的后门，可以通过这个类操作内存
public class CASDemo {

    // 解决ABA问题，引入原子引用！
    // 带版本号的原子操作！

    // AtomicStampedReference 注意：如果泛型是个包装类，注意对象的引用问题
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1, 1);

    // CAS compareAndSet：比较并交换！
    public static void main(String[] args) {
        /*AtomicInteger atomicInteger = new AtomicInteger(1);

        // 期望、更新
        // public final boolean compareAndSet(int expect, int update)
        // 如果我期望的值达到了，那么就更新，否则，就不更新了，CAS是CPU的并发原语！
        // ======== 捣乱的线程 ========
        System.out.println(atomicInteger.compareAndSet(1, 2));
        System.out.println(atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(2, 1));
        System.out.println(atomicInteger.get());
        // ======== 期望的线程 ========
        System.out.println(atomicInteger.compareAndSet(1, 6));
        System.out.println(atomicInteger.get());*/



        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();// 获取版本号
            System.out.println("a1 => " + stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            atomicStampedReference.compareAndSet(1, 2,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);

            System.out.println("a2 => " + atomicStampedReference.getStamp());

            System.out.println(atomicStampedReference.compareAndSet(2, 1,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("a3 => " + atomicStampedReference.getStamp());

        }, "A").start();

        // 乐观锁原理相同
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();// 获取版本号
            System.out.println("b1 => " + stamp);

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(atomicStampedReference.compareAndSet(1, 6,
                    stamp, stamp + 1));
            System.out.println("b2 => " + atomicStampedReference.getStamp());
            System.out.println("b2-new => " + atomicStampedReference.getReference());

        }, "B").start();





    }
}
