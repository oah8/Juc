package oah.project.kuangshen.single;

import java.lang.reflect.Constructor;

/**
 * @ClassName Hungry
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.01 20:58
 * @Version 1.0
 */
// 饿汉式单例
public class Hungry {

    private byte[] data1 = new byte[1024 * 1024];
    private byte[] data2 = new byte[1024 * 1024];
    private byte[] data3 = new byte[1024 * 1024];
    private byte[] data4 = new byte[1024 * 1024];

    private Hungry() {

    }
    private final static Hungry HUNGRY = new Hungry();

    public static Hungry getInstance() {
        return HUNGRY;
    }

    public static void main(String[] args) throws Exception {
        Hungry instance1 = Hungry.getInstance();
        Constructor<Hungry> declaredConstructor = Hungry.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Hungry instance2 = declaredConstructor.newInstance();

        System.out.println(instance1);
        System.out.println(instance2);
    }

}
