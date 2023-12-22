module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql.rowset;
    requires ojdbc8;


    opens com.example.javafx to javafx.fxml;
    exports com.example.javafx;
    exports com.example.javafx.util;
    opens com.example.javafx.util to javafx.fxml;
    exports com.example.javafx.controller;
    opens com.example.javafx.controller to javafx.fxml;
}