package fx_auto;

import javax.swing.JOptionPane;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class TimerJuego extends AnimationTimer {

	private Auto[] autos;
	private Auto jugador;
	private Pane paneCancha;
	private Caparazon caparazon;
	private Explosion explosion;
	private Label labelPuntuacion;

	private int puntuacion;

	public TimerJuego(Auto[] autos, Pane paneCancha, Auto jugador, Caparazon caparazon, Explosion explosion,
			Label labelPuntuacion) {
		this.autos = new Auto[autos.length];
		this.autos = autos;
		this.paneCancha = paneCancha;
		this.jugador = jugador;
		this.caparazon = caparazon;
		this.explosion = explosion;
		this.labelPuntuacion = labelPuntuacion;
		this.puntuacion = 0;
	}

	@Override
	public void handle(long arg0) {
		caparazon.dispararCaparazon();

		for (int i = 0; i < autos.length; i++) {
			autos[i].mover(paneCancha.getHeight());

			if (jugador.getBoundsInParent().intersects(autos[i].getBoundsInParent())) {
				jugador.setX(autos[i].getX());

				caparazon.desactivar();

				reiniciar();

				break;
			}

			if (autos[i].getVy() > 4) {
				autos[i].setVy(4);
			}

			if (caparazon.getBoundsInParent().intersects(autos[i].getBoundsInParent()) && caparazon.getActivo()) {
				explosion.setX(autos[i].getX());
				explosion.setY(autos[i].getY());

				autos[i].setY(-400);

				explosion.setVisible(true);
				caparazon.desactivar();
				break;
			}

			if (explosion.getBoundsInParent().intersects(autos[i].getBoundsInParent())
					|| explosion.getBoundsInParent().intersects(jugador.getBoundsInParent())) {
				explosion.setVisible(false);
			}

			if ((i + 1) % 3 == 0) {
				labelPuntuacion.setText("Puntuación: " + puntuacion++);
			}

			if (puntuacion % 3000 == 0 || puntuacion % 5000 == 0) {
				caparazon.activar();
			}

			for (int j = 0; j < autos.length; j++) {
				if (j != i && autos[i].getBoundsInParent().intersects(autos[j].getBoundsInParent())) {
					if (autos[i].getY() < autos[j].getY()) {
						autos[i].setVy(autos[j].getVy() / 2);
						autos[j].setVy(autos[i].getVy() * 4);
					}
				}
			}

		}
	}

	private void reiniciar() {
		JOptionPane.showMessageDialog(null, "¡Perdiste!\nPuntuación: " + puntuacion);

		puntuacion = 0;

		for (int i = 0; i < autos.length; i++) {
			double x = 90 + i / 2 * 60;
			double y = autos[i].getY() - 750;

			autos[i].setX(x);
			autos[i].setY(y);
			autos[i].setVy(Math.random() * 3);
		}

		jugador.setX(150);
		jugador.setY(450);
	}

}
