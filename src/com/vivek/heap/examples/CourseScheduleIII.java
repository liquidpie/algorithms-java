package com.vivek.heap.examples;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 630. Course Schedule III
 *
 * There are n different online courses numbered from 1 to n. You are given an array courses where courses[i] = [durationi, lastDayi] indicate that the ith course should be taken continuously for durationi days and must be finished before or on lastDayi.
 *
 * You will start on the 1st day and you cannot take two or more courses simultaneously.
 *
 * Return the maximum number of courses that you can take.
 *
 * Example 1:
 *
 * Input: courses = [[100,200],[200,1300],[1000,1250],[2000,3200]]
 * Output: 3
 * Explanation:
 * There are totally 4 courses, but you can take 3 courses at most:
 * First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
 * Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day.
 * Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
 * The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
 *
 * Example 2:
 *
 * Input: courses = [[1,2]]
 * Output: 1
 *
 * Example 3:
 *
 * Input: courses = [[3,2],[4,3]]
 * Output: 0
 *
 * Solution:
 * During iteration, say I want to add the current course, currentTotalTime being total time of all courses taken till now,
 * but adding the current course might exceed my deadline or it doesn’t.
 *
 * 1. If it doesn’t, then I have added one new course. Increment the currentTotalTime with duration of current course.
 * 2. If it exceeds deadline, I can swap current course with current courses that has biggest duration.
 * * No harm done and I might have just reduced the currentTotalTime, right?
 * * What preprocessing do I need to do on my course processing order so that this swap is always legal?
 *
 * Reference: https://leetcode.com/problems/course-schedule-iii/description/
 */
public class CourseScheduleIII {

    public static void main(String[] args) {
        CourseScheduleIII helper = new CourseScheduleIII();
        {
            int[][] courses = {{5,5},{4,6},{2,6}};
            System.out.println(helper.scheduleCourse(courses)); // 2
        }
        {
            int[][] courses = {{100,200},{200,1300},{1000,1250},{2000,3200}};
            System.out.println(helper.scheduleCourse(courses)); // 3
        }
    }

    public int scheduleCourse(int[][] courses) {
        int duration = 0;

        // Sort the courses by their deadlines (Greedy! We have to deal with courses with early deadlines first)
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);

        // max heap - add course duration
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        for (int[] course : courses) {
            duration += course[0];
            // add the current course to a priority queue
            pq.offer(course[0]);
            //If time exceeds, drop the previous course which costs the most time. (That must be the best choice!)
            if (duration > course[1]) {
                duration -= pq.poll();
            }
        }

        return pq.size();
    }

}
