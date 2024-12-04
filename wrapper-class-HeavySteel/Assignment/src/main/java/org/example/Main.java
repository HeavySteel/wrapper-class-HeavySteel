package org.example;

import org.example.users.Database;
import org.example.users.UserInfo;

public class Main {
    public static void main(String[] args) {
        var db = new Database();

        // Create users using the Builder pattern
        var user1 = new UserInfo.Builder()
                .userID(1)
                .firstName("John")
                .lastName("Doe")
                .ssn4("1234")
                .email("john.doe@example.com")
                .birthMonth(5)
                .birthDate(15)
                .birthYear(1990)
                .build();

        var user2 = new UserInfo.Builder()
                .userID(2)
                .firstName("Jane")
                .lastName("Smith")
                .ssn4("5678")
                .email("jane.smith@example.com")
                .birthMonth(7)
                .birthDate(20)
                .birthYear(1985)
                .build();

        // Insert users
        System.out.println(db.insertUser(user1).getMessage());
        System.out.println("There are " + db.getUserCount() + " users in the database.");
        System.out.println(db.insertUser(user2).getMessage());
        System.out.println("There are " + db.getUserCount() + " users in the database.");
        System.out.println(db.insertUser(user1).getMessage()); // Attempt to insert duplicate user
        System.out.println("There are " + db.getUserCount() + " users in the database.");

        // Update user
        System.out.println("Email before update: " + db.getUser(1).getEmail()); // Use the getter method for email
        System.out.println(db.updateUser(1, "email", "cool_dude@example.com").getMessage());
        System.out.println("Email after update: " + db.getUser(1).getEmail()); // Use the getter method for email
        System.out.println("There are " + db.getUserCount() + " users in the database.");

        // Add note to user
        System.out.println(db.addNoteToUser(1, "First note.").getMessage());
        System.out.println(db.addNoteToUser(2, "This is the first note for user two.").getMessage());
        System.out.println(db.addNoteToUser(2, "This is the second note for user two.").getMessage());
        System.out.println(db.addNoteToUser(2, "This is a random (third) note for user two.").getMessage());

        // Try to add note to non-existent user
        System.out.println(db.addNoteToUser(3, "Note for non-existent user.").getMessage()); // User not found

        // Delete user
        System.out.println("There are " + db.getUserCount() + " users in the database.");
        System.out.println(db.deleteUser(1).getMessage());
        System.out.println("There are " + db.getUserCount() + " users in the database.");
        System.out.println(db.deleteUser(1).getMessage()); // Attempt to delete non-existent user
        System.out.println("There are " + db.getUserCount() + " users in the database.");
    }
}
