package org.example.model;

public class UserValidator {
    public void validate(User user) {
        if (user == null) {
            throw new ValidationException("Null user error");
        }
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new ValidationException("Username is empty error");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new ValidationException("Password is empty error");
        }
        if (user.getUsername().length() < 8) {
            throw new ValidationException("Username too short");
        }
        if (user.getPassword().length() < 8) {
            throw new ValidationException("Password is too short error");
        }
    }
}
