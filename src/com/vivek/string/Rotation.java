package com.vivek.string;

/**
 * Check if a string is rotation of another string
 *
 * For ex: 'waterbottle' is rotation of 'erbottlewat'
 */
public class Rotation {

    static boolean isRotation(String str1, String str2) {

        // check if the length of both the strings is same
        if (str1.length() == str2.length()) {
            // concatenate str1 with itself
            String str1str1 = str1 + str1;
            // and check if str2 is substring of it
            return str1str1.contains(str2);
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String str1 = "apple";
        String str2 = "pleap";

        System.out.println(isRotation(str1, str2));
    }

}
