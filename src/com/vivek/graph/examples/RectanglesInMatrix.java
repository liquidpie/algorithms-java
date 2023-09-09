package com.vivek.graph.examples;

import java.util.ArrayList;
import java.util.List;

/**
 * Give a matrix of 1's and 0's. For example, matrix = [
 * ["1","1","1","1","1"],
 * ["1","1","0","0","1"],
 * ["1","1","0","0","1"],
 * ["1","1","1","1","1"]
 * ]
 *
 * Round 1: find the rectangle that is made of 0s, either return the start and end index OR height and length of the rectangle.
 * There is only 1 rectangle in each matrix. I solved it by looping to find the first and last zero.
 *
 * Round 2: same problem but now the matrix may contains many rectangles. Return the start and end indexes of each rectangle in an array.
 *
 * matrix = [
 * ["0","1","1","1","1"],
 * ["1","1","0","0","1"],
 * ["0","1","0","0","1"],
 * ["0","1","1","1","1"],
 * ["1","0","1","1","1"]
 * ] => 4 rectangles in here . 0 by itself is also a rectangle, and vertical rectangle also counts.
 *
 * https://leetcode.com/discuss/interview-question/1062462/indeed-karat-questions
 */
public class RectanglesInMatrix {

    private class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private class Rectangle {
        Point start, end;

        public Rectangle(Point start, Point end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "{(" + start.x + "," + start.y + "),(" + end.x + "," + end.y + ")}";
        }
    }

    List<Rectangle> count(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;

        List<Rectangle> rectangles = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 0 && isStarting(i, j, arr)) {
                    rectangles.add(getCoordinates(i, j, arr));
                }
            }
        }
        return rectangles;
    }

    boolean isStarting(int i, int j, int[][] arr) {
        if (i > 0 && arr[i - 1][j] == 0)
            return false;
        if (j > 0 && arr[i][j - 1] == 0)
            return false;

        return true;
    }

    Rectangle getCoordinates(int r, int c, int[][] arr) {
        Point start = new Point(r, c);
        int i = r, j = c;
        while (i < arr.length && arr[i][c] == 0) i++;
        while (j < arr[0].length && arr[r][j] == 0) j++;

        Point end = new Point(i - 1, j - 1);
        return new Rectangle(start, end);
    }
}
