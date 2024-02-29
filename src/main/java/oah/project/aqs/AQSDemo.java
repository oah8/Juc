package oah.project.aqs;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AQSDemo
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.13 20:20
 * @Version 1.0
 */
public class AQSDemo {
    static final int MAXIMUM_CAPACITY = 1 << 30;
    public static void main(String[] args) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        res.add(2);
        res.add(3);
        res.add(3);
        res.add(4);
        for (int i = 0; i < res.size(); i++) {
            if (res.get(i) == 3) {
                res.remove(i);
            }
        }
        for (Integer i : res) {
            System.out.println(i);
        }

        System.out.println(tableSizeFor(50));

    }
    static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
