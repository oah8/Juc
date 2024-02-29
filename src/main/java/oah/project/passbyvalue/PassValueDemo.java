package oah.project.passbyvalue;

/**
 * @ClassName PassValueDemo
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.02 20:35
 * @Version 1.0
 */
public class PassValueDemo {
    public static void main(String[] args) {
        int a = 1;
        String str = "1";
        User user = new User("张三", 18);

        System.out.println("调用方法前a为：" + a);
        System.out.println("调用方法前str为：" + str);
        System.out.println("调用方法前user为：" + user);

        System.out.println("------------------------------------------");
        convert(a, str, user);
        System.out.println("------------------------------------------");

        System.out.println("调用方法后a为：" + a);
        System.out.println("调用方法后str为：" + str);
        System.out.println("调用方法后user为：" + user);

    }

    public static void convert(int a, String str, User user) {
        a = 2;
        str = "2";
        user = new User("赵六", 16);
        user.setAge(20);
        user.setUsername("李四");

        System.out.println("调用方法中a为：" + a);
        System.out.println("调用方法中str为：" + str);
        System.out.println("调用方法中user为：" + user);
    }
}
