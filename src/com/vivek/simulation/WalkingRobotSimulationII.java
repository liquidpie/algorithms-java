package com.vivek.simulation;

import java.util.Arrays;

/**
 * 2069. Walking Robot Simulation II
 *
 * A width x height grid is on an XY-plane with the bottom-left cell at (0, 0) and the top-right cell at (width - 1, height - 1).
 * The grid is aligned with the four cardinal directions ("North", "East", "South", and "West"). A robot is initially at cell (0, 0) facing direction "East".
 *
 * The robot can be instructed to move for a specific number of steps. For each step, it does the following.
 *
 *     1. Attempts to move forward one cell in the direction it is facing.
 *     2. If the cell the robot is moving to is out of bounds, the robot instead turns 90 degrees counterclockwise and retries the step.
 *
 * After the robot finishes moving the number of steps required, it stops and awaits the next instruction.
 *
 * Implement the Robot class:
 *
 *     - Robot(int width, int height) Initializes the width x height grid with the robot at (0, 0) facing "East".
 *     - void step(int num) Instructs the robot to move forward num steps.
 *     - int[] getPos() Returns the current cell the robot is at, as an array of length 2, [x, y].
 *     - String getDir() Returns the current direction of the robot, "North", "East", "South", or "West".
 *
 *
 * Input:
 * ["Robot", "step", "step", "getPos", "getDir", "step", "step", "step", "getPos", "getDir"]
 * [[6, 3], [2], [2], [], [], [2], [1], [4], [], []]
 * Output
 * [null, null, null, [4, 0], "East", null, null, null, [1, 2], "West"]
 *
 * Explanation:
 * Robot robot = new Robot(6, 3); // Initialize the grid and the robot at (0, 0) facing East.
 * robot.step(2);  // It moves two steps East to (2, 0), and faces East.
 * robot.step(2);  // It moves two steps East to (4, 0), and faces East.
 * robot.getPos(); // return [4, 0]
 * robot.getDir(); // return "East"
 * robot.step(2);  // It moves one step East to (5, 0), and faces East.
 *                 // Moving the next step East would be out of bounds, so it turns and faces North.
 *                 // Then, it moves one step North to (5, 1), and faces North.
 * robot.step(1);  // It moves one step North to (5, 2), and faces North (not West).
 * robot.step(4);  // Moving the next step North would be out of bounds, so it turns and faces West.
 *                 // Then, it moves four steps West to (1, 2), and faces West.
 * robot.getPos(); // return [1, 2]
 * robot.getDir(); // return "West"
 *
 * Reference:
 * https://leetcode.com/problems/walking-robot-simulation-ii/
 */
public class WalkingRobotSimulationII {

    public static void main(String[] args) {
        Robot robot = new Robot(6, 3); // Initialize the grid and the robot at (0, 0) facing East.
        robot.step(2);  // It moves two steps East to (2, 0), and faces East.
        robot.step(2);  // It moves two steps East to (4, 0), and faces East.
        System.out.println(Arrays.toString(robot.getPos())); // return [4, 0]
        System.out.println(robot.getDir()); // return "East"
        robot.step(2);  // It moves one step East to (5, 0), and faces East.
        // Moving the next step East would be out of bounds, so it turns and faces North.
        // Then, it moves one step North to (5, 1), and faces North.
        robot.step(1);  // It moves one step North to (5, 2), and faces North (not West).
        robot.step(4);  // Moving the next step North would be out of bounds, so it turns and faces West.
        // Then, it moves four steps West to (1, 2), and faces West.
        System.out.println(Arrays.toString(robot.getPos())); // return [1, 2]
        System.out.println(robot.getDir()); // return "West"
    }

    static class Robot {

        private final int w;
        private final int h;
        private int[] pos = {0, 0};
        private int dir = 0; // 0-E, 1-N, 2-W, 3-S

        public Robot(int width, int height) {
            this.w = width;
            this.h = height;
        }

        public void step(int num) {
            // to prevent TLE, we need to do below:
            // because we have to travel through perimeter of the given grid
            // and after travel w * 2 + h * 2 - 4 we will reach at the same position.
            num %= w * 2 + h * 2 - 4;
            if (num == 0)
                num = w * 2 + h * 2 - 4;

            for (int i = 0; i < num; i++) {
                int px = pos[0];
                int py = pos[1];
                switch (dir) {
                    case 0 -> px++;
                    case 1 -> py++;
                    case 2 -> px--;
                    case 3 -> py--;
                }
                if (px == w || px < 0 || py == h || py < 0) {
                    dir = (dir + 1) % 4;
                    i--;
                } else {
                    pos[0] = px;
                    pos[1] = py;
                }
            }
        }

        public int[] getPos() {
            return pos;
        }

        public String getDir() {
            String direction = null;
            switch (dir) {
                case 0 -> direction = "East";
                case 1 -> direction = "North";
                case 2 -> direction = "West";
                case 3 -> direction = "Sount";
            }
            return direction;
        }
    }

}
