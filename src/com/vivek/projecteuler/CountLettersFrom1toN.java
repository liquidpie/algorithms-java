package com.vivek.projecteuler;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountLettersFrom1toN {

	public static void main(String args[] ) throws Exception {
		try (Scanner in = new Scanner(System.in)) {
			int n = in.nextInt();
			for (int i = 0; i < n; i++) {
				int num = in.nextInt();
				int count = 0;
				for (int j = 1; j <= num; j++) {
					count += countNumWords(j);
				}
				System.out.println(count);
			}
		}
	}

	private static int countNumWords(int num) {
		Map<Integer, Integer>  map = new HashMap<>();
		map.put(1, 3);
		map.put(2, 3);
		map.put(3, 5);
		map.put(4, 4);
		map.put(5, 4);
		map.put(6, 3);
		map.put(7, 5);
		map.put(8, 5);
		map.put(9, 4);
		map.put(10, 3);
		map.put(11, 6);
		map.put(12, 6);
		map.put(13, 8);
		map.put(14, 8);
		map.put(15, 7);
		map.put(16, 7);
		map.put(17, 9);
		map.put(18, 8);
		map.put(19, 8);
		map.put(20, 6);
		map.put(30, 6);
		map.put(40, 5);
		map.put(50, 5);
		map.put(60, 5);
		map.put(70, 7);
		map.put(80, 6);
		map.put(90, 6);
		map.put(100, 7);
		map.put(1000, 8);
		map.put(0, 3);

		if (num == 0) {
			return 0;
		}
		if (num == 1000) {
			return map.get(num);
		}
		if (num > 99) {
			int count = 0;
			if  (countNumWords(num % 100) != 0) {
				count = "and".length() + countNumWords(num % 100);
			}
			return countNumWords(num/100) + "hundred".length() + count;
		}
		if (num > 20) {
			return map.get((num / 10) * 10) + countNumWords(num % 10);
		}
		return map.get(num);
	}

}
