package com.vivek.sort.examples;

import java.util.Comparator;
import java.util.List;

/**
 * Sort primarily by PopularityScore in descending order.
 * For ties, sort by price in ascending order.
 */
public class SortByMultipleKeys {

    public static void main(String[] args) {
        List<Product> items = List.of(
                new Product("Laptop", 80, 100),
                new Product("Tablet", 90, 700),
                new Product("Phone", 90, 600),
                new Product("Monitor", 80, 200),
                new Product("Keyboard", 80, 100)
        );

        List<Product> sortedItems = sortItems(items);
        System.out.println(sortedItems);
    }

    static List<Product> sortItems(List<Product> items) {
        return items.stream().sorted(
                Comparator.comparingInt(Product::getPopularity).reversed().thenComparing(Product::getPrice)
        ).toList();
    }

    static class Product {
        String name;
        int popularity;
        int price;

        Product(String name, int popularity, int price) {
            this.name = name;
            this.popularity = popularity;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public int getPopularity() {
            return popularity;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "[" + name + ", " + popularity + ", " + price + "]";
        }
    }
}
