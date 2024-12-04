package org.example.users;


public interface IDatabase {
    /**
     * Inserts a user into the database.
     *
     * @param user The user to insert.
     * @return A response indicating the result of the operation.
     */
    Responses insertUser(UserInfo user);

    /**
     * Deletes a user from the database by userID.
     *
     * @param userID The ID of the user to delete.
     * @return A response indicating the result of the operation.
     */
    Responses deleteUser(int userID);

    /**
     * Updates a user's field by userID.
     *
     * @param userID    The ID of the user to update.
     * @param fieldName The name of the field to update.
     * @param value     The new value for the field.
     * @return A response indicating the result of the operation.
     */
    Responses updateUser(int userID, String fieldName, Object value);

    /**
     * Adds a note to a user by userID.
     *
     * @param userID The ID of the user to add the note to.
     * @param note   The note to add.
     * @return A response indicating the result of the operation.
     */
    Responses addNoteToUser(int userID, String note);

    /**
     * Retrieves a user by userID.
     *
     * @param userID The ID of the user to retrieve.
     * @return An Optional containing the UserInfo if found, or empty if not found.
     */
    UserInfo getUser(int userID);

    /**
     * Retrieves the number of users in the database.
     *
     * @return The number of users in the database.
     */
    int getUserCount();
}
