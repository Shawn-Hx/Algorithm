package cn.edu.nju.sort;

public class QuickSort implements Sort {

    private int partition(int[] arr, int left, int right) {
        int i = left, j = right + 1;
        while (true) {
            while (arr[++i] < arr[left] && i < right);
            while (arr[--j] > arr[left] && j > left);
            if (i >= j)
                break;
            swap(arr, i, j);
        }
        swap(arr, left, j);
        return j;
    }

//    private int partition(int[] arr, int left, int right) {
//        int pivot = arr[left];
//        while (left < right) {
//            while (left < right && arr[right] >= pivot) right--;
//            arr[left] = arr[right];
//            while (left < right && arr[left] <= pivot) left++;
//            arr[right] = arr[left];
//        }
//        arr[left] = pivot;
//        return left;
//    }

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
