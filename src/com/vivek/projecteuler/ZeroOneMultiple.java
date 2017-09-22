package com.vivek.projecteuler;

import java.math.BigInteger;

public class ZeroOneMultiple {
	
	public static void main(String... args) {
		System.out.println(Zero_One(99999));
	}
	
	static String Zero_One(int num) {
        BigInteger ONE = BigInteger.ONE;
        BigInteger i = new BigInteger("1");
        while (true) {
            String str = i.toString(2);
            System.out.println("binary: " + str);
            Double value = Double.parseDouble(str);
            System.out.println("long: " + value);
            if (value % num == 0) {
                return value + "";
            }
            i = i.add(ONE);
        }
    }

}
