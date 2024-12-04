package org.example.users.utilities;

public class Validation {
    // Validation methods
    public static void validateUserID(int userID) {
        if (userID <= 0) {
            throw new IllegalArgumentException("User ID must be greater than zero.");
        }
    }

    public static void validateBirthDate(int birthMonth, int birthDate, int birthYear) {
        if (birthMonth < 1 || birthMonth > 12) {
            throw new IllegalArgumentException("Birth month must be between 1 and 12.");
        }

        int maxDaysInMonth = getMaxDaysInMonth(birthMonth, birthYear);
        if (birthDate < 1 || birthDate > maxDaysInMonth) {
            throw new IllegalArgumentException("Birth date must be between 1 and " + maxDaysInMonth + ".");
        }

        if (birthYear < 1900 || birthYear > 2024) {
            throw new IllegalArgumentException("Birth year must be between 1900 and 2024.");
        }
    }

    public static int getMaxDaysInMonth(int month, int year) {
        // Handle leap years for February
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> (isLeapYear(year) ? 29 : 28);
            default -> throw new IllegalArgumentException("Invalid month: " + month);
        };
    }

    // Correct leap year calculation
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }
}
