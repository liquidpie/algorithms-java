package com.vivek.array.examples;

import java.util.Arrays;

/**
 * Imagine you are given the entry time and exit time of each Atlassian employee as an interval for the whole
 * day. Hypothetically we can have any number of hours in a day. ”At the end of the day, you need to figure out
 * how many employees were in the office at each hour”
 *
 * employee_times = [[1, 2], [5, 6],[1,5]] [[3,1]]
 * Hour: 00 | Employee Count: 0
 * Hour: 01 | Employee Count: 2
 * Hour: 02 | Employee Count: 1
 * Hour: 03 | Employee Count: 1
 * Hour: 04 | Employee Count: 1
 * Hour: 05 | Employee Count: 1
 * Hour: 06 | Employee Count: 0
 *
 * - The entry_time > exit_time. This can happen when the employee entered at a time on a particular day and exited the next day.
 *   Print the total number of employees in the office per hour irrespective of the day.
 * - The added constraint is that the employee needs to exit before they spend more than a day in the office.
 * - We are also given the max_hours for a day.
 */
public class EmployeeEntryExitTime {

    public static void main(String[] args) {
        int[][] times = {{1, 2}, {5, 6},{1,5}};
        System.out.println(Arrays.toString(employeeCountPerHour(6, times)));
    }

    static int[] employeeCountPerHour(int maxHour, int[][] entryExitTime) {
        int[] result = new int[maxHour + 1];

        for (int i = 0; i < entryExitTime.length; i++) {
            result[entryExitTime[i][0]] += 1;
            result[entryExitTime[i][1]] -= 1;
        }

        for (int i = 1; i < maxHour + 1; i++) {
            result[i] += result[i - 1];
        }

        return result;
    }

}
