package com.vivek.simulation;

/**
 * 1041. Robot Bounded In Circle
 *
 * On an infinite plane, a robot initially stands at (0, 0) and faces north. Note that:
 *
 *     The north direction is the positive direction of the y-axis.
 *     The south direction is the negative direction of the y-axis.
 *     The east direction is the positive direction of the x-axis.
 *     The west direction is the negative direction of the x-axis.
 *
 * The robot can receive one of three instructions:
 *
 *     "G": go straight 1 unit.
 *     "L": turn 90 degrees to the left (i.e., anti-clockwise direction).
 *     "R": turn 90 degrees to the right (i.e., clockwise direction).
 *
 * The robot performs the instructions given in order, and repeats them forever.
 *
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 *
 * Example 1:
 *
 * Input: instructions = "GGLLGG"
 * Output: true
 * Explanation: The robot is initially at (0, 0) facing the north direction.
 * "G": move one step. Position: (0, 1). Direction: North.
 * "G": move one step. Position: (0, 2). Direction: North.
 * "L": turn 90 degrees anti-clockwise. Position: (0, 2). Direction: West.
 * "L": turn 90 degrees anti-clockwise. Position: (0, 2). Direction: South.
 * "G": move one step. Position: (0, 1). Direction: South.
 * "G": move one step. Position: (0, 0). Direction: South.
 * Repeating the instructions, the robot goes into the cycle: (0, 0) --> (0, 1) --> (0, 2) --> (0, 1) --> (0, 0).
 * Based on that, we return true.
 *
 * Example 2:
 *
 * Input: instructions = "GG"
 * Output: false
 * Explanation: The robot is initially at (0, 0) facing the north direction.
 * "G": move one step. Position: (0, 1). Direction: North.
 * "G": move one step. Position: (0, 2). Direction: North.
 * Repeating the instructions, keeps advancing in the north direction and does not go into cycles.
 * Based on that, we return false.
 *
 * Example 3:
 *
 * Input: instructions = "GL"
 * Output: true
 * Explanation: The robot is initially at (0, 0) facing the north direction.
 * "G": move one step. Position: (0, 1). Direction: North.
 * "L": turn 90 degrees anti-clockwise. Position: (0, 1). Direction: West.
 * "G": move one step. Position: (-1, 1). Direction: West.
 * "L": turn 90 degrees anti-clockwise. Position: (-1, 1). Direction: South.
 * "G": move one step. Position: (-1, 0). Direction: South.
 * "L": turn 90 degrees anti-clockwise. Position: (-1, 0). Direction: East.
 * "G": move one step. Position: (0, 0). Direction: East.
 * "L": turn 90 degrees anti-clockwise. Position: (0, 0). Direction: North.
 * Repeating the instructions, the robot goes into the cycle: (0, 0) --> (0, 1) --> (-1, 1) --> (-1, 0) --> (0, 0).
 * Based on that, we return true.
 *
 * Approach: https://leetcode.com/problems/robot-bounded-in-circle/editorial/
 * The robot's trajectory attractor is a set of trajectories toward which a system tends to evolve.
 * The question may sound a bit theoretical - is this attractor limited or not? In other words,
 * if there exists a circle in the plane such that the robot never leaves the circle.
 *
 * Why is it interesting to know? There are a bunch of practical problems related to topology, network planning,
 * and password brute-forcing. For all these problems, the first thing to understand is do we work within a limited space or
 * the behavior of our system could drastically diverge at some point?
 *
 * Intuition
 *
 * This solution is based on two facts about the limit cycle trajectory.
 *      - After at most 4 cycles, the limit cycle trajectory returns to the initial point x = 0, y = 0.
 *        That is related to the fact that 4 directions (north, east, south, west) define the repeated cycles' plane symmetry
 *      - We do not need to run 4 cycles to identify the limit cycle trajectory. One cycle is enough. There could be two situations here.
 *          First, if the robot returns to the initial point after one cycle, that's the limit cycle trajectory.
 *          Second, if the robot doesn't face north at the end of the first cycle, that's the limit cycle trajectory.
 *          Once again, that's the consequence of the plane symmetry for the repeated cycles
 *
 * Let's provide a strict mathematical proof.
 * If the robot doesn't face north at the end of the first cycle, then that's the limit cycle trajectory.
 * First, let's check which direction the robot faces after 4 cycles.
 * Let's use numbers from 0 to 3 to mark the directions: north = 0, east = 1, south = 2, west = 3.
 * After one cycle the robot is facing direction k != 0.
 * After 4 cycles, the robot faces direction (k * 4) % 4 = 0, i.e. after 4 cycles, the robot is always facing north.
 *
 * Reference:
 * https://leetcode.com/problems/robot-bounded-in-circle/description/
 */
public class RobotBoundedInCircle {

    public static void main(String[] args) {
        System.out.println(isRobotBounded("GGLLGG"));
        System.out.println(isRobotBounded("GG"));
        System.out.println(isRobotBounded("GL"));
    }

    static boolean isRobotBounded(String instructions) {
        int x = 0;
        int y = 0;
        int direction = 0; // N-0, E-1, S-2, W-3

        for (char c : instructions.toCharArray()) {
            if (c == 'G') {
                switch (direction) {
                    case 0 -> y++;
                    case 1 -> x++;
                    case 2 -> y--;
                    case 3 -> x--;
                }
            } else if (c == 'R') {
                direction = (direction + 1) % 4;
            } else {
                direction = (direction + 3) % 4;
            }
        }

        return (x == 0 && y == 0) || (direction != 0); // when after 1 cycle, if it faces other than north, it will converge to a circle
    }

}
