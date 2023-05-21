package com.vivek.heap.examples;

import java.util.*;

/**
 * Task Scheduler
 *
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task.
 * Tasks could be done in any order. Each task is done in one unit of time.
 * For each unit of time, the CPU could complete either one task or just be idle.
 *
 * However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array),
 * that is that there must be at least n units of time between any two same tasks.
 *
 * Return the least number of units of times that the CPU will take to finish all the given tasks.
 *
 * Example 1:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation:
 * A -> B -> idle -> A -> B -> idle -> A -> B
 * There is at least 2 units of time between any two same tasks.
 *
 * Example 2:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 0
 * Output: 6
 * Explanation: On this case any permutation of size 6 would work since n = 0.
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * And so on.
 *
 * Example 3:
 *
 * Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * Output: 16
 * Explanation:
 * One possible solution is
 * A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
 *
 *          * Approach: We use a max heap to maintain the list of frequencies of all the
 *          * characters. Given that there must be atleast n units of time between any two
 *          * same tasks we fetch n+1 elements from the max heap and add them to the list.
 *          * We then reduce the frequency of each of the elements by 1 and add it back to
 *          * max heap. After that, if the heap is empty then we just add the size of the list
 *          * to the cycles. Otherwise, we just use n+1 as the number of elements.
 *          *
 *          * Time Complexity: O(Mlgk), where M = tasks.length and k is the number of distinct characters
 *          * Space Complexity: O(lgk), where K is the number of distinct characters.
 *
 * https://leetcode.com/problems/task-scheduler/
 */
public class TaskScheduler {

    public static void main(String[] args) {
        char[] tasks = {'A','A','A','B','B','B'};
        int n = 2;
        System.out.println(leastInterval(tasks, n));
    }

    static int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char task : tasks) {
            freq.put(task, freq.getOrDefault(task, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((f1, f2) -> f2 - f1);
        pq.addAll(freq.values());

        int cycles = 0;
        while (!pq.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= n + 1; i++) {
                if (!pq.isEmpty()) {
                    list.add(pq.poll());
                }
            }

            for (int val : list) {
                if (--val > 0)
                    pq.add(val);
            }

            cycles += pq.isEmpty() ? list.size() : n + 1;
        }

        return cycles;
    }

}
