package com.vivek.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 'K' Closest Points to the Origin (easy)
 *
 * Given an array of points in the a 2D plane, find ‘K’ closest points to the origin.
 *
 * Example 1:
 * Input: points = [[1,2],[1,3]], K = 1
 * Output: [[1,2]]
 * Explanation: The Euclidean distance between (1, 2) and the origin is sqrt(5).
 * The Euclidean distance between (1, 3) and the origin is sqrt(10).
 * Since sqrt(5) < sqrt(10), therefore (1, 2) is closer to the origin.
 *
 * Example 2:
 * Input: point = [[1, 3], [3, 4], [2, -1]], K = 2
 * Output: [[1, 3], [2, -1]]
 *
 * The Euclidean distance of a point P(x,y) from the origin can be calculated through the following formula:
 * √x2 + y2
 */
public class KClosestPointsToOrigin {

    static List<Point> kClosestPoints(List<Point> points, int k) {
        PriorityQueue<Point> maxheap = new PriorityQueue<>((p1, p2) -> p2.distanceFromOrigin() - p1.distanceFromOrigin());

        for (int i = 0; i < k; i++) {
            maxheap.add(points.get(i));
        }

        for (int i = k; i < points.size(); i++) {
            if (points.get(i).distanceFromOrigin() < maxheap.peek().distanceFromOrigin()) {
                maxheap.poll();
                maxheap.add(points.get(i));
            }
        }

        return new ArrayList<>(maxheap);

    }

    public static void main(String[] args) {
        List<Point> points = List.of(new Point(1, 3), new Point(3, 4), new Point(2, -1));
        List<Point> result = kClosestPoints(points, 2);
        System.out.print("Here are the k points closest the origin: ");
        for (Point p : result)
            System.out.print("[" + p.x + " , " + p.y + "] ");
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int distanceFromOrigin() {
            return x * x + y * y;
        }
    }

}
