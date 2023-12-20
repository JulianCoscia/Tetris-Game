package Graficos;

import javax.swing.ImageIcon;

public abstract class BloqueGrafico {

	protected int rotacion;	//0, 1, 2 ,3
	protected String[] imagenes;

	public ImageIcon getImageIcon() {
		ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(imagenes[rotacion]));
		return imageIcon;
	}
	
	public void rotar() {
		rotacion = (rotacion + 1) % 4;
	}
}
