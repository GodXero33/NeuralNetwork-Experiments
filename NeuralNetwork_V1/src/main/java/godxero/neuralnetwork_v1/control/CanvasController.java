package godxero.neuralnetwork_v1.control;

import godxero.neuralnetwork_v1.module.NeuralNetworkManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class CanvasController {
	@FXML
	public Canvas drawCanvas;
	final private NeuralNetworkManager networkManager;
	final private Object lock;
	private boolean isPaused;

	public CanvasController (NeuralNetworkManager networkManager) {
		this.networkManager = networkManager;
		this.lock = new Object();
	}

	public void initialize () {
		Thread animationThread = new Thread(() -> {
			while (true) {
				synchronized (this.lock) {
					try {
						if (isPaused) {
							this.lock.wait();
						}
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}

				long startTime = System.currentTimeMillis();
				Platform.runLater(() -> {
					final GraphicsContext ctx = this.drawCanvas.getGraphicsContext2D();
					this.draw(ctx);
				});

				long frameDuration = 1000 / 60;
				long elapsedTime = System.currentTimeMillis() - startTime;
				long sleepTime = frameDuration - elapsedTime;

				if (sleepTime > 0) {
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
			}
		});

		animationThread.setDaemon(true);
		animationThread.start();
		this.startAnimation();
	}

	public void startAnimation () {
		synchronized (this.lock) {
			isPaused = false;
			this.lock.notify();
		}
	}

	public void stopAnimation () {
		synchronized (this.lock) {
			isPaused = true;
		}
	}

	public boolean getRunning () {
		return !isPaused;
	}

	private void draw (GraphicsContext ctx) {
		final double width = this.drawCanvas.getWidth();
		final double height = this.drawCanvas.getHeight();

		ctx.clearRect(0, 0, width, height);
		ctx.translate(width / 2, height / 2);
		this.networkManager.draw(ctx, width, height);
		ctx.translate(-width / 2, -height / 2);
	}

	public void resizeCanvas (double width, double height) {
		this.drawCanvas.setWidth(width);
		this.drawCanvas.setHeight(height);

		final GraphicsContext ctx = drawCanvas.getGraphicsContext2D();
		this.draw(ctx);
	}
}
