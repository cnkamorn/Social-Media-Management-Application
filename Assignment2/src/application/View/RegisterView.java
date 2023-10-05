package application.View;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegisterView {

	public RegisterView() {
	}

	public void getScene() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Register.fxml"));
			Parent parentNode = loader.load();
			Scene scene = new Scene(parentNode);
			Stage stage = new Stage();
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}