package com.vivek.array;

/**
 * Write a program to print all the LEADERS in the array.
 * An element is leader if it is greater than all the elements to its right side.
 * And the rightmost element is always a leader. For example int the array {16, 17, 4, 3, 5, 2}, leaders are 17, 5 and 2.
 */
public class Leader {

    void printLeaders(int[] arr, int n) {

        // rightmost element is always a leader
        int maxFromRight = arr[n - 1];
        System.out.println(maxFromRight);

        for (int i = n - 2; i >= 0; i--) {
            if (maxFromRight < arr[i]) {
                maxFromRight = arr[i];
                System.out.println(maxFromRight);
            }
        }
    }

    public static void main(String[] args) {
        Leader lead = new Leader();
        int arr[] = {16, 17, 4, 3, 5, 2};
        int n = arr.length;
        lead.printLeaders(arr, n);
    }

}
