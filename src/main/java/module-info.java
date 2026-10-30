module com.chacon.materiales {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;

    opens com.chacon.materiales to javafx.fxml;
    opens com.chacon.materiales.controller to javafx.fxml;
    opens com.chacon.materiales.dto;
    exports com.chacon.materiales;
    exports com.chacon.materiales.dto;
    exports com.chacon.materiales.controller;
    exports com.chacon.materiales.dao;
    exports com.chacon.materiales.service;
}
