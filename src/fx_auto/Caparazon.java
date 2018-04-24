package fx_auto;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Caparazon extends Circle {

	private Auto jugador;
	private boolean activo;
	private boolean disparado;

	public Caparazon(Auto jugador) {
		this.setRadius(5);
		this.setCenterX(jugador.getX() + jugador.getWidth() / 2 + this.getRadius() / 2);
		this.setCenterY(jugador.getY() + jugador.getHeight() + this.getRadius() * 2);
		this.jugador = jugador;
		this.activo = true;
		this.disparado = false;
	}

	public void dispararCaparazon() {

		if (disparado) {
			double y = this.getCenterY();

			y -= 10;

			this.setCenterY(y);

		} else {
			this.setCenterX(jugador.getX() + jugador.getWidth() / 2 + this.getRadius() / 2);
			this.setCenterY(jugador.getY() + jugador.getHeight() + this.getRadius() * 2);
		}
	}

	public void disparar() {
		this.disparado = true;
	}

	public void cargarImagen(String ruta) throws MalformedURLException {
		File file = new File(ruta);
		String localUrl = file.toURI().toURL().toString();
		Image img = new Image(localUrl);
		this.setFill(new ImagePattern(img));
	}

	public void desactivar() {
		this.activo = false;
		this.disparado = false;
		this.setVisible(false);
	}

	public void activar() {
		this.activo = true;
		this.disparado = false;
		this.setVisible(true);
	}

	public boolean getActivo() {
		return activo;
	}

}
