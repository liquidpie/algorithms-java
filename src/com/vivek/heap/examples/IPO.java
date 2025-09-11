package com.vivek.heap.examples;

import java.util.*;

/**
 * 502. IPO
 *
 * Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital, LeetCode would like to work on some projects to increase its capital before the IPO. Since it has limited resources, it can only finish at most k distinct projects before the IPO. Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.
 *
 * You are given n projects where the ith project has a pure profit profits[i] and a minimum capital of capital[i] is needed to start it.
 *
 * Initially, you have w capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.
 *
 * Pick a list of at most k distinct projects from given projects to maximize your final capital, and return the final maximized capital.
 *
 * The answer is guaranteed to fit in a 32-bit signed integer.
 *
 *
 *
 * Example 1:
 *
 * Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
 * Output: 4
 * Explanation: Since your initial capital is 0, you can only start the project indexed 0.
 * After finishing it you will obtain profit 1 and your capital becomes 1.
 * With capital 1, you can either start the project indexed 1 or the project indexed 2.
 * Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
 * Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
 *
 * Example 2:
 *
 * Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
 * Output: 6
 *
 * Note: The question should specify that you don't lose the capital when you use it for a project.
 *
 * Solution:
 * Create a list of pairs (capital, profit) for all n projects.
 *
 * Sort the list of projects by their minimum capital requirements in ascending order.
 *
 * Initialize a priority queue to store the profits of the available projects.
 *
 * Create an i variable and Iterate over k projects.
 * ///While i is less than n and the minimum capital requirement of the current project is less than or equal to w.
 * // Add the profit of the current project to the priority queue.
 * // If the priority queue is empty, break out of the loop because we can't afford any more projects.
 * // Select the project with the highest profit from the priority queue and add its profit to w.
 *
 * Reference: https://leetcode.com/problems/ipo/
 */
public class IPO {

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;

        // Create a list of pairs (capital, profit) for all n projects
        List<Project> projects = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            projects.add(new Project(capital[i], profits[i]));
        }

        // Sort the list of projects by their minimum capital requirements in ascending order
        projects = projects.stream().sorted(Comparator.comparingInt(p -> p.capital)).toList();

        // Initialize a priority queue to store the profits of the available projects
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // Initialize a variable i to 0 to keep track of the current project
        int i = 0;
        // Iterate over k projects
        while (k > 0) {
            // While i is less than n and the minimum capital requirement of the current project is less than or equal to w
            while (i < n && projects.get(i).capital <= w) {
                // Add the profit of the current project to the priority queue
                pq.offer(projects.get(i).profit);
                i++;
            }
            // If the priority queue is empty, break out of the loop because we can't afford any more projects
            if (pq.isEmpty())
                break;
            // Select the project with the highest profit from the priority queue and add its profit to w
            w += pq.poll();
            k--;
        }

        return w;

    }

    static class Project {
        int capital;
        int profit;

        Project(int _capital, int _profit) {
            capital = _capital;
            profit = _profit;
        }
    }

}
