package oah.project.kuangshen.function;

/**
 * @ClassName Demo03
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.01 17:10
 * @Version 1.0
 */

import java.util.function.Consumer;

/**
 * Consumer 消费型接口：只有输入，没有返回值
 */
public class Demo03 {
    public static void main(String[] args) {
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        };
        Consumer<String> consumer = (str)->{
            System.out.println(str);
        };
        consumer.accept("sad");

    }
}
