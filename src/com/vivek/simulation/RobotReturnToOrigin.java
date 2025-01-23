package com.vivek.simulation;

import java.util.HashMap;
import java.util.Map;

/**
 * 657. Robot Return to Origin
 *
 * There is a robot starting at the position (0, 0), the origin, on a 2D plane. Given a sequence of its moves,
 * judge if this robot ends up at (0, 0) after it completes its moves.
 *
 * You are given a string moves that represents the move sequence of the robot where moves[i] represents its ith move.
 * Valid moves are 'R' (right), 'L' (left), 'U' (up), and 'D' (down).
 *
 * Return true if the robot returns to the origin after it finishes all of its moves, or false otherwise.
 *
 * Note: The way that the robot is "facing" is irrelevant. 'R' will always make the robot move to the right once,
 * 'L' will always make it move left, etc. Also, assume that the magnitude of the robot's movement is the same for each move.
 *
 * Example 1:
 *
 * Input: moves = "UD"
 * Output: true
 * Explanation: The robot moves up once, and then down once. All moves have the same magnitude,
 * so it ended up at the origin where it started. Therefore, we return true.
 *
 * Example 2:
 *
 * Input: moves = "LL"
 * Output: false
 * Explanation: The robot moves left twice. It ends up two "moves" to the left of the origin.
 * We return false because it is not at the origin at the end of its moves.
 *
 * Reference:
 * https://leetcode.com/problems/robot-return-to-origin/description/
 */
public class RobotReturnToOrigin {

    public static void main(String[] args) {
        String moves1 = "UD";
        String moves2 = "LL";

        System.out.println(judgeCircle2(moves1)); // true
        System.out.println(judgeCircle2(moves2)); // false
    }

    static boolean judgeCircle(String moves) {
        // U, D, L, R
        Map<Character, int[]> coords = new HashMap<>();
        coords.put('U', new int[]{-1, 0});
        coords.put('D', new int[]{1, 0});
        coords.put('L', new int[]{0, -1});
        coords.put('R', new int[]{0, 1});

        int[] current = {0, 0};

        for (char ch : moves.toCharArray()) {
            int[] points = coords.get(ch);
            current[0] += points[0];
            current[1] += points[1];
        }

        return current[0] == 0 && current[1] == 0;
    }

    static boolean judgeCircle2(String moves) {
        int x = 0;
        int y = 0;

        for (char ch : moves.toCharArray()) {
            if (ch == 'U')
                x--;
            else if (ch == 'D')
                x++;
            else if (ch == 'L')
                y--;
            else
                y++;
        }

        return x == 0 && y == 0;
    }

}
