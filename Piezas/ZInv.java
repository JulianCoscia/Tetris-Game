package Piezas;

import Graficos.TetriminoGraficoCeleste;
import Logica.Grilla;

public class ZInv extends Tetrimino{
	
	public ZInv(Grilla grillaPrincipal) {
		this.miGrilla= grillaPrincipal;
		y=miGrilla.getBloque(0, 4);
		z=miGrilla.getBloque(0, 3);
		miRepresentacion = new TetriminoGraficoCeleste();
		y.ocupar(miRepresentacion.getBloqueGraficoY());
		z.ocupar(miRepresentacion.getBloqueGraficoZ());
		rotacion = 0;
		primerAparicion= true;
	}
	
	public void rotar() {
		Bloque auxW, auxY, auxX, auxZ;
		if (!primerAparicion) {
			switch(rotacion) {
	        case 0: //Falta hacer el metodo rotar en el tetrimino grafico
	        	if (w.getFila() - 1 >= 0) { //Compruebo que no salga de la grilla al rotar
	            	auxW= y;
	            	auxX= x;
	            	auxY= miGrilla.getBloque(y.getFila() - 1, y.getColumna() - 1);
	            	auxZ= miGrilla.getBloque(z.getFila() - 2, z.getColumna());
	            	if (auxY.estaLibre()  && auxZ.estaLibre())
	            	{ //Compruebo que las posiciones a donde rotaran esten disponibles
	            		this.setRotacion(90);
	            		miRepresentacion.rotar();
	            		modificarBloques (auxW, auxX, auxY, auxZ);
	            	}
	        	}
	            break;
	        case 90:
	        	if (w.getColumna() < miGrilla.getCantcolumnas() - 1) {
	            	auxW= miGrilla.getBloque(w.getFila(), w.getColumna() - 1);
	            	auxX= w;
	            	auxY= x;
	            	auxZ= miGrilla.getBloque(z.getFila() + 1, z.getColumna() + 2);
	            	if (auxW.estaLibre() && auxZ.estaLibre())
	            	{ //Compruebo que las posiciones a donde rotaran esten disponibles
	            		this.setRotacion(180);
	            		miRepresentacion.rotar();
	            		modificarBloques (auxW, auxX, auxY, auxZ);
	            	}
	        	}
	            break;
	        case 180: //Falta hacer el metodo rotar en el tetrimino grafico
	        	if (z.getFila() - 1 >= 0) { //Compruebo que no salga de la grilla al rotar
	            	auxW= miGrilla.getBloque(w.getFila() - 2, w.getColumna());
	            	auxX= miGrilla.getBloque(x.getFila() - 1, x.getColumna() - 1);
	            	auxY= y;
	            	auxZ= x;
	            	if (auxX.estaLibre()  && auxW.estaLibre())
	            	{ //Compruebo que las posiciones a donde rotaran esten disponibles
	            		this.setRotacion(270);
	            		miRepresentacion.rotar();
	            		modificarBloques (auxW, auxX, auxY, auxZ);
	            	}
	        	}
	            break;
	        case 270:
	        	if (z.getColumna() < miGrilla.getCantcolumnas() - 1) {
	            	auxW= miGrilla.getBloque(w.getFila() + 1, w.getColumna() + 2);
	            	auxX= y;
	            	auxY= z;
	            	auxZ= miGrilla.getBloque(z.getFila(), z.getColumna() - 1);
	            	if (auxW.estaLibre() && auxZ.estaLibre())
	            	{ //Compruebo que las posiciones a donde rotaran esten disponibles
	            		this.setRotacion(0);
	            		miRepresentacion.rotar();
	            		modificarBloques (auxW, auxX, auxY, auxZ);
	            	}
	        	}
	            break;
			}
		}
	}

