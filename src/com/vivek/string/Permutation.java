package com.vivek.string;

/**
 * Created by VJaiswal on 05/04/18.
 */
public class Permutation {

    private void permute(String str, int l, int r) {

        if (l == r)
            System.out.println(str);
        else {
            for (int i = l; i <= r; i++) {
                str = swap(str, l, i);
                permute(str, l + 1, r);
                str = swap(str, l , i);
            }
        }
    }

    private String swap(String str, int i, int j) {
        char[] arr = str.toCharArray();
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        return String.valueOf(arr);
    }

    public static void main(String[] args) {
        String str = "ABC";
        Permutation permutation = new Permutation();
        permutation.permute(str, 0, str.length() - 1);
    }
}
