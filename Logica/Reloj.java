package Logica;

public class Reloj extends Thread{
	protected boolean activo;		
	protected int step;				
	protected Juego miJuego;	
	protected int auxStep;
	
	public Reloj(int s, Juego j) {
		activo = true;
		step = s;
		miJuego = j;
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
	
	public void setStep(int s) {
		step = s;
	}
	
	public int getStep() {
		return step;
	}
	
	public void setAuxStep(int auxStep) {
		this.auxStep = auxStep;
	}
	
	public int getAuxStep() {
		return auxStep;
	}
	
	/*
	 * Aumenta la velocidad de caida del bloque restando el tiempo en la que el reloj ejecuta el codigo del hilo.
	 * el parametro resta es la Cantidad de milisegundos a restar a la velocidad de caida del bloque.
	 */
	public void reducirStep(int resta) {
	//Controla que la velocidad de caida sea mayor a 0.
	  if (step-resta > 100)
		step = step-resta;
	  else
		  step = 100;
	}
	
	public void run() {
		while(activo) {
			try {
				Thread.sleep(step);
				miJuego.operar(miJuego.moverAbajo);
			}
			catch (InterruptedException e) {}
		}
	}
}//Fin de reloj