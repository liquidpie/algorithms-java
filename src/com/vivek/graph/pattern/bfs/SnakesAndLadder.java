package com.vivek.graph.pattern.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 909. Snakes and Ladders
 *
 * You are given an n x n integer matrix board where the cells are labeled from 1 to n2 in a Boustrophedon style starting from the bottom left of the board (i.e. board[n - 1][0]) and alternating direction each row.
 *
 * You start on square 1 of the board. In each move, starting from square curr, do the following:
 *
 *     Choose a destination square next with a label in the range [curr + 1, min(curr + 6, n2)].
 *         This choice simulates the result of a standard 6-sided die roll: i.e., there are always at most 6 destinations, regardless of the size of the board.
 *     If next has a snake or ladder, you must move to the destination of that snake or ladder. Otherwise, you move to next.
 *     The game ends when you reach the square n2.
 *
 * A board square on row r and column c has a snake or ladder if board[r][c] != -1. The destination of that snake or ladder is board[r][c]. Squares 1 and n2 are not the starting points of any snake or ladder.
 *
 * Note that you only take a snake or ladder at most once per dice roll. If the destination to a snake or ladder is the start of another snake or ladder, you do not follow the subsequent snake or ladder.
 *
 *     For example, suppose the board is [[-1,4],[-1,3]], and on the first move, your destination square is 2. You follow the ladder to square 3, but do not follow the subsequent ladder to 4.
 *
 * Return the least number of dice rolls required to reach the square n2. If it is not possible to reach the square, return -1.
 *
 * Example 1:
 *
 * Input: board = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
 * Output: 4
 * Explanation:
 * In the beginning, you start at square 1 (at row 5, column 0).
 * You decide to move to square 2 and must take the ladder to square 15.
 * You then decide to move to square 17 and must take the snake to square 13.
 * You then decide to move to square 14 and must take the ladder to square 35.
 * You then decide to move to square 36, ending the game.
 * This is the lowest possible number of moves to reach the last square, so return 4.
 *
 * Example 2:
 *
 * Input: board = [[-1,-1],[-1,3]]
 * Output: 1
 *
 * Solution:
 *
 * Using BFS
 *
 *     Let's break the down the approach into 3 simple steps, kindly check out the example walkthrough for sure as it'll make the problem seem much simpler.
 *
 * 1️⃣ Step 1 → Initialize Distances & Queue
 *
 *     We initialize array min_rolls, where min_rolls[i] is the minimum number of rolls required to reach cell i.
 *     We start with min_rolls[1] = 0 as we're initially at cell 1.
 *     A queue is also used to here which assists us in the BFS.
 *
 * int[] min_rolls = new int[n * n + 1];
 * Arrays.fill(min_rolls, -1);
 * Queue<Integer> q = new LinkedList<>();
 * min_rolls[1] = 0;
 * q.offer(1);
 *
 * 2️⃣ Step 2 → BFS
 *
 *     While the queue isn't empty, we pop the front square of it and for each outcome after rolling the die (from 1 to 6), we use t to store the move.
 *     We then find the row and col of our position using row and cols which converts our location to 0 based indexing and computes then from bottom and left respectively.
 *
 * while (!q.isEmpty()) {
 *     int x = q.poll();
 *     for (int i = 1; i <= 6 && x + i <= n * n; i++) {
 *         int t = x + i;
 *         int row = (t - 1) / n;
 *         int col = (t - 1) % n;
 *         int v = board[n - 1 - row][(row % 2 == 1) ? (n - 1 - col) : col];
 *         int y = (v > 0 ? v : t);
 *
 * 3️⃣ Step 3 → Reached the end
 *
 *     We use this condition to check that if we've reached the n2 cell then we simply terminate our bfs and return min_rolls[x] + 1.
 *     Else we set d[y] = d[x] + 1 and continue with our bfs.
 *
 * if (y == n * n) return min_rolls[x] + 1;
 * if (min_rolls[y] == -1) {
 *     min_rolls[y] = min_rolls[x] + 1;
 *     q.offer(y);
 * }
 *
 * https://leetcode.com/problems/snakes-and-ladders/solutions/6797437/using-bfs-with-images-example-walkthroug-22bs/?envType=study-plan-v2&envId=top-interview-150
 *
 * Reference: https://leetcode.com/problems/snakes-and-ladders
 */
public class SnakesAndLadder {

    public static void main(String[] args) {
        SnakesAndLadder helper = new SnakesAndLadder();
        int[][] board = {
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,35,-1,-1,13,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,15,-1,-1,-1,-1}
        };
        System.out.println(helper.snakesAndLadders(board));
    }

    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] minRolls = new int[n * n + 1];
        Arrays.fill(minRolls, -1);
        minRolls[1] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        while (!queue.isEmpty()) {
            int s = queue.poll();
            for (int i = 1; i <= 6 && s + i <= n * n; i++) {
                int t = s + i;
                int row = (t - 1) / n;
                int col = (t - 1) % n;
                int v = board[n - 1 - row][(row % 2 == 1) ? (n - 1 - col) : col];
                t = v > 0 ? v : t;

                if (t == n * n) {
                    return minRolls[s] + 1;
                } else if (minRolls[t] == -1) {
                    minRolls[t] = minRolls[s] + 1;
                    queue.add(t);
                }
            }
        }

        return minRolls[n * n];
    }

}
