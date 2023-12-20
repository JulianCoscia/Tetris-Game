package Piezas;

import Graficos.BloqueGrafico;
import Logica.Grilla;

public class Bloque{
	
	protected Grilla miGrilla;
	protected int fila,columna;
	protected BloqueGrafico miRepresentacion;
	protected boolean estaLibre;
	
	public Bloque(int fila, int columna, Grilla grilla, BloqueGrafico bg) {
		this.fila= fila;
		this.columna= columna;
		miGrilla= grilla;
		estaLibre=true;
		miRepresentacion= bg;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public boolean estaLibre() {
		return estaLibre;
	}
	
	public void setLibre(boolean l) {
		estaLibre=l;
	}
	
	public void ocupar(BloqueGrafico bg) {
		miGrilla.cambioBloque(fila, columna, bg.getImageIcon());
		estaLibre=false;
	}

	public void desocupar() {
		miGrilla.cambioBloque(fila, columna, miGrilla.getBLibre().getImageIcon());
		estaLibre=true;
	}

	public BloqueGrafico getMiRepresentacion () {
		return miRepresentacion;
	}

	public void setMiRepresentacion (BloqueGrafico bg) {
		miRepresentacion= bg;
	}
}