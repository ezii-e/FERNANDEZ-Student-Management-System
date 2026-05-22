module fernandez.student_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;

    opens fernandez.student_management_system.controller to javafx.fxml;
    opens fernandez.student_management_system.model to javafx.base;

    exports fernandez.student_management_system.app;
}
