package com.crop.app.domain.repository;

import com.crop.app.domain.model.User;

public interface UserRepository {

    User findByUsername(String username);

    User findByEmail(String email);

    boolean authenticate(String username, String password);

    User register(User user);
}
