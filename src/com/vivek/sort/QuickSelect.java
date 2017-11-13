package com.vivek.sort;

/**
 * Quick select algorithm (Hoare's selection algorithm)
 *      – select the Kth element or the first K element from a list in linear time
 *
 * The idea of the quick select is quite simple: just like with quicksort,
 * select a random element from the list, and place every item that is smaller to the first half of the array,
 * and every element that is equal to or greater than the pivot, in the second half
 * (the ‘half’ is not entirely correct, as it is possible that the result will not be exactly ‘half’).
 *
 * The quick select algorithm can get the top K element from a list of N items in linear time, O(n),
 * with a very reasonable multiplication factor. The quick select does not use recursion so the
 * performance is great for even large datasets.
 *
 * Reference: http://blog.teamleadnet.com/2012/07/quick-select-algorithm-find-kth-element.html
 */
public class QuickSelect {

    static int findKth(int[] arr, int k) {

        int beg = 0;
        int end = arr.length - 1;

        // if beg == end we reached the kth element
        while (beg < end) {
            int i = beg;
            int j = end;

            int mid = arr[(i + j) / 2];

            // stop if the i and j meets
            while (i < j) {

                if (arr[i] >= mid) {    // put the large values at the end
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                    j--;
                } else {   // the value is smaller than the pivot, skip
                    i++;
                }

                // if we stepped up (i++) we need to step one down
                if (arr[i] > mid) {
                    i--;
                }

                // the i pointer is on the end of the first k elements
                if (k <= i) {
                    end = i;
                } else {
                    beg = i + 1;
                }
            }
        }
        return arr[k];
    }

    public static void main(String[] args) {
        int[] arr = {5, 1, 4, 3, 2};
        int k = 2;
        System.out.println(findKth(arr, k));
    }

}
