package com.vivek.maths;

/**
 * Todo
 *
 * Given two coordinates (x, y) and (a, b). Find if it is possible to reach (x, y) from (a, b).
 * Only possible moves from any coordinate (i, j) are
 *
 *
 *     (i-j, j)
 *     (i, i-j)
 *     (i+j, j)
 *     (i, i+j)
 *
 * Given x, y, a, b can be negative.
 * Examples:
 *
 *
 * Input : (x, y) = (1, 1) and  (a, b) = (2, 3).
 * Output : Yes.
 * (1, 1) -> (2, 1) -> (2, 3).
 *
 * Input : (x, y) = (2, 1) and  (a, b) = (2, 3).
 * Output : Yes.
 *
 * Input : (x, y) = (35, 15) and  (a, b) = (20, 25).
 * Output : Yes.
 * (35, 15) -> (20, 15) -> (5, 15) -> (5, 10) -> (5, 5) ->
 * (10, 5) -> (15, 5) -> (20, 5) -> (20, 25)
 *
 * we can notice that the moves are similar steps of Euclidean algorithm for finding GCD.
 * So, it is only possible to reach coordinate (a, b) from (x, y) if GCD of x, y is equal to GCD of a, b.
 * Otherwise, it is not possible.
 *
 * Reference: https://www.geeksforgeeks.org/check-possible-move-given-coordinate-desired-coordinate/
 */
public class ReachDesiredCoordinate {

    static boolean canReachDesiredCoordinate(int x, int y, int a, int b) {

        // Find absolute values of all as sign doesn't matter.
        x = Math.abs(x);
        y = Math.abs(y);
        a = Math.abs(a);
        b = Math.abs(b);

        return GCD.gcd(x, y) == GCD.gcd(a, b);
    }

    public static void main(String[] args) {
        int x = 35, y = 15;
        int a = 20, b = 25;
        System.out.println(canReachDesiredCoordinate(x, y, a, b) ? "Yes" : "No");
    }
}
