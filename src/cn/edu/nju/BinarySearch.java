package cn.edu.nju;

public class BinarySearch {

    /**
     * 标准二分查找
     */
    public static int binarySearch(int[] arr, int key) {
        int i = 0, j = arr.length - 1;
        while (i <= j) {
            int mid = i + ((j - i) >> 1);
            if (arr[mid] == key)
                return mid;
            if (arr[mid] > key)
                j = mid - 1;
            else
                i = mid + 1;
        }
        return -1;
    }

    public static int lowerBound(int[] arr, int key) {
        int l = -1, r = arr.length;
        while (r - l > 1) {
            int mid = l + (r - l >> 1);
            if (arr[mid] >= key) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }

    public static int upperBound(int[] arr, int key) {
        return 0;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 3, 3, 4, 5, 6, 8, 10, 11};
        System.out.println(lowerBound(arr, 1));
    }
}
