package fernandez.student_management_system.factory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;

public final class AuthWindowFactory {

    private AuthWindowFactory() {
    }

    public static Scene createLoginScene() throws IOException {

        URL fxml = AuthWindowFactory.class.getResource(
                "/fernandez/student_management_system/login-view.fxml"
        );

        if (fxml == null) {
            throw new IOException("login-view.fxml NOT FOUND. Check resources folder path.");
        }

        FXMLLoader loader = new FXMLLoader(fxml);
        return new Scene(loader.load(), 420, 300);
    }

    public static Scene createSignUpScene() throws IOException {

        URL fxml = AuthWindowFactory.class.getResource(
                "/fernandez/student_management_system/signup-view.fxml"
        );

        if (fxml == null) {
            throw new IOException("signup-view.fxml NOT FOUND. Check resources folder path.");
        }

        FXMLLoader loader = new FXMLLoader(fxml);
        return new Scene(loader.load(), 420, 360);
    }
}
