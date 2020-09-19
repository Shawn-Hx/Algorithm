package cn.edu.nju.heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LeetCode 295. Find Median from Data Stream
 */
public class MedianFinder {

    private final Queue<Integer> high;
    private final Queue<Integer> low;

    public MedianFinder() {
        // using "(o1, o2) -> o2 - o1" may exceed the range of Integer!
        // should use Comparator.reverseOrder()
        low = new PriorityQueue<>(Comparator.reverseOrder());
        high = new PriorityQueue<>(Comparator.comparingInt(o -> o));
    }

    public void addNum(int num) {
        // maintain low.size == high.size or low.size + 1 == high.size
        if (low.size() == high.size()) {
            low.add(num);
            high.add(low.remove());
        } else {
            high.add(num);
            low.add(high.remove());
        }
    }

    public double findMedian() {
        if (low.size() < high.size())
            return high.peek();
        // in case result exceed the range of Integer
        return ((double)low.peek() + high.peek()) / 2.0d;
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(2147483647);
        medianFinder.addNum(2147483647);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-2147483648);
        System.out.println(medianFinder.findMedian());
    }

}
