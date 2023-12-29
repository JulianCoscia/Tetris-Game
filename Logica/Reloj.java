package Logica;

public class Reloj extends Thread{
	protected boolean activo;		
	protected int velocidad;				
	protected Juego miJuego;	
	protected int auxVelocidad;
	
	public Reloj(int velocidad, Juego juego) {
		activo = true;
		this.velocidad = velocidad;
		miJuego = juego;
		auxVelocidad = velocidad;
	}
	
	public void detener() {
		activo = false;
	}
	
	public void iniciar() {
		activo = true;
	}
	
	public boolean estado() {
		return activo;
	}
	
	public void setVelocidad(int vel) {
		velocidad = vel;
	}
	
	public int getVelocidad() {
		return velocidad;
	}
	
	public void setAuxVelocidad(int auxStep) {
		this.auxVelocidad = auxStep;
	}
	
	public int getAuxVelocidad() {
		return auxVelocidad;
	}
	
	/*
	 * Aumenta la velocidad de caida del bloque restando el tiempo en la que el reloj ejecuta el codigo del hilo.
	 * el parametro resta es la Cantidad de milisegundos a restar a la velocidad de caida del bloque.
	 */
	public void aumentarVelocidad(int valor) {
	//Controla que la velocidad de caida sea mayor a 0.
	  if (velocidad-valor > 100)
		velocidad = velocidad-valor;
	  else
		  velocidad = 100;
	}
	
	public void run() {
		while(activo) {
			try {
				Thread.sleep(velocidad);
				miJuego.operar(miJuego.moverAbajo);
			}
			catch (InterruptedException e) {}
		}
	}
}//Fin de reloj