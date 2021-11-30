package ua.lits.common;

import java.util.stream.Stream;

public class DataBuilder {

    public static Stream<Integer> streamInt(int maxSize, int start, int step) throws Exception {
        if (maxSize < 0) {
            throw new Exception("The maxSize arg can't be less than zero");
        }
        return Stream
                .iterate(start, val -> val + step)
                .limit(maxSize);
    }

    public static Stream<Integer> streamInt(int maxSize) throws Exception {
        return streamInt(maxSize, 0, 1);
    }
}
