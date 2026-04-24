package com.crop.app.infrastructure.mapper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import com.crop.app.common.exception.UserDatabaseReadException;
import com.crop.app.domain.model.User;
import com.crop.app.infrastructure.loader.UserDatabaseLoader;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public final class UserMapper {

    private static final Gson GSON = new Gson();

    private UserMapper() {}

    public static User mapFromJson(String userId) {
        try (InputStreamReader reader = new InputStreamReader(
                UserDatabaseLoader.getDatabaseStream(userId), StandardCharsets.UTF_8)) {

            return GSON.fromJson(reader, User.class);
        }

        catch (IOException | JsonSyntaxException e) {
            throw new UserDatabaseReadException("Failed to map JSON for user: " + userId
                    + " at path: " + UserDatabaseLoader.getDatabasePathAbsolute(userId), e);
        }
    }
}
