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

public final class UserDatabaseLoader {

    private static final Gson GSON = new Gson();

    private static final Gson PRETTY_GSON = new GsonBuilder().setPrettyPrinting().create();

    private UserDatabaseLoader() {}

    private static String requireDbId(String dbId) throws ResourceLoaderException {
        if (dbId == null || dbId.isBlank()) {
            throw new ResourceLoaderException("Database identifier cannot be null or blank.");
        }

        return dbId;
    }

    public static String format(String dbId) {
        String validatedDbId = requireDbId(dbId);
        String filename = validatedDbId.endsWith(".json") ? validatedDbId : validatedDbId + ".json";
        return ResourceConstants.DB_PATH + "/" + filename;
    }

    public static String getDatabasePathAbsolute(String db) {
        return ResourceLoader.getAbsolutePath(format(db));
    }

    public static URL getDatabase(String db) {
        return ResourceLoader.getResource(format(db));
    }

    public static InputStream getDatabaseStream(String db) {
        return ResourceLoader.getResourceStream(format(db));
    }

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

        catch (IOException | JsonSyntaxException e) {
            throw new UserDatabaseReadException("Failed to load users from database at path: "
                    + UserDatabaseLoader.getDatabasePathAbsolute("userdb"), e);
        }
    }

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

            try (BufferedWriter writer =
                    Files.newBufferedWriter(writablePath, StandardCharsets.UTF_8)) {
                PRETTY_GSON.toJson(users, writer);
            }
        } catch (IOException e) {
            throw new UserDatabaseWriteException(
                    "Failed to save users to database at path: " + writablePath, e);
        }
    }
}
