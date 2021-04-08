package com.vivek.graph.examples;

/**
 * Given a boolean 2D matrix, find the number of islands. A group of connected 1s forms an island. For example, the below matrix contains 5 islands
 * Example:
 *
 *
 * Input : mat[][] = {{1, 1, 0, 0, 0},
 *                    {0, 1, 0, 0, 1},
 *                    {1, 0, 0, 1, 1},
 *                    {0, 0, 0, 0, 0},
 *                    {1, 0, 1, 0, 1}}
 * Output : 5
 *
 * This is a variation of the standard problem: “Counting the number of connected components in an undirected graph”.
 *
 * A cell in 2D matrix can be connected to 8 neighbours. So, unlike standard DFS(), where we recursively call for all adjacent vertices,
 * here we can recursively call for 8 neighbours only. We keep track of the visited 1s so that they are not visited again.
 */
public class IslandCount {

    static final int ROW = 5;
    static final int COL = 5;

    int countIslands(int[][] matrix) {

        // Make a bool array to mark visited cells.
        // Initially all cells are unvisited
        boolean[][] visited = new boolean[ROW][COL];

        // Initialize count as 0 and traverse through the all cells
        // of given matrix
        int count = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                // If a cell with value 1 is not visited yet, then new island found, Visit all cells in this island and increment island count
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    dfs(matrix, i, j, visited);
                    ++count;
                }
            }
        }

        return count;
    }

    private void dfs(int[][] matrix, int row, int col, boolean[][] visited) {
        // These arrays are used to get row and column numbers of 8 neighbors of a given cell
        int[] rowNbr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colNbr = {-1, 0, 1, -1, 1, -1, 0, 1};

        visited[row][col] = true;

        // Recur for all connected neighbours
        for (int k = 0; k < 8; k++) {
            if (isSafe(matrix, row + rowNbr[k], col + colNbr[k], visited)) {
                dfs(matrix, row + rowNbr[k], col + colNbr[k], visited);
            }
        }
    }

    private boolean isSafe(int[][] matrix, int row, int col, boolean[][] visited) {
        return row >= 0 && row < ROW
                && col >= 0 && col < COL
                && matrix[row][col] == 1
                && !visited[row][col];
    }

    public static void main(String[] args) {
        int[][] m = new int[][] {
                { 1, 1, 0, 0, 0 },
                { 0, 1, 0, 0, 1 },
                { 1, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 0 },
                { 1, 0, 1, 0, 1 }
        };

        IslandCount islands = new IslandCount();

        System.out.println(islands.countIslands(m));
    }

}
