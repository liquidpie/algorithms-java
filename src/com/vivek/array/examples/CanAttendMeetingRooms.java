package com.vivek.array.examples;

import java.util.Arrays;

/**
 * Meeting Rooms
 *
 * Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.
 *
 * Example 1:
 *
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 *
 * Example 2:
 *
 * Input: intervals = [[7,10],[2,4]]
 * Output: true
 *
 * https://leetcode.com/problems/meeting-rooms/
 */
public class CanAttendMeetingRooms {

    public static void main(String[] args) {
        int[][] intervals = {{0,30},{5,10},{15,20}};
        System.out.println(canAttendMeetings(intervals));
    }

    static boolean canAttendMeetings(int[][] meetings) {
        Arrays.sort(meetings, (m1, m2) -> Integer.compare(m1[0], m2[0]));
        for (int i = 1; i < meetings.length; i++) {
            if (meetings[i - 1][1] > meetings[i][0])
                return false;
        }
        return true;
    }

}
