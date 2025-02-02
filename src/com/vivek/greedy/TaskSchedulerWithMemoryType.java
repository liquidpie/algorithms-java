package com.vivek.greedy;

import java.util.*;

/**
 * Optimal Task Scheduling with Memory and Type Constraints
 *
 * Given an array task_memory, representing the memory requirements for each task, and another array task_type,
 * representing the type of each task, along with an integer max_memory which is the maximum memory the server can use to
 * process two tasks concurrently, your function should calculate the minimum amount of time required to process all tasks.
 * Each task requires 1 unit of time to process. The server can process two tasks at the same time only if:
 *
 *     They are of the same type.
 *     Their combined memory usage does not exceed max_memory.
 *
 * Example 1:
 *
 * Input: n = 4, task_memory = [7, 2, 3, 9], task_type = [1, 2, 1, 3], max_memory = 10
 * Output: 3
 *
 * Explanation:
 * - Tasks 0 (type 1, memory 7) and 2 (type 1, memory 3) can be processed together in 1 unit of time because their combined memory (10) matches the max_memory limit.
 * - Task 1 (type 2, memory 2) and Task 3 (type 3, memory 9) must be processed separately, taking 1 unit of time each due to differing types and exceeding memory constraints.
 * Thus, the total time required is 1 + 1 + 1 = 3 units.
 *
 * Reference:
 * https://enginebogie.com/interview/experience/wayfair-software-development-engineer-2/188
 *
 * https://leetcode.com/discuss/interview-question/algorithms/4418282/WayFair-Hackerrank-or-SSE-or-Dec-2023/
 */
public class TaskSchedulerWithMemoryType {

    public static void main(String[] args) {
        int[] taskMemory = {7, 2, 3, 9};
        int[] taskType = {1, 2, 1, 3};
        int maxMemory = 10;

        System.out.println(minTime(taskMemory, taskType, maxMemory)); // Output: 3
    }

    static int minTime(int[] taskMemory, int[] taskType, int maxMemory) {
        int n = taskMemory.length;
        int time = 0;

        Map<Integer, List<Integer>> tasksMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            tasksMap.computeIfAbsent(taskType[i], k -> new ArrayList<>()).add(taskMemory[i]);
        }

        for (var tasks : tasksMap.values()) {
            Collections.sort(tasks);

            if (tasks.size() > 1) {
                int start = 0;
                int end = tasks.size() - 1;
                while (start < end) {
                    int memory = tasks.get(start) + tasks.get(end);
                    if (memory > maxMemory) {
                        end--;
                    } else {
                        start++;
                        end--;
                    }
                    time++;
                }
                if (start == end) {
                    time++;
                }
            } else {
                time++;
            }
        }

        return time;
    }

}
