package cn.edu.nju.sort;

public class QuickSort implements Sort {

    private int partition(int[] arr, int l, int r) {
        int x = arr[r];
        int i = l - 1;
        for (int j = l; j < r; j++) {
            if (arr[j] < x) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, r);
        return i + 1;
    }

    /**
     * Partition算法1
     */
    private int partition1(int[] arr, int l, int r) {
        int i = l, j = r + 1;
        while (true) {
            while (arr[++i] < arr[l] && i < r);
            while (arr[--j] >= arr[l] && j > l);
            if (i >= j)
                break;
            swap(arr, i, j);
        }
        swap(arr, l, j);
        return j;
    }

    /**
     * Partition算法2，不用swap函数
     */
    private int partition2(int[] arr, int l, int r) {
        int pivot = arr[l];
        while (l < r) {
            // 此处为大于等于，发现小于基准时放到左边
            while (l < r && arr[r] >= pivot) r--;
            arr[l] = arr[r];
            // 此处为小于，大于等于基准时放到右边
            while (l < r && arr[l] < pivot) l++;
            arr[r] = arr[l];
        }
        // 退出循环时，i、j相等，将此位置填入基准
        arr[l] = pivot;
        return l;
}

    private void sort(int[] arr, int left, int right) {
        if (left >= right)
            return;
        int p = partition(arr, left, right);
        sort(arr, left, p - 1);
        sort(arr, p + 1, right);
    }

    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

}
