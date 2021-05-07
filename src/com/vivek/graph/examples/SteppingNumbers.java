package com.vivek.graph.examples;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given two integers ‘n’ and ‘m’, find all the stepping numbers in range [n, m].
 * A number is called stepping number if all adjacent digits have an absolute difference of 1.
 * 321 is a Stepping Number while 421 is not.
 *
 * Examples :
 *
 * Input : n = 0, m = 21
 * Output : 0 1 2 3 4 5 6 7 8 9 10 12 21
 *
 * Input : n = 10, m = 15
 * Output : 10, 12
 *
 *
 * The idea is to use a Breadth First Search/Depth First Search traversal.
 *
 * How to build the graph?
 * Every node in the graph represents a stepping number; there will be a directed edge from a node U to V if V can be transformed from U. (U and V are Stepping Numbers) A Stepping Number V can be transformed from U in following manner.
 * lastDigit refers to the last digit of U (i.e. U % 10)
 * An adjacent number V can be:
 *
 *     U*10 + lastDigit + 1 (Neighbor A)
 *     U*10 + lastDigit – 1 (Neighbor B)
 *
 * By applying above operations a new digit is appended to U, it is either lastDigit-1 or lastDigit+1, so that the new number V formed from U is also a Stepping Number.
 * Therefore, every Node will have at most 2 neighboring Nodes.
 * Edge Cases: When the last digit of U is 0 or 9
 *
 *     Case 1: lastDigit is 0 : In this case only digit ‘1’ can be appended.
 *     Case 2: lastDigit is 9 : In this case only digit ‘8’ can be appended.
 *
 * What will be the source/starting Node?
 *
 *     Every single digit number is considered as a stepping Number, so bfs traversal for every digit will give all the stepping numbers starting from that digit.
 *     Do a bfs/dfs traversal for all the numbers from [0,9].
 *
 * Note: For node 0, no need to explore neighbors during BFS traversal since it will lead to 01, 012, 010 and these will be covered by the BFS traversal starting from node 1.
 * Example to find all the stepping numbers from 0 to 21
 *
 * -> 0 is a stepping Number and it is in the range
 *    so display it.
 * -> 1 is a Stepping Number, find neighbors of 1 i.e.,
 *    10 and 12 and push them into the queue
 *
 * How to get 10 and 12?
 * Here U is 1 and last Digit is also 1
 * V = 10 + 0 = 10 ( Adding lastDigit - 1 )
 * V = 10 + 2 = 12 ( Adding lastDigit + 1 )
 *
 * Then do the same for 10 and 12 this will result into
 * 101, 123, 121 but these Numbers are out of range.
 * Now any number transformed from 10 and 12 will result
 * into a number greater than 21 so no need to explore
 * their neighbors.
 *
 * -> 2 is a Stepping Number, find neighbors of 2 i.e.
 *    21, 23.
 * -> 23 is out of range so it is not considered as a
 *    Stepping Number (Or a neighbor of 2)
 *
 * The other stepping numbers will be 3, 4, 5, 6, 7, 8, 9.
 *
 * https://www.geeksforgeeks.org/stepping-numbers/
 */
public class SteppingNumbers {

    static void bfs(int n, int m, int num) {

        Queue<Integer> queue = new LinkedList<>();

        queue.add(num);

        while (!queue.isEmpty()) {

            int stepNum = queue.poll();

            // If the Stepping Number is in the range [n,m] then display
            if (stepNum >= n && stepNum <= m) {
                System.out.print(stepNum + " ");
            }

            // If Stepping Number is 0 or greater than m, no need to explore the neighbors
            if (stepNum == 0 || stepNum > m) {
                continue;
            }

            int lastDigit = stepNum % 10;
            int numA = stepNum * 10 + lastDigit - 1;
            int numB = stepNum * 10 + lastDigit + 1;

            if (lastDigit == 0) {
                queue.add(numB);
            } else if (lastDigit == 9) {
                queue.add(numA);
            } else {
                queue.add(numA);
                queue.add(numB);
            }
        }
    }

    public static void main(String[] args) {
        int n = 10;
        int m = 21;

        for (int i = 0; i <= 9; i++) {
            bfs(n, m, i);
        }
    }

}
