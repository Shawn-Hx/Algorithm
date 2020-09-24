package cn.edu.nju;

public class BinarySearch {

    /**
     * 标准二分查找
     */
    public static int binarySearch(int[] arr, int key) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l >> 1);
            if (arr[mid] == key)
                return mid;
            if (arr[mid] > key)
                r = mid - 1;
            else
                l = mid + 1;
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
        int l = -1, r = arr.length;
        while (r - l > 1) {
            int mid = l + (r - l >> 1);
            if (arr[mid] <= key) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return r;
    }
    
}
