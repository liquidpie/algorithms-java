package com.vivek.string.examples;

import java.util.ArrayList;
import java.util.List;

/**
 *
 Given a list of IP addresses as input(String), identify which bucket it falls into and return -1 if its not a valid IP address.
 Buckets:

 0.0.0.0 - 127.255.255.255
 128.0.0.0 - 191. 255.255.255
 192.0.0.0 - 223.255.255.255
 224.0.0.0 - 239.255.255.255
 240.0.0.0 - 255.255.255.255

 */
public class IPAddressBucket {

    public static void main(String[] args) {
        String[] ipAddresses = {"192.168.1.1", "192.168.1.256", "172.16.17.32", "256.256.256.256", "127.255.255.255"};
        for (String ip : ipAddresses) {
            System.out.println(getIPAddressBucket(ip));
        }
    }

    static String getIPAddressBucket(String ip) {
        List<Bucket> buckets = new ArrayList<>();
        buckets.add(new Bucket(127, "0.0.0.0 - 127.255.255.255"));
        buckets.add(new Bucket(191, "128.0.0.0 - 191.255.255.255"));
        buckets.add(new Bucket(223, "192.0.0.0 - 223.255.255.255"));
        buckets.add(new Bucket(239, "224.0.0.0 - 239.255.255.255"));
        buckets.add(new Bucket(255, "240.0.0.0 - 255.255.255.255"));

        String[] parts = ip.split("\\.");
        int part1 = Integer.parseInt(parts[0]);
        int part2 = Integer.parseInt(parts[1]);
        int part3 = Integer.parseInt(parts[2]);
        int part4 = Integer.parseInt(parts[3]);

        if (part1 < 0 || part1 > 255 ||
                part2 < 0 || part2 > 255 ||
                part3 < 0 || part3 > 255 ||
                part4 < 0 || part4 > 255)
            return "-1";

        for (Bucket bucket : buckets)
            if (part1 <= bucket.id)
                return bucket.name;

        return "-1";
    }

    static class Bucket {
        int id;
        String name;

        public Bucket(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

}
