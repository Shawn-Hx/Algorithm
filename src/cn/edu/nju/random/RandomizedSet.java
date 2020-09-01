package cn.edu.nju.random;

import java.util.*;

/**
 * LeetCode 380. Insert Delete GetRandom O(1)
 */
public class RandomizedSet {

    private List<Integer> values;
    private Map<Integer, Integer> indexMap;
    private Random random;

    public RandomizedSet() {
        values = new ArrayList<>();
        indexMap = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (indexMap.containsKey(val))
            return false;
        values.add(val);
        indexMap.put(val, values.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!indexMap.containsKey(val))
            return false;
        int loc = indexMap.get(val);
        int last = values.get(values.size() - 1);
        values.set(loc, last);
        indexMap.put(last, loc);
        values.remove(values.size() - 1);
        indexMap.remove(val);
        return true;
    }

    public int getRandom() {
        return values.get(random.nextInt(values.size()));
    }
}
