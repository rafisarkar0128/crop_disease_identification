package com.crop.app.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;
import com.crop.app.domain.model.User;
import com.crop.app.domain.repository.UserRepository;
import com.crop.app.infrastructure.loader.UserDatabaseLoader;

public class JsonUserRepository implements UserRepository {

    private final List<User> users;

    public JsonUserRepository(List<User> users) {
        this.users = users != null ? new ArrayList<>(users) : new ArrayList<>();
    }

    @Override
    public User findByUsername(String username) {
        if (username == null || username.isBlank()) {
            return null;
        }
        return users.stream().filter(u -> u.getUsername().equals(username.trim())).findFirst()
                .orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        if (email == null || email.isBlank()) {
            return null;
        }
        return users.stream().filter(u -> u.getEmail().equalsIgnoreCase(email.trim())).findFirst()
                .orElse(null);
    }

    @Override
    public boolean authenticate(String username, String password) {
        User user = findByUsername(username);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }

    @Override
    public User register(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        if (findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists.");
        }

        if (findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        users.add(user);
        UserDatabaseLoader.saveUsersToDatabase(users);
        return user;
    }
}
