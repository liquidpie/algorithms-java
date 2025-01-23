package com.vivek.simulation;

import java.util.Scanner;

/**
 * Created by VJaiswal on 15/06/17.
 */
public class BotBoardMoves {

    enum MOVES {LEFT, RIGHT, UP, DOWN}

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();
            in.nextLine();
            char[][] matrix = new char[n][n];
            Point m = null;
            Point p = null;
            for (int i = 0; i < n; i++) {
                String s = in.nextLine().trim();
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = s.charAt(j);
                    if (matrix[i][j] == 'm') {
                        m = new Point(i, j);
                    }
                    if (matrix[i][j] == 'p') {
                        p = new Point(i, j);
                    }
                }
            }

            minMoves(matrix, n, m, p);
        }
    }

    static void minMoves(char[][] matrix, int n, Point m, Point p) {
        while (p.i > m.i) {
            System.out.println(MOVES.DOWN.toString());
            m.i++;
        }
        while (p.i < m.i) {
            System.out.println(MOVES.UP.toString());
            m.i--;
        }
        while (p.j > m.j) {
            System.out.println(MOVES.RIGHT.toString());
            m.j++;
        }
        while (p.j < m.j) {
            System.out.println(MOVES.LEFT.toString());
            m.j--;
        }
    }

    static class Point {
        int i;
        int j;
        Point(int x, int y) {
            i = x;
            j = y;
        }
    }

}
