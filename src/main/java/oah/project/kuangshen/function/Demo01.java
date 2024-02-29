package oah.project.kuangshen.function;

/**
 * @ClassName Demo01
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.01 16:51
 * @Version 1.0
 */

import java.util.function.Function;

/**
 * Function 函数型接口
 */
public class Demo01 {
    public static void main(String[] args) {
        // 工具类：输出输入的值
//        Function<String, String> function = new Function<String, String>() {
//            @Override
//            public String apply(String str) {
//                return str;
//            }
//        };
        Function<String, String> function = (str)->{return str;};
        System.out.println(function.apply("asd"));
    }
}
