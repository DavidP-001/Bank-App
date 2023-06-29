module com.example.bankapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.mariadb.jdbc;
    requires jakarta.persistence;
    //added
    requires org.hibernate.orm.core;
    requires spring.security.crypto;

    opens com.example.bankapp to javafx.fxml;
    //added
    opens com.example.bankapp.model;
    exports com.example.bankapp;
    exports com.example.bankapp.controller;
    opens com.example.bankapp.controller to javafx.fxml;
    //added
    exports com.example.bankapp.model;
    exports com.example.bankapp.testing;
    opens com.example.bankapp.testing to javafx.fxml;

    exports com.example.bankapp.utility;
    opens com.example.bankapp.utility;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.classmate;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
}