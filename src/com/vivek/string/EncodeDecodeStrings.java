package com.vivek.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 271. Encode and Decode Strings
 *
 * Design an algorithm to encode a list of strings to a string.
 * The encoded string is then sent over the network and is decoded back to the original list of strings.
 *
 * Machine 1 (sender) has the function:
 *
 * string encode(vector<string> strs) {
 *   // ... your code
 *   return encoded_string;
 * }
 * Machine 2 (receiver) has the function:
 * vector<string> decode(string s) {
 *   //... your code
 *   return strs;
 * }
 * So Machine 1 does:
 *
 * string encoded_string = encode(strs);
 * and Machine 2 does:
 *
 * vector<string> strs2 = decode(encoded_string);
 * strs2 in Machine 2 should be the same as strs in Machine 1.
 *
 * Implement the encode and decode methods.
 *
 * You are not allowed to solve the problem using any serialize methods (such as eval).
 *
 * Example 1:
 *
 * Input: dummy_input = ["Hello","World"]
 * Output: ["Hello","World"]
 * Explanation:
 * Machine 1:
 * Codec encoder = new Codec();
 * String msg = encoder.encode(strs);
 * Machine 1 ---msg---> Machine 2
 *
 * Machine 2:
 * Codec decoder = new Codec();
 * String[] strs = decoder.decode(msg);
 * Example 2:
 *
 * Input: dummy_input = [""]
 * Output: [""]
 *
 * Solution:
 * 1. Use a delimeter
 * 2. Use a non-ASCII delimiter (like utf-8 character)
 * 3. Chunked Encoding (passing length of the string before delimiter)
 *
 * We don't need to encode characters. However, I've done the same.
 *
 * Reference:
 * https://leetcode.com/problems/encode-and-decode-strings
 */
public class EncodeDecodeStrings {

    private static final String specifier = "#~SEP~#";
    private static final String empty = "#~EMP~#";

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder builder = new StringBuilder();

        for (String str : strs) {
            builder.append(specifier)
                    .append(convertToCode(str));
        }
        return builder.toString();
    }

    private String convertToCode(String str) {
        if (str.isEmpty())
            return empty;

        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) ((arr[i] + 6) % 256);
        }
        return String.valueOf(arr);
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> strs = new ArrayList<>();
        String[] strArray = s.split(specifier);

        for (int i = 1; i < strArray.length; i++) {
            strs.add(convertFromCode(strArray[i]));
        }

        return strs;
    }

    private String convertFromCode(String str) {
        if (str.equals(empty))
            return "";

        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) ((arr[i] + 250) % 256);
        }
        return String.valueOf(arr);
    }

    public static void main(String[] args) {
//        List<String> strs = List.of("Hello","World");
        List<String> strs = List.of("");

        EncodeDecodeStrings codec = new EncodeDecodeStrings();
        String encodedStr = codec.encode(strs);
        System.out.println(encodedStr);
        List<String> decodedStr = codec.decode(encodedStr);
        System.out.println(decodedStr);
    }

}
