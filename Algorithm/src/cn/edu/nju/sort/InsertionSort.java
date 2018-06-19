package cn.edu.nju.sort;

public class InsertionSort implements Sort {

    @Override
    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 边后移边寻找插入位置
            int temp = arr[i], j;
            for (j = i - 1; j >= 0 && arr[j] > temp; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = temp;
        }
    }
}
