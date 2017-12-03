package com.vivek.array;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Given a sorted array of integers print a no of triplets( 3 elements)
 * whose sum is 0.If there is no such triplet print 0.
 * Integers in the array can be repeated, so there can be repeated triplets whose sum is zero.
 * You have to implement the function findTriplet(int []) and your 
 * function should print the number of triplets whose sum is 0.  
 * For example if given array is A= {-9,-4,1,2,3,8} your function should print 2 ,
 * as there are two triplets whose sum is zero (1,3,-4) and (1,8,-9).
 */
public class SumTriplet {

	public static void main (String[] args) throws Exception
	{
		
		try (Scanner in = new Scanner(System.in)) {
			int n = in.nextInt();
			
			int[] arr = new int[n];
			
			for (int i = 0; i < n; i++) {
			    arr[i] = in.nextInt();
			}
			
			System.out.println(Arrays.toString(arr));
			
			System.out.println(findTriplet(arr));
		}
	}
	
	private static int findTriplet(int[] arr) {
	    int count = 0;
	    
	    for (int i = 0; i < arr.length - 2; i++) {
	        int start = i + 1;
	        int end = arr.length - 1;
	        
	        while (start < end) {
	            if (arr[i] + arr [start] + arr[end] == 0) {
	            	System.out.println("triplet found (" + arr[i] + ", " + arr[start] + ", " + arr[end] + ")");
	                count++;
	                start++;
	                end--;
	            }
	            else if (arr[i] + arr [start] + arr[end] < 0) {
	                start++;
	            }
	            else {
	                end--;
	            }
	        }
	    }
	    
	    return count;
	}
	
}
