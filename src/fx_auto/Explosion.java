package fx_auto;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Explosion extends Rectangle {

	public Explosion() {
		this.setX(50);
		this.setY(50);
		this.setHeight(32);
		this.setWidth(32);
		this.setVisible(false);
	}

	public void cargarImagen(String ruta) throws MalformedURLException {
		File file = new File(ruta);
		String localUrl = file.toURI().toURL().toString();
		Image img = new Image(localUrl);
		this.setFill(new ImagePattern(img));
	}

}
