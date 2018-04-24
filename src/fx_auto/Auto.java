package fx_auto;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Auto extends Rectangle {

	private double vy;

	public Auto(double x, double y, double ancho, double alto) {
		this.setX(x);
		this.setY(y);
		this.setWidth(ancho);
		this.setHeight(alto);
		this.vy = 3;
	}

	public void mover(double alto) {
		double y = this.getY();

		y += vy;

		this.setY(y);

		if (this.getY() + this.getHeight() >= alto + 50) {
			this.setY(Math.random() * 10 - 30);
			this.setVy(Math.random() * 3 + 1);
		}
	}

	public void cargarImagen(String ruta) throws MalformedURLException {
		File file = new File(ruta);
		String localUrl = file.toURI().toURL().toString();
		Image img = new Image(localUrl);
		this.setFill(new ImagePattern(img));
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public double getVy() {
		return vy;
	}

	public double getCenterX() {
		return this.getX() + this.getWidth() / 2;
	}

	public double getCenterY() {
		return this.getY() + this.getHeight() / 2;
	}

}
