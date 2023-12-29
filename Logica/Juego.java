package Logica;

import java.awt.Toolkit;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import GUI.GUI;
import GUI.VentanaInformacion;
import Musica.Musica;
import Piezas.Bloque;
import Piezas.Cuadrado;
import Piezas.L;
import Piezas.LInv;
import Piezas.Palo;
import Piezas.T;
import Piezas.Tetrimino;
import Piezas.Z;
import Piezas.ZInv;

public class Juego{
	private static final int VelocidadDeCaidaRapida = 20;
	protected GUI miVentana;
	protected Reloj miReloj;
	protected Grilla miGrillaPrincipal;
	protected Tetrimino tetriminoActual;
	protected int puntaje;
	protected VentanaInformacion ventanaInformacion;
	protected Random rand;
	private int tetriminoSiguiente;
	public final int moverIzquierda = 1;
	public final int moverDerecha = 2;
	public final int moverAbajo = 3;
	public final int rotar = 4;
	public final int mostrarPuntaje= 5;
	protected Musica musica;

	
	public Juego(GUI gui) {
		miVentana=gui;
		miGrillaPrincipal=new Grilla(this);
		rand = new Random();
		miReloj = new Reloj(750,this);
		puntaje=0;
		crearPieza(rand.nextInt(7));
		musica=new Musica();
	}

	public void iniciarJuego() {
		miReloj.start();
		ventanaInformacion.actualizarSiguienteBLoque(tetriminoSiguiente);
		this.correrMusica();
	}
	
	public void crearPieza(int valorPieza) {
		//establece la velocidad de caida a la normal
		this.bajarNormal();
		
		//Crea la siguiente pieza que caera
		switch(valorPieza) {
		case 0: 
			tetriminoActual= new Cuadrado(miGrillaPrincipal); 
		    break;
		case 1: 
			tetriminoActual= new Palo(miGrillaPrincipal);  //paso la grilla principal como parametro para ligar al tetrimino actual dentro de esta
		    break;
		case 2: 
			tetriminoActual= new L(miGrillaPrincipal);
		    break;
		case 3: 
			tetriminoActual= new LInv(miGrillaPrincipal);
		    break;
		case 4: 
			tetriminoActual= new Z(miGrillaPrincipal);
		    break;
		case 5: 
			tetriminoActual= new ZInv(miGrillaPrincipal);
		    break;
		case 6: 
			tetriminoActual= new T(miGrillaPrincipal);
		    break;
		}
		tetriminoSiguiente = rand.nextInt(7);
		miReloj.setVelocidad(miReloj.getVelocidad());
	}
	
	public synchronized void operar(int operacion) {
		switch(operacion) {
		case moverIzquierda:
			this.moverIzquierda();
			break;
		case moverDerecha:
			this.moverDerecha();
			break;
		case moverAbajo:
			this.moverAbajo();
			break;
		case rotar:
			this.rotar();
			break;
		case mostrarPuntaje:
			miVentana.mostrarPuntaje();
			break;
		}
	}

