package godxero.neuralnetwork_v1;

import godxero.neuralnetwork_v1.control.CanvasController;
import godxero.neuralnetwork_v1.module.NeuralNetworkManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
	public static void main (String[] args) {
		Application.launch();
	}

	@Override
	public void start (Stage stage) throws IOException {
		final CanvasController controller = new CanvasController(new NeuralNetworkManager());
		final FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("canvas_view.fxml"));
		fxmlLoader.setController(controller);

		final Scene scene = new Scene(fxmlLoader.load());

		scene.widthProperty().addListener((obs, oldVal, newVal) -> controller.resizeCanvas(scene.getWidth(), scene.getHeight()));
		scene.heightProperty().addListener((obs, oldVal, newVal) -> controller.resizeCanvas(scene.getWidth(), scene.getHeight()));
		scene.setOnKeyReleased(event -> {
			if (event.getCode() != KeyCode.SPACE) return;

			if (controller.getRunning()) {
				controller.stopAnimation();
			} else {
				controller.startAnimation();
			}
		});

//		stage.setResizable(false);
		stage.setTitle("Neural Network");
		stage.setScene(scene);
		stage.show();
	}
}
