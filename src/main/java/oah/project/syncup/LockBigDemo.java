package oah.project.syncup;

/**
 * @ClassName LockBigDemo
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.13 20:02
 * @Version 1.0
 */

/**
 * 锁粗化
 * 假如方法中首尾相接，前后相邻的都是同一个锁对象，那JIT编译器就会把这几个synchronized块合并成一个大块，
 * 加粗加大范围，一次申请锁使用即可，避免次次的申请和释放锁，提升了性能
 */
public class LockBigDemo {

    static Object objectLock = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (objectLock) {
                System.out.println("11111111");
            }

            synchronized (objectLock) {
                System.out.println("22222222");
            }

            synchronized (objectLock) {
                System.out.println("33333333");
            }

            synchronized (objectLock) {
                System.out.println("44444444");
            }

            synchronized (objectLock) {
                System.out.println("55555555");
            }


            synchronized (objectLock) {
                System.out.println("11111111");
                System.out.println("22222222");
                System.out.println("33333333");
                System.out.println("44444444");
                System.out.println("55555555");
            }
        }, "t1").start();
    }
}
