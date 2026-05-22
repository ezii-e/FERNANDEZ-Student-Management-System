package fernandez.student_management_system.app;

import fernandez.student_management_system.factory.AuthWindowFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class StudentApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(AuthWindowFactory.createLoginScene());
        stage.setTitle("Login");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
