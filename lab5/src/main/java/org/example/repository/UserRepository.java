package org.example.repository;

import org.example.model.User;

import java.util.Optional;

public interface UserRepository extends Repository<User>{
    /**
     *
     * @param username
     * @param password
     * @return
     */
    Optional<User> findUser(String username, String password);
}
