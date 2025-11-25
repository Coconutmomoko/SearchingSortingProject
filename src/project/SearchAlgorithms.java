package project;

import java.util.Scanner;

/**
 * Linear and binary search demos.
 * Output texts match the example screenshots.
 */
public class SearchAlgorithms {

    public static void runLinearSearch(Scanner scanner) {
        int[] data = Utils.createFixedArray0to9();

        System.out.print("In the list are values 0, ..., 9; which value would you like to search with linear search? ");
        int target = Utils.readInt(scanner);

        int index = linearSearch(data, target);

        if (index >= 0) {
            System.out.println();
            System.out.println("Found");
        } else {
            System.out.println();
            System.out.println("Not found");
        }
        System.out.println();
    }

    public static void runBinarySearch(Scanner scanner) {
        int[] data = Utils.createFixedArray0to9();

        System.out.print("In the list are values 0, ..., 9; which value would you like to search with binary search? ");
        int target = Utils.readInt(scanner);

        int index = binarySearch(data, target);

        if (index >= 0) {
            System.out.println();
            System.out.println("Found");
        } else {
            System.out.println();
            System.out.println("Not found");
        }
        System.out.println();
    }

    // -------- algorithms --------

    public static int linearSearch(int[] data, int target) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == target) return i;
        }
        return -1;
    }

    public static int binarySearch(int[] data, int target) {
        int left = 0;
        int right = data.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (data[mid] == target) return mid;
            if (data[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}
