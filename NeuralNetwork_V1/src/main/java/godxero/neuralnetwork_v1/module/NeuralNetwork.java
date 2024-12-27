package godxero.neuralnetwork_v1.module;

public class NeuralNetwork {
	private int inputSize;
	private int outputSize;
	private double[][] weights;
	private double[] bias;
	private double learningRate;

	public NeuralNetwork (int inputSize, int outputSize) {
		this.inputSize = inputSize;
		this.outputSize = outputSize;
		this.weights = new double[outputSize][inputSize];
		this.bias = new double[outputSize];
		this.learningRate = 0.01;

		for (double[] row : this.weights) for (int g = 0; g < inputSize; g++) row[g] = Math.random();
		for (int a = 0; a < outputSize; a++) this.bias[a] = Math.random();
	}

	private double activation (double x) {
		return 1 / (1 + Math.exp(-x));
	}

	public double[] propagate (double[] inputs) {
		final double[] output = new double[this.outputSize];

		for (int a = 0; a < this.outputSize; a++) {
			for (int b = 0; b < this.inputSize; b++) output[a] += this.weights[a][b] * inputs[b];

			output[a] += this.bias[a];
			output[a] = this.activation(output[a]);
		}

		return output;
	}

	public void train (double[] inputs, double[] target) {
		final double[] output = this.propagate(inputs);
		final double[] errors = new double[this.outputSize];

		for (int a = 0; a < this.outputSize; a++) {
			errors[a] = target[a] - output[a];

			for (int b = 0; b < this.inputSize; b++) this.weights[a][b] += errors[a] * output[a] * (1 - output[a]) * inputs[b] * this.learningRate;

			this.bias[a] += errors[a] * this.learningRate;
		}
	}

	public int getInputSize () {
		return this.inputSize;
	}

	public int getOutputSize () {
		return this.outputSize;
	}

	public double[][] getWeights () {
		return this.weights;
	}

	public double[] getBias () {
		return this.bias;
	}
}
