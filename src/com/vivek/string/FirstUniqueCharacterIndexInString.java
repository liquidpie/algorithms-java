package com.vivek.string;

/**
 * First Unique Character in a String
 *
 * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
 *
 * Example 1:
 *
 * Input: s = "leetcode"
 * Output: 0
 * Example 2:
 *
 * Input: s = "loveleetcode"
 * Output: 2
 * Example 3:
 *
 * Input: s = "aabb"
 * Output: -1
 *
 */
public class FirstUniqueCharacterIndexInString {

    public static void main(String[] args) {
        String s = "loveleetcode";
        System.out.println(firstUniqChar(s));
    }

    public static int firstUniqChar(String s) {
        int[] visited = new int[26];
        for (char ch : s.toCharArray()) {
            visited[ch - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (visited[s.charAt(i) - 'a'] == 1)
                return i;
        }

        return -1;
    }

}


