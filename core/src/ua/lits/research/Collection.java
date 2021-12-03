package ua.lits.research;

import ua.lits.common.DataBuilder;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Collection {

    public static void main(String[] args) {
        doListObject();
        doList();
        String s = " ku ";
        Set<Object> set = new TreeSet<>();
        set.add("ku");
        set.add(1);
        System.out.println(s.trim());

        ///
        int i1 = 20;
        byte b1 = 100;
        System.out.println(calculate(b1, i1));

        /// Error
//        IntStream.rangeClosed(1, 10).flatMap((i, consumer) -> {
//            for (int j=1; j<=i; j++) {
//                consumer.accept(j);
//            }
//        });
    }

    ////
    static int calculate(int i1, int i2) {
        return i1 + i2;
    }
    static double calculate(byte i1, byte i2) {
        return i1 % i2;
    }

    private static void doList() {
        try {
            List<Integer> list = DataBuilder.streamInt(10, 1, 1).collect(Collectors.toList());
            ListIterator<Integer> iterator = list.listIterator();

            Integer curVal;
            while (iterator.hasNext()) {
                curVal = iterator.next();
                if (curVal.equals(2)) {
                    iterator.remove();
                    iterator.add(22);
                } else if (curVal == 4) {
                    iterator.set(44);
                }
            }
            list.forEach(System.out::println);
            System.out.println("====");

            iterator = list.listIterator();
            while (iterator.hasNext()) {
                if (iterator.next() == 5) {
                    iterator.remove();
                }
            }
            Collections.shuffle(list);
            list.sort(Integer::compareTo);
            list.forEach(System.out::println);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Research of Classes parametrized with Object
     */
    private static void doListObject() {
        List<Object> arrayList;

        arrayList = Stream.iterate(0L, val -> val + 1).limit(10).collect(Collectors.toList());
        arrayList.add(5);
        arrayList.add("Kuku");
        arrayList.add(null);
        for (Object val : arrayList) {
            System.out.printf("%s %s\n", val, null == val ? "NULL" : val.getClass().getName());
        }
        System.out.println("====\n");

        // ====
        List<Optional<Object>> listOptional;

        listOptional = Stream.iterate(Optional.of((Object) 1D), val -> Optional.of((Double)val.orElse(-1D) + 1))
                .limit(10).collect(Collectors.toList());
        listOptional.add(Optional.of(5));
        listOptional.add(Optional.of("Kuku"));
        listOptional.add(Optional.ofNullable(null));
        for (Optional<Object> val : listOptional) {
            System.out.printf("%s %s\n",
                    val.orElse("NULL"),
//                    val.isPresent() ? val.get().getClass().getName() : "EMPTY"
                    val.map(o -> o.getClass().getName()).orElse("EMPTY")
            );
        }
        System.out.println("====\n");
    }
}
