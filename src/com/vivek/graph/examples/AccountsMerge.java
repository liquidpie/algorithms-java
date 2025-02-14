package com.vivek.graph.examples;

import java.util.*;
import java.util.stream.Stream;

/**
 * 721. Accounts Merge
 *
 * Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name,
 * and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common
 * email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people
 * could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name,
 * and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 *
 * Example 1:
 *
 * Input: accounts =
 * [["John","johnsmith@mail.com","john_newyork@mail.com"],
 * ["John","johnsmith@mail.com","john00@mail.com"],
 * ["Mary","mary@mail.com"],
 * ["John","johnnybravo@mail.com"]]
 *
 * Output:
 * [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],
 * ["Mary","mary@mail.com"],
 * ["John","johnnybravo@mail.com"]]
 *
 * Explanation:
 * The first and second John's are the same person as they have the common email "johnsmith@mail.com".
 * The third John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 *
 * Example 2:
 *
 * Input: accounts =
 * [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],
 * ["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],
 * ["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],
 * ["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],
 * ["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
 *
 * Output:
 * [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],
 * ["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],
 * ["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],
 * ["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],
 * ["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
 *
 * Solution:
 * Our goal is, for each person, we want to identify all of the emails that belong to that person.
 * Therefore, every time we find two accounts with an email in common, we will merge the two accounts into one.
 *
 * Whenever we must work with a set of elements (emails) that are connected (belong to the same user),
 * we should always consider visualizing our input as a graph. In this problem, converting the input into a graph will
 * facilitate the process of "merging" two accounts.
 *
 * Emails can be represented as nodes, and an edge between nodes will signify that they belong to the same person.
 * Since all of the emails in an account belong to the same person, we can connect all of the emails with edges.
 * Thus, each account can be represented by a connected component. What if two accounts have an email in common?
 * Then we can add an edge between the two connected components, effectively merging them into one connected component.
 *
 * Reference:
 * https://leetcode.com/problems/accounts-merge/description/
 */
public class AccountsMerge {

    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(List.of("John","johnsmith@mail.com","john_newyork@mail.com"));
        accounts.add(List.of("John","johnsmith@mail.com","john00@mail.com"));
        accounts.add(List.of("Mary","mary@mail.com"));
        accounts.add(List.of("John","johnnybravo@mail.com"));

        List<List<String>> mergedAccounts = accountsMerge(accounts);
        System.out.println(mergedAccounts);
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Set<String>> emailAdjacency = new HashMap<>();
        for (var emails : accounts) {
            for (int i = 2; i < emails.size(); i++) {
                emailAdjacency.computeIfAbsent(emails.get(i - 1), k -> new HashSet<>()).add(emails.get(i));
                emailAdjacency.computeIfAbsent(emails.get(i), k -> new HashSet<>()).add(emails.get(i - 1));
            }
        }

        List<List<String>> mergedAccounts = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        for (var account : accounts) {
            if (!visited.contains(account.get(1))) {
                Set<String> newAccount = new TreeSet<>();
                connectedComponent(account.get(1), emailAdjacency, visited, newAccount);
                mergedAccounts.add(Stream.concat(Stream.of(account.get(0)), newAccount.stream()).toList());
            }
        }
        return mergedAccounts;
    }

    private static void connectedComponent(String start, Map<String, Set<String>> edges, Set<String> visited, Set<String> component) {
        component.add(start);
        visited.add(start);

        if (edges.containsKey(start)) {
            for (var node : edges.get(start)) {
                if (!visited.contains(node)) {
                    connectedComponent(node, edges, visited, component);
                }
            }
        }
    }

}
