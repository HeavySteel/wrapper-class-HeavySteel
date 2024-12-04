package org.example.users;

import java.util.HashMap;
import java.util.Map;

public class Database implements IDatabase {
    // Simulated in-memory database using a HashMap
    private final Map<Integer, UserInfo> userDatabase = new HashMap<>();

    @Override
    public Responses insertUser(UserInfo user) {
        if (userDatabase.containsKey(user.getUserID())) {
            return new Responses("User with ID " + user.getUserID() + " already exists.");
        }
        userDatabase.put(user.getUserID(), user);
        return new Responses("User with ID " + user.getUserID() + " inserted successfully.");
    }

    @Override
    public Responses deleteUser(int userID) {
        if (!userDatabase.containsKey(userID)) {
            return new Responses("User with ID " + userID + " not found.");
        }
        userDatabase.remove(userID);
        return new Responses("User with ID " + userID + " deleted successfully.");
    }

    @Override
    public Responses updateUser(int userID, String fieldName, Object value) {
        UserInfo user = userDatabase.get(userID);
        if (user == null) {
            return new Responses("User with ID " + userID + " not found.");
        }

        // Check the field to update
        if ("name".equalsIgnoreCase(fieldName)) {
            user.setName((String) value);
            return new Responses("User name updated successfully.");
        } else if ("email".equalsIgnoreCase(fieldName)) {
            user.setEmail((String) value);
            return new Responses("User email updated successfully.");
        } else {
            return new Responses("Invalid field name: " + fieldName);
        }
    }

    @Override
    public Responses addNoteToUser(int userID, String note) {
        UserInfo user = userDatabase.get(userID);
        if (user == null) {
            return new Responses("User with ID " + userID + " not found.");
        }
        // Simulating adding a note to the user (could store this note in a field in UserInfo)
        return new Responses("Note added to user with ID " + userID + ": " + note);
    }

    @Override
    public UserInfo getUser(int userID) {
        return userDatabase.get(userID);
    }

    @Override
    public int getUserCount() {
        return userDatabase.size();
    }
}
