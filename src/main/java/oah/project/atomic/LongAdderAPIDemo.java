package oah.project.atomic;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;

/**
 * @ClassName LongAdderAPIDemo
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.08 10:07
 * @Version 1.0
 */
public class LongAdderAPIDemo {

    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();

        longAdder.increment();
        longAdder.increment();
        longAdder.increment();
        System.out.println(longAdder.sum());

        // LongAccumulator longAccumulator = new LongAccumulator((x, y)->(x + y), 0);
        LongAccumulator longAccumulator = new LongAccumulator(new LongBinaryOperator() {
            @Override
            public long applyAsLong(long left, long right) {
                return left + right;
            }
        }, 0);
        longAccumulator.accumulate(1); // 1
        longAccumulator.accumulate(3); // 4

        System.out.println(longAccumulator.get());
    }

}






