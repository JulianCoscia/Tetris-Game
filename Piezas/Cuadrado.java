package Piezas;

import Graficos.TetriminoGraficoRojo;
import Logica.Grilla;

public class Cuadrado extends Tetrimino{
	
	public Cuadrado(Grilla grillaPrincipal) {
		this.miGrilla= grillaPrincipal;
		y=miGrilla.getBloque(0, 4);
		z=miGrilla.getBloque(0, 5);
		miRepresentacion = new TetriminoGraficoRojo();
		y.ocupar(miRepresentacion.getBloqueGraficoY());
		z.ocupar(miRepresentacion.getBloqueGraficoZ());
		rotacion = 0;
		primerAparicion= true;
	}

	public void rotar() {
		Bloque auxW, auxY, auxX, auxZ;
		if (!primerAparicion) {
			switch(rotacion) {
			case 0:
				auxW= x;
				auxX= z;
				auxY= w;
				auxZ= y;
				this.setRotacion(90);
        		miRepresentacion.rotar();
        		modificarBloques (auxW, auxX, auxY, auxZ);
        		break;
			case 90:
				auxW= x;
				auxX= z;
				auxY= w;
				auxZ= y;
				this.setRotacion(180);
        		miRepresentacion.rotar();
        		modificarBloques (auxW, auxX, auxY, auxZ);
        		break;
			case 180:
				auxW= x;
				auxX= z;
				auxY= w;
				auxZ= y;
				this.setRotacion(270);
        		miRepresentacion.rotar();
        		modificarBloques (auxW, auxX, auxY, auxZ);
        		break;
			case 270:
				auxW= x;
				auxX= z;
				auxY= w;
				auxZ= y;
				this.setRotacion(0);
        		miRepresentacion.rotar();
        		modificarBloques (auxW, auxX, auxY, auxZ);
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
			infW= y;
			infX= z;
			infY= miGrilla.getInferior(y);
			infZ= miGrilla.getInferior(z);
			if (infY.estaLibre() && infZ.estaLibre() ) {
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
			switch (rotacion) {
			case 0:	
				if (z.getFila() != miGrilla.getCantFilas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
					infW= y;
					infX= z;
					infY= miGrilla.getInferior(y);
					infZ= miGrilla.getInferior(z);
					if (infY.estaLibre() && infZ.estaLibre() ) {
						abajoDisponible= true;
						modificarBloques (infW, infX, infY, infZ);
					}	
				}
				break;
			case 90: 
				if (z.getFila() != miGrilla.getCantFilas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
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
			case 180: 
				if (w.getFila() != miGrilla.getCantFilas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
					infW= miGrilla.getInferior(w);
					infX= miGrilla.getInferior(x);
					infY= w;
					infZ= x;
					if (infW.estaLibre() && infX.estaLibre() ) {
						abajoDisponible= true;
						modificarBloques (infW, infX, infY, infZ);
					}	
				}
				break;
			case 270: 
				if (w.getFila() != miGrilla.getCantFilas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
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
			if (y.getColumna() != 0) {
				izqY= miGrilla.getIzquierdo(y);
				izqZ= y;
				if (izqY.estaLibre() ) {
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
			switch (rotacion) {
			case 0:
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
			case 90:
				if (z.getColumna() != 0) { //Para no pedirle posiciones que no esten dentro de la grilla
					izqW= y;
					izqX= z;
					izqY= miGrilla.getIzquierdo(y);
					izqZ= miGrilla.getIzquierdo(z);
					if (izqZ.estaLibre() && izqY.estaLibre() ) {
						modificarBloques (izqW, izqX, izqY, izqZ);
					}
				}
				break;
			case 180:
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
			case 270:
				if (w.getColumna() != 0) { //Para no pedirle posiciones que no esten dentro de la grilla
					izqW= miGrilla.getIzquierdo(w);
					izqX= miGrilla.getIzquierdo(x);
					izqY= w;
					izqZ= x;
					if (izqW.estaLibre() && izqX.estaLibre() ) {
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
			if (z.getColumna() != miGrilla.getCantcolumnas() - 1) {
				derY= z;
				derZ= miGrilla.getDerecho(z);
				if (derZ.estaLibre() ) {
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
			switch (rotacion){
			case 0:
				if (z.getColumna() != miGrilla.getCantcolumnas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
					derW= x;
					derX= miGrilla.getDerecho(x);
					derY= z;
					derZ= miGrilla.getDerecho(z);
					if (derZ.estaLibre() && derX.estaLibre()) {
						modificarBloques (derW, derX, derY, derZ);
					}
				}
				break;
			case 90:
				if (w.getColumna() != miGrilla.getCantcolumnas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
					derW= miGrilla.getDerecho(w);
					derX= miGrilla.getDerecho(x);
					derY= w;
					derZ= x;
					if (derW.estaLibre() && derX.estaLibre()) {
						modificarBloques (derW, derX, derY, derZ);
					}
				}
				break;
			case 180:
				if (w.getColumna() != miGrilla.getCantcolumnas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
					derW= miGrilla.getDerecho(w);
					derX= w;
					derY= miGrilla.getDerecho(y);
					derZ= y;
					if (derW.estaLibre() && derY.estaLibre()) {
						modificarBloques (derW, derX, derY, derZ);
					}
				}
				break;
			case 270:
				if (z.getColumna() != miGrilla.getCantcolumnas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
					derW= y;
					derX= z;
					derY= miGrilla.getDerecho(y);
					derZ= miGrilla.getDerecho(z);
					if (derZ.estaLibre() && derY.estaLibre()) {
						modificarBloques (derW, derX, derY, derZ);
					}
				}
				break;
			}
		}
	}

	public int[] lineasARevisar () { //Devuelve  un arreglo de enteros con las lineas que el juego debe revisar si estan completas
		int arr[]= new int [2];
		if (primerAparicion) {
			arr[0]= 0;
			arr[1]= -1;
		}
		else {
			arr[0]= x.getFila();
			arr[1]= z.getFila();
		}
		return arr;
	}	
}
