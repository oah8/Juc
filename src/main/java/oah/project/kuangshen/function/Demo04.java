package oah.project.kuangshen.function;

import java.util.function.Supplier;

/**
 * @ClassName Demo04
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.01 17:13
 * @Version 1.0
 */
public class Demo04 {
    public static void main(String[] args) {
//        Supplier<Integer> supplier = new Supplier<Integer>() {
//            @Override
//            public Integer get() {
//                return 1024;
//            }
//        };
        Supplier<Integer> supplier = ()->{return 1024;};
        System.out.println(supplier.get());

    }
}
