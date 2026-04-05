module crop.disease.identification {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.main to javafx.fxml;

    exports com.main;
}
