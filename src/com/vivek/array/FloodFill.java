package com.vivek.array;

/**
 * Created by VJaiswal on 22/06/18.
 *
 * In MS-Paint, when we take the brush to a pixel and click,
 * the color of the region of that pixel is replaced with a new selected color.
 * Following is the problem statement to do this task.

 Given a 2D screen, location of a pixel in the screen and a color,
 replace color of the given pixel and all adjacent same colored pixels with the given color.

 Input:
 screen[M][N] = {{1, 1, 1, 1, 1, 1, 1, 1},
                 {1, 1, 1, 1, 1, 1, 0, 0},
                 {1, 0, 0, 1, 1, 0, 1, 1},
                 {1, 2, 2, 2, 2, 0, 1, 0},
                 {1, 1, 1, 2, 2, 0, 1, 0},
                 {1, 1, 1, 2, 2, 2, 2, 0},
                 {1, 1, 1, 1, 1, 2, 1, 1},
                 {1, 1, 1, 1, 1, 2, 2, 1},
                 };
 x = 4, y = 4, newColor = 3
 The values in the given 2D screen indicate colors of the pixels.
 x and y are coordinates of the brush, newColor is the color that
 should replace the previous color on screen[x][y] and all surrounding
 pixels with same color.

 Output:
 Screen should be changed to following.
 screen[M][N] = {{1, 1, 1, 1, 1, 1, 1, 1},
                 {1, 1, 1, 1, 1, 1, 0, 0},
                 {1, 0, 0, 1, 1, 0, 1, 1},
                 {1, 3, 3, 3, 3, 0, 1, 0},
                 {1, 1, 1, 3, 3, 0, 1, 0},
                 {1, 1, 1, 3, 3, 3, 3, 0},
                 {1, 1, 1, 1, 1, 3, 1, 1},
                 {1, 1, 1, 1, 1, 3, 3, 1},
                 };

 https://www.geeksforgeeks.org/flood-fill-algorithm-implement-fill-paint/

 */
public class FloodFill {

    static void floodFill(int[][] screen, int x, int y, int oldVal, int newVal) {
        int n = screen.length;

        // coordinates are outside the screen
        if ((x < 0 || x >= n) || (y < 0 || y >= n)) {
            return;
        }

        if (screen[x][y] != oldVal) {
            return;
        }

        screen[x][y] = newVal;

        floodFill(screen, x + 1, y, oldVal, newVal);
        floodFill(screen, x - 1, y, oldVal, newVal);
        floodFill(screen, x, y + 1, oldVal, newVal);
        floodFill(screen, x, y - 1, oldVal, newVal);

    }

    private static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] screen = {
                {1, 1, 1, 1, 1, 1, 1, 2},
                {1, 1, 1, 1, 1, 1, 0, 0},
                {1, 0, 0, 1, 1, 0, 1, 1},
                {1, 2, 2, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 2, 2, 0},
                {1, 1, 1, 1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1, 2, 2, 1},
        };

        int x = 4;
        int y = 4;
        int oldVal = 2;
        int newVal = 3;

        floodFill(screen, x, y, oldVal, newVal);

        print(screen);
    }

}
