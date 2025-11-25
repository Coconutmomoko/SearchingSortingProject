package project;

import java.util.Random;
import java.util.Scanner;

/**
 * Utility methods.
 */
public class Utils {

    private static final Random RAND = new Random();

    public static int[] createFixedArray0to9() {
        int[] data = new int[10];
        for (int i = 0; i < 10; i++) data[i] = i;
        return data;
    }

    public static int[] randomIntArray(int size, int min, int max) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = RAND.nextInt(max - min + 1) + min;
        }
        return data;
    }

    /**
     * Print array in the format required by the example screenshot:
     * -93 -36 25 44 ...
     * (no commas, space-separated)
     */
    public static String arrayToStringSpaces(int[] data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(data[i]);
            if (i < data.length - 1) sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * Convert primitive int[] to CountedInt[] for performance counting.
     */
    public static CountedInt[] toCountedIntArray(int[] raw) {
        CountedInt[] arr = new CountedInt[raw.length];
        for (int i = 0; i < raw.length; i++) {
            arr[i] = new CountedInt(raw[i]);
        }
        return arr;
    }

    /**
     * Read integer safely from console.
     * The official screenshot does NOT show reprompt text,
     * so the minimal version is used.
     */
    public static int readInt(Scanner scanner) {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Not an integer, try again: ");
            }
        }
    }

    public static long nanosToMillis(long startNanos, long endNanos) {
        return (endNanos - startNanos) / 1_000_000;
    }
}
