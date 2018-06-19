package cn.edu.nju.sort;

public class HeapSort implements Sort {

    private void upToBottom(int[] arr, int start, int size) {
        int value = arr[start];
        int index = start * 2 + 1;
        while (index < size) {
            if (index + 1 < size && arr[index + 1] > arr[index])
                index ++;
            if (value >= arr[index])
                break;
            arr[start] = arr[index];
            start = index;
            index = start * 2 + 1;
        }
        arr[start] = value;
    }

    @Override
    public void sort(int[] arr) {
        int n = arr.length;
        // 建堆
        for (int i = n / 2 - 1; i >= 0; i--)
            upToBottom(arr, i, n);
        // 排序
        for (int i = n - 1; i >= 1; i--) {
            swap(arr, 0, i);
            upToBottom(arr, 0, i);
        }
    }

}
