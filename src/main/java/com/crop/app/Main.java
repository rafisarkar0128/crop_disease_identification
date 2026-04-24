package com.crop.app;

import com.crop.app.common.constants.AppConstants;
import com.crop.app.gui.view.LoginPage;
import com.crop.app.infrastructure.loader.IconLoader;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(AppConstants.APP_NAME + " - " + AppConstants.APP_VERSION);
        primaryStage.getIcons().add(IconLoader.getIconAsImage("logo-main.png"));
        primaryStage.setScene(new LoginPage(primaryStage).createScene());
        primaryStage.show();
    }
}
