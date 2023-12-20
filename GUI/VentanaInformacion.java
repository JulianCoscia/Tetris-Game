package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import Logica.Cronometro;
import Logica.Juego;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class VentanaInformacion extends JFrame{
	private Juego miJuego = null;
	private JLabel sigBloque;
	private Cronometro miCronometro;
	
	public VentanaInformacion (Juego juego){
		 miJuego = juego;
		 setTitle("Informacion");
		 setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/IconoVentana.png")));
		 setVisible(true);
		 setBounds(100, 100, 200, 500);
		 setLayout(new GridLayout(3,2,0,0));
		 setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		 JLabel puntaje = new JLabel(miJuego.getPuntaje()+"");
		 JLabel tiempo = new JLabel("");
		 JLabel etiquetaPuntaje = new JLabel("Puntaje");
		 JLabel etiquetaTiempo = new JLabel("Tiempo");
		 JLabel etiquetaBloque = new JLabel("Sig. bloque");
		 
		 sigBloque = new JLabel();
		 miCronometro = new Cronometro(tiempo);
		 miCronometro.asignarJuego(miJuego);
		 miCronometro.start();
		 
		 this.getContentPane().add(etiquetaPuntaje);
		 this.getContentPane().add(puntaje);
		 this.getContentPane().add(etiquetaTiempo);
		 this.getContentPane().add(tiempo);
		 this.getContentPane().add(etiquetaBloque);
		 this.getContentPane().add(sigBloque);
		 
		 puntaje.setBackground(Color.BLACK);
		 puntaje.setForeground(Color.WHITE);
		 puntaje.setOpaque(true);
		 
		 tiempo.setBackground(Color.BLACK);
		 tiempo.setForeground(Color.WHITE);
		 tiempo.setOpaque(true);
		 
		 etiquetaPuntaje.setBackground(Color.BLACK);
		 etiquetaPuntaje.setForeground(Color.WHITE);
		 etiquetaPuntaje.setOpaque(true);
		 
		 etiquetaTiempo.setBackground(Color.BLACK);
		 etiquetaTiempo.setForeground(Color.WHITE);
		 etiquetaTiempo.setOpaque(true);
		 
		 etiquetaBloque.setBackground(Color.BLACK);
		 etiquetaBloque.setForeground(Color.WHITE);
		 etiquetaBloque.setOpaque(true);
		 
		 sigBloque.setBackground(Color.BLACK);
		 sigBloque.setOpaque(true);
		 sigBloque.setForeground(Color.WHITE);
		 
	     ActionListener l1 =new ActionListener() {
	    	 public void actionPerformed(ActionEvent ae){
	    		 puntaje.setText(""+ juego.getPuntaje());
	    //Add here all your logic that contribute to update every second
	    	 }
	     };
	     Timer t1 = new Timer(1000,l1);
	     t1.start(); 
	}
	
	public void actualizarSiguienteBLoque(int tetriminoSiguiente) {
		switch(tetriminoSiguiente) {
		case 0: 
			ImageIcon imagenIcon = new ImageIcon(this.getClass().getResource("/images/sigCuadrado.png"));
			sigBloque.setIcon(imagenIcon);
		    break;
		case 1: 
			ImageIcon imagenIcon1 = new ImageIcon(this.getClass().getResource("/images/sigPalo.png"));
			sigBloque.setIcon(imagenIcon1);
		    break;
		case 2: 
			ImageIcon imagenIcon2 = new ImageIcon(this.getClass().getResource("/images/sigL.png"));
			sigBloque.setIcon(imagenIcon2);
		    break;
		case 3: 
			ImageIcon imagenIcon3 = new ImageIcon(this.getClass().getResource("/images/sigJ.png"));
			sigBloque.setIcon(imagenIcon3);
		    break;
		case 4: 
			ImageIcon imagenIcon4 = new ImageIcon(this.getClass().getResource("/images/sigZ.png"));
			sigBloque.setIcon(imagenIcon4);
		    break;
		case 5: 
			ImageIcon imagenIcon5 = new ImageIcon(this.getClass().getResource("/images/sigS.png"));
			sigBloque.setIcon(imagenIcon5);
		    break;
		case 6: 
			ImageIcon imagenIcon6 = new ImageIcon(this.getClass().getResource("/images/sigT.png"));
			sigBloque.setIcon(imagenIcon6);
		    break;
		}
	}
	
	public void detenerCronometro () {
		miCronometro.detener();
	}
	
	public String getTiempo() {
		return miCronometro.getTiempo();
	}
}

