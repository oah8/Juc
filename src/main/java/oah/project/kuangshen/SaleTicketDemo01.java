package oah.project.kuangshen;

/**
 * @ClassName SaleTicket
 * @Description TODO
 * @Author _oah
 * @Date 2024.01.31 11:37
 * @Version 1.0
 */
// 多线程开发，线程就是一个单独的资源类，没有任何附属的操作！
// 1. 属性、方法
public class SaleTicketDemo01 {

    public static void main(String[] args) {
        // 并发：多线程操作同一个资源类，把资源类丢进线程
        Ticket01 ticket = new Ticket01();
        // @FunctionalInterface 函数式接口，jdk1.8 lambda表达式 (参数)->{代码}
        new Thread(()->{
            for(int i = 1; i < 20; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(()->{
            for(int i = 1; i < 20; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(()->{
            for(int i = 1; i < 20; i++) {
                ticket.sale();
            }
        }, "C").start();
    }

}

// 资源类 OOP
class Ticket01 {
    // 属性、方法
    private int number = 30;

    // 卖票的方式
    public synchronized void sale() {
        if(number > 0) {
            System.out.println(Thread.currentThread().getName() + "正在卖出第" + (number--) + "票，剩余: " + number);
        }
    }

}
