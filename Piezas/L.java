package Piezas;

import Graficos.TetriminoGraficoAzul;
import Logica.Grilla;

public class L extends Tetrimino{
	
	public L(Grilla grillaPrincipal) {
		this.miGrilla= grillaPrincipal;
		w=miGrilla.getBloque(0, 3);
		x=miGrilla.getBloque(0, 4);
		y=miGrilla.getBloque(0, 5);
		miRepresentacion = new TetriminoGraficoAzul();
		w.ocupar(miRepresentacion.getBloqueGraficoW());
		x.ocupar(miRepresentacion.getBloqueGraficoX());
		y.ocupar(miRepresentacion.getBloqueGraficoY());
		rotacion = 0;
		primerAparicion= true;
	}
	
	public void rotar() {
		Bloque auxW, auxY, auxX, auxZ;
		if (!primerAparicion) {// No puede rotar si la pieza aun no esta mostrada en su totalidad
			switch(rotacion) {
	        case 0: //Falta hacer el metodo rotar en el tetrimino grafico
	        	if (w.getFila() - 2 >= 0) { //Compruebo que no salga de la grilla al rotar    		
	            	auxW= miGrilla.getBloque(w.getFila() - 2, w.getColumna() + 1);
	            	auxX= miGrilla.getBloque(x.getFila() - 1, x.getColumna());
	            	auxY= x;
	            	auxZ= y;
	            	if (auxW.estaLibre() && auxX.estaLibre())
	            	{ //Compruebo que las posiciones a donde rotaran esten disponibles
	            		this.setRotacion(90);
	            		miRepresentacion.rotar();
	            		modificarBloques (auxW, auxX, auxY, auxZ);
	            	}
	        	}
	            break;
	        case 90:
	        	if (w.getColumna() - 1 >= 0 ) {
	            	auxW= miGrilla.getBloque(w.getFila() + 1, w.getColumna() + 1);
	            	auxX= x;
	            	auxY= miGrilla.getBloque(y.getFila() - 1, y.getColumna() - 1);
	            	auxZ= miGrilla.getBloque(z.getFila(), z.getColumna() - 2);
	            	if (auxW.estaLibre() && auxY.estaLibre() && auxZ.estaLibre())
	            	{ //Compruebo que las posiciones a donde rotaran esten disponibles
	            		this.setRotacion(180);
	            		miRepresentacion.rotar();
	            		modificarBloques (auxW, auxX, auxY, auxZ);
	            	}
	        	}
	            break;
	        case 180:
	        	if (w.getFila() - 1 >= 0 ) {
	            	auxW= miGrilla.getBloque(w.getFila() + 1, w.getColumna());
	            	auxX= w;
	            	auxY= miGrilla.getBloque(y.getFila() - 1, y.getColumna() + 2);
	            	auxZ= miGrilla.getBloque(z.getFila() - 2, z.getColumna() + 1);
	            	if (auxW.estaLibre() && auxY.estaLibre() && auxZ.estaLibre())
	            	{ //Compruebo que las posiciones a donde rotaran esten disponibles
	            		this.setRotacion(270);
	            		miRepresentacion.rotar();
	            		modificarBloques (auxW, auxX, auxY, auxZ);
	            	}
	        	}
	            break;
	        case 270:
	        	if (w.getColumna() - 2 >= 0 ) {
	            	auxW= miGrilla.getBloque(w.getFila(), w.getColumna() - 2);
	            	auxX= miGrilla.getBloque(x.getFila() + 1, x.getColumna() - 1);
	            	auxY= w;
	            	auxZ= x;
	            	if (auxW.estaLibre() && auxX.estaLibre())
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
			infW= miGrilla.getInferior(w);
			infX= miGrilla.getInferior(x);
			infY= miGrilla.getInferior(y);
			infZ= y;
			if (infW.estaLibre() && infX.estaLibre() && infY.estaLibre()  ) {
				abajoDisponible= true;
				primerAparicion= false;
				w.desocupar();
				x.desocupar();
				y.desocupar();
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
				if (w.getFila() != miGrilla.getCantFilas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
					infW= miGrilla.getInferior(w);
					infX= miGrilla.getInferior(x);
					infY= miGrilla.getInferior(y);
					infZ= y;
					if (infW.estaLibre() && infX.estaLibre() && infY.estaLibre()  ) {
						abajoDisponible= true;
						modificarBloques (infW, infX, infY, infZ);
					}
				}
				break;
			case 90:
				if (z.getFila() != miGrilla.getCantFilas() - 1 ) { //Para no pedirle posiciones que no esten dentro de la grilla
					infW= x;
					infX= y;
					infY= miGrilla.getInferior(y);
					infZ= miGrilla.getInferior(z);
					if (infY.estaLibre() && infZ.estaLibre() ) {
						abajoDisponible= true;
						modificarBloques (infW, infX, infY, infZ);
					}
				}
				break;
			case 180:
				if (z.getFila() != miGrilla.getCantFilas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
					infW= miGrilla.getInferior(w);
					infX= miGrilla.getInferior(x);
					infY= z;
					infZ= miGrilla.getInferior(z);
					if (infW.estaLibre() && infX.estaLibre() && infZ.estaLibre() ) {
						abajoDisponible= true;
						modificarBloques (infW, infX, infY, infZ);
					}
				}
				break;
			case 270:
				if (w.getFila() != miGrilla.getCantFilas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
					infW= miGrilla.getInferior(w);
					infX= w;
					infY= x;
					infZ= miGrilla.getInferior(z);
					if (infW.estaLibre() && infZ.estaLibre() ) {
						abajoDisponible= true;
						modificarBloques (infW, infX, infY, infZ);
					}
				}
				break;
	
			}
		}
		if (!abajoDisponible) {
			if (primerAparicion) {
				w.setMiRepresentacion(miRepresentacion.getBloqueGraficoW());
				x.setMiRepresentacion(miRepresentacion.getBloqueGraficoX());
				y.setMiRepresentacion(miRepresentacion.getBloqueGraficoY());
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
			if (w.getColumna() != 0) {
				izqW= miGrilla.getIzquierdo(w);
				izqX= w;
				izqY= x;
				if (izqW.estaLibre()) {
					w.desocupar();
					x.desocupar();
					y.desocupar();
					izqW.ocupar(miRepresentacion.getBloqueGraficoW());
					izqX.ocupar(miRepresentacion.getBloqueGraficoX());
					izqY.ocupar(miRepresentacion.getBloqueGraficoY());
					w= izqW;
					x= izqX;
					y= izqY;
				}
			}
		}
		else {
			switch(rotacion) {
			case 0:
				if (w.getColumna() != 0) { //Para no pedirle posiciones que no esten dentro de la grilla
					izqW= miGrilla.getIzquierdo(w);
					izqX= w;
					izqY= x;
					izqZ= miGrilla.getIzquierdo(z);
					if (izqW.estaLibre() && izqZ.estaLibre() ) {
						modificarBloques (izqW, izqX, izqY, izqZ);
					}
				}
				break;
			case 90:
				if (w.getColumna() != 0) { //Para no pedirle posiciones que no esten dentro de la grilla
					izqW= miGrilla.getIzquierdo(w);
					izqX= miGrilla.getIzquierdo(x);
					izqY= miGrilla.getIzquierdo(y);
					izqZ= y;
					if (izqW.estaLibre() && izqX.estaLibre() && izqY.estaLibre()) {
						modificarBloques (izqW, izqX, izqY, izqZ);
					}
				}
				break;
			case 180:
				if (z.getColumna() != 0) { //Para no pedirle posiciones que no esten dentro de la grilla
					izqW= x;
					izqX= y;
					izqY= miGrilla.getIzquierdo(y);
					izqZ= miGrilla.getIzquierdo(z);
					if (izqZ.estaLibre() && izqY.estaLibre()) {
						modificarBloques (izqW, izqX, izqY, izqZ);
					}
				}
				break;
			case 270:
				if (z.getColumna() != 0) { //Para no pedirle posiciones que no esten dentro de la grilla
					izqW= miGrilla.getIzquierdo(w);
					izqX= miGrilla.getIzquierdo(x);
					izqY= z;
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
			if (y.getColumna() != miGrilla.getCantcolumnas() - 1) {
				derW= x;
				derX= y;
				derY= miGrilla.getDerecho(y);
				if (derY.estaLibre()) {
					w.desocupar();
					x.desocupar();
					y.desocupar();
					derW.ocupar(miRepresentacion.getBloqueGraficoW());
					derX.ocupar(miRepresentacion.getBloqueGraficoX());
					derY.ocupar(miRepresentacion.getBloqueGraficoY());
					w= derW;
					x= derX;
					y= derY;
				}
			}
		}
		else {
			switch(rotacion) {
			case 0:
				if (z.getColumna() != miGrilla.getCantcolumnas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
					derW= x;
					derX= y;
					derY= miGrilla.getDerecho(y);
					derZ= miGrilla.getDerecho(z);
					if (derZ.estaLibre() && derY.estaLibre()) {
						modificarBloques (derW, derX, derY, derZ);
					}
				}
				break;
			case 90:
				if (z.getColumna() != miGrilla.getCantcolumnas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
					derW= miGrilla.getDerecho(w);
					derX= miGrilla.getDerecho(x);
					derY= z;
					derZ= miGrilla.getDerecho(z);
					if (derW.estaLibre() && derX.estaLibre() && derZ.estaLibre() ) {
						modificarBloques (derW, derX, derY, derZ);
					}
				}
				break;
			case 180:
				if (w.getColumna() != miGrilla.getCantcolumnas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
					derW= miGrilla.getDerecho(w);
					derX= w;
					derY= x;
					derZ= miGrilla.getDerecho(z);
					if (derW.estaLibre() && derZ.estaLibre() ) {
						modificarBloques (derW, derX, derY, derZ);
					}
				}
				break;
			case 270:
				if (w.getColumna() != miGrilla.getCantcolumnas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
					derW= miGrilla.getDerecho(w);
					derX= miGrilla.getDerecho(x);
					derY= miGrilla.getDerecho(y);
					derZ= y;
					if (derW.estaLibre() && derX.estaLibre() && derY.estaLibre()) {
						modificarBloques (derW, derX, derY, derZ);
					}
				}
				break;
			}
		}
	}

	public int[] lineasARevisar() {
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
				arr[2]= y.getFila();
			}
		}
		return arr;
	}
}