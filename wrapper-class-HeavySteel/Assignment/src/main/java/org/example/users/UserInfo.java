package org.example.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.example.users.utilities.Validation.*;

public record UserInfo (
        int userID,
        String firstName,
        String lastName,
        String ssn4,
        String email,
        int birthMonth,
        int birthDate,
        int birthYear,
        List<String> comments
) {
    // Compact constructor with validation
    public UserInfo {
        validateUserID(userID);
        Objects.requireNonNull(firstName, "First name cannot be null.");
        Objects.requireNonNull(lastName, "Last name cannot be null.");
        Objects.requireNonNull(ssn4, "SSN cannot be null.");
        Objects.requireNonNull(email, "Email cannot be null.");
        validateBirthDate(birthMonth, birthDate, birthYear);
        Objects.requireNonNull(comments, "Comments list cannot be null.");
    }

    // Static Builder class
    public static class Builder {
        private int userID;
        private String firstName;
        private String lastName;
        private String ssn4;
        private String email;
        private int birthMonth;
        private int birthDate;
        private int birthYear;
        private List<String> comments = new ArrayList<>();

        // Setter for userID with validation
        public Builder userID(int userID) {
            validateUserID(userID);
            this.userID = userID;
            return this;
        }

        // Setter for firstName
        public Builder firstName(String firstName) {
            this.firstName = Objects.requireNonNull(firstName, "First name cannot be null.");
            return this;
        }

        // Setter for lastName
        public Builder lastName(String lastName) {
            this.lastName = Objects.requireNonNull(lastName, "Last name cannot be null.");
            return this;
        }

        // Setter for ssn4
        public Builder ssn4(String ssn4) {
            this.ssn4 = Objects.requireNonNull(ssn4, "SSN cannot be null.");
            return this;
        }

        // Setter for email
        public Builder email(String email) {
            this.email = Objects.requireNonNull(email, "Email cannot be null.");
            return this;
        }

        // Setter for birthMonth
        public Builder birthMonth(int birthMonth) {
            if (birthMonth < 1 || birthMonth > 12) {
                throw new IllegalArgumentException("Birth month must be between 1 and 12.");
            }
            this.birthMonth = birthMonth;
            return this;
        }

        // Setter for birthDate
        public Builder birthDate(int birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        // Setter for birthYear
        public Builder birthYear(int birthYear) {
            if (birthYear < 1900 || birthYear > 2024) {
                throw new IllegalArgumentException("Birth year must be between 1900 and 2024.");
            }
            this.birthYear = birthYear;
            return this;
        }

        // Add comment to the list
        public Builder addComment(String comment) {
            comments.add(Objects.requireNonNull(comment, "Comment cannot be null."));
            return this;
        }

        // Build the UserInfo object
        public UserInfo build() {
            return new UserInfo(userID, firstName, lastName, ssn4, email, birthMonth, birthDate, birthYear, comments);
        }
    }
}
