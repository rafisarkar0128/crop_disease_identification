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

package com.crop.app.infrastructure.mapper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import com.crop.app.common.exception.UserDatabaseReadException;
import com.crop.app.domain.model.User;
import com.crop.app.infrastructure.loader.UserDatabaseLoader;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Maps user database JSON into {@link User} domain objects.
 *
 * <p>
 * This utility reads user data from classpath resources via {@link UserDatabaseLoader} and
 * deserializes it with Gson.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 19-04-2026
 */
public final class UserMapper {

    private static final Gson GSON = new Gson();

    /**
     * Prevents instantiation of this utility class.
     */
    private UserMapper() {
        // Utility class
    }

    /**
     * Reads user JSON and converts it to a {@link User} object.
     *
     * @param userId the identifier for the user
     * @return a user object populated from the corresponding JSON data
     * @throws UserDatabaseReadException if an error occurs during the mapping process
     */
    public static User mapFromJson(String userId) {
        try (InputStreamReader reader = new InputStreamReader(
                UserDatabaseLoader.getDatabaseStream(userId), StandardCharsets.UTF_8)) {

            return GSON.fromJson(reader, User.class);
        }

        // Catching both IOException and JsonSyntaxException to handle file read errors and JSON
        // parsing errors respectively, and wrapping them in a custom unchecked exception for better
        // error handling in the application flow.
        catch (IOException | JsonSyntaxException e) {
            throw new UserDatabaseReadException("Failed to map JSON for user: " + userId
                    + " at path: " + UserDatabaseLoader.getDatabasePathAbsolute(userId), e);
        }
    }
}
