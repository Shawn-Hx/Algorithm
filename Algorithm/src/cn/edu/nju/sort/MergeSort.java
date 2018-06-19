package cn.edu.nju.sort;

public class MergeSort implements Sort {

    private void merge(int[] arr, int l1, int r1, int l2, int r2) {
        int left1 = l1, left2 = l2;
        int[] a = new int[r1 - l1 + r2 - l2 + 2];
        int p = 0;
        while (l1 <= r1 && l2 <= r2) {
            if (arr[l1] <= arr[l2])
                a[p++] = arr[l1++];
            else
                a[p++] = arr[l2++];
        }
        while (l1 <= r1)
            a[p++] = arr[l1++];
        while (l2 <= r2)
            a[p++] = arr[l2++];
        p = 0;
        for (int i = left1; i <= r1; i++)
            arr[i] = a[p++];
        for (int i = left2; i <= r2; i++)
            arr[i] = a[p++];
    }

    private void sort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        sort(arr, left, mid);
        sort(arr, mid + 1, right);
        merge(arr, left, mid, mid + 1, right);
    }

    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

}
