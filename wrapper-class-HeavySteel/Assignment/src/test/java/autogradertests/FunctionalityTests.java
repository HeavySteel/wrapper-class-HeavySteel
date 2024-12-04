package autogradertests;
import org.example.users.Database;
import org.example.users.IDatabase;
import org.example.users.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionalityTests {

    private Database database;

    @BeforeEach
    void setUp() {
        database = new Database();
    }

    @Test
    void testInterface() {
        assertInstanceOf(IDatabase.class, database);
    }

    @Test
    void testMixedInsertsDeletesAndUpdates() {
        // Insert 5 users
        for (int i = 1; i <= 5; i++) {
            UserInfo user = new UserInfo.Builder()
                    .userID(i)
                    .firstName("First" + i)
                    .lastName("Last" + i)
                    .ssn4(String.format("%04d", i))
                    .email("user" + i + "@example.com")
                    .birthMonth(1 + i)
                    .birthDate(10 + i)
                    .birthYear(1990 + i)
                    .build();
            database.insertUser(user);
        }

        assertEquals(5, database.getUserCount());

        // Delete user 2
        database.deleteUser(2);
        assertEquals(4, database.getUserCount());
        assertNull(database.getUser(2));

        // Update user 1 and user 3
        database.updateUser(1, "email", "updated1@example.com");
        database.updateUser(3, "firstName", "UpdatedFirst3");

        UserInfo user1 = database.getUser(1);
        UserInfo user3 = database.getUser(3);

        assertEquals("updated1@example.com", user1.email());
        assertEquals("UpdatedFirst3", user3.firstName());

        // Verify other users remain unchanged
        UserInfo user4 = database.getUser(4);
        UserInfo user5 = database.getUser(5);

        assertNotNull(user4);
        assertEquals("user4@example.com", user4.email());
        assertNotNull(user5);
        assertEquals("user5@example.com", user5.email());
    }

    @Test
    void testNotesInsertionAndTracking() {
        // Insert a user
        UserInfo user = new UserInfo.Builder()
                .userID(1)
                .firstName("John")
                .lastName("Doe")
                .ssn4("1234")
                .email("john.doe@example.com")
                .birthMonth(5)
                .birthDate(15)
                .birthYear(1990)
                .build();

        database.insertUser(user);

        // Add notes across updates
        List<String> notes = new ArrayList<>();
        notes.add("This is note 1.");
        notes.add("This is note 2.");

        database.addNoteToUser(1, notes.get(0));
        database.updateUser(1, "email", "john.updated@example.com");
        database.addNoteToUser(1, notes.get(1));

        // Verify notes
        UserInfo updatedUser = database.getUser(1);
        assertNotNull(updatedUser);
        assertEquals(2, updatedUser.comments().size());
        assertTrue(updatedUser.comments().containsAll(notes));

        // Add another note after an update
        String additionalNote = "This is note 3.";
        database.addNoteToUser(1, additionalNote);
        notes.add(additionalNote);

        updatedUser = database.getUser(1);
        assertEquals(3, updatedUser.comments().size());
        assertTrue(updatedUser.comments().containsAll(notes));
    }
}
