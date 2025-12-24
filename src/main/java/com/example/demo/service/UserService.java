package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.List;

public interface UserService {

    // Auth-related
    User register(String email, String password);
    User findByEmail(String email);

    // CRUD required by UserController
    User createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
