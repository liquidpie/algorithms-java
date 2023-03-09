package com.vivek.array.pattern.partitioning;

import com.vivek.utils.Utils;

/**
 * Problem: Red, White and Blue
 *
 * Level: Medium
 * Note: This solution uses Enums and Exceptions. While optional, it always looks good if you use them in an interview.
 * You're given a list of Marbles. Each marble can have one of 3 colors (Red, White or Blue). Arrange the marbles in order of the colors (Red -> White -> Blue).
 * Colors are represented as follows:
 * 0-Red
 * 1 - White 2-Blue
 *  Questions to Clarify:
 * Q. What to do if the input has an unknown color? A. Throw an Exception
 * Q. Do I need to move around the existing elements of the array, or can I count the elements (for example, 4 zeros) and then fill them up in a new array?
 * A. Yes, you need to move elements around. A marble is represented as an integer in this problem, but in
 * reality there might be more information about a marble in each element.
 * Solution:
 * This is very similar to the Dutch National Flag problem. You will use the same solution, with 2 pointers - one tracking ​low_boundary​ and one tracking ​high_boundary​.
 * Only white marbles will be left in the middle.
 *
 * Test Cases:
 * Edge Cases: empty array, null array, invalid color
 * Base Cases: single element, two elements
 * Regular Cases: list has element with - all 3 colors, only 2 colors, only 1 color
 * Time Complexity: O(n) Space Complexity: O(1)
 */
public class ArrangeMarblesInOrder {

    public static void main(String[] args) {
        Marble[] marbles = {Marble.of(Color.RED), Marble.of(Color.BLUE), Marble.of(Color.WHITE)};
        arrangeMarbles(marbles);
        Utils.printArray(marbles);
    }

    static void arrangeMarbles(Marble[] marbles) {
        if (marbles == null || marbles.length == 0)
            return;

        int low = 0; int high = marbles.length - 1; int i = 0;
        while (i <= high) {
            Color color = marbles[i].getColor();
            if (color == Color.RED) {
                Utils.swap(marbles, i, low);
                low++;
                i++;
            } else if (color == Color.BLUE) {
                Utils.swap(marbles, i, high);
                high--;
            } else if (color == Color.WHITE) {
                i++;
            } else {
                throw new IllegalArgumentException("Unknown color");
            }
        }
    }

    enum Color {
        RED(0), WHITE(1), BLUE(2);

        private final int colorId;

        Color(int colorId) {
            this.colorId = colorId;
        }

        public int getValue() {
            return colorId;
        }
    }

    public static class Marble {
        Color color;
        int data;

        public static Marble of(Color color) {
            return new Marble(color, -1);
        }

        public static Marble of(Color color, int data) {
            return new Marble(color, data);
        }

        public Marble(Color color, int data) {
            this.color = color;
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public Color getColor() {
            return color;
        }

        @Override
        public String toString() {
            return color.toString();
        }
    }

}
