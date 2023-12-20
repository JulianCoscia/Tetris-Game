package Piezas;

import Graficos.TetriminoGraficoMarron;
import Logica.Grilla;

public class T extends Tetrimino{
	
	public T(Grilla grillaPrincipal) {
		this.miGrilla= grillaPrincipal;
		z=miGrilla.getBloque(0, 4);
		miRepresentacion = new TetriminoGraficoMarron();
		z.ocupar(miRepresentacion.getBloqueGraficoZ());
		rotacion = 0;
		primerAparicion= true;
	}
	
	public void rotar() {	//X es el bloque del medio	//Z es la punta que sobresale	W X Y
		Bloque auxW, auxY, auxX, auxZ;	
		if (!primerAparicion) {
			switch(rotacion) {
	        case 0: //Falta hacer el metodo rotar en el tetrimino grafico
	        	if (w.getFila() - 1 >= 0) { //Compruebo que no salga de la grilla al rotar    		
	            	auxW= miGrilla.getBloque(w.getFila() - 1, w.getColumna() + 1);
	            	auxX= x;
	            	auxY= z;
	            	auxZ= w;
	            	if (auxW.estaLibre())
	            	{ //Compruebo que las posiciones a donde rotaran esten disponibles
	            		this.setRotacion(90);
	            		miRepresentacion.rotar();
	            		modificarBloques (auxW, auxX, auxY, auxZ);
	            	}
	        	}
	            break;
	        case 90:
	        	if (w.getColumna() + 1 <=  miGrilla.getCantcolumnas() - 1) {
	        		auxW= miGrilla.getBloque(w.getFila() + 1, w.getColumna() + 1);
	            	auxX= x;
	            	auxY= z;
	            	auxZ= w;
	            	if (auxW.estaLibre())
	            	{ //Compruebo que las posiciones a donde rotaran esten disponibles	
	            		this.setRotacion(180);
	            		miRepresentacion.rotar();
	            		modificarBloques (auxW, auxX, auxY, auxZ);
	            	}
	        	}
	            break;
	        case 180:
	        	if (w.getFila() + 1 <= miGrilla.getCantFilas() - 1) {
	        		auxW= miGrilla.getBloque(w.getFila() + 1, w.getColumna() - 1);
	            	auxX= x;
	            	auxY= z;
	            	auxZ= w;
	            	if (auxW.estaLibre())
	            	{ //Compruebo que las posiciones a donde rotaran esten disponibles
	            		this.setRotacion(270);
	            		miRepresentacion.rotar();
	            		modificarBloques (auxW, auxX, auxY, auxZ);
	            	}
	        	}
	            break;
	        case 270:
	        	if (w.getColumna() - 1 >= 0 ) {
	        		auxW= miGrilla.getBloque(w.getFila() - 1, w.getColumna() - 1);
	            	auxX= x;
	            	auxY= z;
	            	auxZ= w;
	            	if (auxW.estaLibre())
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
			infW= miGrilla.getIzquierdo(z);
			infX= z;
			infY= miGrilla.getDerecho(z);
			infZ= miGrilla.getInferior(z);
			if (infZ.estaLibre()) {
				abajoDisponible= true;
				primerAparicion= false;
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
					infX= z;
					infY= miGrilla.getInferior(y);
					infZ= miGrilla.getInferior(z);
					if (infW.estaLibre() && infZ.estaLibre() && infY.estaLibre()  ) {
						abajoDisponible= true;
						modificarBloques (infW, infX, infY, infZ);
					}
				}
				break;
			case 90:
				if (y.getFila() != miGrilla.getCantFilas() - 1 ) { //Para no pedirle posiciones que no esten dentro de la grilla
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
				if (w.getFila() != miGrilla.getCantFilas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
					infW= miGrilla.getInferior(w);
					infX= miGrilla.getInferior(x);
					infY= miGrilla.getInferior(y);
					infZ= x;
					if (infW.estaLibre() && infX.estaLibre() && infY.estaLibre() ) {
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
			if (z.getColumna() > 1) {
				izqZ= miGrilla.getIzquierdo(z);
				if (izqZ.estaLibre()) {
					z.desocupar();
					izqZ.ocupar(miRepresentacion.getBloqueGraficoZ());
					z= izqZ;
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
				if (z.getColumna() != 0) { //Para no pedirle posiciones que no esten dentro de la grilla
					izqW= miGrilla.getIzquierdo(w);
					izqX= z;
					izqY= miGrilla.getIzquierdo(y);
					izqZ= miGrilla.getIzquierdo(z);
					if (izqW.estaLibre() && izqZ.estaLibre() && izqY.estaLibre()) {
						modificarBloques (izqW, izqX, izqY, izqZ);
					}
				}
				break;
			case 180:
				if (y.getColumna() != 0) { //Para no pedirle posiciones que no esten dentro de la grilla
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
				if (w.getColumna() != 0) { //Para no pedirle posiciones que no esten dentro de la grilla
					izqW= miGrilla.getIzquierdo(w);
					izqX= miGrilla.getIzquierdo(x);
					izqY= miGrilla.getIzquierdo(y);
					izqZ= x;
					if (izqW.estaLibre() && izqX.estaLibre() && izqY.estaLibre()) {
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
			if (z.getColumna() != miGrilla.getCantcolumnas() - 2) {
				derZ= miGrilla.getDerecho(z);
				if (derZ.estaLibre()) {
					z.desocupar();
					derZ.ocupar(miRepresentacion.getBloqueGraficoZ());
					z= derZ;
				}
			}
		}
		else {
			switch(rotacion) {
			case 0:
				if (y.getColumna() != miGrilla.getCantcolumnas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
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
				if (w.getColumna() != miGrilla.getCantcolumnas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
					derW= miGrilla.getDerecho(w);
					derX= miGrilla.getDerecho(x);
					derY= miGrilla.getDerecho(y);
					derZ= x;
					if (derW.estaLibre() && derX.estaLibre() && derY.estaLibre() ) {
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
				if (z.getColumna() != miGrilla.getCantcolumnas() - 1) { //Para no pedirle posiciones que no esten dentro de la grilla
					derW= miGrilla.getDerecho(w);
					derX= z;
					derY= miGrilla.getDerecho(y);
					derZ= miGrilla.getDerecho(z);
					if (derW.estaLibre() && derZ.estaLibre() && derY.estaLibre()) {
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
