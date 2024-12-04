package autogradertests;
import org.example.users.Database;
import org.example.users.Responses;
import org.example.users.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CountTests {

    private Database database;
    private final Random random = new Random(400);
    private String[] firstNames;
    private String[] lastNames;
    private String[] emails;

    @BeforeEach
    void setUp() {
        database = new Database();

        // Pre-generate arrays for first names, last names, and emails
        firstNames = new String[120];
        lastNames = new String[120];
        emails = new String[120];

        for (int i = 0; i < 120; i++) {
            firstNames[i] = randomString(5);
            lastNames[i] = randomString(7);
            emails[i] = randomString(10) + "@example.com";
        }
    }

    @Test
    void testRandomizedOperations() {
        // Insert 100 users with pre-generated random data
        for (int i = 1; i <= 100; i++) {
            UserInfo user = new UserInfo.Builder()
                .userID(i)
                .firstName(firstNames[i - 1])
                .lastName(lastNames[i - 1])
                .ssn4(randomSSN4())
                .email(emails[i - 1])
                .birthMonth(random.nextInt(12) + 1)
                .birthDate(random.nextInt(28) + 1) // Simplified: Assume up to 28 days for all months
                .birthYear(1900 + random.nextInt(123)) // Random year between 1900 and 2023
                .build();

            database.insertUser(user);
        }

        // Verify count after inserts and field values
        assertEquals(100, database.getUserCount());
        for (int i = 1; i <= 100; i++) {
            UserInfo user = database.getUser(i);
            assertNotNull(user);
            assertEquals(firstNames[i - 1], user.firstName());
            assertEquals(lastNames[i - 1], user.lastName());
            assertEquals(emails[i - 1], user.email());
        }

        // Delete random users, ensuring no duplicate deletes
        Set<Integer> deletedUserIds = new HashSet<>();
        for (int i = 1; i <= 10; i++) {
            int userIdToDelete;
            do {
                userIdToDelete = random.nextInt(100) + 1; // Random ID between 1 and 100
            } while (deletedUserIds.contains(userIdToDelete)); // Avoid duplicates

            Responses response = database.deleteUser(userIdToDelete);
            deletedUserIds.add(userIdToDelete);
            assertEquals(Responses.USER_DELETED, response);
        }

        // Verify count after deletes
        int remainingUsers = 100 - deletedUserIds.size();
        assertEquals(remainingUsers, database.getUserCount());

        // Update random users
        for (int i = 1; i <= 10; i++) {
            int userIdToUpdate = random.nextInt(100) + 1;
            if (database.getUser(userIdToUpdate) != null) {
                // Update email and firstName with new random strings
                String updatedEmail = randomString(8) + "@updated.com";
                String updatedFirstName = randomString(6);

                database.updateUser(userIdToUpdate, "email", updatedEmail);
                database.updateUser(userIdToUpdate, "firstName", updatedFirstName);

                // Store updated values for later verification
                emails[userIdToUpdate - 1] = updatedEmail;
                firstNames[userIdToUpdate - 1] = updatedFirstName;
            }
        }

        // Insert more random users (IDs 101-120) using pre-generated data
        for (int i = 101; i <= 120; i++) {
            UserInfo user = new UserInfo.Builder()
                    .userID(i)
                    .firstName(firstNames[i - 1])
                    .lastName(lastNames[i - 1])
                    .ssn4(randomSSN4())
                    .email(emails[i - 1])
                    .birthMonth(random.nextInt(12) + 1)
                    .birthDate(random.nextInt(28) + 1)
                    .birthYear(1900 + random.nextInt(123))
                    .build();

            database.insertUser(user);
        }

        // Verify count after additional inserts
        int expectedTotalUsers = remainingUsers + 20; // 100 - deleted + 20 new inserts
        assertEquals(expectedTotalUsers, database.getUserCount());

        // Verify all user data
        for (int userId = 1; userId <= 120; userId++) {
            UserInfo user = database.getUser(userId);
            if (user != null) {
                assertEquals(firstNames[userId - 1], user.firstName());
                assertEquals(lastNames[userId - 1], user.lastName());
                assertEquals(emails[userId - 1], user.email());
                assertTrue(user.birthMonth() >= 1 && user.birthMonth() <= 12);
                assertTrue(user.birthDate() >= 1 && user.birthDate() <= 28);
                assertTrue(user.birthYear() >= 1900 && user.birthYear() <= 2023);
            }
        }
    }

    // Helper Methods

    private String randomString(int length) {
        return random.ints(97, 123) // ASCII a-z
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private String randomSSN4() {
        return String.format("%04d", random.nextInt(10000)); // Generates a random 4-digit SSN segment
    }
}
