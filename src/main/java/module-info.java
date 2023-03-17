module com.example.kg2_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.example.kg2_2 to javafx.fxml;
    exports com.example.kg2_2;
}