package com.vivek.array;


/**
 * Given a square matrix of alphabets which contain English letters in arbitrary manner. 
 * While searching a word in it, you can go left to right horizontally, vertically downwards or diagonally towards right(both upwards and downwards).
 * You have to find the number of matches of a given word in a matrix. 
 */
public class WordMatchInMatrix {

	public static void main(String... args) {
		char[][] matrix = {{'A', 'A', 'K'}, 
						   {'A', 'S', 'K'}, 
						   {'A', 'K', 'K'}};
		String word = "ASK";
		int count = countWordInMatrix(matrix, word);
		System.out.println(count);
	}

	private static int countWordInMatrix(char[][] matrix, String word) {
		int len = word.length();
		int n = matrix.length;
		int count = 0;
		int index1 = 0;
		int index2 = 0;
		char[] temp;
		char[] temp2;
		
		// Horizontally & Vertically
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < (n - len + 1); j++) {
				temp = new char[len];
				temp2 = new char[len];
				for (int k = j; k < (j + len); k++) {
					temp[index1++] = matrix[i][k];
					temp2[index2++] = matrix[k][i];
				}
				if (String.valueOf(temp).equals(word)) {
					count++;
				}
				if (String.valueOf(temp2).equals(word)) {
					count++;
				}
				index1 = 0;
				index2 = 0;
			}
		}
		index1 = 0;
		
		
		// Diagonally
		temp = new char[len];
		for (int j = 0; (j + len - 1) < n; j++) {
			for (int k = j; k < (j + len); k++) {
				temp[index1++] = matrix[k][k];
			}
			if (String.valueOf(temp).equals(word)) {
				count++;
			}
			index1 = 0;
		}
		index1 = 0;
			
		// Reverse Diagonally
		temp = new char[len];
		for (int j = n - 1; j >= (n - len); j--) {
			for (int k = (n - 1 - j); k < (n - 1 - j + len); k++) {
				if ((j + k) == (n - 1)) {
					temp[index1++] = matrix[j][k];
				}
			}
			if (String.valueOf(temp).equals(word)) {
				count++;
			}
		}
		
		return count;
	}
	
}
