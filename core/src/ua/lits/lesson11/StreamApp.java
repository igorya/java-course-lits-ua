package ua.lits.lesson11;

import ua.lits.common.DataBuilder;
import ua.lits.common.PrintHelper;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class StreamApp {

    void run() {
        List<Integer> list;
        int maxSize = 20;

        try {
            // Raw example of reusable code
            list = makeFilteredList(maxSize, val -> 0 == val % 2);
            PrintHelper.dumpIterable(list, "EVEN list");
            PrintHelper.info("The SUM of the EVEN list equals: " + sumIntList(list));
            PrintHelper.info("");

            // Raw example of reusable code
            list = makeFilteredList(maxSize, val -> 0 != val % 2);
            PrintHelper.dumpIterable(list, "ODD list");
            PrintHelper.info("The SUM of the ODD list equals: " + sumIntList(list));
            PrintHelper.info("");

            // All in one as func style
            PrintHelper.info(
                    String.format(
                            "Total SUM of unfiltered list with %d items: %d",
                            maxSize,
                            DataBuilder.streamInt(maxSize).reduce(0, Integer::sum)
                    )
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        PrintHelper.info("--== END of Stream Application ==--");
        PrintHelper.info("");
    }

    private List<Integer> makeFilteredList(int maxSize, Predicate<Integer> predicate) throws Exception {
        return DataBuilder.streamInt(maxSize)
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private int sumIntList(List<Integer> list) {
        return list.stream().reduce(0, Integer::sum);
    }
}
