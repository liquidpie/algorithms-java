package com.vivek.projecteuler;

/**
 * You are given an arithmetic progression with the initial term A and the common difference D. You’re also given a geometric progression with the initial term B and the common ratio R.

Find the count of integers common to both series up the value Lim .

Your task is to complete the function countSeriesTerms(A, D, B, R, Lim) and return the count of common terms in both the series. The code to handle input and output is already provided in the environment.

Input Parameters
A, D, B, R and Lim in that order.
Constraints
1 ≤ A, D, B, R, Lim ≤ 100000
Output Format
Return the count of the numbers that satisfy the required property.

Sample Input #1:
A = 1
D = 1
B = 1
R = 2
Lim = 4

Sample Output #1:
3

Explanation #1:
The arithmetic series with with A = 1, D = 1 and Lim = 4 is {1,2,3,4}
The geometric series with B = 1, R = 2 and Lim = 4 is {1,2,4}
The common terms are 1, 2 and 4, so their count is 3 .
 *
 */
public class CountSeriesTerms {
	
	static int countSeriesTerms(int A, int D, int B, int R, int Lim) {
        int aNum = A;
        int gNum = B;
        int count = 0;
        
        while (aNum <= Lim || gNum <= Lim) {
            if (aNum == gNum) {
                count++;
                aNum += D;
                gNum = gNum * R;
            }
            else if (aNum < gNum) {
                aNum += D;
            }
            else {
                gNum = gNum * R;
            }
        }
        
        return count;

    }

}
