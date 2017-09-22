package com.vivek.string;

import java.util.Scanner;

public class BomberAlgo {
	
	public static void main(String... args) {
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();
		System.out.println(bombedString(str));
	}

	private static String bombedString(String str) {
		if (str.isEmpty()) {
			return "Empty String";
		}

		boolean isDuplicateCheckReqd = false;
		for (int i = 1; i < str.length(); i++) {
			char prev = str.charAt(i - 1);
			char current = str.charAt(i);
			if (prev == current) {
                isDuplicateCheckReqd = true;
                int start = i - 1;
                int end = i;
                while ((end < str.length() - 1) && (str.charAt(end) == str.charAt(end + 1))) {
                    end++;
                }
				// remove duplicates
                str = str.substring(0, start) + str.substring(end + 1);
                break;
			}
		}

		return isDuplicateCheckReqd ? bombedString(str) : str;
	}

}
