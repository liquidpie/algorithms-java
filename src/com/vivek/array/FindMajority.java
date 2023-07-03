package com.vivek.array;

/**
 * Created by VJaiswal on 08/04/18.
 *
 * Miscellaneous problems on Array
 */
public class FindMajority {

    /**
     * The majority element is the element that occurs more than half of the size of the array.
     * @param arr
     * @return majority element. otherwise, -1
     *
     * @link https://stackoverflow.com/questions/4325200/find-the-majority-element-in-array
     */
    int findMajority(int[] arr) {
        int n = arr.length;
        int majority = 0;
        int count = 0;

        // find the majority element irrespective of count
        for (int i = 0; i < n; i++) {
            if (count == 0)
                majority = arr[i];
            if (arr[i] == majority)
                count++;
            else
                count--;
        }
        
        count = 0;

        // find the count of majority element
        for (int i = 0; i < n; i++) {
            if (arr[i] == majority)
                count++;
        }

        if (count > n / 2)
            return majority;

        return -1;
    }

}
