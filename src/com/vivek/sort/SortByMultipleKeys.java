package com.vivek.sort;

import java.util.Comparator;
import java.util.List;

public class SortByMultipleKeys {

    public static void main(String[] args) {
        List<Item> items = List.of(
                new Item(3, "Jane"),
                new Item(3, "John"),
                new Item(1, "Alice"),
                new Item(2, "Bob")
        );

        List<Item> sortedItems = sortItems(items);
        System.out.println(sortedItems);
    }

    static List<Item> sortItems(List<Item> items) {
        return items.stream().sorted(
                Comparator.comparingInt(Item::getIndex).thenComparing(Item::getName)
        ).toList();
    }

    static class Item {
        private final int index;
        private final String name;

        public Item(int index, String name) {
            this.index = index;
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "[" + index + ", " + name + "]";
        }
    }
}
