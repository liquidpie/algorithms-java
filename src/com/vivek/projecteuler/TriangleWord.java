package com.vivek.projecteuler;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TriangleWord {

	public static void main(String args[] ) throws Exception {
		try (Scanner in = new Scanner(System.in)) {
			int n = in.nextInt();
			in.nextLine();
			for (int i = 0; i < n; i++) {
				String word = in.nextLine();
				System.out.println(isTriangleWord(word));
			}
		}
	}

	private static boolean isTriangleWord(String str) {
		Map<Character, Integer>  map = new HashMap<>();
		map.put('A', 1);
		map.put('B', 2);
		map.put('C', 3);
		map.put('D', 4);
		map.put('E', 5);
		map.put('F', 6);
		map.put('G', 7);
		map.put('H', 8);
		map.put('I', 9);
		map.put('J', 10);
		map.put('K', 11);
		map.put('L', 12);
		map.put('M', 13);
		map.put('N', 14);
		map.put('O', 15);
		map.put('P', 16);
		map.put('Q', 17);
		map.put('R', 18);
		map.put('S', 19);
		map.put('T', 20);
		map.put('U', 21);
		map.put('V', 22);
		map.put('W', 23);
		map.put('X', 24);
		map.put('Y', 25);
		map.put('Z', 26);

		char[] arr = str.toUpperCase().toCharArray();
		int wordPosCount = 0;
		for (char ch :  arr) {
			wordPosCount += map.get(ch);
		}

		for (int i = 1; i < 50; i++) {
			int count = (i * (i + 1)) / 2;
			if (count == wordPosCount) {
				return true;
			}
		}

		return false;
	}

}
