package ua.lits.research;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Collection {

    public static void main(String[] args) {
        doList();
    }

    /**
     * Research of Classes parametrized with Object
     */
    static void doList() {
        List<Object> arrayList = new ArrayList<>();

        arrayList = Stream.iterate(0L, val -> val + 1).limit(10).collect(Collectors.toList());
        arrayList.add(5);
        arrayList.add("Kuku");
        arrayList.add(null);
        for (Object val : arrayList) {
            System.out.printf("%s %s\n", val, null == val ? "NULL" : val.getClass().getName());
        }
        System.out.println("====\n");

        // ====
        List<Optional<Object>> listOptional = new ArrayList<>();

        listOptional = Stream.iterate(Optional.of((Object) 1D), val -> Optional.of((Double)val.orElse(-1D) + 1))
                .limit(10).collect(Collectors.toList());
        listOptional.add(Optional.of(5));
        listOptional.add(Optional.of("Kuku"));
        listOptional.add(Optional.ofNullable(null));
        for (Optional<Object> val : listOptional) {
            System.out.printf("%s %s\n",
                    val.orElse("NULL"),
                    val.isPresent() ? val.get().getClass().getName() : "EMPTY"
            );
        }
        System.out.println("====\n");
    }
}
