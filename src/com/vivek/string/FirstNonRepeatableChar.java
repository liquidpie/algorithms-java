package com.vivek.string;

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNonRepeatableChar {
	
	public static void main(String... args) {
		System.out.println(findFirstNonRepeatingChar("BCBEACD"));
	}
	
	private static char findFirstNonRepeatingChar(String str) {
		Map<Character, Integer> list = new LinkedHashMap<>(str.length());
		for (char c : str.toCharArray()) {
			list.put(c, list.getOrDefault(c, 0) + 1);
		}
		
		for (Map.Entry<Character, Integer> entry : list.entrySet()) {
			if (entry.getValue() == 1) {
				return entry.getKey();
			}
		}
		
		return str.charAt(0);
	}

}
