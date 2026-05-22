package fernandez.student_management_system.factory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;

public final class StudentWindowFactory {

    private StudentWindowFactory() {
    }

    public static Scene createScene() throws IOException {

        URL fxml = StudentWindowFactory.class.getResource(
                "/fernandez/student_management_system/student-view.fxml"
        );

        if (fxml == null) {
            throw new IOException("student-view.fxml NOT FOUND. Check resources folder path.");
        }

        FXMLLoader loader = new FXMLLoader(fxml);
        return new Scene(loader.load());
    }
}
