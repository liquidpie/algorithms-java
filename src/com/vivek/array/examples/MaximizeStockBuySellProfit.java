package com.vivek.array.examples;

/**
 * Stock Buy Sell to Maximize Profit
 *
 * Given a list of stock prices for n days, find the maximum profit with a single buy or sell activity.
 * Given a list of daily stock prices (integers for simplicity), return the buy and sell prices for making the
 * maximum profit.
 * We need to maximize the single buy/sell profit. If we can’t make any profit, we’ll try to minimize the loss. For
 * the below examples, buy and sell prices for making a maximum profit are given below.
 *
 * The values in the array represent the cost of a stock each day. As we can buy and sell the stock only once,
 * we need to find the best buy and sell prices for which our profit is maximized (or loss is minimized) over a
 * given span of time.
 *
 * [8 5 12 9 19 1] - Buy: 5 & Sell: 19
 * [21 12 11 9 6 3] - Buy: 12 & Sell: 11
 *
 * Hint: Use Kadane's algorithm
 *
 * The basic algorithm as follows:
 *
 * current profit = INT_MIN
 * current buy = stock_prices[0]
 * global sell = stock_prices[1]
 * global profit = global sell - current buy
 * for i = 1 to stock_prices.length:
 *  current profit = stock_prices[i] - current buy
 * if current profit is greater than global profit
 *  then update global profit to current profit and update global sell to stock_prices[i]
 * if stock_prices[i] is less than current buy
 *  then update current buy to stock_prices[i]
 * return global profit and global sell
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 */
public class MaximizeStockBuySellProfit {

    static Tuple<Integer, Integer> findBuySellStockPrices(int[] arr) {
        if (arr == null || arr.length < 2) {
            return null;
        }
        int currentBuy = arr[0];
        int globalSell = arr[1];
        int globalProfit = globalSell - currentBuy;

        for (int i = 1; i < arr.length; i++) {
            int currentProfit = arr[i] - currentBuy;
            if (currentProfit > globalProfit) {
                globalProfit = currentProfit;
                globalSell = arr[i];
            }
            if (arr[i] < currentBuy) {
                currentBuy = arr[i];
            }
        }

        return new Tuple<>(globalSell - globalProfit, globalSell);
    }

    static class Tuple<X, Y> {
        X x;
        Y y;

        public Tuple(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 3, 2, 1, 2, 5};
        Tuple<Integer, Integer> tuple = findBuySellStockPrices(arr);
        if (tuple != null) {
            System.out.println("Buy Price: " + tuple.x + ", Sell Price: " + tuple.y);
        } else {
            System.out.println("Not enough data");
        }
    }

}
