package autogradertests;

import org.example.users.Database;
import org.example.users.Responses;
import org.example.users.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ErrorCodesTests {

    private Database database;

    @BeforeEach
    void setUp() {
        database = new Database();
    }

    @Test
    void testInsertUserAlreadyExists() {
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
        Responses response = database.insertUser(user);

        assertEquals(Responses.USER_ALREADY_EXISTS, response);
        assertEquals(Responses.USER_ALREADY_EXISTS.getCode(), response.getCode());
        assertEquals(Responses.USER_ALREADY_EXISTS.getMessage(), response.getMessage());
    }

    @Test
    void testDeleteUserNotFound() {
        Responses response = database.deleteUser(99);

        assertEquals(Responses.USER_NOT_FOUND, response);
        assertEquals(Responses.USER_NOT_FOUND.getCode(), response.getCode());
        assertEquals(Responses.USER_NOT_FOUND.getMessage(), response.getMessage());
    }

    @Test
    void testDeleteUserSuccess() {
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
        Responses response = database.deleteUser(1);

        assertEquals(Responses.USER_DELETED, response);
        assertEquals(Responses.USER_DELETED.getCode(), response.getCode());
        assertEquals(Responses.USER_DELETED.getMessage(), response.getMessage());
    }

    @Test
    void testUpdateUserFieldNotFound() {
        Responses response = database.updateUser(99, "email", "new.email@example.com");

        assertEquals(Responses.USER_NOT_FOUND, response);
        assertEquals(Responses.USER_NOT_FOUND.getCode(), response.getCode());
        assertEquals(Responses.USER_NOT_FOUND.getMessage(), response.getMessage());
    }

    @Test
    void testUpdateUserFieldSuccess() {
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
        Responses response = database.updateUser(1, "email", "new.email@example.com");

        assertEquals(Responses.USER_UPDATED, response);
        assertEquals(Responses.USER_UPDATED.getCode(), response.getCode());
        assertEquals(Responses.USER_UPDATED.getMessage(), response.getMessage());
    }

    @Test
    void testAddNoteToUserNotFound() {
        Responses response = database.addNoteToUser(99, "This is a test note.");

        assertEquals(Responses.USER_NOT_FOUND, response);
        assertEquals(Responses.USER_NOT_FOUND.getCode(), response.getCode());
        assertEquals(Responses.USER_NOT_FOUND.getMessage(), response.getMessage());
    }

    @Test
    void testAddNoteToUserSuccess() {
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
        Responses response = database.addNoteToUser(1, "This is a test note.");

        assertEquals(Responses.NOTE_ADDED, response);
        assertEquals(Responses.NOTE_ADDED.getCode(), response.getCode());
        assertEquals(Responses.NOTE_ADDED.getMessage(), response.getMessage());
    }
}
