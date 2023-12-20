package Piezas;

import Graficos.TetriminoGraficoAmarillo;
import Logica.Grilla;

public class Palo extends Tetrimino{
	
	public Palo(Grilla grillaPrincipal) {
		this.miGrilla= grillaPrincipal;
		w=miGrilla.getBloque(0, 3);
		x=miGrilla.getBloque(0, 4);
		y=miGrilla.getBloque(0, 5);
		z=miGrilla.getBloque(0, 6);
		miRepresentacion = new TetriminoGraficoAmarillo();
		w.ocupar(miRepresentacion.getBloqueGraficoW());
		x.ocupar(miRepresentacion.getBloqueGraficoX());
		y.ocupar(miRepresentacion.getBloqueGraficoY());
		z.ocupar(miRepresentacion.getBloqueGraficoZ());
		rotacion = 0;
		primerAparicion= false;
	}
	
	public void rotar() {
		Bloque auxW, auxY, auxX, auxZ;
		
		switch(rotacion) {
        case 0: //Falta hacer el metodo rotar en el tetrimino grafico
        	if (z.getFila() + 2 < miGrilla.getCantFilas()  &&  w.getFila() -1 >= 0) { //Compruebo que no salga de la grilla al rotar
        		auxW= miGrilla.getBloque(w.getFila() - 1, w.getColumna() + 1);
            	auxX= x;
            	auxY= miGrilla.getBloque(y.getFila() + 1, y.getColumna() - 1);
            	auxZ= miGrilla.getBloque(z.getFila() + 2, z.getColumna() - 2);
            	if (auxW.estaLibre()  && auxY.estaLibre() && auxZ.estaLibre())
            	{ //Compruebo que las posiciones a donde rotaran esten disponibles
            		this.setRotacion(90);
            		miRepresentacion.rotar();
            		modificarBloques (auxW, auxX, auxY, auxZ);
            	}
        	}
            break;
        case 90:
        	if (w.getColumna()-1 >= 0 && w.getColumna() + 2 < miGrilla.getCantcolumnas()) {        		
            	auxW= miGrilla.getBloque(w.getFila() + 1, w.getColumna() + 2);
            	auxX= miGrilla.getBloque(x.getFila(), x.getColumna() + 1);
            	auxY= x;
            	auxZ= miGrilla.getBloque(z.getFila() - 2, z.getColumna() - 1);
            	if (auxW.estaLibre() && auxX.estaLibre() && auxZ.estaLibre())
            	{ //Compruebo que las posiciones a donde rotaran esten disponibles
            		this.setRotacion(180);
            		miRepresentacion.rotar();
            		modificarBloques (auxW, auxX, auxY, auxZ);
            	}
        	}
            break;
        case 180: //Falta hacer el metodo rotar en el tetrimino grafico
        	if (z.getFila() + 2 < miGrilla.getCantFilas()  &&  z.getFila() -1 >= 0) { //Compruebo que no salga de la grilla al rotar
        		auxW= miGrilla.getBloque(w.getFila() + 2, w.getColumna() - 2);
            	auxX= miGrilla.getBloque(x.getFila() + 1, x.getColumna() - 1);
            	auxY= y;
            	auxZ= miGrilla.getBloque(z.getFila() - 1, z.getColumna() + 1);
            	if (auxW.estaLibre()  && auxX.estaLibre() && auxZ.estaLibre())
            	{ //Compruebo que las posiciones a donde rotaran esten disponibles
            		this.setRotacion(270);
            		miRepresentacion.rotar();
            		modificarBloques (auxW, auxX, auxY, auxZ);
            	}
        	}
            break;
        case 270:
        	if (z.getColumna()-1 >= 0 && z.getColumna() + 2 < miGrilla.getCantcolumnas()) {        		
            	auxW= miGrilla.getBloque(w.getFila() - 2, w.getColumna() - 1);
            	auxX= y;
            	auxY= miGrilla.getBloque(y.getFila(), y.getColumna() + 1);
            	auxZ= miGrilla.getBloque(z.getFila() + 1, z.getColumna() + 2);
            	if (auxW.estaLibre() && auxY.estaLibre() && auxZ.estaLibre())
            	{ //Compruebo que las posiciones a donde rotaran esten disponibles
            		this.setRotacion(0);
            		miRepresentacion.rotar();
            		modificarBloques (auxW, auxX, auxY, auxZ);
            	}
        	}
            break;
		}
	}

