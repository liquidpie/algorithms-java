package com.vivek.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by VJaiswal on 18/05/18.
 */
public class AnagramCheck {

    static boolean checkAnagram(String str1, String str2) {
        if (str1.length() != str2.length())
            return false;

        Map<Character, Integer> charCountMap = new HashMap<>();

        // fill char count map for str1
        for (char ch : str1.toCharArray()) {
            if (!charCountMap.containsKey(ch)) {
                charCountMap.put(ch, 1);
            } else {
                charCountMap.put(ch, charCountMap.get(ch) + 1);
            }
        }

        // traverse map for str2 and decrease the count
        for (char ch : str2.toCharArray()) {
            if (!charCountMap.containsKey(ch)) {
                return false;
            }
            charCountMap.put(ch, charCountMap.get(ch) - 1);
        }

        // check each value is zero
        for (int count : charCountMap.values()) {
            if (count != 0)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "bca";

        System.out.println(checkAnagram(str1, str2));
    }

}
