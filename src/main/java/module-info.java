module crop.disease.identification {
    requires javafx.graphics;
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.crop.app to javafx.fxml;
    opens com.crop.app.domain.model to com.google.gson;
    opens com.crop.app.gui.view to javafx.fxml;
    opens com.crop.app.gui.controller to javafx.fxml;

    exports com.crop.app;
}
