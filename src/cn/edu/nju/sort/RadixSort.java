package cn.edu.nju.sort;

import java.util.Arrays;

public class RadixSort {

    /**
     * Radix sort using counting sort
     * support only non-negative numbers
     * @param arr   arr to sort
     * @param d     binary total bits
     */
    public static void sort(int[] arr, int d) {
        int[] tmp = new int[arr.length];
        for (int i = 0; i < d; i++) {
            CountingSort.countingSort(arr, tmp, 1, i);
            System.arraycopy(tmp, 0, arr, 0, arr.length);
        }
    }

    public static void main(String[] args) {
        int[] arr = {10, 345, 234, 243, 584, 234, 243, 95, 204, 285};
        RadixSort.sort(arr, 10);
        Arrays.stream(arr).forEach(e -> System.out.print(e + " "));
    }
}
