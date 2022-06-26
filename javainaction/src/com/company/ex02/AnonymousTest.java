package com.company.ex02;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class AnonymousTest {

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(" inner thread ");
            }
        });

        t.run();

        // 의미없는 코드
        new Runnable() {
            @Override
            public void run() {
                System.out.println("outer thread");
            }
        };


        Comparator<Object> weight = (v1, v2) -> v1.toString().compareTo(v2.toString());

        Predicate<String> predicate = (s) -> !s.isEmpty();
        List<String> strings = Arrays.asList("1", "2", "3", "4");
        List<String> strings2 = filter(strings, predicate);

        strings.stream().forEach(v -> System.out.print(v + " "));
        strings2.stream().forEach(v -> System.out.print(v + " "));

        ToIntFunction<String> stringToInt= (v) -> Integer.parseInt(v);
        ToIntFunction<String> stringToInt2= Integer::parseInt;

        BiPredicate<List<String>, String> contain = (v1, v2) -> v1.contains(v2);
        BiPredicate<List<String>, String> contain2 = List::contains;

        List<? extends Serializable> list = Arrays.asList(1, 2, 3, "33");
        System.out.println("list = " + list);

    }

    public interface Predicate<T> {
        boolean test(T t);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if(p.test(t)) result.add(t);
        }
        return result;
    }
}
