package oah.project.kuangshen.function;

import java.util.function.Predicate;

/**
 * @ClassName Demo02
 * @Description 断定型接口
 * @Author _oah
 * @Date 2024.02.01 17:01
 * @Version 1.0
 */
public class Demo02 {
    public static void main(String[] args) {
        // 判断字符串是否为空
//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String str) {
//                return str.isEmpty();
//            }
//        };
        Predicate<String> predicate = (str)->{return str.isEmpty();};
        System.out.println(predicate.test("asd"));
    }
}
