module com.example.ogreniyorum {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires java.sql;


    opens com.example.ogreniyorum.Models to javafx.base;
    opens com.example.ogreniyorum to javafx.fxml;
    exports com.example.ogreniyorum;
    exports com.example.ogreniyorum.controllers;
    opens com.example.ogreniyorum.controllers to javafx.fxml;
    exports com.example.ogreniyorum.managers;
    opens com.example.ogreniyorum.managers to javafx.fxml;
}