package autogradertests;
import org.example.users.UserInfo;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class BirthdayTests {

    private final Random random = new Random(400);

    @Test
    void testValidBDayLY() {
        int randomYear = getLeapYear();
        UserInfo user = new UserInfo.Builder()
                .userID(1)
                .firstName("John")
                .lastName("Doe")
                .ssn4("1234")
                .email("john.doe@example.com")
                .birthMonth(2)
                .birthDate(29)
                .birthYear(randomYear) // Leap year
                .build();

        assertNotNull(user);
    }

    @Test
    void testInvalidBirthDateNonLeapYear() {
        int randomYear = getNonLeapYear();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new UserInfo.Builder()
                    .userID(1)
                    .firstName("Jane")
                    .lastName("Smith")
                    .ssn4("5678")
                    .email("jane.smith@example.com")
                    .birthMonth(2)
                    .birthDate(29)
                    .birthYear(randomYear) // Non-leap year
                    .build();
        });

        assertEquals("Birth date must be between 1 and 28.", exception.getMessage());
    }

    @Test
    void testValidBirthDateMonthWith31Days() {
        int randomMonth = getMonthWith31Days();
        int randomDate = random.nextInt(31) + 1;
        UserInfo user = new UserInfo.Builder()
                .userID(2)
                .firstName("Alice")
                .lastName("Brown")
                .ssn4("2345")
                .email("alice.brown@example.com")
                .birthMonth(randomMonth) // Month with 31 days
                .birthDate(randomDate)
                .birthYear(1990)
                .build();

        assertNotNull(user);
    }

    @Test
    void testInvalidBirthDateMonthWith30Days() {
        int randomMonth = getMonthWith30Days();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new UserInfo.Builder()
                    .userID(3)
                    .firstName("Bob")
                    .lastName("Johnson")
                    .ssn4("3456")
                    .email("bob.johnson@example.com")
                    .birthMonth(randomMonth) // Month with 30 days
                    .birthDate(31) // Invalid date
                    .birthYear(1985)
                    .build();
        });

        assertEquals("Birth date must be between 1 and 30.", exception.getMessage());
    }

    @Test
    void testInvalidBirthMonthBelowRange() {
        int invalidMonth = -random.nextInt(100) - 1; // Generates a value <= 0
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new UserInfo.Builder()
                    .userID(4)
                    .firstName("Charlie")
                    .lastName("Davis")
                    .ssn4("4567")
                    .email("charlie.davis@example.com")
                    .birthMonth(invalidMonth)
                    .birthDate(15)
                    .birthYear(1995)
                    .build();
        });

        assertEquals("Birth month must be between 1 and 12.", exception.getMessage());
    }

    @Test
    void testInvalidBirthMonthAboveRange() {
        int invalidMonth = random.nextInt(100) + 13; // Generates a value >= 13
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new UserInfo.Builder()
                    .userID(5)
                    .firstName("Dana")
                    .lastName("Evans")
                    .ssn4("5678")
                    .email("dana.evans@example.com")
                    .birthMonth(invalidMonth)
                    .birthDate(15)
                    .birthYear(1995)
                    .build();
        });

        assertEquals("Birth month must be between 1 and 12.", exception.getMessage());
    }

    @Test
    void testInvalidBirthYearBelowRange() {
        int invalidYear = random.nextInt(1900) - 100; // Generates a value < 1900
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new UserInfo.Builder()
                    .userID(6)
                    .firstName("Frank")
                    .lastName("Green")
                    .ssn4("7890")
                    .email("frank.green@example.com")
                    .birthMonth(5)
                    .birthDate(15)
                    .birthYear(invalidYear)
                    .build();
        });

        assertTrue(exception.getMessage().contains("Birth year must be between 1900 and"));
    }

    @Test
    void testInvalidBirthYearAboveRange() {
        int currentYear = 2024;
        int invalidYear = random.nextInt(100) + currentYear + 1; // Generates a value > currentYear
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new UserInfo.Builder()
                    .userID(7)
                    .firstName("Grace")
                    .lastName("Harris")
                    .ssn4("8901")
                    .email("grace.harris@example.com")
                    .birthMonth(8)
                    .birthDate(15)
                    .birthYear(invalidYear)
                    .build();
        });

        assertTrue(exception.getMessage().contains("Birth year must be between 1900 and"));
    }

    // Helper Methods

    private int getLeapYear() {
        return 2000 + 4 * random.nextInt(6); // Returns a leap year
    }

    private int getNonLeapYear() {
        int year = 1900 + random.nextInt(123);
        return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) ? year + 1 : year; // Non-leap year
    }

    private int getMonthWith31Days() {
        int[] months = {1, 3, 5, 7, 8, 10, 12};
        return months[random.nextInt(months.length)];
    }

    private int getMonthWith30Days() {
        int[] months = {4, 6, 9, 11};
        return months[random.nextInt(months.length)];
    }
}
