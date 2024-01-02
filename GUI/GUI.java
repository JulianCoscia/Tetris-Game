package GUI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import Logica.Juego;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	
	protected Juego miJuego;
	protected JLabel [][] matrizPrincipal;
	protected JPanel contentPane;
	private boolean bajarRapido = false;
	
	public GUI(int filas, int columnas) {
		initGUI(filas, columnas);
	}
	
	private void initGUI(int filas, int columnas) {
		setTitle("Tetris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 340, 704);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocation(285, 20);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/IconoTetris.png")));
		contentPane.setLayout(new GridLayout(filas,columnas,0,0));
		contentPane.setVisible(true);
		matrizPrincipal = new JLabel[filas][columnas];
		for (int i = 0; i < filas; i++) {
			for(int j =0; j< columnas; j++) {
				matrizPrincipal[i][j]= new JLabel();
				JLabel lbl = matrizPrincipal[i][j];
				contentPane.add(lbl);
			}
		}
		this.addKeyListener(new KeyListener() {			
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {							
				if(e.getKeyCode()==KeyEvent.VK_LEFT) {
					miJuego.operar(miJuego.moverIzquierda);
				}
				if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
					miJuego.operar(miJuego.moverDerecha);
				}
				if(e.getKeyCode()==KeyEvent.VK_UP) {	
					miJuego.operar(miJuego.rotar);			
				}
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					if (bajarRapido == false) {
						miJuego.bajarRapido();
						bajarRapido = true;}	
				}
			}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					miJuego.bajarNormal();
					bajarRapido = false;
				}
			}
		});	
	}
	
	public void registrarJuego(Juego juego) {
		miJuego = juego;
	}
	
	
	public void actualizarCelda(int fila, int colum, ImageIcon imagen) {
		matrizPrincipal[fila][colum].setIcon(imagen);
	}
	
	public void mostrarPuntaje () {
		String s= "Juego terminado. Su puntaje es de: " + miJuego.getPuntaje() + ". Duracion de la partida: " + miJuego.getTiempo();
		JOptionPane.showMessageDialog(contentPane, s, "Juego terminado", 1);
		System.exit(1);
	}
	
	public void restablecerFocoDeVentana() {
		this.requestFocus();
	}
}