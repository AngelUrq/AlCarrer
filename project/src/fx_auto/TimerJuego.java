package fx_auto;

import java.util.Random;

import javax.swing.JOptionPane;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

public class TimerJuego extends AnimationTimer {

	private Auto[] autos;
	private Auto jugador;
	private Pane paneCancha;

	public TimerJuego(Auto[] autos, Pane paneCancha, Auto jugador) {
		this.autos = new Auto[autos.length];
		this.autos = autos;
		this.paneCancha = paneCancha;
		this.jugador = jugador;
	}

	@Override
	public void handle(long arg0) {

		for (int i = 0; i < autos.length; i++) {
			autos[i].mover(paneCancha.getHeight());

			if (jugador.getBoundsInParent().intersects(autos[i].getBoundsInParent())) {
				jugador.setX(autos[i].getX());
				reiniciar();
				break;
			}
		}

	}

	private void reiniciar() {
		for (int i = 0; i < autos.length; i++) {
			double x = 90 + i / 2 * 60;
			double y = new Random().nextInt(400) - 700;

			autos[i].setX(x);
			autos[i].setY(y);
			autos[i].setVy(Math.random() * 3);
		}

		JOptionPane.showMessageDialog(null, "¡Perdiste!");

		jugador.setX(150);
		jugador.setY(450);
	}

}
