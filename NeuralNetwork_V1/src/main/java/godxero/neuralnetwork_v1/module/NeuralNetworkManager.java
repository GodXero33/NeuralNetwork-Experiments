package godxero.neuralnetwork_v1.module;

import javafx.scene.canvas.GraphicsContext;

public class NeuralNetworkManager {
	final private NeuralNetwork network;
	final private NeuralNetworkDraw drawer;

	public NeuralNetworkManager () {
		this.network = new NeuralNetwork(2, 3);
		this.drawer = new NeuralNetworkDraw();
	}

	public void draw (GraphicsContext ctx, double width, double height) {
		this.drawer.draw(ctx, this.network, width, height);
	}
}
