package godxero.neuralnetwork_v1.module;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class NeuralNetworkDraw {
	private NeuralNetwork network;
	private Vector2D mouse;

	public NeuralNetworkDraw (NeuralNetwork network) {
		this.network = network;
		this.mouse = new Vector2D();
	}

	public void draw (GraphicsContext ctx, double width, double height) {
		final int inputSize = this.network.getInputSize();
		final int outputSize = this.network.getOutputSize();
		final double[][] weights = this.network.getWeights();
		final double[] bias = this.network.getBias();
		final double size = Math.min(Math.min(width, height) / Math.max(inputSize, outputSize), 50.0);
		final double yInputGap = height / inputSize;
		final double yOutputGap = height / outputSize;
		final double yInputOffset = (yInputGap - size) * 0.5;
		final double yOutputOffset = (yOutputGap - size) * 0.5;

		ctx.setFill(new Color(1.0, 0.0, 0.0, 1.0));
		ctx.setStroke(new Color(0.0, 0.0, 0.0, 1.0));

		ctx.beginPath();

		for (int a = 0; a < inputSize; a++) for (int b = 0; b < outputSize; b++) {
			ctx.moveTo(size * 1.5 - width * 0.5, a * yInputGap + yInputOffset - height * 0.5 + size * 0.5);
			ctx.lineTo(width * 0.5 - size * 1.5, b * yOutputGap + yOutputOffset - height * 0.5 + size * 0.5);
		}

		ctx.stroke();

		for (int y = 0; y < inputSize; y++) ctx.fillRect(size - width * 0.5, y * yInputGap + yInputOffset - height * 0.5, size, size);
		for (int y = 0; y < outputSize; y++) ctx.fillRect(width * 0.5 - size * 2, y * yOutputGap + yOutputOffset - height * 0.5, size, size);

		ctx.fillRect(this.mouse.getX() - width * 0.5, this.mouse.getY() - height * 0.5, 30, 30);
	}

	public void updateMouse (double x, double y) {
		this.mouse.setX(x);
		this.mouse.setY(y);
	}
}
