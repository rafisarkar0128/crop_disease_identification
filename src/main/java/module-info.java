module crop.disease.identification {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.crop.main to javafx.fxml;

    exports com.crop.main;
}
