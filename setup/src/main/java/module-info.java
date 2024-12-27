module godxero.neuralnetwork_v1 {
	requires javafx.controls;
	requires javafx.fxml;


	opens godxero.neuralnetwork_v1 to javafx.fxml;
	exports godxero.neuralnetwork_v1;
	exports godxero.neuralnetwork_v1.control;
	opens godxero.neuralnetwork_v1.control to javafx.fxml;
}