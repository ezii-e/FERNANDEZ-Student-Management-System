package fernandez.student_management_system.controller;

import fernandez.student_management_system.factory.AuthWindowFactory;
import fernandez.student_management_system.factory.StudentWindowFactory;
import fernandez.student_management_system.repository.AuthRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuthController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    private final AuthRepository authRepository = new AuthRepository();

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            showError("Missing data", "Enter your username and password.");
            return;
        }
        try {
            if (authRepository.authenticate(username, password)) {
                Stage stage = (Stage) usernameField.getScene().getWindow();
                Scene scene = StudentWindowFactory.createScene();
                stage.setTitle("Student Management System");
                stage.setScene(scene);
            } else {
                showError("Login failed", "Invalid username or password.");
            }
        } catch (Exception e) {
            showError("Login error", e.getMessage());
        }
    }

    @FXML
    private void handleOpenSignUp(ActionEvent event) throws Exception {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(AuthWindowFactory.createSignUpScene());
        stage.setTitle("Sign Up");
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
