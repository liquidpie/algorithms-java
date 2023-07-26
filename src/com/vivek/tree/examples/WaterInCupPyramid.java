package com.vivek.tree.examples;

import java.util.LinkedList;
import java.util.Queue;

/**
 * There is a pyramid with 1 cup at level , 2 at level 2 , 3 at level 3 and so on..
 * It looks something like this
 * Incoming flow = 15, capacity = 1
 *
 * Level 1:               1(14)
 * Level 2:           1(6)     1(6)
 * Level 3:       1(2)     1(5)     1(2)
 * Level 4:    1(1)   1(2.5)  1(2.5)    1(1)
 * Level 5:   1  1(0.75)  1(1.5)  1(0.75)   1
 * Level 6:  0 0.375 1(0.125) 1(0.125) 0.375 0
 * Level 7: 0 0  0.0625   0.125    0.0625   0 0
 *
 * Every cup has capacity C. you pour L liters of water from top. When cup 1 gets filled, it overflows to cup 2,3 equally,
 * and when they get filled, Cup 4 and 6 get water only from 2 and 3 resp but 5 gets water from both the cups and so on.
 *
 * Now given C and M. Print the amount of water in all cups.
 */
public class WaterInCupPyramid {

    public static void main(String[] args) {
        Cup root = buildPyramid();
        root.water = 4;
        fillWater(root, 1);
        printDistribution(root);
    }

    static class Cup {
        double water;
        Cup left;
        Cup right;
    }

    static void fillWater(Cup cup, int capacity) {

        if (cup == null)
            return;

        if (cup.water > capacity) {
            double overflow = cup.water - capacity;
            cup.water = capacity;

            if (cup.left != null) {
                cup.left.water += overflow / 2;
                cup.right.water += overflow / 2;
            }
        }

        fillWater(cup.left, capacity);
        fillWater(cup.right, capacity);
    }

    private static void printDistribution(Cup root) {
        if (root == null)
            return;
        // prints overlapping cups twice
        Queue<Cup> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Cup cup = queue.poll();
            System.out.println(cup.water);
            if (cup.left != null)
                queue.add(cup.left);
            if (cup.right != null)
                queue.add(cup.right);
        }
    }

    private static Cup buildPyramid() {
        Cup root = new Cup();
        root.left = new Cup();
        root.right = new Cup();
        root.left.left = new Cup();
        root.left.right = new Cup();
        root.right.left = root.left.right;
        root.right.right = new Cup();

        return root;
    }

}
