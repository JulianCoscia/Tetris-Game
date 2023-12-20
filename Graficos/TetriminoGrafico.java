package Graficos;

public abstract class TetriminoGrafico {
	
	protected BloqueGrafico w,x,y,z;
		
	public void rotar() {		
		w.rotar();
		x.rotar();
		y.rotar();
		z.rotar();
	}
	
	public BloqueGrafico getBloqueGraficoW() {
		return w;
	}
	
	public BloqueGrafico getBloqueGraficoX() {
		return x;
	}
	
	public BloqueGrafico getBloqueGraficoY() {
		return y;
	}
	
	public BloqueGrafico getBloqueGraficoZ() {
		return z;
	}
}
