package cn.edu.nju.sort;

public class CountingSort {

    /**
     * Counting sort
     * @param arr array to sort
     * @param res res
     * @param k   every num in arr satisfy 0 <= num <= k
     */
    public static void countingSort(int[] arr, int[] res, int k) {
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

    public static void countingSort(int[] arr, int[] res, int k, int d) {
        int[] count = new int[k + 1];
        for (int num : arr) {
            int tmp = num >> d & 1;
            assert 0 <= tmp && tmp <= k;
            count[tmp]++;
        }
        for (int i = 1; i <= k; i++) {
            count[i] += count[i - 1];
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            int tmp = arr[i] >> d & 1;
            res[count[tmp] - 1] = arr[i];
            count[tmp]--;
        }
    }
}
