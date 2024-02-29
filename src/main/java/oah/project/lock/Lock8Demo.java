package oah.project.lock;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Lock8Demo
 * @Description TODO
 * @Author _oah
 * @Date 2024.01.22 12:51
 * @Version 1.0
 */

// 资源类
class Phone {
    public static synchronized void sendEmail() {
        // 暂停几秒钟
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--------sendEmail--------");
    }

    public synchronized void sendSms() {
        System.out.println("--------sendSms-------");
    }

    public void hello() {
        System.out.println("--------hello--------");
    }

}



/**
 * 题目：谈谈你对多线程锁的理解，8所案例说明
 * 口诀：线程    操作      资源类
 * 8锁案例说明：
 * 1. 标准访问有a,b两个线程，请问先打印邮件还是短信  先打印邮件再打印短信
 * 2. sendEmail方法中加入暂停3秒种，请问先打印邮件还是短信   先打印邮件再打印短信
 * 3. 添加一个普通的hello方法，请问打印邮件还是hello  先打印hello再打印邮件
 * 4. 有两部手机，请问先打印邮件再打印短信    先打印短信再打印邮件
 * 5. 有两个静态同步方法，有1部手机，请问先打印邮件还是短信    先打印邮件再打印短信
 * 6. 有两个静态同步方法，有2部手机，请问先打印邮件还是短信    先打印邮件再打印短信
 * 7. 有1个静态同步方法，有一个普通同步方法，有1部手机，请问先打印邮件还是短信    先打印短信再打印邮件
 * 8. 有1个静态同步方法，有一个普通同步方法，有2部手机，请问先打印邮件还是短信    先打印短信再打印邮件
 */
public class Lock8Demo {

    public static void main(String[] args) { // 一切程序的入口

        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();

        // 暂停毫秒，保证a线程先启动
        try {
            TimeUnit.MICROSECONDS.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
             // phone.sendSms();
            // phone.hello();
             phone2.sendSms();
            // phone.sendSms();
        }, "b").start();

    }




}





















































