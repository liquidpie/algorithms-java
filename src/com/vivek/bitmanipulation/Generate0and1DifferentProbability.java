package com.vivek.bitmanipulation;

/**
 * Generate 0 and 1 with 25% and 75% probability
 *
 * Given a function rand50() that returns 0 or 1 with equal probability, write a function that returns 1 with 75% probability and 0 with 25% probability
 * using rand50() only. Minimize the number of calls to the rand50() method. Also, the use of any other library function and
 * floating-point arithmetic are not allowed.
 *
 * The idea is to use Bitwise OR. A bitwise OR takes two bits and returns 0 if both bits are 0, while otherwise, the result is 1.
 * So it has 75% probability that it will return 1.
 *
 * Reference:
 * https://www.geeksforgeeks.org/generate-0-1-25-75-probability/
 */
public class Generate0and1DifferentProbability {

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            System.out.print(rand75());
        }
    }

    // rand() function will generate odd or even
    // number with equal probability. If rand()
    // generates odd number, the function will
    // return 1 else it will return 0.
    static int rand50() {
        return (int) (10 *  Math.random()) & 1;
    }

    static int rand75() {
        return rand50() | rand50();
    }

}
