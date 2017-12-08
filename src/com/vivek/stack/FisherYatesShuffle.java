package com.vivek.stack;

import java.util.Random;
import java.util.Stack;

/**
 * Created by VJaiswal on 24/11/17.
 *
 * Fisher-Yates shuffle algorithm to shuffle a stack of elements
 */
public class FisherYatesShuffle {

    private Random random = new Random();

    static class Element {}

    void shuffle(Stack<Element> stack) {

        for (int i = stack.size() - 1; i > 0; i--) {

            int swapIndex = random.nextInt(i);

            Element temp = stack.get(i);

            stack.set(i, stack.get(swapIndex));
            stack.set(swapIndex, temp);
        }

    }
}
