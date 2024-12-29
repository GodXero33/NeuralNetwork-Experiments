package godxero.neuralnetwork_v1.module;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class NeuralNetworkDraw {
	final private NeuralNetwork network;
	final private Vector2D mouse;

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
		final int inputSize_2 = inputSize * 2;
		final double halfSize = size * 0.5;

		ctx.setFill(new Color(1.0, 0.0, 0.0, 1.0));
		ctx.setStroke(new Color(0.0, 0.0, 0.0, 1.0));

		ctx.beginPath();

		for (int a = 0; a < inputSize; a++) for (int b = 0; b < outputSize; b++) {
			ctx.moveTo(size * 1.5 - width * 0.5, a * yInputGap + yInputOffset - height * 0.5 + halfSize);
			ctx.lineTo(width * 0.5 - size * 1.5, b * yOutputGap + yOutputOffset - height * 0.5 + halfSize);
		}

		ctx.stroke();

		final double[] nodes = new double[(inputSize + outputSize) * 2];

		for (int a = 0; a < inputSize; a++) {
			nodes[a * 2] = size - width * 0.5;
			nodes[a * 2 + 1] = a * yInputGap + yInputOffset - height * 0.5;
		}

		for (int a = 0; a < outputSize; a++) {
			nodes[inputSize_2 + a * 2] = width * 0.5 - size * 2;
			nodes[inputSize_2 + a * 2 + 1] = a * yOutputGap + yOutputOffset - height * 0.5;
		}

		for (int y = 0; y < inputSize; y++) ctx.fillOval(nodes[y * 2], nodes[y * 2 + 1], size, size);
		for (int y = 0; y < outputSize; y++) ctx.fillOval(nodes[inputSize_2 + y * 2], nodes[inputSize_2 + y * 2 + 1], size, size);

		ctx.setFill(new Color(1.0, 0.0, 1.0, 1.0));

		for (int a = 0; a < inputSize_2; a += 2) {
			final double x = nodes[a];
			final double y = nodes[a + 1];
			final double mx = this.mouse.getX() - width * 0.5;
			final double my = this.mouse.getY() - height * 0.5;
			final double distance = (mx - x - halfSize) * (mx - x - halfSize) + (my - y - halfSize) * (my - y - halfSize);

			if (distance < halfSize * halfSize) {
				ctx.fillOval(x, y, size, size);
			}
		}

		for (int a = 0; a < outputSize * 2; a += 2) {
			final double x = nodes[inputSize_2 + a];
			final double y = nodes[inputSize_2 + a + 1];
			final double mx = this.mouse.getX() - width * 0.5;
			final double my = this.mouse.getY() - height * 0.5;
			final double distance = (mx - x - halfSize) * (mx - x - halfSize) + (my - y - halfSize) * (my - y - halfSize);

			if (distance < halfSize * halfSize) {
				ctx.fillOval(x, y, size, size);
			}
		}
	}

	public void updateMouse (double x, double y) {
		this.mouse.setX(x);
		this.mouse.setY(y);
	}
}
