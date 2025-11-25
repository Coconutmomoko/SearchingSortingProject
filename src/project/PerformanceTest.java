package project;

import java.util.Scanner;

/**
 * Performance testing for sorting algorithms.
 * Output format matches the example screenshot exactly.
 */
public class PerformanceTest {

    public static void runPerformanceTest(Scanner scanner) {
        System.out.print("Give base size n (e.g., 1000): ");
        int n = Utils.readInt(scanner);

        int[] sizes = new int[10];
        for (int i = 0; i < 10; i++) {
            sizes[i] = (i + 1) * n;
        }

        System.out.println();

        // print header row, matching screenshot alignment
        System.out.print("\t\t\t\t");
        for (int size : sizes) {
            System.out.printf("%8d", size);
        }
        System.out.println();

        // bubble sort
        printAlgorithmResults("bubbleSort", sizes);

        // insertion sort
        printAlgorithmResults("insertionSort", sizes);

        // merge sort
        printAlgorithmResults("mergeSort", sizes);

        // quick sort
        printAlgorithmResults("quickSort", sizes);

        // selection sort
        printAlgorithmResults("selectionSort", sizes);

        System.out.println();
    }

    private static void printAlgorithmResults(String algoName, int[] sizes) {
        long[] compArray = new long[sizes.length];
        long[] timeArray = new long[sizes.length];

        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];

            int[] raw = Utils.randomIntArray(size, 0, size * 10);
            CountedInt[] data = Utils.toCountedIntArray(raw);

            CountedInt.resetComparisonCount();
            long start = System.nanoTime();

            runAlgorithm(algoName, data);

            long end = System.nanoTime();

            compArray[i] = CountedInt.getComparisonCount();
            timeArray[i] = Utils.nanosToMillis(start, end);
        }

        // comparisons line
        System.out.print(algoName + ",random,comparisons");
        for (long c : compArray) {
            System.out.printf("%8d", c);
        }
        System.out.println();

        // ms line
        System.out.print(algoName + ",random,ms");
        for (long t : timeArray) {
            System.out.printf("%8d", t);
        }
        System.out.println();
    }

    private static void runAlgorithm(String algoName, CountedInt[] data) {
        switch (algoName) {
            case "bubbleSort" -> SortAlgorithms.bubbleSort(data);
            case "insertionSort" -> insertionSortCounted(data);
            case "mergeSort" -> SortAlgorithms.mergeSort(data);
            case "quickSort" -> quickSortCounted(data, 0, data.length - 1);
            case "selectionSort" -> SortAlgorithms.selectionSort(data);
            default -> throw new IllegalArgumentException("Unknown algorithm: " + algoName);
        }
    }

    // --- counted insertion sort ---
    private static void insertionSortCounted(CountedInt[] data) {
        for (int i = 1; i < data.length; i++) {
            CountedInt key = data[i];
            int j = i - 1;

            while (j >= 0 && data[j].compareTo(key) > 0) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = key;
        }
    }

    // --- counted quicksort ---
    private static void quickSortCounted(CountedInt[] data, int low, int high) {
        if (low >= high) return;

        int pivotIndex = partitionCounted(data, low, high);
        quickSortCounted(data, low, pivotIndex - 1);
        quickSortCounted(data, pivotIndex + 1, high);
    }

    private static int partitionCounted(CountedInt[] data, int low, int high) {
        CountedInt pivot = data[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (data[j].compareTo(pivot) <= 0) {
                i++;
                CountedInt tmp = data[i];
                data[i] = data[j];
                data[j] = tmp;
            }
        }

        CountedInt tmp = data[i + 1];
        data[i + 1] = data[high];
        data[high] = tmp;

        return i + 1;
    }
}
