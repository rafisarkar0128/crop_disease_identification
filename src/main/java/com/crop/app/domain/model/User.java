package com.crop.app.domain.model;

public class User {

    private String id;
    private String username;
    private String email;
    private String password;
    private String bio;
    private String avatar;

    public User() {
        this.id = "";
        this.username = "";
        this.email = "";
        this.password = "";
        this.bio = "";
        this.avatar = "";
    }

    public User(String id, String username, String email, String password) {

        if (id == null || id.isBlank() || id.isEmpty()) {
            throw new IllegalArgumentException("Parameters ID cannot be null or blank or empty.");
        }

        if (username == null || username.isBlank() || username.isEmpty()) {
            throw new IllegalArgumentException(
                    "Parameters Username cannot be null or blank or empty.");
        }

        if (email == null || email.isBlank() || email.isEmpty()) {
            throw new IllegalArgumentException(
                    "Parameters Email cannot be null or blank or empty.");
        }

        if (password == null || password.isBlank() || password.isEmpty()) {
            throw new IllegalArgumentException(
                    "Parameters Password cannot be null or blank or empty.");
        }

        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.bio = "";
        this.avatar = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