	public int getPuntaje() {	
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	public Reloj getReloj(){
		return miReloj;
	}
	
	public String getTiempo() {
		return ventanaInformacion.getTiempo();
	}
	
	public void setVentanaInformacion(VentanaInformacion ventana) {
		ventanaInformacion = ventana;
	}
	
	public void cambioBloque(int fila, int colum, ImageIcon imagen) {
		miVentana.actualizarCelda(fila, colum, imagen);
	}
	
	public int getTetriminoSiguiente() {
		return tetriminoSiguiente;
	}
	
	public void aumentarVelocidad() {
		miReloj.aumentarVelocidad(50);
	}
	
	/**
	 * Inicia la reproduccion de la musica.
	 */
	public void correrMusica() {
		try{
			AudioInputStream inputStream = null;

			inputStream = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream("/Musica/Musica.wav"));
					
			musica = new Musica();
			musica.abrirClip(inputStream);
			musica.iniciar();
			musica.repetirInfinitamente();

		}catch(IOException | UnsupportedAudioFileException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void quitarMusica() {
		musica.cerrarClip();
	}
	
	public boolean musicaActiva() {
		return musica.getEstado();
	}
	
	/**
	 * Activa la velocidad de caida rapida para un tetrimino.
	 */
	public void bajarRapido() {
		miReloj.setAuxVelocidad(miReloj.getVelocidad());
		miReloj.setVelocidad(VelocidadDeCaidaRapida);
	}
	
	/**
	 * restablece la velocidad de caida del tetrimino.
	 */
	public void bajarNormal() {
		miReloj.setVelocidad(miReloj.getAuxVelocidad());
	}
	
	/**
	 * Este metodo restablece el foco en la ventana principal del juego, para que se pueda seguir juegando con las flechas normalmente y
	 * no haya que hacerle clic de nuevo para que funcione.
	 */
	public void restablecerFocoDeVentana() {
		miVentana.restablecerFocoDeVentana();
	}
	
	private void moverIzquierda() { 
		tetriminoActual.moverIzquierda();
	}
	private void moverDerecha() {
		tetriminoActual.moverDerecha();
	}

	private void moverAbajo() { 
		boolean finDeJuego= false;
		if (!tetriminoActual.moverAbajo()) { //En caso de que tetrimino no pueda mover abajo, debe verificar si hay lineas llenas
			finDeJuego= verificarLineas();
			if (!finDeJuego) {
				crearPieza(tetriminoSiguiente);
				ventanaInformacion.actualizarSiguienteBLoque(tetriminoSiguiente);
			}
			else
				operar(mostrarPuntaje);
		}
	}
	
	private boolean verificarLineas() { 
		boolean finDeJuego= false;
		int[] arr = tetriminoActual.lineasARevisar();
		int i= 0;
		int lineasEliminadas= 0;
		boolean bajar= false;
		int menorFila= miGrillaPrincipal.getCantFilas() - 1;
		int mayorFila= 0;
		int posFila;
		while (i< arr.length && arr[i] != -1 ) {
			posFila= arr[i];
			if (lineaCompleta(posFila)) {
				eliminarLinea(posFila);
				Toolkit.getDefaultToolkit().beep();	//Sonidito
				lineasEliminadas++;
				bajar= true;
			}
			if (mayorFila < posFila) {
				mayorFila= arr[i];
			}
			if (menorFila > posFila) {
				menorFila= posFila;
			}
			i++;
		}
		if (bajar) {
			bajarLineas (lineasEliminadas, mayorFila);
			switch(lineasEliminadas) {
				case 1:
					puntaje=puntaje+100;
					break;
				case 2:
					puntaje=puntaje+200;
					break;
				case 3:
					puntaje=puntaje+500;
					break;
				case 4:
					puntaje=puntaje+800;
					break;
			}
		}
		else if (menorFila== 0) {
			finDeJuego= true;
			miReloj.detener();
			ventanaInformacion.detenerCronometro();
		}
		return finDeJuego;
	}
	
	private boolean lineaCompleta (int i) {
		boolean completa= true;
		int col= 0;
		while (col< miGrillaPrincipal.getCantcolumnas() && completa) {
			if (miGrillaPrincipal.getBloque(i, col).estaLibre())
				completa= false;
			col++;
		}
		return completa;
	}
	
	private void eliminarLinea (int i) {
		for (int col= 0; col< miGrillaPrincipal.getCantcolumnas(); col++) {
			miGrillaPrincipal.getBloque(i, col).desocupar();
		}
	}
	
	//La idea seria leer a partir de la fila eliminada que este en la posicion de mas abajo
	/*
	 * Si la linea esta complamente vacia, entonces debo incrementar lineas vacias en 1
	 * Si la linea no es vacia y lineasVacias > 1, 
	 * entonces tengo que bajar dicha linea no vacia la cantidad de lineasVacias que se hayan leido
	 */
	private void bajarLineas (int lineasEliminadas, int filaAEmpezar) { //En caso de leer que hay una cantidad de (lineasElimiadas+1) como lineas vacias entonces se que tengo que dejar de bajar
		int lineasVacias= 0; //  se iran leyendo las lineas de abajo arriba, si lineas leidas como vacias son mayores a las lineas que se eliminaron entonces debemos dejar de revisar para arriba
		boolean listo= false;
		Bloque b;
		int i= filaAEmpezar;
		while (i >= 0 && !listo) {
			boolean filaVacia= true;
			for (int j= 0; j < miGrillaPrincipal.getCantcolumnas(); j++) {
				b= miGrillaPrincipal.getBloque(i, j);
				if (!b.estaLibre()){
					filaVacia= false;
					if (lineasVacias > 0) {
						miGrillaPrincipal.getBloque(b.getFila() + lineasVacias, b.getColumna()).ocupar(b.getMiRepresentacion());
						miGrillaPrincipal.getBloque(i, j).desocupar();
					}
				}
			}
			i--;
			if (filaVacia) {
				lineasVacias++;
				if (lineasVacias > lineasEliminadas)
					listo= true;
			}
		}
	}
	
	private void rotar() {
		tetriminoActual.rotar();
	}
	
}