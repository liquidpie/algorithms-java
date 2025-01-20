package com.vivek.heap.examples;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TopKCompaniesHighestStockPricesAvg {

    public static void main(String[] args) {
        int[][] stockPrices = {
            {100, 200, 300, 400, 500, 600, 700},
            {150, 250, 350, 450, 550, 650, 750},
            {90, 190, 290, 390, 490, 590, 690},
            {80, 180, 280, 380, 480, 580, 680},
            {120, 220, 320, 420, 520, 620, 720}
        };

        topKCompaniesHighestAvgStockPrices(stockPrices, 3);
    }

    static void topKCompaniesHighestAvgStockPrices(int[][] stockPrices, int k) {
        PriorityQueue<Company> minHeap = new PriorityQueue<>(Comparator.comparingDouble(Company::getAvgPrice));

        for (int i = 0; i < stockPrices.length; i++) {
            // calculate average price
            int sum = 0;
            for (int price : stockPrices[i]) {
                sum += price;
            }
            double avgPrice = (double) sum / stockPrices[i].length;

            // add company to min heap
            minHeap.add(new Company(i, avgPrice));
            // remove the smallest element
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        // print
        System.out.println("Top " + k + " companies with highest average stock prices:");
        while (!minHeap.isEmpty()) {
            Company company = minHeap.poll();
            System.out.println("Company " + company.id + ": " + company.avgPrice);
        }
    }

    static class Company {
        int id;
        double avgPrice;

        public Company(int id, double avgPrice) {
            this.id = id;
            this.avgPrice = avgPrice;
        }

        public int getId() {
            return id;
        }

        public double getAvgPrice() {
            return avgPrice;
        }
    }

}
