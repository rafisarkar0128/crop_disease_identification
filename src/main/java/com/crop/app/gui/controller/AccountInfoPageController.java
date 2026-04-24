package com.crop.app.gui.controller;

import java.util.Objects;
import com.crop.app.domain.model.User;
import com.crop.app.infrastructure.loader.IconLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class AccountInfoPageController {

    private User currentUser;

    @FXML
    private ImageView accountAvatar;

    @FXML
    private Label accountUsername;

    @FXML
    private Label accountEmail;

    @FXML
    private Label accountBio;

    public AccountInfoPageController() {}

    public AccountInfoPageController(User currentUser) {
        this.currentUser = Objects.requireNonNull(currentUser, "currentUser");
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = Objects.requireNonNull(currentUser, "currentUser");
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void init() {
        if (currentUser == null) {
            throw new IllegalStateException(
                    "Current user must be set before initializing the account info page.");
        }

        accountAvatar.setImage(IconLoader.getIconAsImage(currentUser.getAvatar()));
        accountAvatar.setPreserveRatio(true);
        accountAvatar.setSmooth(true);
        accountAvatar.setCache(true);

        accountUsername.setText(currentUser.getUsername());
        accountEmail.setText(currentUser.getEmail());

        accountBio.setText(currentUser.getBio());
        accountBio.setWrapText(true);
        accountBio.setMaxWidth(450);
    }
}
