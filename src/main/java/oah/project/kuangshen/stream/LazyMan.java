package oah.project.kuangshen.stream;

import java.lang.reflect.Constructor;

/**
 * @ClassName LazyMan
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.02 8:58
 * @Version 1.0
 */
public class LazyMan {
    private LazyMan() {}

    private volatile static LazyMan lazyMan;

    public static LazyMan getInstance() {
        if(lazyMan == null) {
            synchronized (LazyMan.class) {
                if(lazyMan == null) {
                    lazyMan = new LazyMan();
                }
            }
        }
        return lazyMan;
    }

    public static void main(String[] args) throws Exception {
        LazyMan instance1 = LazyMan.getInstance();
        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyMan instance2 = declaredConstructor.newInstance();


        System.out.println(instance1);
        System.out.println(instance2);
    }
}