	public boolean moverAbajo() {
		boolean abajoDisponible= false;
		Bloque infW;
		Bloque infX;
		Bloque infY;
		Bloque infZ;
		if (primerAparicion) {
			infW= miGrilla.getDerecho(y);
			infX= y;
			infY= miGrilla.getInferior(y);
			infZ= miGrilla.getInferior(z);
			if (infW.estaLibre() && infY.estaLibre() && infZ.estaLibre()  ) {
				abajoDisponible= true;
				primerAparicion= false;
				y.desocupar();
				z.desocupar();
				infW.ocupar(miRepresentacion.getBloqueGraficoW());
				infX.ocupar(miRepresentacion.getBloqueGraficoX());
				infY.ocupar(miRepresentacion.getBloqueGraficoY());
				infZ.ocupar(miRepresentacion.getBloqueGraficoZ());
				w= infW;
				x= infX;
				y= infY;
				z= infZ;
			}
		}
		else {
			switch(rotacion) {
			case 0:
				if (z.getFila() != miGrilla.getCantFilas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
					infW= miGrilla.getInferior(w);
					infX= y;
					infY= miGrilla.getInferior(y);
					infZ= miGrilla.getInferior(z);
					if (infW.estaLibre() && infY.estaLibre() && infZ.estaLibre() ) {
						abajoDisponible= true;
						modificarBloques (infW, infX, infY, infZ);
					}
				}
				break;
			case 90:
				if (w.getFila() != miGrilla.getCantFilas() - 1 ) { //Para no pedirle posiciones que no esten dentro de la grilla
					infW= miGrilla.getInferior(w);
					infX= w;
					infY= miGrilla.getInferior(y);
					infZ= y;
					if (infW.estaLibre() && infY.estaLibre() ) {
						abajoDisponible= true;
						modificarBloques (infW, infX, infY, infZ);
					}
				}
				break;
			case 180:
				if (x.getFila() != miGrilla.getCantFilas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
					infW= miGrilla.getInferior(w);
					infX= miGrilla.getInferior(x);
					infY= x;
					infZ= miGrilla.getInferior(z);
					if (infW.estaLibre() && infX.estaLibre() && infZ.estaLibre() ) {
						abajoDisponible= true;
						modificarBloques (infW, infX, infY, infZ);
					}
				}
				break;
			case 270:
				if (z.getFila() != miGrilla.getCantFilas() - 1 ) { //Para no pedirle posiciones que no esten dentro de la grilla
					infW= x;
					infX= miGrilla.getInferior(x);
					infY= z;
					infZ= miGrilla.getInferior(z);
					if (infX.estaLibre() && infZ.estaLibre() ) {
						abajoDisponible= true;
						modificarBloques (infW, infX, infY, infZ);
					}
				}
				break;
			}
		}
		if (!abajoDisponible) {
			if (primerAparicion) {
				y.setMiRepresentacion(miRepresentacion.getBloqueGraficoY());
				z.setMiRepresentacion(miRepresentacion.getBloqueGraficoZ());
			}
			else 
				setBloquesGraficos();
		}
		return abajoDisponible;
	}

	public void moverIzquierda() {
		Bloque izqW;
		Bloque izqX;
		Bloque izqY;
		Bloque izqZ;
		if (primerAparicion) {
			if (z.getColumna() != 0) { // ya que si la columna de Y se encuentre en 1 al mover a izquierda, el bloque W se saldria de la grilla aunque todavia no se vea
				izqY= z;
				izqZ= miGrilla.getIzquierdo(z);
				if (izqZ.estaLibre()) {
					y.desocupar();
					z.desocupar();
					izqY.ocupar(miRepresentacion.getBloqueGraficoY());
					izqZ.ocupar(miRepresentacion.getBloqueGraficoZ());
					y= izqY;
					z= izqZ;
				}
			}
		}
		else {
			switch(rotacion) {
			case 0:
				if (z.getColumna() != 0) { //Para no pedirle posiciones que no esten dentro de la grilla
					izqW= x;
					izqX= miGrilla.getIzquierdo(x);
					izqY= z;
					izqZ= miGrilla.getIzquierdo(z);
					if (izqX.estaLibre() && izqZ.estaLibre() ) {
						modificarBloques (izqW, izqX, izqY, izqZ);
					}
				}
				break;
			case 90:
				if (z.getColumna() != 0) { //Para no pedirle posiciones que no esten dentro de la grilla
					izqW= miGrilla.getIzquierdo(w);
					izqX= y;
					izqY= miGrilla.getIzquierdo(y);
					izqZ= miGrilla.getIzquierdo(z);
					if (izqW.estaLibre() && izqY.estaLibre() && izqZ.estaLibre()) {
						modificarBloques (izqW, izqX, izqY, izqZ);
					}
				}
				break;
			case 180:
				if (w.getColumna() != 0) { //Para no pedirle posiciones que no esten dentro de la grilla
					izqW= miGrilla.getIzquierdo(w);
					izqX= w;
					izqY= miGrilla.getIzquierdo(y);
					izqZ= y;
					if (izqW.estaLibre() && izqY.estaLibre() ) {
						modificarBloques (izqW, izqX, izqY, izqZ);
					}
				}
				break;
			case 270:
				if (w.getColumna() != 0) { //Para no pedirle posiciones que no esten dentro de la grilla
					izqW= miGrilla.getIzquierdo(w);
					izqX= miGrilla.getIzquierdo(x);
					izqY= x;
					izqZ= miGrilla.getIzquierdo(z);
					if (izqW.estaLibre() && izqX.estaLibre() && izqZ.estaLibre()) {
						modificarBloques (izqW, izqX, izqY, izqZ);
					}
				}
				break;
			}
		}
	}

