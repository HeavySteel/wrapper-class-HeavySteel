package org.example.users;

public enum Responses {
    USER_NOT_FOUND(404, "User not found."),
    USER_DELETED(200, "User successfully deleted."),
    USER_INSERTED(200, "User successfully inserted."),
    USER_UPDATED(200, "User successfully updated."),
    USER_ALREADY_EXISTS(409, "User already exists."),
    NOTE_ADDED(200, "Note successfully added.");

    private final int code;
    private final String message;

    Responses(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}