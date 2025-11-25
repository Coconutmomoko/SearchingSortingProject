package project;

import java.util.Random;

/**
 * Sorting demos for menu choice 3 and 4.
 * Lines and headings match the example screenshots.
 */
public class SortAlgorithms {

    private static final Random RAND = new Random();

    /**
     * Choice 3 demo: insertion sort (n^2).
     */
    public static void runInsertionSortDemo() {
        int[] data = Utils.randomIntArray(10, -99, 99);

        System.out.println();
        System.out.println("Data set before insertion sorting:");
        System.out.println(Utils.arrayToStringSpaces(data));
        System.out.println();

        insertionSort(data);

        System.out.println("Data set after insertion sorting:");
        System.out.println(Utils.arrayToStringSpaces(data));
        System.out.println();
    }

    /**
     * Choice 4 demo: quicksort (n * log(n)).
     */
    public static void runQuicksortDemo() {
        int[] data = Utils.randomIntArray(10, -99, 99);

        System.out.println();
        System.out.println("Data set before quicksort:");
        System.out.println(Utils.arrayToStringSpaces(data));
        System.out.println();

        quickSort(data, 0, data.length - 1);

        System.out.println("Data set after quicksort:");
        System.out.println(Utils.arrayToStringSpaces(data));
        System.out.println();
    }

    // -------- n^2 algorithms --------

    public static void insertionSort(int[] data) {
        for (int i = 1; i < data.length; i++) {
            int key = data[i];
            int j = i - 1;

            while (j >= 0 && data[j] > key) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = key;
        }
    }

    public static void bubbleSort(CountedInt[] data) {
        boolean swapped;
        for (int i = 0; i < data.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    CountedInt tmp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void selectionSort(CountedInt[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j].compareTo(data[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            CountedInt tmp = data[i];
            data[i] = data[minIndex];
            data[minIndex] = tmp;
        }
    }

    // -------- n * log(n) algorithms --------

    public static void quickSort(int[] data, int low, int high) {
        if (low >= high) return;
        int pivotIndex = partition(data, low, high);
        quickSort(data, low, pivotIndex - 1);
        quickSort(data, pivotIndex + 1, high);
    }

    private static int partition(int[] data, int low, int high) {
        int pivot = data[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (data[j] <= pivot) {
                i++;
                int tmp = data[i];
                data[i] = data[j];
                data[j] = tmp;
            }
        }
        int tmp = data[i + 1];
        data[i + 1] = data[high];
        data[high] = tmp;
        return i + 1;
    }

    public static void mergeSort(CountedInt[] data) {
        if (data.length <= 1) return;

        int mid = data.length / 2;
        CountedInt[] left = new CountedInt[mid];
        CountedInt[] right = new CountedInt[data.length - mid];

        System.arraycopy(data, 0, left, 0, mid);
        System.arraycopy(data, mid, right, 0, data.length - mid);

        mergeSort(left);
        mergeSort(right);
        merge(data, left, right);
    }

    private static void merge(CountedInt[] data, CountedInt[] left, CountedInt[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) <= 0) data[k++] = left[i++];
            else data[k++] = right[j++];
        }
        while (i < left.length) data[k++] = left[i++];
        while (j < right.length) data[k++] = right[j++];
    }
}
