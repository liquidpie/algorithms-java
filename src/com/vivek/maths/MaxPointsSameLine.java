package com.vivek.maths;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Count maximum points on same line
 *
 * Given N point on a 2D plane as pair of (x, y) co-ordinates, we need to find maximum number of point which lie on the same line.
 *
 * Examples:
 *
 * Input : points[] = {-1, 1}, {0, 0}, {1, 1},
 *                     {2, 2}, {3, 3}, {3, 4}
 * Output : 4
 * Then maximum number of point which lie on same line are 4, those point are {0, 0}, {1, 1}, {2, 2},{3, 3}
 *
 * We can solve above problem by following approach – For each point p, calculate its slope with other points and use a map
 * to record how many points have same slope, by which we can find out how many points are on same line with p as their one point.
 * For each point keep doing the same thing and update the maximum number of point count found so far.
 *
 * If two point are (x1, y1) and (x2, y2) then their slope will be (y2 – y1) / (x2 – x1) which can be a double value
 * and can cause precision problems. To get rid of the precision problems, we treat slope as pair ((y2 – y1), (x2 – x1))
 * instead of ratio and reduce pair by their gcd before inserting into map.
 *
 * If we use unordered_map in c++ or HashMap in Java for storing the slope pair, then total time complexity of solution will be O(n^2)
 *
 * Reference: https://www.geeksforgeeks.org/count-maximum-points-on-same-line/
 */
public class MaxPointsSameLine {

    static int maxPoints(List<Point> points) {
        // Upto two points all points will be part of the line
        if (points.size() < 3) {
            return points.size();
        }

        int maxPoints = 0;

        for (Point p : points) {
            Map<String, Integer> dict = new HashMap<>();
            int dupes = 0;
            int curMax = 0;

            // pairing with all other points
            for (Point p1: points) {
                String slope = null;
                if (!p.equals(p1)) {
                    if (p1.x == p.x) { // vertical line
                        slope = "INF";
                    } else {
                        slope = String.format("%.2f", (float) (p1.y - p.y) / (p1.x - p.x));
                    }

                    // Increasing the frequency of slope and updating curMax for current point p
                    dict.put(slope, dict.getOrDefault(slope, 0) + 1);
                    curMax = Math.max(curMax, dict.get(slope));

                } else {
                    // if both points are equal, increase duplicates count.
                    // Please note that this will also increment when we map it with itself. We still do it because we will not have to add the extra one at the end.
                    dupes++;
                }
            }
            maxPoints = Math.max(maxPoints, curMax + dupes);
        }

        return maxPoints;
    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) {
        List<Point> points = List.of(new Point(-1, 1), new Point(0, 0), new Point(1,1 ),
                                     new Point(2, 2), new Point(3, 3), new Point(3, 4));

        System.out.println(maxPoints(points));
    }

}