	public boolean moverAbajo() {
		boolean abajoDisponible= false;
		Bloque infW;
		Bloque infX;
		Bloque infY;
		Bloque infZ;
		switch(rotacion) {
		case 0:
			if (x.getFila() != miGrilla.getCantFilas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
				infW= miGrilla.getInferior(w);
				infX= miGrilla.getInferior(x);
				infY= miGrilla.getInferior(y);
				infZ= miGrilla.getInferior(z);
				if (infW.estaLibre() && infX.estaLibre() && infY.estaLibre() && infZ.estaLibre() ) {
					abajoDisponible= true;
					modificarBloques (infW, infX, infY, infZ);
				}
			}
			break;
		case 90:
			if (z.getFila() != miGrilla.getCantFilas() - 1 ) { //Para no pedirle posiciones que no esten dentro de la grilla
				infW= x;
				infX= y;
				infY= z;
				infZ= miGrilla.getInferior(z);
				if (infZ.estaLibre() ) {
					abajoDisponible= true;
					modificarBloques (infW, infX, infY, infZ);
				}
			}
			break;
		case 180:
			if (x.getFila() != miGrilla.getCantFilas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
				infW= miGrilla.getInferior(w);
				infX= miGrilla.getInferior(x);
				infY= miGrilla.getInferior(y);
				infZ= miGrilla.getInferior(z);
				if (infW.estaLibre() && infX.estaLibre() && infY.estaLibre() && infZ.estaLibre() ) {
					abajoDisponible= true;
					modificarBloques (infW, infX, infY, infZ);
				}
			}
			break;
		case 270:
			if (w.getFila() != miGrilla.getCantFilas() - 1 ) { //Para no pedirle posiciones que no esten dentro de la grilla
				infW= miGrilla.getInferior(w);
				infX= w;
				infY= x;
				infZ= y;
				if (infW.estaLibre() ) {
					abajoDisponible= true;
					modificarBloques (infW, infX, infY, infZ);
				}
			}
			break;
		}
		if (!abajoDisponible)
			setBloquesGraficos();
		return abajoDisponible;
	}

	public void moverIzquierda() {
		Bloque izqW;
		Bloque izqX;
		Bloque izqY;
		Bloque izqZ;
		switch(rotacion) {
		case 0:
			if (w.getColumna() != 0) { //Para no pedirle posiciones que no esten dentro de la grilla
				izqW= miGrilla.getIzquierdo(w);
				izqX= w;
				izqY= x;
				izqZ= y;
				if (izqW.estaLibre() ) {
					modificarBloques (izqW, izqX, izqY, izqZ);
				}
			}
			break;
		case 90:
			if (w.getColumna() != 0) { //Para no pedirle posiciones que no esten dentro de la grilla
				izqW= miGrilla.getIzquierdo(w);
				izqX= miGrilla.getIzquierdo(x);
				izqY= miGrilla.getIzquierdo(y);
				izqZ= miGrilla.getIzquierdo(z);
				if (izqW.estaLibre() && izqX.estaLibre() && izqY.estaLibre() && izqZ.estaLibre()) {
					modificarBloques (izqW, izqX, izqY, izqZ);
				}
			}
			break;
		case 180:
			if (z.getColumna() != 0) { //Para no pedirle posiciones que no esten dentro de la grilla
				izqW= x;
				izqX= y;
				izqY= z;
				izqZ= miGrilla.getIzquierdo(z);
				if (izqZ.estaLibre() ) {
					modificarBloques (izqW, izqX, izqY, izqZ);
				}
			}
			break;
		case 270:
			if (z.getColumna() != 0) { //Para no pedirle posiciones que no esten dentro de la grilla
				izqW= miGrilla.getIzquierdo(w);
				izqX= miGrilla.getIzquierdo(x);
				izqY= miGrilla.getIzquierdo(y);
				izqZ= miGrilla.getIzquierdo(z);
				if (izqW.estaLibre() && izqX.estaLibre() && izqY.estaLibre() && izqZ.estaLibre()) {
					modificarBloques (izqW, izqX, izqY, izqZ);
				}
			}
			break;
		}
	}

	public void moverDerecha() {
		Bloque derW;
		Bloque derX;
		Bloque derY;
		Bloque derZ;
		switch(rotacion) {
		case 0:
			if (z.getColumna() != miGrilla.getCantcolumnas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
				derW= x;
				derX= y;
				derY= z;
				derZ= miGrilla.getDerecho(z);
				if (derZ.estaLibre() ) {
					modificarBloques (derW, derX, derY, derZ);
				}
			}
			break;
		case 90:
			if (z.getColumna() != miGrilla.getCantcolumnas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
				derW= miGrilla.getDerecho(w);
				derX= miGrilla.getDerecho(x);
				derY= miGrilla.getDerecho(y);
				derZ= miGrilla.getDerecho(z);
				if (derW.estaLibre() && derX.estaLibre() && derY.estaLibre() && derZ.estaLibre() ) {
					modificarBloques (derW, derX, derY, derZ);
				}
			}
			break;
		case 180:
			if (w.getColumna() != miGrilla.getCantcolumnas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
				derW= miGrilla.getDerecho(w);
				derX= w;
				derY= x;
				derZ= y;
				if (derW.estaLibre() ) {
					modificarBloques (derW, derX, derY, derZ);
				}
			}
			break;
		case 270:
			if (w.getColumna() != miGrilla.getCantcolumnas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
				derW= miGrilla.getDerecho(w);
				derX= miGrilla.getDerecho(x);
				derY= miGrilla.getDerecho(y);
				derZ= miGrilla.getDerecho(z);
				if (derW.estaLibre() && derX.estaLibre() && derY.estaLibre() && derZ.estaLibre() ) {
					modificarBloques (derW, derX, derY, derZ);
				}
			}
			break;
		}
	}
	
	public int[] lineasARevisar () { //Devuelve  un arreglo de enteros con las lineas que el juego debe revisar si estan completas
		int [] arr= new int [4];
		if (rotacion== 0 || rotacion== 180) {
			arr[0]= w.getFila();
			arr[1]= -1;
		}
		else {
			arr[0]= w.getFila();
			arr[1]= x.getFila();
			arr[2]= y.getFila();
			arr[3]= z.getFila();
		}
		return arr;
	}
}