	public void moverDerecha() {
		Bloque derW;
		Bloque derX;
		Bloque derY;
		Bloque derZ;
		if (primerAparicion) {
			if (y.getColumna() != miGrilla.getCantcolumnas() - 2) {
				derY= miGrilla.getDerecho(y);
				derZ= y;
				if (derY.estaLibre()) {
					y.desocupar();
					z.desocupar();
					derY.ocupar(miRepresentacion.getBloqueGraficoY());
					derZ.ocupar(miRepresentacion.getBloqueGraficoZ());
					y= derY;
					z= derZ;
				}
			}
		}
		else {
			switch(rotacion) {
			case 0:
				if (w.getColumna() != miGrilla.getCantcolumnas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
					derW= miGrilla.getDerecho(w);
					derX= w;
					derY= miGrilla.getDerecho(y);
					derZ= y;
					if (derW.estaLibre() && derY.estaLibre() ) {
						modificarBloques (derW, derX, derY, derZ);
					}
				}
				break;
			case 90:
				if (w.getColumna() != miGrilla.getCantcolumnas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
					derW= miGrilla.getDerecho(w);
					derX= miGrilla.getDerecho(x);
					derY= x;
					derZ= miGrilla.getDerecho(z);
					if (derW.estaLibre() && derX.estaLibre() && derZ.estaLibre() ) {
						modificarBloques (derW, derX, derY, derZ);
					}
				}
				break;
			case 180:
				if (z.getColumna() != miGrilla.getCantcolumnas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
					derW= x;
					derX= miGrilla.getDerecho(x);
					derY= z;
					derZ= miGrilla.getDerecho(z);
					if (derX.estaLibre() && derZ.estaLibre() ) {
						modificarBloques (derW, derX, derY, derZ);
					}
				}
				break;
			case 270:
				if (z.getColumna() != miGrilla.getCantcolumnas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
					derW= miGrilla.getDerecho(w);
					derX= y;
					derY= miGrilla.getDerecho(y);
					derZ= miGrilla.getDerecho(z);
					if (derW.estaLibre() && derY.estaLibre() && derZ.estaLibre() ) {
						modificarBloques (derW, derX, derY, derZ);
					}
				}
				break;
			}
		}
	}
	
	public int[] lineasARevisar () { //Devuelve  un arreglo de enteros con las lineas que el juego debe revisar si estan completas
		int [] arr= new int [3];
		if (primerAparicion) {
			arr[0]= 0;
			arr[1]= -1;
		}
		else {
			if (rotacion== 0 || rotacion== 180) {
				arr[0]= w.getFila();
				arr[1]= z.getFila();
				arr[2]= -1;
			}
			else {
				arr[0]= w.getFila();
				arr[1]= x.getFila();
				arr[2]= z.getFila();
			}
		}
		return arr;
	}
}
