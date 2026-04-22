/*
 * Crop Disease Identification
 *
 * Copyright 2026-Present Md. Rafi Sarkar (rafisarkar0128), and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.crop.app.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;
import com.crop.app.domain.model.User;
import com.crop.app.domain.repository.UserRepository;

/**
 * JSON-based user repository implementation.
 *
 * <p>
 * Loads user data from a JSON file and provides authentication and lookup operations.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 19-04-2026
 */
public class JsonUserRepository implements UserRepository {
    /**
     * List of users to manage.
     */
    private final List<User> users;

    /**
     * Creates a user repository with a pre-loaded list of users.
     *
     * @param users the list of users to manage
     */
    public JsonUserRepository(List<User> users) {
        this.users = users != null ? users : new ArrayList<>();
    }

    /**
     * Finds a user by username.
     *
     * @param username the username to search for
     * @return the user if found, null otherwise
     */
    @Override
    public User findByUsername(String username) {
        if (username == null || username.isBlank()) {
            return null;
        }
        return users.stream().filter(u -> u.getUsername().equals(username.trim())).findFirst()
                .orElse(null);
    }

    /**
     * Authenticates a user with the provided credentials.
     *
     * @param username the username
     * @param password the password
     * @return true if credentials match an existing user, false otherwise
     */
    @Override
    public boolean authenticate(String username, String password) {
        User user = findByUsername(username);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }
}
