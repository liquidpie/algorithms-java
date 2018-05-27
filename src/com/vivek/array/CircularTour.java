package com.vivek.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Find the first circular tour that visits all petrol pumps

 Suppose there is a circle. There are n petrol pumps on that circle. You are given two sets of data.

 1. The amount of petrol that every petrol pump has.
 2. Distance from that petrol pump to the next petrol pump.

 Calculate the first point from where a truck will be able to complete the circle (
 The truck will stop at each petrol pump and it has infinite capacity).
 Expected time complexity is O(n). Assume for 1 litre petrol, the truck can go 1 unit of distance.

 For example, let there be 4 petrol pumps with amount of petrol and distance to next petrol pump value pairs as
 {4, 6}, {6, 5}, {7, 3} and {4, 5}. The first point from where truck can make a circular tour is 2nd petrol pump.
 Output should be “start = 1” (index of 2nd petrol pump).

 https://www.geeksforgeeks.org/find-a-tour-that-visits-all-stations/
 */
public class CircularTour {

    static int findStartingPoint(List<PetrolPump> petrolPumps) {
        int n = petrolPumps.size();
        int[] balance = new int[n];
        for (int i = 0; i < n; i++) {
            balance[i] = petrolPumps.get(i).petrol - petrolPumps.get(i).distance;
        }

        for (int i = 0; i < n; ) {
            int cumulativeBalance = 0;
            int j = i;
            int visited;
            for (visited = 0; visited < n && cumulativeBalance >= 0; visited++) {
                cumulativeBalance += balance[j];
                j++;
                j %= n;
            }
            if (visited == n)
                return i;
            if (j < i)
                return -1;

            i = j;
        }

        return -1;

    }

    public static void main(String[] args) {
        List<PetrolPump> petrolPumps = new ArrayList<>();
        petrolPumps.add(new PetrolPump(4, 6));
        petrolPumps.add(new PetrolPump(6, 5));
        petrolPumps.add(new PetrolPump(7, 3));
        petrolPumps.add(new PetrolPump(4, 5));

        int result = findStartingPoint(petrolPumps);
        System.out.println(result != -1 ? ("Starting index : " + result) : "Tour not possible");

    }

    private static class PetrolPump {
        int petrol;
        int distance;

        PetrolPump(int petrol, int distance) {
            this.petrol = petrol;
            this.distance = distance;
        }
    }

}
