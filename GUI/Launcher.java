package GUI;

import java.awt.EventQueue;
import Logica.Juego;

public class Launcher {
	public static void main(String args[]) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				GUI frame=new GUI(21, 10);
				Juego juego = new Juego(frame);
				frame.registrarJuego(juego);
				VentanaInformacion info = new VentanaInformacion(juego);
				juego.setVentanaInformacion(info);
				juego.iniciarJuego();
				frame.setVisible(true);
				frame.setSize(400, 700);
			}
		});
	}
}