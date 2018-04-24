package fx_auto;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;

public class FxAutoController {

	@FXML
	private Pane paneCancha;

	@FXML
	private Button botonIniciar;

	@FXML
	private Label labelPuntuacion;

	private Auto jugador;
	private Auto[] autos;
	private Caparazon caparazon;
	private Explosion explosion;

	private TimerJuego timer;

	private MediaPlayer mediaPlayer;

	public FxAutoController() {
		jugador = new Auto(150, 450, 32, 32);
		caparazon = new Caparazon(jugador);
		explosion = new Explosion();

		autos = new Auto[8];

		for (int i = 0; i < autos.length; i++) {
			double x = 90 + i / 2 * 60;
			double y = new Random().nextInt(400) - 700;

			autos[i] = new Auto(x, y, 32, 32);
		}
	}

	@FXML
	public void initialize() throws MalformedURLException {
		cargarImagenes();
		agregarObjetos();
	}

	public void botonIniciar() {
		System.out.println("Botón iniciar presionado");
		paneCancha.requestFocus();

		if (botonIniciar.getText().equals("¡Iniciar!")) {
			botonIniciar.setText("Pausar");
			timer.start();
			iniciarMusica();
		} else {
			botonIniciar.setText("¡Iniciar!");
			timer.stop();
			mediaPlayer.stop();
		}

	}

	public void keyPressed(KeyEvent ke) {
		double x = jugador.getX();
		double y = jugador.getY();

		switch (ke.getCode()) {
		case A:
		case LEFT:
			x -= 60;
			break;
		case D:
		case RIGHT:
			x += 60;
			break;
		case W:
		case UP:
			y -= 30;
			break;
		case S:
		case DOWN:
			y += 30;
			break;
		case SPACE:
			caparazon.disparar();
			break;
		default:
			break;
		}

		ke.consume();

		if (x > 77 && x < 313)
			jugador.setX(x);
		if (y > 0 && y < 500)
			jugador.setY(y);
	}

	private void cargarImagenes() throws MalformedURLException {
		jugador.cargarImagen("resources/img/autoPrincipal.png");
		autos[0].cargarImagen("resources/img/auto_amarillo.png");
		autos[1].cargarImagen("resources/img/auto_azul.png");
		autos[2].cargarImagen("resources/img/auto_blanco.png");
		autos[3].cargarImagen("resources/img/auto_cafe.png");
		autos[4].cargarImagen("resources/img/auto_celeste.png");
		autos[5].cargarImagen("resources/img/auto_plomo.png");
		autos[6].cargarImagen("resources/img/auto_rosado.png");
		autos[7].cargarImagen("resources/img/autoUsuario.png");
		caparazon.cargarImagen("resources/img/caparazon.png");
		explosion.cargarImagen("resources/img/explosion.gif");
	}

	private void agregarObjetos() throws MalformedURLException {
		Rectangle clip = new Rectangle(0, 0, 0, 0);
		clip.widthProperty().bind(paneCancha.widthProperty());
		clip.heightProperty().bind(paneCancha.heightProperty());
		paneCancha.setClip(clip);

		paneCancha.getChildren().add(jugador);
		paneCancha.getChildren().add(caparazon);
		paneCancha.getChildren().add(explosion);
		for (int i = 0; i < autos.length; i++) {
			paneCancha.getChildren().add(autos[i]);
		}

		timer = new TimerJuego(autos, paneCancha, jugador, caparazon, explosion, labelPuntuacion);
	}

	private void iniciarMusica() {
		String musicFile = "";

		switch ((int) Math.round(Math.random())) {
		case 0:
			musicFile = "objection.mp3";
			break;
		case 1:
			musicFile = "rainbow_road.mp3";
			break;
		}

		Media sound = new Media(new File(musicFile).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.play();
	}

}
