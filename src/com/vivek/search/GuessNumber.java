package com.vivek.search;

/**
 *
 */
public class GuessNumber {

    public static void main(String[] args) {
        int n = 2126753390; int pick = 1702766719;
        Solution helper = new Solution(pick);
        System.out.println(helper.guessNumber(n));
    }

    static class GuessGame {

        private int pick;

        public GuessGame(int pick) {
            this.pick = pick;
        }

        int guess(int num) {
            return Integer.compare(pick, num);
        }
    }

    static class Solution extends GuessGame {

        public Solution(int pick) {
            super(pick);
        }

        public int guessNumber(int n) {
            int l = 1;
            int r = n;

            while (l <= r) {
                long mid = ((long) l + r) / 2;
                int m = (int) mid;
                int k = guess(m);
                if (k == 0)
                    return m;
                else if (k == 1)
                    l = m + 1;
                else
                    r = m - 1;
            }

            return -1;
        }

    }
}
