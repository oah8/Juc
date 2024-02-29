package oah.project.kuangshen.stream;

/**
 * @ClassName Test
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.01 17:17
 * @Version 1.0
 */

import java.util.Arrays;
import java.util.List;

/**
 * 题目要求：请按照给出数据，找出同时满足以下条件的用户
 * 也即以下条件：
 * 1、全部满足偶数ID
 * 2、年龄大于24
 * 3、用户名转为大写
 * 4、用户名字母倒排序
 * 5、只输出一个用户名字 limit
 */
public class Test {
    public static void main(String[] args) {
        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(16, "e", 26);
        // 集合就是存储
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);
        // 计算交给Stream流
        // Lambda表达式，链式编程，函数式接口，Stream流计算
        list.stream()
                .filter(u->{return u.getId() % 2 == 0;})
                .filter(u->{return u.getAge() > 23;})
                .map(u->{return u.getName().toUpperCase();})
                .sorted((uu1, uu2)->{return uu2.compareTo(uu1);})
                .limit(1)
                .forEach(System.out::println);


    }
}
