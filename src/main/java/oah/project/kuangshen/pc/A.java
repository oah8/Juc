package oah.project.kuangshen.pc;

/**
 * @ClassName A
 * @Description TODO
 * @Author _oah
 * @Date 2024.01.31 13:10
 * @Version 1.0
 */

/**
 * 线程之间的通信问题: 生产者和消费者问题!   等待唤醒，通知唤醒
 * 线程交替执行 A，B 操作同一个变量  num = 0
 * A num + 1
 * B num - 1
 */
public class A {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
            for(int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }, "A").start();

        new Thread(()->{
            for(int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "B").start();

        new Thread(()->{
            for(int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "C").start();

        new Thread(()->{
            for(int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "D").start();

    }
}

// 判断等待，业务，通知
class Data { // 数字，资源类
    private int number = 0;
    // +1
    // wait在if语句中，当执行wait的时候会释放锁，
    // 当被notifyAll()唤醒的时候，如果再次获得锁会从wait的位置继续往下执行，而不是从加锁的位置开始执行
    public synchronized void increment() throws InterruptedException {
        // if 改为 while
        while(number != 0) {
            // 等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        // 通知其他线程，我 +1 完毕了
        this.notifyAll();
    }

    // -1
    public synchronized void decrement() throws InterruptedException {
        while(number == 0) {
            // 等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        // 通知其他线程，我 -1 完毕了
        this.notifyAll();
    }

}
