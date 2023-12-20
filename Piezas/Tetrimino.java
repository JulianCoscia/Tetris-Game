package Piezas;

import Graficos.TetriminoGrafico;
import Logica.Grilla;

public abstract class Tetrimino{
	
	protected Bloque w,x,y,z;
	protected Grilla miGrilla;
	protected TetriminoGrafico miRepresentacion;
	protected int rotacion;
	protected boolean primerAparicion;

	public abstract boolean moverAbajo();
	public abstract void moverIzquierda();
	public abstract void moverDerecha();
	public abstract void rotar();
	public abstract int [] lineasARevisar ();
	
	protected int getRotacion() {
		return rotacion;
	}
	
	protected void setRotacion( int rotacion) {
		this.rotacion= rotacion;
	}
	
	protected void modificarBloques (Bloque auxW, Bloque auxX, Bloque auxY, Bloque auxZ){
		w.desocupar();
		x.desocupar();
		y.desocupar();
		z.desocupar();
		auxW.ocupar(miRepresentacion.getBloqueGraficoW());
		auxX.ocupar(miRepresentacion.getBloqueGraficoX());
		auxY.ocupar(miRepresentacion.getBloqueGraficoY());
		auxZ.ocupar(miRepresentacion.getBloqueGraficoZ());
		w= auxW;
		x= auxX;
		y= auxY;
		z= auxZ;
	}
	
	protected void setBloquesGraficos(){
		w.setMiRepresentacion(miRepresentacion.getBloqueGraficoW());
		x.setMiRepresentacion(miRepresentacion.getBloqueGraficoX());
		y.setMiRepresentacion(miRepresentacion.getBloqueGraficoY());
		z.setMiRepresentacion(miRepresentacion.getBloqueGraficoZ());
	}	
}