package cn.edu.nju.twopointers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LeetCode 1610. Maximum Number of Visible Points
 */
public class VisiblePoints {

    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int overlap = 0;
        int x0 = location.get(0), y0 = location.get(1);
        List<Double> angles = new ArrayList<>();
        for (List<Integer> point : points) {
            int x = point.get(0), y = point.get(1);
            if (x == x0 && y == y0)
                overlap++;
            else
                angles.add(Math.atan2(y - y0, x - x0));
        }
        Collections.sort(angles);
        int n = angles.size();
        for (int i = 0; i < n; i++)
            angles.add(angles.get(i) + 2 * Math.PI);
        double range = (double)angle / 180 * Math.PI;
        int j = 0, res = 0;
        for (int i = 0; i < n; i++) {
            while (j < angles.size() && angles.get(j) - angles.get(i) <= range)
                j++;
            res = Math.max(res, j - i);
        }
        return res + overlap;
    }

}
