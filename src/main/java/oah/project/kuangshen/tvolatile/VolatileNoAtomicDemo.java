package oah.project.kuangshen.tvolatile;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName VolatileNoAtomicDemo
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.04 20:16
 * @Version 1.0
 */

class MyNumber {
    /*
    int number;
    public synchronized void addPlusPlus() {
        number++;
    }
    */
    volatile int number;
    public void addPlusPlus() {
        number++;
    }
}

public class VolatileNoAtomicDemo {
    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();

        for(int i = 1; i <= 10; i++) {
            new Thread(()->{
                for(int j = 1; j <= 1000; j++) {
                    myNumber.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        // 暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(myNumber.number);
    }
}

