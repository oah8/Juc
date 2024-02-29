package oah.project.kuangshen.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName AtomicStampedDemo
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.05 16:38
 * @Version 1.0
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
class Book {
    private int id;

    private String bookName;
}

public class AtomicStampedDemo {

    public static void main(String[] args) {

        Book javaBook = new Book(1, "javaBook");
        AtomicStampedReference<Book> atomicStampedReference = new AtomicStampedReference<>(javaBook, 1);

        System.out.println(atomicStampedReference.getReference() + "\t" + atomicStampedReference.getStamp());



        Book mysqlBook = new Book(2, "mysqlBook");
        boolean b;
        b = atomicStampedReference.compareAndSet(javaBook, mysqlBook, 1, 2);
        System.out.println(atomicStampedReference.getReference() + "\t" + atomicStampedReference.getStamp());


        b = atomicStampedReference.compareAndSet(mysqlBook, javaBook, 2, 3);
        System.out.println(atomicStampedReference.getReference() + "\t" + atomicStampedReference.getStamp());




    }



}












