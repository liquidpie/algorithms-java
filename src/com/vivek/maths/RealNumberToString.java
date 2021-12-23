package com.vivek.maths;

import java.util.HashMap;
import java.util.Map;

public class RealNumberToString {

    static String divide(int dividend, int divisor) {
        if (dividend == 0)
            return "0";

        StringBuilder builder = new StringBuilder();

        long x = dividend;
        long y = divisor;

        if ((x < 0 && y > 0) || (x > 0 && y < 0)) {
            builder.append("-");
        }

        x = Math.abs(x);
        y = Math.abs(y);

        builder.append(x / y);

        if (x % y == 0) {
            return builder.toString();
        }

        builder.append(".");
        long remainder = x % y;

        builder.append(fractionalPart(remainder * 10, y));

        return builder.toString();
    }

    static String fractionalPart(long x, long y) {

        StringBuilder builder = new StringBuilder();
        Map<Long, Integer> remainderMap = new HashMap<>(); // holds indexes of remainders
        int i = 0;

        while (true) {

            long quotient = x / y;
            long remainder = x % y;

            if (remainder == 0) {
                builder.append(quotient);
                break;
            }

            if (remainderMap.containsKey(remainder) &&
                    builder.charAt(remainderMap.get(remainder)) == (quotient + '0')) {

                builder.insert(remainderMap.get(remainder), "(");
                builder.append(")");
                break;
            } else {
                builder.append(quotient);
                remainderMap.put(remainder, i);
            }

            x = remainder * 10;
            i++;
        }

        return builder.toString();

    }

    public static void main(String[] args) {
//        System.out.println(divide(2, 4));
//        System.out.println(divide(6, 3));
//        System.out.println(divide(-1, 3));
        System.out.println(divide(229, 990));
    }

}
