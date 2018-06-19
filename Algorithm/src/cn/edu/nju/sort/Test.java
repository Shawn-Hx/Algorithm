package cn.edu.nju.sort;

import java.util.Arrays;
import java.util.Random;

public class Test {

    private static int[] generateRandomArray() {
        return generateRandomArray(32, 128);
    }

    private static int[] generateRandomArray(int number, int range) {
        int[] arr = new int[number];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(range);
        }
        return arr;
    }

    private static void testSort(Sort sort) {
        int[] arr = generateRandomArray();
        sort.sort(arr);
        Arrays.stream(arr).forEach(e -> System.out.print(e + " "));
    }

    private static long computeTime(int[] arr, Sort sort) {
        long begin = System.currentTimeMillis();
        sort.sort(arr);
        return System.currentTimeMillis() - begin;
    }

    public static void main(String[] args) {
        int[] arr = generateRandomArray(1 << 16, 1 << 20);
        System.out.println("快速排序：\t" + computeTime(arr, new QuickSort()));
        arr = generateRandomArray(1 << 16, 1 << 20);
        System.out.println("堆排序：\t\t" + computeTime(arr, new HeapSort()));
        arr = generateRandomArray(1 << 16, 1 << 20);
        System.out.println("归并排序：\t" + computeTime(arr, new MergeSort()));
        arr = generateRandomArray(1 << 16, 1 << 20);
        System.out.println("插入排序：\t" + computeTime(arr, new InsertionSort()));
    }
}
