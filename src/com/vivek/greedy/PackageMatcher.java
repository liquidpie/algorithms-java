package com.vivek.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Package matcher
 *
 * Description
 *
 * As the owner of an online store, you need to fulfill orders everyday.
 * To optimize the packing of each order, you decide to write an algorithm to match boxes and items based on their respective sizes.
 *
 * You have access to the following two boxes:
 * - A medium box (identifier: M)
 * - A large box (identifier: L)
 *
 * When possible, you should try to fit multiple items in the same box but boxes can only contain one type of product.
 * This is the list of items you sell along with associated boxes:
 * - Camera (identifier: Cam): one can fit in a medium box, and up to two can fit in a large box
 * - Gaming Console (identifier: Game): too big for a medium box, but up to two can fit in a large box
 * - max of 2 g consoles can fit in 1 box
 * - Bluetooth speaker (identifier: Blue): one can fit in a large box . max is 1 per large box
 *
 * Your goal is to design a function that takes a list of items and returns the box & item matches (examples below).
 *
 * Your solution should work for any number of each item greater than or equal to zero.
 * Input = [], Output = []
 * ## Input/Output expectations
 * ["Cam"] -> [M: ["Cam"]]
 * ["Cam", "Game"] -> [M: ["Cam"], L: ["Game"]]
 * ["Game", "Blue"] -> [L: ["Game"], L : ["Blue"]]
 * ["Game", "Game", "Blue"] -> [L: ["Game", "Game"], L : ["Blue"]]
 * ["Cam", "Cam", "Game", "Game"] -> [L: ["Cam", "Cam"], L: ["Game", "Game"]]
 * ["Cam", "Cam", "Cam", "Game", "Game", "Game", "Cam", "Blue"] ->
 * [L: ["Cam", "Cam"], L: ["Cam", "Cam"], L: ["Game", "Game"], L: ["Game"], L: ["Blue"]]
 * ["Cam", "Cam", "Cam", "Game", "Game", "Cam", "Cam", "Blue", "Blue"] -> [L: ["Cam", "Cam"] , L: ["Cam", "Cam"] , M: ["Cam"] , L: ["Game", "Game"] , L: ["Blue"] , L: ["Blue"]]
 *
 * Reference:
 * https://leetcode.com/discuss/interview-question/1367405/Shopify-Coding-On-Site-(Package-Matcher)
 *
 */
public class PackageMatcher {

    public static void main(String[] args) {
        {
            List<String> items = List.of("Cam");
            System.out.println(packageMatcher(items));
        }
        {
            List<String> items = List.of("Cam", "Game");
            System.out.println(packageMatcher(items));
        }
        {
            List<String> items = List.of("Game", "Blue");
            System.out.println(packageMatcher(items));
        }
        {
            List<String> items = List.of("Game", "Game", "Blue");
            System.out.println(packageMatcher(items));
        }
        {
            List<String> items = List.of("Cam", "Cam", "Game", "Game");
            System.out.println(packageMatcher(items));
        }
        {
            List<String> items = List.of("Cam", "Cam", "Cam", "Game", "Game", "Game", "Cam", "Blue");
            System.out.println(packageMatcher(items));
        }
        {
            List<String> items = List.of("Cam", "Cam", "Game", "Game", "Cam", "Cam", "Cam", "Blue", "Blue");
            System.out.println(packageMatcher(items)); // [M: [Cam], L: [Cam, Cam], L: [Cam, Cam], L: [Game, Game], L: [Blue], L: [Blue]]
        }
    }

    static List<Map<Character, List<String>>> packageMatcher(List<String> items) {
        Map<String, Map<Character, Integer>> itemToBoxCountMap = new HashMap<>();
        itemToBoxCountMap.put("Cam", Map.of('M', 1, 'L', 2));
        itemToBoxCountMap.put("Game", Map.of('M', 0, 'L', 2));
        itemToBoxCountMap.put("Blue", Map.of('M', 0, 'L', 1));

        List<Map<Character, List<String>>> result = new ArrayList<>();

        Map<String, Integer> itemCountMap = new HashMap<>();
        for (String item : items) {
            itemCountMap.merge(item, 1, Integer::sum);
        }

        for (Map.Entry<String, Integer> itemEntry : itemCountMap.entrySet()) {
            String item = itemEntry.getKey();
            int count = itemEntry.getValue();
            result.addAll(boxesForItem(itemToBoxCountMap.get(item), item, count));
        }

        return result;
    }

    static List<Map<Character, List<String>>> boxesForItem(Map<Character, Integer> boxLimitMap, String item, int count) {
        List<Map<Character, List<String>>> boxItems = new ArrayList<>();
        int largeBoxCount = count / boxLimitMap.get('L');
        int mediumBoxCount = boxLimitMap.get('M') > 0 ? (count - boxLimitMap.get('L') * largeBoxCount) / boxLimitMap.get('M') : 0;
        if (mediumBoxCount == 0 && largeBoxCount == 0)
            largeBoxCount += count % boxLimitMap.get('L');

        for (int i = 0; i < mediumBoxCount; i++) {
            Map<Character, List<String>> box = new HashMap<>();
            for (int j = 0; j < boxLimitMap.get('M') && j <= count; j++) {
                box.computeIfAbsent('M', k -> new ArrayList<>()).add(item);
                count--;
            }
            boxItems.add(box);
        }

        for (int i = 0; i < largeBoxCount; i++) {
            Map<Character, List<String>> box = new HashMap<>();
            for (int j = 0; j < boxLimitMap.get('L') && j <= count; j++) {
                box.computeIfAbsent('L', k -> new ArrayList<>()).add(item);
                count--;
            }
            boxItems.add(box);
        }

        return boxItems;
    }

}
