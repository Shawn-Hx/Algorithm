package cn.edu.nju.sort;

public class CountingSort {

    public void countingSort(int[] arr, int[] res, int k) {
        int[] count = new int[k + 1];
        for (int num : arr) {
            assert 0 <= num && num <= k;
            count[num]++;
        }
        for (int i = 1; i <= k; i++) {
            count[i] += count[i - 1];
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            res[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }
    }

}
