package com.vivek.logprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Problem Breakdown
 *
 *     Input: Log files for two days with entries (timestamp, customerId, productId).
 *     Criteria: A loyal customer is one who:
 *         1. Visits both the days meaning must appear in both log files.
 *         2. They must have viewed at least two unique products across both days.
 *
 * Algorithm
 *
 * Basic Approach:
 *     Parse the log files and store the data in a map where the key is the customerId and the value is a set of product IDs they viewed.
 *     Find customers who exist in both day logs.
 *     For those customers, check if they have viewed at least two unique products on each day.
 *     Return the list of such customers.
 */
public class LoyalCustomers {


    public static void main(String[] args) throws IOException {
        // Example file paths
        String logDay1 = "log_day1.txt";
        String logDay2 = "log_day2.txt";

        // Parse logs for both days
        Map<String, Set<String>> day1Data = parseLogFile(logDay1);
        Map<String, Set<String>> day2Data = parseLogFile(logDay2);

        // Find loyal customers
        List<String> loyalCustomers = findLoyalCustomers(day1Data, day2Data);

        // Print results
        System.out.println("Loyal Customers: " + loyalCustomers);
    }

    private static Map<String, Set<String>> parseLogFile(String filePath) throws IOException {
        URL url = LoyalCustomers.class.getResource(filePath);
        Map<String, Set<String>> customerProducts = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(new File(url.getPath())))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assume log format: timestamp,customerId,productId
                String[] parts = line.split(",");
                String customerId = parts[1];
                String productId = parts[2];

                // Add product to the customer's set
                customerProducts.computeIfAbsent(customerId, k -> new HashSet<>()).add(productId);
            }
        }

        return customerProducts;
    }

    private static List<String> findLoyalCustomers(Map<String, Set<String>> day1Data, Map<String, Set<String>> day2Data) {
        // Find intersection of customers in both days
        Set<String> commonCustomers = new HashSet<>(day1Data.keySet());
        commonCustomers.retainAll(day2Data.keySet());

        // Filter customers who viewed at least two unique products on both days
        return commonCustomers.stream()
                .filter(customer -> Stream.concat(day1Data.get(customer).stream(), day2Data.get(customer).stream()).collect(Collectors.toSet()).size() >= 2)
                .collect(Collectors.toList());
    }

}
