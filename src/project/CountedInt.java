package project;

/**
 * Wrapper for int that counts comparisons globally.
 */
public class CountedInt implements Comparable<CountedInt> {

    private static long comparisonCount = 0;
    private final int value;

    public CountedInt(int value) {
        this.value = value;
    }

    public static void resetComparisonCount() {
        comparisonCount = 0;
    }

    public static long getComparisonCount() {
        return comparisonCount;
    }

    @Override
    public int compareTo(CountedInt other) {
        comparisonCount++;
        return Integer.compare(this.value, other.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
