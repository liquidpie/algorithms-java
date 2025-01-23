package com.vivek.string.examples;

/**
 * Say, an organization issues ID cards to its employees with unique ID codes. The ID code for an employee named Jigarius Caesar looks as follows: CAJI202002196.
 *
 * Here’s how the ID code is derived:
 *
 *     CA: First 2 characters of the employee’s last name.
 *     JI: First 2 characters of the employee’s first name.
 *     2020: Full year of joining.
 *     02: 2 digit representation of the month of joining.
 *     19: Indicates that this is the 19th employee who joined in Feb 2020.
 *         This will have at least 2 digits, starting with 01, 02, and so on.
 *     6: The last digit is a verification digit which is computed as follows:
 *         Take the numeric part of the ID code (without the verification digit).
 *         Sum all digits in odd positions. Say this is O.
 *         Sum all digits in even positions. Say this is E.
 *         Difference between O & E. Say this is V.
 *         If V is negative, ignore the sign, e.g., -6 is treated as 6. Say this is V.
 *         If V is greater than 9, divide it by 10 and take the reminder. Say this is V.
 *         V is the verification code.
 *
 * For the above ID card, here’s how you‘ll test the verification digit.
 *
 * CAJI202002196 # ID code
 * 202002196 # Numeric part
 * 20200219 # Ignoring verification digit
 * 2 + 2 + 0 + 1 = 5 # Sum of odd position digits, i.e. O
 * 0 + 0 + 2 + 9 = 11 # Sum of even position digits, i.e. E
 * 5 - 11 = -6 # V = O - E
 * 6 # Verification digit, ignoring sign
 *
 * An ID code is considered valid if:
 *
 *     The first 4 characters of the card are correct, i.e. CAJI.
 *     The verification digit is correct, i.e. 6.
 *
 * Reference:
 * https://jigarius.com/blog/shopify-software-developer-interview
 */
public class ValidEmployeeId {

    public static void main(String[] args) {
        System.out.println(isValid("Jigarius, Caesar", "CAJI202002196"));
    }

    static boolean isValid(String empName, String id) {
        String firstName = empName.split(",")[1].trim().toUpperCase();
        String lastName = empName.split(",")[0].trim().toUpperCase();

        if (!id.startsWith(firstName.substring(0, 2) + lastName.substring(0, 2)))
            return false;

        String date = id.substring(4, id.length() - 1);
        int even = 0;
        int odd = 0;
        for (int i = 0; i < date.length(); i += 2) {
            even += date.charAt(i) - '0';
            odd += date.charAt(i + 1) - '0';
        }

        int diff = Math.abs(even - odd) % 10;

        return id.charAt(id.length() - 1) - '0' == diff;

    }

}
