package com.vivek.greedy;

/**
 134. Gas Station

 There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].

 You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station.
 You begin the journey with an empty tank at one of the gas stations.

 Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once
 in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique.


 Example 1:

 Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 Output: 3
 Explanation:
 Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 Travel to station 4. Your tank = 4 - 1 + 5 = 8
 Travel to station 0. Your tank = 8 - 2 + 1 = 7
 Travel to station 1. Your tank = 7 - 3 + 2 = 6
 Travel to station 2. Your tank = 6 - 4 + 3 = 5
 Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
 Therefore, return 3 as the starting index.

 Example 2:

 Input: gas = [2,3,4], cost = [3,4,3]
 Output: -1
 Explanation:
 You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
 Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 Travel to station 0. Your tank = 4 - 3 + 2 = 3
 Travel to station 1. Your tank = 3 - 3 + 3 = 3
 You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
 Therefore, you can't travel around the circuit once no matter where you start.


 Solution:
 https://leetcode.com/problems/gas-station/solutions/1706142/java-c-python-an-explanation-that-ever-exists-till-now/


 Just to add some insight as to why just checking (total_surplus < 0) ? -1 : start is sufficient at the end to get the start index.

 So, we are resetting start as soon as surplus dips below 0 and setting it to the next index.
 Lets say the last update to start index was at index k where 0 <= k < n (length of the array);

 At the end of the iteration, surplus contains the total amount of fuel collected from the last updated start index till the end of tha array
 surplus = (gas[k] - cost[k]) + (gas[k + 1] - cost[k + 1]) + ... + (gas[n - 1] - cost[n - 1])

 This surplus must be able to offset the net fuel consumption before the kth index so that we are able to circle back and finish at the index k.
 Net Fuel Consumption Before k = (gas[0] - cost[0]) + (gas[1] - cost[1]) + ... + (gas[k - 1] - cost[k - 1])

 Now, totalSurplus contains the total fuel collected from the 0th to the (n-1)th index
 total_surplus = (gas[0] - cost[0]) + (gas[1] - cost[1]) + ... (gas[n - 1] - cost[n -1]);

 What this means is Net Fuel Consumption Before k = total_surplus - surplus.

 This implies that if (surplus + total_surplus - surplus) >= 0 then the current start is the correct answer
 ==> (totalSurplus >= 0) ? start : -1

 Reference: https://leetcode.com/problems/gas-station
 */
public class GasStation {

    static int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int totalSurplus = 0;
        int surplus = 0;
        int start = 0;

        for (int i = 0; i < n; i++) {
            totalSurplus += gas[i] - cost[i];
            surplus += gas[i] - cost[i];
            if (surplus < 0) {
                surplus = 0;
                start = i + 1;
            }
        }

        return totalSurplus < 0 ? -1 : start;
    }

    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        System.out.println(canCompleteCircuit(gas, cost));
    }

}
