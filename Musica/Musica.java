package Musica;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

public class Musica{
	private static final boolean Abierto = true;
	private static final boolean Cerrado = false;
	private boolean estado;
	private Clip clip;
	
	public Musica() {
	            	try {
						clip =AudioSystem.getClip();
					} catch (LineUnavailableException e) {
						e.printStackTrace();
					}
	}

	/**
	 * Abre el archivo de audio a reproducir.
	 * @param stream Archivo de audio.
	 * @throws LineUnavailableException
	 * @throws IOException
	 */
	public void abrirClip (AudioInputStream stream) throws LineUnavailableException, IOException {
		clip.open(stream);
		estado = Abierto;
	}
	
	/**
	 * Inicia la reproduccion de la musica.
	 */
	public void iniciar() {
		clip.start();
	}
	
	/**
	 * Pone el reproduccion en loop la cancion actual.
	 */
	public void repetirInfinitamente() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	/**
	 * Detiene la reproduccion de la musica.
	 */
	public void detener() {
		clip.stop();
	}
	
	public void cerrarClip(){
		clip.close();
		estado = Cerrado;
	}
	
	public boolean getEstado() {
		return estado;
	}
	
	/**
	 * Establece el volumen de la musica.
	 * @param val Volumen.
	 */
	public void setVolumen(int val) {
		//hASTA ACA LLEGA Y SE ACTUALIZA BIEN. FALTA PODER CAMBIARLE EL VOLUMEN
		//ADEMAS CUANDO TOCO EL VOLUMEN DESPUES YA NO VUELVE A MOVER EL JUEGO. SE MUEVE LA BARRA CON LA FLECHITAS. HAY QUE DESACTIVAR ESO
		FloatControl volumen = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volumen.setValue(val/100f);
	}
}
