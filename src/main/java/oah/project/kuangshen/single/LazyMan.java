package oah.project.kuangshen.single;

import java.lang.reflect.Constructor;

/**
 * @ClassName LazyMan
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.01 21:11
 * @Version 1.0
 */
// 懒汉式单例
public class LazyMan {

    private LazyMan() {
        synchronized (LazyMan.class) {
            if(qinjiang == false) {
                qinjiang = true;
            } else {
                throw new RuntimeException("不要试图使用反射破坏异常");
            }
        }
    }

    private static boolean qinjiang = false;

    private volatile static LazyMan lazyMan;

    // 双重检测锁模式的懒汉式单例，DCL懒汉式
    public static LazyMan getInstance() {
        if(lazyMan == null) {
            synchronized (LazyMan.class) {
                if(lazyMan == null) {
                    lazyMan = new LazyMan(); // 不是原子性操作
                    /**
                     * 1.分配内存空间
                     * 2.执行构造方法，初始化对象
                     * 3.把这个对象指向这个空间
                     *
                     *
                     * 123
                     * 132 A
                     *     B
                     */
                }
            }
        }
        return lazyMan;
    }

    // 多线程并发
    public static void main(String[] args) throws Exception{
        /*for(int i = 0; i < 10; i++) {
            new Thread(() -> {
                LazyMan.getInstance();
            }).start();
        }*/

        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        LazyMan instance1 = declaredConstructor.newInstance();
//        LazyMan instance2 = declaredConstructor.newInstance();
        LazyMan instance2 = LazyMan.getInstance();

        System.out.println(instance1);
//        System.out.println(instance2);


    }

}
