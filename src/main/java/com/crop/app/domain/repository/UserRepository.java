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

package com.crop.app.domain.repository;

import com.crop.app.domain.model.User;

/**
 * Repository interface for user data access operations.
 *
 * <p>
 * Defines methods for retrieving and authenticating users.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 19-04-2026
 */
public interface UserRepository {

    /**
     * Finds a user by username.
     *
     * @param username the username to search for
     * @return the user if found, null otherwise
     */
    User findByUsername(String username);

    /**
     * Finds a user by email.
     *
     * @param email the email to search for
     * @return the user if found, null otherwise
     */
    User findByEmail(String email);

    /**
     * Authenticates a user with the provided credentials.
     *
     * @param username the username
     * @param password the password
     * @return true if credentials match an existing user, false otherwise
     */
    boolean authenticate(String username, String password);

    /**
     * Registers a new user and persists it.
     *
     * @param user the user to register
     * @return the persisted user
     */
    User register(User user);
}
