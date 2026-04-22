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

package com.crop.app.infrastructure.loader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import com.crop.app.common.constants.ResourceConstants;
import com.crop.app.common.exception.ResourceLoaderException;
import com.crop.app.common.exception.UserDatabaseReadException;
import com.crop.app.common.exception.UserDatabaseWriteException;
import com.crop.app.domain.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * Utility for loading user database resources from the classpath.
 *
 * <p>
 * Provides helpers for resolving user database paths and reading resources as URLs or input
 * streams. Identifiers are validated and automatically normalized to a `.json` filename.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 19-04-2026
 */
public final class UserDatabaseLoader {
    /**
     * Gson instance for JSON deserialization of user data. This is a thread-safe and reusable
     * instance that can be shared across the application for all user database loading operations.
     */
    private static final Gson GSON = new Gson();

    /**
     * Gson instance with pretty-printing enabled for persisting user data.
     */
    private static final Gson PRETTY_GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Prevents instantiation of this utility class.
     */
    private UserDatabaseLoader() {}

    /**
     * Ensures the database identifier is present before path formatting.
     *
     * @param dbId the database identifier or filename
     * @return the validated database identifier
     * @throws ResourceLoaderException if the database identifier is null or blank
     */
    private static String requireDbId(String dbId) throws ResourceLoaderException {
        if (dbId == null || dbId.isBlank()) {
            throw new ResourceLoaderException("Database identifier cannot be null or blank.");
        }

        return dbId;
    }

    /**
     * Builds a database resource path for the given database identifier.
     *
     * @param dbId the database identifier or filename (e.g., "userdb.json" for dbId "userdb")
     * @return the formatted database path
     * @throws ResourceLoaderException if the database identifier is null or blank
     */
    public static String format(String dbId) {
        String validatedDbId = requireDbId(dbId);
        String filename = validatedDbId.endsWith(".json") ? validatedDbId : validatedDbId + ".json";
        return ResourceConstants.DB_PATH + "/" + filename;
    }

    /**
     * Builds an absolute database path for the given database identifier.
     *
     * @param db the database identifier or filename
     * @return the absolute path to the database resource
     * @throws ResourceLoaderException if the database identifier is null or blank
     */
    public static String getDatabasePathAbsolute(String db) {
        return ResourceLoader.getAbsolutePath(format(db));
    }

    /**
     * Resolves a database resource path to a URL.
     *
     * @param db the name of the database resource
     * @return the URL of the database resource
     * @throws ResourceLoaderException if the resource cannot be found
     */
    public static URL getDatabase(String db) {
        return ResourceLoader.getResource(format(db));
    }

    /**
     * Opens a database resource as an input stream.
     *
     * @param db the name of the database resource
     * @return an input stream for the specified database resource
     * @throws ResourceLoaderException if the resource stream cannot be opened
     */
    public static InputStream getDatabaseStream(String db) {
        return ResourceLoader.getResourceStream(format(db));
    }

    /**
     * Loads users from the user database JSON file.
     *
     * @return a list of users
     * @throws UserDatabaseReadException if an error occurs while loading the user data
     */
    public static List<User> loadUsersFromDatabase() {
        try {
            Path preferredPath = Paths.get("src", "main", "resources",
                    ResourceConstants.BASE_PACKAGE_PATH, format("userdb"));

            if (Files.exists(preferredPath)) {
                try (var reader = Files.newBufferedReader(preferredPath, StandardCharsets.UTF_8)) {
                    User[] userArray = GSON.fromJson(reader, User[].class);
                    return Arrays.asList(userArray != null ? userArray : new User[0]);
                }
            }

            try (InputStreamReader reader = new InputStreamReader(
                    UserDatabaseLoader.getDatabaseStream("userdb"), StandardCharsets.UTF_8)) {
                User[] userArray = GSON.fromJson(reader, User[].class);
                return Arrays.asList(userArray != null ? userArray : new User[0]);
            }
        }

        // Catching both IOException and JsonSyntaxException to handle file read errors and JSON
        // parsing errors respectively, and wrapping them in a custom unchecked exception for better
        // error handling in the application flow.
        catch (IOException | JsonSyntaxException e) {
            throw new UserDatabaseReadException("Failed to load users from database at path: "
                    + UserDatabaseLoader.getDatabasePathAbsolute("userdb"), e);
        }
    }

    /**
     * Resolves a writable path for the user database file.
     *
     * <p>
     * During local development this prefers the source resource file under
     * {@code src/main/resources}. If unavailable, it falls back to classpath-resolved file paths
     * when possible.
     *
     * @return writable filesystem path to the user database
     */
    private static Path resolveWritableUserDatabasePath() {
        Path sourceResourcePath = Paths.get("src", "main", "resources",
                ResourceConstants.BASE_PACKAGE_PATH, format("userdb"));
        if (Files.exists(sourceResourcePath)) {
            return sourceResourcePath;
        }

        URL url = getDatabase("userdb");
        if ("file".equalsIgnoreCase(url.getProtocol())) {
            try {
                return Paths.get(url.toURI());
            } catch (URISyntaxException e) {
                throw new UserDatabaseWriteException(
                        "Failed to resolve writable user database path from URL: " + url, e);
            }
        }

        throw new UserDatabaseWriteException("User database is not writable for protocol: "
                + url.getProtocol() + ". Run the app from the project workspace to enable writes.");
    }

    /**
     * Persists users to the user database JSON file with pretty formatting.
     *
     * @param users list of users to save
     * @throws UserDatabaseWriteException if users cannot be saved
     */
    public static void saveUsersToDatabase(List<User> users) {
        if (users == null) {
            throw new UserDatabaseWriteException("Users list cannot be null.");
        }

        Path writablePath = resolveWritableUserDatabasePath();
        try {
            Path parent = writablePath.getParent();
            if (parent != null && !Files.exists(parent)) {
                Files.createDirectories(parent);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(writablePath,
                    StandardCharsets.UTF_8)) {
                PRETTY_GSON.toJson(users, writer);
            }
        } catch (IOException e) {
            throw new UserDatabaseWriteException(
                    "Failed to save users to database at path: " + writablePath, e);
        }
    }
}
