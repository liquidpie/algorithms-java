package com.vivek.backtracking;

/**
 * This problem is exactly the same as "Partition Array Into Two Arrays to Minimize Sum Difference"
 *
 * @link PartitionArrayMinSumDifference.java
 *
 * Given a set of n integers, divide the set in two subsets of n/2 sizes each such that
 * the absolute difference of the sum of two subsets is as minimum as possible.
 *
 * If n is even, then sizes of two subsets must be strictly n/2 and if n is odd,
 * then size of one subset must be (n-1)/2 and size of other subset must be (n+1)/2.
 *
 * For example, let given set be {3, 4, 5, -3, 100, 1, 89, 54, 23, 20}, the size of set is 10.
 * Output for this set should be {4, 100, 1, 23, 20} and {3, 5, -3, 89, 54}.
 * Both output subsets are of size 5 and sum of elements in both subsets is same (148 and 148).
 *
 * Let us consider another example where n is odd. Let given set be {23, 45, -34, 12, 0, 98, -99, 4, 189, -1, 4}.
 * The output subsets should be {45, -34, 12, 98, -1} and {23, 0, -99, 4, 189, 4}.
 * The sums of elements in two subsets are 120 and 121 respectively.
 *
 * The following solution tries every possible subset of half size. If one subset of half size is formed,
 * the remaining elements form the other subset. We initialize current set as empty and one by one build it.
 * There are two possibilities for every element, either it is part of current set,
 * or it is part of the remaining elements (other subset). We consider both possibilities for every element.
 * When the size of current set becomes n/2, we check whether this solutions is better than the best solution available so far.
 * If it is, then we update the best solution.
 *
 * Following is the implementation for Tug of War problem. It prints the required arrays.
 *
 * Time Complexity: O(2^n)
 *
 * SpaceComplexity: O(n)
 *
 * https://www.geeksforgeeks.org/tug-of-war/
 */
public class TugOfWar {

    private int minDiff;

    // main function that generate an arr
    void tugOfWar(int[] arr) {
        int n = arr.length;

        // the boolean array that contains the inclusion and exclusion of an element in current set.
        // The number excluded automatically form the other set
        boolean[] currElements = new boolean[n];
        // The inclusion/exclusion array for final solution
        boolean[] soln = new boolean[n];

        minDiff = Integer.MAX_VALUE;

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            currElements[i] = soln[i] = false;
        }

        // Find the solution using recursive function TOWUtil()
        TOWUtil(arr, n, currElements, 0, soln, sum, 0, 0);

        // Print the solution
        System.out.print("The first subset is: ");
        for (int i = 0; i < n; i++) {
            if (soln[i] == true)
                System.out.print(arr[i] + " ");
        }
        System.out.print("\nThe second subset is: ");
        for (int i = 0; i < n; i++) {
            if (soln[i] == false)
                System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // function that tries every possible solution by calling itself recursively
    void TOWUtil(int[] arr, int n, boolean[] currElements, int noOfSelectedElements,
                 boolean[] soln, int sum, int currSum, int currPosition) {
        // checks whether it is going out of bound
        if (currPosition == n)
            return;

        // checks that the numbers of elements left are not less than the number of elements
        // required to form the solution
        if ((n / 2 - noOfSelectedElements) > (n - currPosition))
            return;

        // consider the cases when current element is not included in the solution
        TOWUtil(arr, n, currElements, noOfSelectedElements, soln, sum, currSum, currPosition + 1);

        // add the current element to the solution
        noOfSelectedElements++;
        currSum = currSum + arr[currPosition];
        currElements[currPosition] = true;

        // checks if a solution is formed
        if (noOfSelectedElements == n / 2) {
            // checks if the solution formed is better than the best solution so far
            if (Math.abs(sum / 2 - currSum) < minDiff) {
                minDiff = Math.abs(sum / 2 - currSum);
                for (int i = 0; i < n; i++)
                    soln[i] = currElements[i];
            }
        }
        else {
            // consider the cases where current element is included in the solution
            TOWUtil(arr, n, currElements, noOfSelectedElements, soln, sum, currSum, currPosition + 1);
        }

        // removes current element before returning to the caller of this function
        currElements[currPosition] = false;
    }

    public static void main (String[] args) {
        int[] arr = {23, 45, -34, 12, 0, 98, -99, 4, 189, -1, 4};
        TugOfWar app = new TugOfWar();
        app.tugOfWar(arr);
        System.out.println("Minimum Absolute Difference: " + app.minDiff);
    }
}
