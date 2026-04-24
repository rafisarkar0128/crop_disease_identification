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

package com.crop.app.domain.service;

import com.crop.app.domain.model.User;
import com.crop.app.domain.repository.UserRepository;

/**
 * Service for user authentication and profile management operations.
 *
 * <p>
 * This service encapsulates business logic for user-related use cases.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 19-04-2026
 */
public class UserAuthService {
    /**
     * The user repository implementation.
     */
    private final UserRepository userRepository;

    /**
     * Creates a user authentication service with the provided repository.
     *
     * @param userRepository the user repository implementation
     */
    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Authenticates a user with the provided credentials.
     *
     * @param username the username
     * @param password the password
     * @return true if authentication succeeds, false otherwise
     */
    public boolean authenticate(String username, String password) {
        return userRepository.authenticate(username, password);
    }

    /**
     * Retrieves a user by username.
     *
     * @param username the username
     * @return the user if found, null otherwise
     */
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Retrieves a user by email.
     *
     * @param email the user email
     * @return the user if found, null otherwise
     */
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Registers and persists a new user.
     *
     * @param user the user to register
     * @return the registered user
     * @throws IllegalArgumentException if the user is invalid or the password is too short
     */
    public User register(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        if (user.getPassword() == null || user.getPassword().length() < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters long.");
        }

        return userRepository.register(user);
    }
}
