package com.vivek.string;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstRepeatableWordInAString {

	public static void main(String[] args) {
		System.out.println(firstRepeatedWord("He had had quite enough of this nonsense."));
	}
	
	static String firstRepeatedWord(String s) {
        Set<String> set = new HashSet<>();
        
        String pattern = "(?<=[^a-zA-Z\\d])([a-zA-Z]+)(?=[^a-zA-Z\\d])";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(s);
        while (m.find()) {
            String str = m.group();
            if (set.contains(str)) {
                return str;
            }
            else {
                set.add(str);
            }
        }
        
        return "";
    }

}
