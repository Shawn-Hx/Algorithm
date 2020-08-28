package cn.edu.nju.sort;

import java.util.Random;

public class Test {

    private static int[] generateRandomArray() {
        return generateRandomArray(32, 128);
    }

    private static int[] generateRandomArray(int length, int range) {
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(range);
        }
        return arr;
    }

    private static long computeTime(int[] arr, Sort sort) {
        long begin = System.nanoTime();
        sort.sort(arr);
        return System.nanoTime() - begin;
    }

    public static void main(String[] args) {
        int[] arr = generateRandomArray(1 << 16, 1 << 20);
        System.out.println("Quick sort:\t\t" + computeTime(arr, new QuickSort()));
        arr = generateRandomArray(1 << 16, 1 << 20);
        System.out.println("Heap sort:\t\t" + computeTime(arr, new HeapSort()));
        arr = generateRandomArray(1 << 16, 1 << 20);
        System.out.println("Merge sort:\t\t" + computeTime(arr, new MergeSort()));
        arr = generateRandomArray(1 << 16, 1 << 20);
        System.out.println("Insertion sort:\t" + computeTime(arr, new InsertionSort()));
    }
}
