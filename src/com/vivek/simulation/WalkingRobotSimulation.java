package com.vivek.simulation;

import java.util.HashSet;
import java.util.Set;

/**
 * 874. Walking Robot Simulation
 *
 * A robot on an infinite XY-plane starts at point (0, 0) facing north. The robot receives an array of integers commands,
 * which represents a sequence of moves that it needs to execute. There are only three possible types of instructions the robot can receive:
 *
 *     -2: Turn left 90 degrees.
 *     -1: Turn right 90 degrees.
 *     1 <= k <= 9: Move forward k units, one unit at a time.
 *
 * Some of the grid squares are obstacles. The ith obstacle is at grid point obstacles[i] = (xi, yi).
 * If the robot runs into an obstacle, it will stay in its current location (on the block adjacent to the obstacle) and move onto the next command.
 * Return the maximum squared Euclidean distance that the robot reaches at any point in its path (i.e. if the distance is 5, return 25).
 *
 * Note:
 *     There can be an obstacle at (0, 0). If this happens, the robot will ignore the obstacle until it has moved off the origin.
 *     However, it will be unable to return to (0, 0) due to the obstacle.
 *     North means +Y direction.
 *     East means +X direction.
 *     South means -Y direction.
 *     West means -X direction.
 *
 * Example 1:
 * Input: commands = [4,-1,3], obstacles = []
 * Output: 25
 *
 * Explanation:
 * The robot starts at (0, 0):
 *
 *     Move north 4 units to (0, 4).
 *     Turn right.
 *     Move east 3 units to (3, 4).
 *
 * The furthest point the robot ever gets from the origin is (3, 4), which squared is 3^2 + 4^2 = 25 units away.
 *
 * Example 2:
 * Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 * Output: 65
 *
 * Explanation:
 * The robot starts at (0, 0):
 *
 *     Move north 4 units to (0, 4).
 *     Turn right.
 *     Move east 1 unit and get blocked by the obstacle at (2, 4), robot is at (1, 4).
 *     Turn left.
 *     Move north 4 units to (1, 8).
 *
 * The furthest point the robot ever gets from the origin is (1, 8), which squared is 1^2 + 8^2 = 65 units away.
 *
 * Example 3:
 * Input: commands = [6,-1,-1,6], obstacles = [[0,0]]
 * Output: 36
 *
 * Explanation:
 *
 * The robot starts at (0, 0):
 *
 *     Move north 6 units to (0, 6).
 *     Turn right.
 *     Turn right.
 *     Move south 5 units and get blocked by the obstacle at (0,0), robot is at (0, 1).
 *
 * The furthest point the robot ever gets from the origin is (0, 6), which squared is 6^2 = 36 units away.
 *
 * Complexity Analysis
 *
 * Let m and n be the length of commands and obstacles, respectively.
 *
 *     Time complexity: O(m+n)
 *          The algorithm initially iterates over the obstacles array and hashes each obstacle’s coordinates, taking O(n) time.
 *          The algorithm then loops over the commands array. In the worst case, each command is a positive integer k.
 *          Since the maximum value of k is limited to 9, this step has a time complexity of O(9⋅m)=O(m).
 *
 *     Thus, the overall time complexity of the algorithm is O(n)+O(m)=O(m+n).
 *
 *     Space complexity: O(n)
 *          The only additional space used by the algorithm is the obstacleSet, which stores up to n hashed obstacle positions.
 *          The directions and currentPosition arrays and all other primitive variables use constant space.
 *
 *     Thus, the space complexity of the algorithm is O(n).
 *
 * Reference:
 * https://leetcode.com/problems/walking-robot-simulation/
 */
public class WalkingRobotSimulation {

    public static void main(String[] args) {
        System.out.println(robotSim(new int[]{4, -1, 3}, new int[][]{})); // Output: 25
        System.out.println(robotSim(new int[]{4, -1, 4, -2, 4}, new int[][]{{2, 4}})); // Output: 65
        System.out.println(robotSim(new int[]{6, -1, -1, 6}, new int[][]{{0, 0}})); // Output: 36
    }

    static int robotSim(int[] commands, int[][] obstacles) {
        Set<String> obstaclesSet = new HashSet<>();
        for (int[] obstacle : obstacles)
            obstaclesSet.add(obstacle[0] + "_" + obstacle[1]);

        int x = 0;
        int y = 0;
        int direction = 0; // N-0, E-1, S-2, W-3

        int farthestDist = 0;

        for (int command : commands) {
            if (command == -1)
                direction = (direction + 1) % 4;
            else if (command == -2)
                direction = (direction + 3) % 4;
            else {
                for (int i = 0; i < command; i++) {
                    int px = x;
                    int py = y;
                    switch (direction) {
                        case 0 -> y++;
                        case 1 -> x++;
                        case 2 -> y--;
                        case 3 -> x--;
                    }
                    if (obstaclesSet.contains(x + "_" + y)) {
                        x = px; y = py;
                        break;
                    }
                    farthestDist = Math.max(farthestDist, (x * x) + (y * y));
                }
            }
        }

        return farthestDist;
    }

}
