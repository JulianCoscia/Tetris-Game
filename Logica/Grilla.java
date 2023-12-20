package Logica;

import javax.swing.ImageIcon;
import Graficos.BloqueGrafico;
import Graficos.BloqueGraficoLibre;
import Piezas.Bloque;

public class Grilla {

	protected Juego miJuego;
	protected Bloque[][] matriz;
	protected final int cantFilas=21;
	protected final int	cantColumnas=10;
	protected BloqueGrafico bloqueLibre;
	
	public Grilla(Juego juego) {
		miJuego= juego;
		matriz = new Bloque[cantFilas][cantColumnas];
		bloqueLibre = new BloqueGraficoLibre();	
		for(int i=0; i<cantFilas; i++) {
			for(int j=0; j<cantColumnas; j++) {
				matriz[i][j] = new Bloque(i, j, this, bloqueLibre);
				cambioBloque(i, j, bloqueLibre.getImageIcon());
			}
		}
	}
	
	public Bloque getBloque(int fila, int columna) {
		return matriz[fila][columna];
	}
	
	public Bloque getInferior (Bloque bloque) {
		return matriz[bloque.getFila() + 1][bloque.getColumna()];
	}
	
	public Bloque getIzquierdo (Bloque bloque) {
		return matriz[bloque.getFila()][bloque.getColumna() - 1];
	}
	
	public Bloque getDerecho (Bloque bloque) {
		return matriz[bloque.getFila()][bloque.getColumna() + 1];
	}
	
	
	public BloqueGrafico getBLibre() {
		return bloqueLibre;
	}
	
	public void cambioBloque(int fila, int colum, ImageIcon imagen) {
		miJuego.cambioBloque(fila, colum, imagen);
	}

	public int getCantFilas() {
		return cantFilas;
	}

	public int getCantcolumnas() {
		return cantColumnas;
	}
}
