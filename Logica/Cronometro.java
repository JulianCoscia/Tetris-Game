package Logica;

import javax.swing.JLabel;

public class Cronometro extends Thread{
	
	private int minutos;
	private int segundos;
	private JLabel tiempo;
	private boolean activo;
	private Juego miJuego;
	
	public Cronometro (JLabel etiqueta) {
		activo= true;
		tiempo = etiqueta;
		minutos = 0;
		segundos = 0;
	}
	
	public void detener() {
		activo = false;
	}
	
	public String getTiempo() {
		return tiempo.getText();
	}
	
	public void asignarJuego(Juego juego) {
		miJuego = juego;
	}
	
	public void run() {
		try {
			while (activo) {
				Thread.sleep(1000);
				segundos = segundos + 1;
				if (segundos % 30 == 0) {
					miJuego.aumentarVelocidad();
				}
				//Esto es solamente visual, para que se muestre en formato 00:00
				if (segundos == 60) {
					minutos = minutos + 1;
					segundos = 0;}
				if (segundos < 10 & minutos < 10)
					tiempo.setText("0"+minutos+":0"+segundos);
					if (segundos < 10)
						tiempo.setText(minutos+":0"+segundos);
					else
						if (segundos > 10 & minutos > 10)
							tiempo.setText(minutos+":"+segundos);
						else
							if (segundos > 10 & minutos < 10)
								tiempo.setText("0"+minutos+":"+segundos);
			}
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
