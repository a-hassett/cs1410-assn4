//Ally Hassett -- Assignment 4 -- CS 1410

import java.lang.Math;

public class EvaluationDriver {
    static final int MAX_VALUE = 1_000_000;
    static final int MAX_ARRAY_SIZE = 100_000;
    static final int ARRAY_SIZE_START = 20_000;
    static final int ARRAY_SIZE_INCREMENT = 20_000;
    static final int NUMBER_SEARCHES = 50_000;

    public static void main(String[] args){
        //format the printed output for each type
        demoLinearSearchUnsorted();
        demoLinearSearchSorted();
        demoBinarySearchSelectionSort();
        demoBinarySearchFastSort();
    }

    public static void demoLinearSearchUnsorted(){
        int timesFound = 0;

        System.out.println("--- Linear Search Timing (unsorted) ---");

        for(int i = ARRAY_SIZE_START; i <= MAX_ARRAY_SIZE; i+=ARRAY_SIZE_INCREMENT) {
            int[] randomArray = generateNumbers(i, MAX_VALUE);
            long before = System.currentTimeMillis();

            /*generate random number to look for in the array
            if in the array, increase number of successful finds*/
            for(int k = 0; k < NUMBER_SEARCHES; k++){
                int key = (int)(Math.random() * MAX_VALUE);
                if(linearSearch(randomArray, key)){
                    timesFound += 1;
                }
            }
            long after = System.currentTimeMillis();

            //format the printed output
            System.out.printf("%-22s: %d\n", "Number of items", i);
            System.out.printf("%-22s: %d\n", "Times value was found", timesFound);
            System.out.printf("%-22s: %d ms\n\n", "Total search time", (int)(after - before));
        }
    }

    public static void demoLinearSearchSorted(){
        int timesFound = 0;

        System.out.println("--- Linear Search Timing (Selection Sort) ---");

        for(int i = ARRAY_SIZE_START; i <= MAX_ARRAY_SIZE; i+=ARRAY_SIZE_INCREMENT) {
            int[] randomArray = generateNumbers(i, MAX_VALUE);
            long before = System.currentTimeMillis();
            selectionSort(randomArray);

            /*generate random number to look for in the array
            if in the array, increase number of successful finds*/
            for(int k = 0; k < NUMBER_SEARCHES; k++){
                int key = (int)(Math.random() * MAX_VALUE);
                if(linearSearch(randomArray, key)){
                    timesFound += 1;
                }
            }
            long after = System.currentTimeMillis();

            //format the printed output
            System.out.printf("%-22s: %d\n", "Number of items", i);
            System.out.printf("%-22s: %d\n", "Times value was found", timesFound);
            System.out.printf("%-22s: %d ms\n\n", "Total search time", (int)(after - before));
        }
    }

    public static void demoBinarySearchSelectionSort(){
        int timesFound = 0;

        System.out.println("--- Binary Search Timing (Selection Sort) ---");

        for(int i = ARRAY_SIZE_START; i <= MAX_ARRAY_SIZE; i+=ARRAY_SIZE_INCREMENT) {
            int[] randomArray = generateNumbers(i, MAX_VALUE);
            long before = System.currentTimeMillis();
            selectionSort(randomArray);

            /*generate random number to look for in the array
            if in the array, increase number of successful finds*/
            for(int k = 0; k < NUMBER_SEARCHES; k++){
                int key = (int)(Math.random() * MAX_VALUE);
                if(binarySearch(randomArray, key)){
                    timesFound += 1;
                }
            }
            long after = System.currentTimeMillis();

            //format the printed output
            System.out.printf("%-22s: %d\n", "Number of items", i);
            System.out.printf("%-22s: %d\n", "Times value was found", timesFound);
            System.out.printf("%-22s: %d ms\n\n", "Total search time", (int)(after - before));
        }
    }

    public static void demoBinarySearchFastSort(){
        int timesFound = 0;

        System.out.println("--- Binary Search Timing (Arrays.sort) ---");

        for(int i = ARRAY_SIZE_START; i <= MAX_ARRAY_SIZE; i+=ARRAY_SIZE_INCREMENT) {
            int[] randomArray = generateNumbers(i, MAX_VALUE);
            long before = System.currentTimeMillis();
            java.util.Arrays.sort(randomArray);

            /*generate random number to look for in the array
            if in the array, increase number of successful finds*/
            for(int k = 0; k < NUMBER_SEARCHES; k++){
                int key = (int)(Math.random() * MAX_VALUE);
                if(binarySearch(randomArray, key)){
                    timesFound += 1;
                }
            }
            long after = System.currentTimeMillis();

            //format the printed output
            System.out.printf("%-22s: %d\n", "Number of items", i);
            System.out.printf("%-22s: %d\n", "Times value was found", timesFound);
            System.out.printf("%-22s: %d ms\n\n", "Total search time", (int)(after - before));
        }
    }

    public static int[] generateNumbers(int howMany, int maxValue){
        //error situation
        if (howMany < 1){
            return null;
        }

        //create array with howMany items in the range of 0 to maxValue
        int[] randomArray = new int[howMany];
        for(int i = 0; i < howMany; i++){
            randomArray[i] = (int)(Math.random() * maxValue);
        }

        return randomArray;
    }

    public static boolean linearSearch(int[] data, int search){
        //search each item in the array for the one we want
        for(int i = 0; i < data.length; i++){
            if (data[i] == search){
                return true;
            }
        }
        return false;
    }

    public static boolean binarySearch(int[] data, int search){
        int low = 0;
        int high = data.length - 1;
        int mid = 0;

        //if desired value isn't the middle one of current set, return correct half of the set and try again
        while(low <= high) {
            mid = (high + low) / 2;
            if (data[mid] == search) {
                return true;
            } else if (data[mid] > search) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

    public static void selectionSort(int[] data){
        for(int i = 0; i < data.length; i++){
            for(int j = i + 1; j < data.length; j++) {
                if (data[j] < data[i]) {
                    int temp = data[j];
                    data[j] = data[i];
                    data[i] = temp;
                }
            }
        }
    }
}
