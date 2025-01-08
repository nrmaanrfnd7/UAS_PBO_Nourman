module com.example.crud {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.pbo.crud_mvc to javafx.fxml;
    exports com.pbo.crud_mvc;
    exports com.pbo.crud_mvc.model;
    opens com.pbo.crud_mvc.model to javafx.fxml;
    exports com.pbo.crud_mvc.model.dao;
    opens com.pbo.crud_mvc.model.dao to javafx.fxml;
    exports com.pbo.crud_mvc.database;
    opens com.pbo.crud_mvc.database to javafx.fxml;
    exports com.pbo.crud_mvc.controller;
    opens com.pbo.crud_mvc.controller to javafx.fxml;
}