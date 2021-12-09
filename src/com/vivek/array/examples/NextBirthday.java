package com.vivek.array.examples;

/**
 * Given an employee data containing employee id and date of birth.
 *
 *  List<Employee>  ID, Date of birth
 *
 *  API -> Given date --> upcoming date where someone birth date will fall
 *
 * Design an api which takes date as an input and returns next date at which a birthday is falling
 */
public class NextBirthday {

    private static final int[] birthdayArray = new int[366];

    public static void main(String[] args) {
        int[] input = { 2, 5, 6, 9 , 10, 20, 50, 100 };
        loadEmployeeData(input);

        System.out.println(nextBirthdate(19));
        System.out.println(nextBirthdate(130));
        System.out.println(nextBirthdate(1));
        System.out.println(nextBirthdate(2));
        System.out.println(nextBirthdate(3));
        System.out.println(nextBirthdate(100));
        System.out.println(nextBirthdate(365));
    }

    static void loadEmployeeData(int[] data) {
        if (data == null || data.length == 0) {
            return;
        }
        int j = 0;
        int n = data.length;

        for (int i = 1; i < 366; i++) {
            if (j < n && data[j] <= i) {
                j++;
            }
            birthdayArray[i] = j < n ? data[j] : data[0];
        }
    }

    static int nextBirthdate(int x) {
        return birthdayArray[x];
    }

}
