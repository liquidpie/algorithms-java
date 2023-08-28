package com.vivek.bitmanipulation;

/**
 * Sum of Two Integers
 *
 * Given two integers a and b, return the sum of the two integers without using the operators + and -.
 *
 * Example 1:
 *
 * Input: a = 1, b = 2
 * Output: 3
 *
 * Example 2:
 *
 * Input: a = 2, b = 3
 * Output: 5
 *
 * Solution: https://leetcode.com/problems/sum-of-two-integers/solutions/84290/java-simple-easy-understand-solution-with-explanation/?envType=list&envId=xb7kzh6j
 *
 * For this problem, the main crux is that, we are dividing the task of adding 2 numbers into two parts -
 *
 * Let a = 13 and b = 10. Then we want to add them, a+b
 * In binary, it would look as follows -
 * a      =  1 1 0 1
 * b      =  1 0 1 0
 * --------------------
 * a+b = (1) 0 1 1 1
 * Now we can break the addition into two parts, one is simple addition without taking care of carry, and other is taking the carry.
 * With that strategy in mind, we have the following -
 * simpleAddition(a, b):
 * a      =  1 1 0 1
 * b      =  1 0 1 0
 * --------------------
 * a+b    =  0 1 1 1
 *
 * carry(a, b):
 * carry = 1 0 0 0 0
 * *shift left by one, because we add the carry next left step :P
 * Now if we can add the carry to our simpleAddition result, we can get our final answer. So add them using the simpleAddition method, and take care of the new carry again, unless carry becomes zero.
 * This simpleAddition is performed by XOR ^operator, and the carry is performed by AND &operator.
 * So our final answer would look as -
 * (a^b) =    0 1 1 1
 * +carry = 1 0 0 0 0
 * -----------------------------
 * ans =    1 0 1 1 1
 * -----------------------------
 * which is 23
 *
 * https://leetcode.com/problems/sum-of-two-integers
 */
public class SumTwoIntegers {

    public static void main(String[] args) {
        System.out.println(getSum(2, 5));
    }

    static int getSum(int a, int b) {
        if (a == 0)
            return b;
        if (b == 0)
            return a;

        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }
        return a;
    }

}
