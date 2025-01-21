package com.vivek.string;

/**
 * Implement a search and replace functionality for a string, you need to design a solution that efficiently handles:
 *     1. Searching for a substring within a string.
 *     2. Replacing all occurrences of that substring with a new string.
 */
public class SearchAndReplace {

    public static void main(String[] args) {
        String text = "abcabcabc";
        String searchStr = "abc";
        String replaceStr = "123";

        System.out.println(searchAndReplace(text, searchStr, replaceStr));
    }

    static String searchAndReplace(String text, String searchStr, String replaceStr) {

        if (text == null || text.isEmpty() || searchStr == null || searchStr.isEmpty() || replaceStr == null || replaceStr.isEmpty())
            return text;

        StringBuilder result = new StringBuilder();
        int start = 0;

        while (start < text.length()) {
            if (text.charAt(start) == searchStr.charAt(0) &&
                    searchStr.length() <= text.length() - start &&
                    searchStr.equals(text.substring(start, start + searchStr.length()))) {
                result.append(replaceStr);
                start += searchStr.length();
            } else {
                result.append(text.charAt(start));
                start++;
            }
        }

        return result.toString();
    }

}
