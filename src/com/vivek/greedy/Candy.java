package com.vivek.greedy;

import java.util.Arrays;

/**
 * 135. Candy
 *
 * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 *     Each child must have at least one candy.
 *     Children with a higher rating get more candies than their neighbors.
 *
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 *
 *
 * Example 1:
 *
 * Input: ratings = [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 *
 * Example 2:
 *
 * Input: ratings = [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 * The third child gets 1 candy because it satisfies the above two conditions.
 *
 * Solution:
 * Traverse From The Start
 *
 *     We can first traverse from the start of the array and check that if the current rating is higher than the last one,
 *     it means that the current child should have more candies than the last one.
 *     So we simply use candies[i] = candies[i - 1] + 1 which is basically :
 *         Candies of Current Child=Candies of Last Child+1, when the current child has a higher rating than the last child.
 *
 *
 * Traverse From The End
 *
 *     Now we simply use candies[i - 1] = max(candies[i] + 1, candies[i - 1]), which basically uses the logic :
 *         Candies of last child=max(Candies of current child+1,candies of last child), when the last child has a higher rating than the current child.
 *     We do this as, if child A has a higher rating than B, then he should have atleast higher than B candies, if not then we assign him B + 1 candies.
 *
 * https://leetcode.com/problems/candy/solutions/6802500/double-pass-greedy-with-images-example-walkthrough-c-python-java/?envType=study-plan-v2&envId=top-interview-150
 *
 * Reference:
 * https://leetcode.com/problems/candy
 */
public class Candy {

    public static void main(String[] args) {
        Candy candy = new Candy();
        {
            int[] ratings = {1, 0, 2};
            System.out.println(candy.candy(ratings)); // 5
        }
        {
            int[] ratings = {1, 2, 2};
            System.out.println(candy.candy(ratings)); // 4
        }
        {
            int[] ratings = {1, 3, 2, 2, 1};
            System.out.println(candy.candy(ratings)); // 7
        }
    }

    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];

        Arrays.fill(candies, 1);

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1])
                candies[i] = candies[i - 1] + 1;
        }

        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1])
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
        }

        int total = 0;
        for (int i = 0; i < n; i++) {
            total += candies[i];
        }

        System.out.println(Arrays.toString(candies));

        return total;
    }

}
