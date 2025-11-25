package project;

import java.util.Scanner;

/**
 * Main menu UI.
 * UI strings are matched to the given example screenshots.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();

            String choice = scanner.nextLine().trim();

            if (choice.equalsIgnoreCase("q")) {
                break;
            }

            switch (choice) {
                case "1" -> SearchAlgorithms.runLinearSearch(scanner);
                case "2" -> SearchAlgorithms.runBinarySearch(scanner);
                case "3" -> SortAlgorithms.runInsertionSortDemo();
                case "4" -> SortAlgorithms.runQuicksortDemo();
                case "5" -> PerformanceTest.runPerformanceTest(scanner);
                default -> {
                    // In example there is no explicit error line shown,
                    // so we keep behavior silent (just loop again).
                }
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("Menu of Searching and Sorting Testbed.");
        System.out.println();
        System.out.println("1)\tLinear searching");
        System.out.println("2)\tBinary searching");
        System.out.println("3)\tn^2 type of sorting");
        System.out.println("4)\tn * log(n) type of sorting");
        System.out.println("5)\tSorting performance");
        System.out.println();
        System.out.println("q/Q) Quit");
        System.out.println();
        System.out.print("Your choice: ");
    }
}
