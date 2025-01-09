package com.vivek.maths;

/**
 * Breakdown amount into coins of different denominations.
 *
 * Minimize the number of coins
 */
public class CoinBreakdown {

    public static void main(String[] args) {
        coinBreakdown(0.65);
        coinBreakdown(1.23);
        coinBreakdown(3.45);
        coinBreakdown(1000.00);
        coinBreakdown(-1.23); // should throw IllegalArgumentException
    }

    static void coinBreakdown(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }

        int cents = (int) Math.round(amount * 100);

        int quarters = 25;
        int dimes = 10;
        int nickels = 5;
        int pennies = 1;

        int numQuarters = cents / quarters;
        cents %= quarters;

        int numDimes = cents / dimes;
        cents %= dimes;

        int numNickels = cents / nickels;
        cents %= nickels;

        int numPennies = cents / pennies;

        System.out.printf("coin breakdown (%.2f): ", amount);
        if (numQuarters > 0)
            System.out.print(numQuarters + " quarters ");
        if (numDimes > 0)
            System.out.print(numDimes + " dimes ");
        if (numNickels > 0)
            System.out.print(numNickels + " nickels ");
        if (numPennies > 0)
            System.out.print(numPennies + " pennies");

        System.out.println();
    }

}
