package cn.edu.nju.random;

import java.util.*;

/**
 * LeetCode 381. Insert Delete GetRandom O(1) - Duplicates allowed
 */
public class RandomizedCollection {

    private final List<Integer> values;
    private final Map<Integer, Set<Integer>> indicesMap;
    private final Random random;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        values = new ArrayList<>();
        indicesMap = new HashMap<>();
        random = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean res = false;
        values.add(val);
        Set<Integer> locs = indicesMap.getOrDefault(val, new HashSet<>());
        if (locs.isEmpty())
            res = true;
        locs.add(values.size() - 1);
        indicesMap.putIfAbsent(val, locs);
        return res;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        Set<Integer> indices = indicesMap.getOrDefault(val, new HashSet<>());
        if (indices.size() == 0)
            return false;
        int index = indices.iterator().next();
        int last = values.get(values.size() - 1);

        values.set(index, last);
        indices.remove(index);
        indicesMap.get(last).add(index);
        indicesMap.get(last).remove(values.size() - 1);
        values.remove(values.size() - 1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return values.get(random.nextInt(values.size()));
    }
}
