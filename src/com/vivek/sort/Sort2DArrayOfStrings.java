package com.vivek.sort;

import java.util.Arrays;

/**
 * To sort a 2D array of strings, you can decide the sorting criteria—such as sorting row-wise, column-wise,
 * or flattening the array into a 1D array and sorting it entirely.
 */
public class Sort2DArrayOfStrings {

    public static void main(String[] args) {
        {
            String[][] array = {
                    {"cat", "apple", "banana"},
                    {"dog", "elephant", "ant"}
            };
            rowWiseSort(array);
            print(array);
        }
        {
            String[][] array = {
                    {"cat", "apple", "banana"},
                    {"dog", "elephant", "ant"}
            };
            columnWiseSort(array);
            print(array);
        }
        {
            String[][] array = {
                    {"cat", "apple", "banana"},
                    {"dog", "elephant", "ant"}
            };
            flattenAndSort(array);
            print(array);
        }
        {
            String[][] array = {
                    {"cat", "apple", "banana"},
                    {"dog", "elephant", "ant"}
            };
            flattenAndSortUsingExtraSpace(array);
            print(array);
        }
    }

    static void rowWiseSort(String[][] array) {
        for (String[] row : array) {
            Arrays.sort(row);
        }
    }

    static void columnWiseSort(String[][] array) {
        // transpose the array
        int rows = array.length;
        int cols = array[0].length;
        String[][] transposed = new String[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = array[i][j];
            }
        }

        // row wise sorting
        rowWiseSort(transposed);

        // transpose the sorted array back
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                array[j][i] = transposed[i][j];
            }
        }
    }

    /**
     * Following uses a bubble sort
     */
    static void flattenAndSort(String[][] array) {
        int rows = array.length;
        int cols = array[0].length;

        for (int i = 0; i < rows * cols - 1; i++) {
            for (int j = i + 1; j < rows * cols; j++) {
                int row1 = i / cols;
                int col1 = i % cols;
                int row2 = j / cols;
                int col2 = j % cols;

                if (array[row1][col1].compareTo(array[row2][col2]) > 0) {
                    String temp = array[row1][col1];
                    array[row1][col1] = array[row2][col2];
                    array[row2][col2] = temp;
                }
            }
        }
    }

    static void flattenAndSortUsingExtraSpace(String[][] array) {
        int rows = array.length;
        int cols = array[0].length;
        String[] flattened = new String[rows + cols + 1];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                flattened[i * cols + j] = array[i][j];
            }
        }

        Arrays.sort(flattened);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = flattened[i * cols + j];
            }
        }
    }

    private static void print(String[][] array) {
        for (String[] row : array) {
            System.out.println(Arrays.toString(row));
        }
    }

}
