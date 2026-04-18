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

package com.crop.app.domain.model;

/**
 * Domain model representing a user account.
 *
 * <p>
 * This type is used as a data container for user credentials and profile information.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 19-04-2026
 */
public class User {

    private String id;
    private String username;
    private String email;
    private String password;

    /**
     * Creates an empty user model.
     */
    public User() {
        this.id = "";
        this.username = "";
        this.email = "";
        this.password = "";
    }

    /**
     * Creates a user model with all fields initialized.
     *
     * @param id the unique user identifier
     * @param username the unique username
     * @param email the user email address
     * @param password the user password
     */
    public User(String id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * Gets the unique user identifier.
     *
     * @return the user ID
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique user identifier.
     *
     * @param id the user ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the unique username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the unique username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user email address.
     *
     * @param email the email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
