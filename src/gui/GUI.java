package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import bloque.Bloque;

import logica.Logica;

import javax.swing.JTextPane;

import java.awt.GridLayout;
import java.awt.Font;

public class GUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Logica mijuego;
	protected JLabel[][] matrizPrincipal;
	private JPanel panelJuego;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/images/icon_tetris.png")));
		setTitle("TETRIS");
		setSize(new Dimension(900, 600));
		setResizable(false);

		mijuego = new Logica(this);
		mijuego.iniciarJuego();
		getContentPane().setLayout(null);

		panelJuego = new JPanel();
		panelJuego.setBounds(0, 0, 900, 600);
		getContentPane().add(panelJuego);
		panelJuego.setVisible(true);
		panelJuego.setLayout(null);

		JLabel sigTetrimino_label = new JLabel("");
		sigTetrimino_label.setBounds(20, 61, 145, 145);
		ponerImagen(sigTetrimino_label, mijuego.getUrlSiguiente());
		panelJuego.add(sigTetrimino_label);

		JLabel puntaje_Label = new JLabel("");
		puntaje_Label.setForeground(Color.WHITE);
		puntaje_Label.setBackground(Color.WHITE);
		puntaje_Label.setBounds(721, 301, 151, 50);
		panelJuego.add(puntaje_Label);

		JPanel panel_grilla = new JPanel();
		panel_grilla.setBounds(207, 24, 488, 576);
		panelJuego.add(panel_grilla);
		panel_grilla.setLayout(new GridLayout(21, 10, 0, 0));

		JLabel TetriNext_label = new JLabel("");
		TetriNext_label.setBounds(20, 47, 167, 181);
		panelJuego.add(TetriNext_label);

		JTextPane puntaje_text = new JTextPane();
		puntaje_text.setBackground(new Color(255, 215, 0));
		puntaje_text.setFont(new Font("Stencil", Font.PLAIN, 16));
		puntaje_text.setBounds(739, 392, 119, 23);
		panelJuego.add(puntaje_text);
		puntaje_text.setEditable(false);
		puntaje_text.setText("Puntaje: 0");

		JTextPane tiempo_text = new JTextPane();
		tiempo_text.setBounds(732, 239, 119, 51);
		panelJuego.add(tiempo_text);
		tiempo_text.setText("Tiempo: " + mijuego.getTiempo());
		tiempo_text.setEditable(false);

		String rutaFotoJuego = this.getClass().getResource("/images/gaming_fondo.png").toString();
		ImageIcon fotoJuego = null;
		try {
			fotoJuego = new ImageIcon(new URL(rutaFotoJuego));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		Image medidaJuego = fotoJuego.getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT);

		JLabel fondoJuego = new JLabel("");
		fondoJuego.setBounds(0, 24, 900, 600);
		panelJuego.add(fondoJuego);
		fondoJuego.setIcon(new ImageIcon(medidaJuego));
		panelJuego.add(fondoJuego, BorderLayout.CENTER);

		matrizPrincipal = new JLabel[21][10];
		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 10; j++) {
				matrizPrincipal[i][j] = new JLabel();
				matrizPrincipal[i][j].setBounds(i, j, 60, 29);
				ponerImagen(matrizPrincipal[i][j], mijuego.obtenerRutaBloqueLibre());
				panel_grilla.add(matrizPrincipal[i][j]);
			}
		}
		
		
		// tetriActual
		Bloque pos1 = mijuego.getTetriActual().getPos1();
		Bloque pos2 = mijuego.getTetriActual().getPos2();
		Bloque pos3 = mijuego.getTetriActual().getPos3();
		Bloque posc = mijuego.getTetriActual().getPosCentral();
		// mijuego.getTetriActual().getTetriminoGrafico().getPos1().obtenerRutaImagen();
		// mijuego.getTetriActual().getTetriminoGrafico().getPos2().obtenerRutaImagen()
		int filapos1 = pos1.getFila();
		int colpos1 = pos1.getColumna();
		int filapos2 = pos2.getFila();
		int colpos2 = pos2.getColumna();
		int filapos3 = pos3.getFila();
		int colpos3 = pos3.getColumna();
		int filaposc = posc.getFila();
		int colposc = posc.getColumna();

		ponerImagen(matrizPrincipal[filapos1][colpos1],
				mijuego.getTetriActual().getTetriminoGrafico().getPos1().obtenerRutaImagen());
		ponerImagen(matrizPrincipal[filapos2][colpos2],
				mijuego.getTetriActual().getTetriminoGrafico().getPos2().obtenerRutaImagen());
		ponerImagen(matrizPrincipal[filapos3][colpos3],
				mijuego.getTetriActual().getTetriminoGrafico().getPos3().obtenerRutaImagen());
		ponerImagen(matrizPrincipal[filaposc][colposc],
				mijuego.getTetriActual().getTetriminoGrafico().getPosCentral().obtenerRutaImagen());
		
	}
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
			//mijuego.moverAbajo();
			mijuego.operar(mijuego.moverAbajo);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			//mijuego.moverIzquierda();
			mijuego.operar(mijuego.moverIzquierda);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			//mijuego.moverDerecha();
			mijuego.operar(mijuego.moverDerecha);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			//mijuego.rotar();
			mijuego.operar(mijuego.moverAbajo);
		}

	}

	private void ponerImagen(JLabel lbl, String ruta) {
		ImageIcon img = new ImageIcon(GUI.class.getResource(ruta));
		Icon imgLbl = new ImageIcon(img.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT));
		lbl.setIcon(imgLbl);
	}

	public void graficarBloque(BloqueGrafico b) {
		b.obtenerRutaImagen();
	}

	public void cambioBloque(Bloque b) {
		String ruta=b.getBloqueGrafico().obtenerRutaImagen();
		int f=b.getFila(); 
		int c=b.getColumna(); //System.out.println("-"+ruta);
		ponerImagen(matrizPrincipal[f][c],ruta);
		
	}

	public void desocuparFila(int f) {
		for (int j = 0; j < 10; j++)
			ponerImagen(matrizPrincipal[f][j], mijuego.obtenerRutaBloqueLibre());
	}

	public void actualizarGrilla(int f) {
		for (int i = f - 1; i >= 0; i--) {
			for (int j = 0; j < 10; j++) {
				JLabel aux = matrizPrincipal[i][j];
				matrizPrincipal[i + 1][j] = aux;
				ponerImagen(matrizPrincipal[f][j], mijuego.obtenerRutaBloqueLibre());
			}
		}

	}

}