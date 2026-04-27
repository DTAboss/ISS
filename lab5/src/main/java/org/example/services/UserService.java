package org.example.services;

import org.example.model.User;
import org.example.model.UserValidator;
import org.example.model.ValidationException;
import org.example.repository.UserRepository;

import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;
    private final UserValidator validator;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.validator = new UserValidator();
    }

    public Optional<User> authenticate(String username, String password) {
        var user = userRepository.findUser(username, password);
        if (user.isPresent()) {
            return user;
        }
        ;
        return Optional.empty();
    }

    public Optional<User> createUser(String username, String password) {
        var user = new User(username, password);
        validator.validate(user);
        userRepository.save(user);
        return Optional.of(user);


    }
}