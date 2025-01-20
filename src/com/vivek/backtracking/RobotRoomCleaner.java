package com.vivek.backtracking;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 489. Robot Room Cleaner
 *
 * You are controlling a robot that is located somewhere in a room. The room is modeled as an m x n binary grid where 0 represents a wall and 1 represents an empty slot.
 *
 * The robot starts at an unknown location in the room that is guaranteed to be empty, and you do not have access to the grid,
 * but you can move the robot using the given API Robot.
 *
 * You are tasked to use the robot to clean the entire room (i.e., clean every empty cell in the room).
 * The robot with the four given APIs can move forward, turn left, or turn right. Each turn is 90 degrees.
 *
 * When the robot tries to move into a wall cell, its bumper sensor detects the obstacle, and it stays on the current cell.
 *
 * Design an algorithm to clean the entire room using the following APIs:
 *
 * interface Robot {
 *   // returns true if next cell is open and robot moves into the cell.
 *   // returns false if next cell is obstacle and robot stays on the current cell.
 *   boolean move();
 *
 *   // Robot will stay on the same cell after calling turnLeft/turnRight.
 *   // Each turn will be 90 degrees.
 *   void turnLeft();
 *   void turnRight();
 *
 *   // Clean the current cell.
 *   void clean();
 * }
 * Note that the initial direction of the robot will be facing up. You can assume all four edges of the grid are all surrounded by a wall.
 *
 * Custom testing:
 *
 * Example 1:
 * The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded".
 * In other words, you must control the robot using only the four mentioned APIs without knowing the room layout and the initial robot's position.
 *
 * Input: room = [[1,1,1,1,1,0,1,1],[1,1,1,1,1,0,1,1],[1,0,1,1,1,1,1,1],[0,0,0,1,0,0,0,0],[1,1,1,1,1,1,1,1]], row = 1, col = 3
 *
 * 1,1,1,1,1,0,1,1
 * 1,1,1,1,1,0,1,1
 * 1,0,1,1,1,1,1,1
 * 0,0,0,1,0,0,0,0
 * 1,1,1,1,1,1,1,1
 *
 * Output: Robot cleaned all rooms.
 * Explanation: All grids in the room are marked by either 0 or 1.
 * 0 means the cell is blocked, while 1 means the cell is accessible.
 * The robot initially starts at the position of row=1, col=3.
 * From the top left corner, its position is one row below and three columns right.
 *
 * Example 2:
 *
 * Input: room = [[1]], row = 0, col = 0
 * Output: Robot cleaned all rooms.
 *
 * https://codesandbox.io/p/sandbox/quizzical-yonath-j0ixe?file=%2Fsrc%2Findex.js
 *
 * ----------------------------------------------------------------
 * Approach: Spiral Backtracking
 * https://leetcode.com/problems/robot-room-cleaner/editorial
 * ----------------------------------------------------------------
 *
 * Concepts to use
 *
 * Let's use two programming concepts.
 *
 *      1. The first one is called constrained programming.
 *          That basically means to put restrictions after each robot moves. The robot moves, and the cell is marked as visited.
 *          That propagates constraints and helps to reduce the number of combinations to consider.
 *      2. The second one called backtracking.
 *          Let's imagine that after several moves the robot is surrounded by the visited cells.
 *          But several steps before there was a cell that proposed an alternative path.
 *          That path wasn't used and hence the room is not yet cleaned up. What to do? To backtrack.
 *          That means to come back to that cell and to explore the alternative path.
 *
 *
 * Intuition:
 *
 * This solution is based on the same idea as the solving algorithm called right-hand rule.
 * Go forward, cleaning and marking all the cells on the way as visited. At the obstacle turn right, again go forward, etc.
 * Always turn right at the obstacles and then go forward. Consider already visited cells as virtual obstacles.
 *
 * What to do if, after the right turn, there is an obstacle just in front?
 *      Turn right again.
 *
 * How to explore the alternative paths from the cell?
 *      Go back to that cell and then turn right from your last explored direction.
 *
 * When to stop?
 *      Stop when you explored all possible paths, i.e. all 4 directions (up, right, down, and left) for each visited cell.
 *
 * Algorithm:
 *
 * Time to write down the algorithm for the backtrack function backtrack(cell = (0, 0), direction = 0).
 *
 *      - Mark the cell as visited and clean it up.
 *      - Explore 4 directions: up, right, down, and left (the order is important since the idea is always to turn right) :
 *          - Check the next cell in the chosen direction :
 *              - If it's not visited yet and there are no obstacles :
 *                  - Move forward.
 *                  - Explore next cells backtrack(new_cell, new_direction).
 *                  - Backtrack, i.e. go back to the previous cell.
