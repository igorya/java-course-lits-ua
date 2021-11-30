package ua.lits.common;

/**
 * Log messages to the stdout
 */
public class PrintHelper {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String INLINE_DIVIDER = " ";

    public static void info(String message) {
        System.out.println(ANSI_GREEN + message + ANSI_RESET);
    }

    public static void error(String message) {
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }

    public static<T> void dumpIterable(Iterable<T> iterable, String title) {
        info("Begin dump: "+ title +":");
        dumpIterable(iterable);
        System.out.println();
        info("End dump: "+ title);
    }

    public static<T> void dumpIterable(Iterable<T> iterable) {
        iterable.forEach((val) -> System.out.print(val + INLINE_DIVIDER));
    }
}
