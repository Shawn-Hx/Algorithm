package cn.edu.nju.random;

import java.util.*;

/**
 * LeetCode 380. Insert Delete GetRandom O(1)
 */
public class RandomizedSet {

    private final List<Integer> values;
    private final Map<Integer, Integer> indexMap;
    private final Random random;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        values = new ArrayList<>();
        indexMap = new HashMap<>();
        random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (indexMap.containsKey(val))
            return false;
        values.add(val);
        indexMap.put(val, values.size() - 1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!indexMap.containsKey(val))
            return false;
        int index = indexMap.get(val);
        int last = values.get(values.size() - 1);
        values.set(index, last);
        indexMap.put(last, index);
        values.remove(values.size() - 1);
        indexMap.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return values.get(random.nextInt(values.size()));
    }
}
