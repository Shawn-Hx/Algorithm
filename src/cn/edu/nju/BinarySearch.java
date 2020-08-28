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

    /**
     * 二分查找变体，若存在重复元素，返回最右边元素下标
     */
    public static int searchRight(int[] arr, int key) {
        int i = 0, j = arr.length - 1;
        while (i < j) {
            int mid = i + ((j - i + 1) >> 1);   // 此处必须有 + 1，目的是当i，j相邻时让mid指向j位置
            if (arr[mid] > key)
                j = mid - 1;
            else if (arr[mid] < key)
                i = mid + 1;
            else
                i = mid;
        }
        if (arr[i] == key) {
            return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 3, 3, 4, 5, 6, 8, 10, 11};
        System.out.println(searchRight(arr, 2));
    }
}
