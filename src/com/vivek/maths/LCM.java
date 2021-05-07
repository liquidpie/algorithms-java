package com.vivek.maths;

/**
 * LCM (Least Common Multiple) of two numbers is the smallest number which can be divided by both numbers.
 *
 * For example, LCM of 15 and 20 is 60, and LCM of 5 and 7 is 35.
 *
 * Example:
 * 15 = 5 * 3
 * 25 = 5 * 5
 * LCM = 5 * 5 * 3
 *     = 75
 *
 * A simple solution is to find all prime factors of both numbers, then find union of all factors present in both numbers.
 * Finally, return the product of elements in union.
 *
 * An efficient solution is based on the below formula for LCM of two numbers ‘a’ and ‘b’.
 *
 *    a x b = LCM(a, b) * GCD (a, b)
 *    LCM(a, b) = (a x b) / GCD(a, b)
 *
 * Reference:
 * https://www.geeksforgeeks.org/program-to-find-lcm-of-two-numbers/
 */
public class LCM {

    static int lcm(int a, int b) {

        return (a * b) / gcd(a, b);

    }

    /**
     * https://www.geeksforgeeks.org/lcm-of-given-array-elements/
     */
    static long lcmOfArray(int[] arr) {
        long lcm = 1;
        int divisor = 2;

        while (true) {
            int countOnes = 0;
            boolean divisible = false;

            for (int i = 0; i < arr.length; i++) {

                if (arr[i] == 0)  // LCM is 0 if any element is 0
                    return 0;

                if (arr[i] == 1)  // Count elements whose factors are found
                    countOnes++;

                if (arr[i] < 0)
                    arr[i] = arr[i] * -1; // change negative to positive

                // Divide element_array by divisor if complete division i.e. without remainder then replace
                // number with quotient; used for finding next factor
                if (arr[i] % divisor == 0) {
                    divisible = true;
                    arr[i] = arr[i] / divisor;
                }
            }

            // If divisor able to completely divide any number from array multiply with lcm and store into lcm and continue
            // to same divisor for next factor finding, else increment divisor
            if (divisible) {
                lcm = lcm * divisor;
            } else {
                divisor++;
            }

            // Check if all element_array is 1 indicate we found all factors and terminate while loop.
            if (countOnes == arr.length) {
                return lcm;
            }
        }

    }

    private static int gcd(int a, int b) {
        if (a == 0)
            return b;

        return gcd(b % a, a);
    }

    public static void main(String[] args) {
        int a = 15, b = 20;
        System.out.println("LCM of " + a + " and " + b + " is " + lcm(a, b));

        int[] element_array = { 2, 7, 3, 9, 4 };
        System.out.println(lcmOfArray(element_array));
    }

}
