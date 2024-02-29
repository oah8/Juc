package oah.project.kuangshen.single;

import java.lang.reflect.Constructor;

/**
 * @ClassName Holder
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.01 23:33
 * @Version 1.0
 */
// 静态内部类
public class Holder {
    private Holder() {}

    private static class HolderClass {
        private static final Holder HOLDER = new Holder();
    }

    public static Holder getInstance() {
        return HolderClass.HOLDER;
    }

    public static void main(String[] args) throws Exception {
        Holder instance1 = Holder.getInstance();
        Constructor<Holder> declaredConstructor = Holder.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Holder instance2 = declaredConstructor.newInstance();

        System.out.println(instance1);
        System.out.println(instance2);
    }
}
