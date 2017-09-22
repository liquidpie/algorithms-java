package com.vivek.array;

/**
 * Given an n x n matrix, where every row and column is sorted in increasing order. 
 * Given a number x, how to decide whether this x is in the matrix. 
 * The designed algorithm should have linear time complexity.
 */
public class SearchInRowWiseColumnWiseSortedMatrix {
	
	private static int search(int[][] mat, int n, int x)
	{
	   int i = 0, j = n-1;  //set indexes for top right element
	   while ( i < n && j >= 0 )
	   {
	      if ( mat[i][j] == x )
	      {
	         System.out.printf("Found at %d, %d", i, j);
	         return 1;
	      }
	      if ( mat[i][j] > x )
	        j--;
	      else //  if mat[i][j] < x
	        i++;
	   }
	 
	   System.out.printf("Element not found");
	   return 0;  // if ( i==n || j== -1 )
	}
	 
	public static void main(String... args) {
	  int[][] mat = { {10, 20, 30, 40},
	                    {15, 25, 35, 45},
	                    {27, 29, 37, 48},
	                    {32, 33, 39, 50},
	                  };
	  search(mat, 4, 29);
	}

}
