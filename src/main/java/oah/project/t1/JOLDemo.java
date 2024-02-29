package oah.project.t1;

/**
 * @ClassName JOLDemo
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.13 10:51
 * @Version 1.0
 */
public class JOLDemo {

    public static void main(String[] args) {

        // Thread.currentThread();
        // VM的细节详细情况
        // System.out.println(VM.current().details());
        // 所有的对象分配的字节都是8的整数倍
        // System.out.println(VM.current().objectAlignment());

//        Object o = new Object(); // 16 bytes
//        System.out.println(ClassLayout.parseInstance(o).toPrintable());
//
//        Customer c1 = new Customer(); // 16 bytes
//        System.out.println(ClassLayout.parseInstance(c1).toPrintable());

    }

}


// 只有一个对象头的实例对象，16字节（忽略压缩指针的影响） + 4字节 + 1字节 = 21字节 -------> 对其填充，24字节
class Customer {
    // 1 第一种情况，只有对象头，没有其它任何实例数据

    // 2 第二种情况，int + boolean
    int id;
    boolean flag = false;
}


// JOL = Java Object LayOut


/**
 * 1 默认配置，启动了压缩指针，-XX:+UseCompressedClassPointers，
 *   12 + 4 (对齐填充) = 一个对象16字节
 *
 * 2 手动配置，关闭了压缩指针，-XX:-UseCompressedClassPointers，
 *   8 + 8 = 16 一个对象16字节
 *
 */