package godxero.neuralnetwork_v1.module;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class NeuralNetworkDraw {
	public void draw (GraphicsContext ctx, NeuralNetwork network, double width, double height) {
		final int inputSize = network.getInputSize();
		final int outputSize = network.getOutputSize();
		final double[][] weights = network.getWeights();
		final double[] bias = network.getBias();
		final double size = width * 0.05;

		ctx.setFill(new Color(1.0, 0.0, 0.0, 1.0));

		for (int y = 0; y < inputSize; y++) ctx.fillArc(width * 0.1, y * size * 2 + height * 0.1, size, size, 0, Math.PI * 2, ArcType.ROUND);
	}
}
