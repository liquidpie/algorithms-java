package com.vivek.array.maxmin;

/**
 * Created by VJaiswal on 08/04/18.
 */
public class MaxAverageSubarray {

    /**
     1) Compute sum of first ‘k’ elements, i.e., elements arr[0..k-1]. Let this sum be ‘sum’.
        Initialize ‘max_sum’ as ‘sum’
     2) Do following for every element arr[i] where i varies from ‘k’ to ‘n-1’
        a) Remove arr[i-k] from sum and add arr[i], i.e., do sum += arr[i] – arr[i-k]
        b) If new sum becomes more than max_sum so far, update max_sum.
     3) Return ‘max_sum’
     */
    int findMaxAverage(int[] arr, int k) {
        int n = arr.length;

        if (k > n) {
            return -1;
        }

        int sum = 0;
        
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        int max_sum = sum;
        int max_end = k - 1;

        for (int i = k; i < n; i++) {
            sum += arr[i] - arr[i - k];
            if (sum > max_sum) {
                max_sum = sum;
                max_end = i;
            }
        }

        // return starting index
        return max_end - k + 1;
    }

}