*               - Turn right because now there is an obstacle (or a virtual obstacle) just in front.
 *
 * Complexity Analysis:
 *
 * Time complexity : O(N−M), where N is a number of cells in the room and M is a number of obstacles.
 *
 * We visit each non-obstacle cell once and only once.
 * At each visit, we will check 4 directions around the cell. Therefore, the total number of operations would be 4⋅(N−M).
 * Space complexity : O(N−M), where N is a number of cells in the room and M is a number of obstacles.
 *
 * We employed a hashtable to keep track of whether a non-obstacle cell is visited or not.
 *
 * Reference:
 * https://leetcode.com/problems/robot-room-cleaner/description/
 */
public class RobotRoomCleaner {

    public static void main(String[] args) {
        // Example 1
        int[][] room = {
                {1, 1, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 1, 1, 0, 1, 1},
                {1, 0, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1}
        };
        int row = 1, col = 3;
        Robot robot = new Robot(room, row, col);

        RobotRoomCleaner cleaner = new RobotRoomCleaner();
        cleaner.cleanRoom(robot);

        // Example 2
        room = new int[][]{{1}};
        row = 0;
        col = 0;
    }

    // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
    private int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private Set<Pair> visited = new HashSet<>();
    private Robot robot;

    void cleanRoom(Robot robot) {
        this.robot = robot;
        backtrack(0, 0, 0);
    }

    void backtrack(int row, int col, int d) {
        visited.add(new Pair(row, col));
        robot.clean();
        // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
        for (int i = 0; i < 4; i++) {
            int newD = (d + i) % 4;
            int newRow = row + directions[newD][0];
            int newCol = col + directions[newD][1];

            if (!visited.contains(new Pair(newRow, newCol)) && robot.move()) {
                backtrack(newRow, newCol, newD);
                goBack();
            }
            // turn the robot following chosen direction : clockwise
            robot.turnRight();
        }
    }

    void goBack() {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pair pair)) return false;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Robot {
        private int[][] room;
        private int x, y, direction;
        private static final int[][] DIRECTIONS = {
                {-1, 0}, // up
                {0, 1},  // right
                {1, 0},  // down
                {0, -1}  // left
        };
        private Set<String> cleanedCells;

        // Constructor: Initializes the robot with the room layout
        public Robot(int[][] room, int startX, int startY) {
            this.room = room;
            this.x = startX;
            this.y = startY;
            this.direction = 0; // Starts facing "up"
            this.cleanedCells = new HashSet<>();
        }

        // Move forward; returns true if the robot moves to an open cell
        public boolean move() {
            int newX = x + DIRECTIONS[direction][0];
            int newY = y + DIRECTIONS[direction][1];

            if (newX >= 0 && newX < room.length && newY >= 0 && newY < room[0].length && room[newX][newY] == 1) {
                x = newX;
                y = newY;
                return true;
            }
            return false;
        }

        // Turn left (90 degrees counter-clockwise)
        public void turnLeft() {
            direction = (direction + 3) % 4; // Equivalent to subtracting 1 modulo 4
        }

        // Turn right (90 degrees clockwise)
        public void turnRight() {
            direction = (direction + 1) % 4;
        }

        // Clean the current cell
        public void clean() {
            cleanedCells.add(x + "," + y);
        }

        // Helper to check if a cell has been cleaned (for debugging or testing)
        public boolean isCleaned(int row, int col) {
            return cleanedCells.contains(row + "," + col);
        }

        // Debugging helper to print the cleaned cells
        public void printCleanedCells() {
            System.out.println("Cleaned cells: " + cleanedCells);
        }
    }
}
