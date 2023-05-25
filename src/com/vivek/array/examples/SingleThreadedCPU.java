package com.vivek.array.examples;


import java.util.*;

/**
 * Single-Threaded CPU
 *
 * You are given nth tasks labeled from 0 to n - 1 represented by a 2D integer array tasks, where tasks[i] = [enqueueTimei, processingTimei] means that the ith task will be available to process at enqueueTimei and will take processingTimei to finish processing.
 *
 * You have a single-threaded CPU that can process at most one task at a time and will act in the following way:
 *
 *     If the CPU is idle and there are no available tasks to process, the CPU remains idle.
 *     If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time. If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
 *     Once a task is started, the CPU will process the entire task without stopping.
 *     The CPU can finish a task then start a new one instantly.
 *
 * Return the order in which the CPU will process the tasks.
 *
 * Example 1:
 *
 * Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
 * Output: [0,2,3,1]
 * Explanation: The events go as follows:
 * - At time = 1, task 0 is available to process. Available tasks = {0}.
 * - Also at time = 1, the idle CPU starts processing task 0. Available tasks = {}.
 * - At time = 2, task 1 is available to process. Available tasks = {1}.
 * - At time = 3, task 2 is available to process. Available tasks = {1, 2}.
 * - Also at time = 3, the CPU finishes task 0 and starts processing task 2 as it is the shortest. Available tasks = {1}.
 * - At time = 4, task 3 is available to process. Available tasks = {1, 3}.
 * - At time = 5, the CPU finishes task 2 and starts processing task 3 as it is the shortest. Available tasks = {1}.
 * - At time = 6, the CPU finishes task 3 and starts processing task 1. Available tasks = {}.
 * - At time = 10, the CPU finishes task 1 and becomes idle.
 *
 * Example 2:
 *
 * Input: tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
 * Output: [4,3,2,0,1]
 * Explanation: The events go as follows:
 * - At time = 7, all the tasks become available. Available tasks = {0,1,2,3,4}.
 * - Also at time = 7, the idle CPU starts processing task 4. Available tasks = {0,1,2,3}.
 * - At time = 9, the CPU finishes task 4 and starts processing task 3. Available tasks = {0,1,2}.
 * - At time = 13, the CPU finishes task 3 and starts processing task 2. Available tasks = {0,1}.
 * - At time = 18, the CPU finishes task 2 and starts processing task 0. Available tasks = {1}.
 * - At time = 28, the CPU finishes task 0 and starts processing task 1. Available tasks = {}.
 * - At time = 40, the CPU finishes task 1 and becomes idle.
 *
 * https://leetcode.com/problems/single-threaded-cpu/
 */
public class SingleThreadedCPU {

    public static void main(String[] args) {
        int[][] tasks = {{1,2},{2,4},{3,2},{4,1}};
        int[][] tasks1 = {{7,10},{7,12},{7,5},{7,4},{7,2}};
        int[][] tasks2 = {{32,19,13},{25,16,9},{31,21,10},{57,32,25},{41,37,4},{73,49,24},{17,2,15},{79,38,41},{71,37,34},{39,33,6},{49,45,4},{36,18,18},{95,46,39},{36,12,24}};

        System.out.println(Arrays.toString(getOrder(tasks)));
        System.out.println(Arrays.toString(getOrder(tasks1)));
        System.out.println(Arrays.toString(getOrder(tasks2)));
    }

    static int[] getOrder(int[][] tasks) {
        int len = tasks.length;
        Task[] sorted = new Task[len];
        for (int i = 0; i < len; i++) {
            sorted[i] = new Task(i, tasks[i][0], tasks[i][1]);
        }
        Arrays.sort(sorted, (task1, task2) -> task1.enqueueTime - task2.enqueueTime);

        PriorityQueue<Task> minHeap = new PriorityQueue<Task>((task1, task2) -> {
            if (task1.processingTime == task2.processingTime) {
                return task1.index - task2.index;
            }
            return task1.processingTime - task2.processingTime;
        });

        int nextTaskIndex = 0;
        int[] order = new int[len];
        int orderIndex = 0;
        int currTime = 0;
        while (orderIndex < len) {
            // Add all the tasks that came in while previous was getting processed
            while (nextTaskIndex < sorted.length && sorted[nextTaskIndex].enqueueTime <= currTime) {
                minHeap.add(sorted[nextTaskIndex]);
                nextTaskIndex++;
            }

            // No tasks came in while previous was getting processed
            if (minHeap.isEmpty()) {
                currTime = sorted[nextTaskIndex].enqueueTime;
                continue;
            }

            Task minTask = minHeap.poll();
            order[orderIndex++] = minTask.index;
            currTime += minTask.processingTime;
        }

        return order;

    }

    static class Task {
        int index;
        int enqueueTime;
        int processingTime;

        Task(int i, int e, int p) {
            this.index = i;
            this.enqueueTime = e;
            this.processingTime = p;
        }

        @Override
        public boolean equals(Object obj) {
            Task t2 = (Task) obj;
            return t2.index == index && t2.enqueueTime == enqueueTime && t2.processingTime == processingTime;
        }

        @Override
        public int hashCode() {
            return index * 107 + enqueueTime * 101 + processingTime;
        }

        @Override
        public String toString() {
            return "index: " + index + ", nqTime: " + enqueueTime + ", pTime: " + processingTime;
        }
    }


}
