package com.vivek.bitmanipulation;

/**
 * Created by VJaiswal on 01/05/18.
 */
public class BinaryRepresentationOfDecimalNumber {

    /**
    A) Convert the integral part of decimal to binary equivalent

        Divide the decimal number by 2 and store remainders in array.
        Divide the quotient by 2.
        Repeat step 2 until we get the quotient equal to zero.
        Equivalent binary number would be reverse of all remainders of step 1.

    B) Convert the fractional part of decimal to binary equivalent

        Multiply the fractional decimal number by 2.
        Integral part of resultant decimal number will be first digit of fraction binary number.
        Repeat step 1 using only fractional part of decimal number and then step 2.

    C) Combine both integral and fractional part of binary number.

    Let's take an example for n = 4.47 k = 3

        Step 1: Conversion of 4 to binary
        1. 4/2 : Remainder = 0 : Quotient = 2
        2. 2/2 : Remainder = 0 : Quotient = 1
        3. 1/2 : Remainder = 1 : Quotient = 0

        So equivalent binary of integral part of decimal is 100.

        Step 2: Conversion of .47 to binary
        1. 0.47 * 2 = 0.94, Integral part: 0
        2. 0.94 * 2 = 1.88, Integral part: 1
        3. 0.88 * 2 = 1.76, Integral part: 1

        So equivalent binary of fractional part of decimal is .011

        Step 3: Combined the result of step 1 and 2.

        Final answer can be written as:
        100 + .011 = 100.011
     */
    static void printBinary(String num, int precision) {
        int intPart = Integer.parseInt(num.split("\\.")[0]);
        double decPart = Double.parseDouble("." + num.split("\\.")[1]);

        String intBinary = "";
        String decBinary = "";
        while (intPart > 0) {
            int rem = intPart % 2;
            intPart >>= 1;
            intBinary = rem + intBinary;
        }

        int len = 0;
        while (decPart > 0 && len != precision) {
            decPart *= 2;
            len++;
            if (decPart >= 1) {
                decBinary += 1;
                decPart -= 1;
            } else {
                decBinary += 0;
            }
        }

        System.out.println(intBinary + "." + decBinary);
    }

    public static void main(String[] args) {
        printBinary("4.47", 3);
    }

}
