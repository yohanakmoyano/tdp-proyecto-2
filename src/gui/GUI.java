package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

import java.awt.event.KeyListener;

import logica.Logica;

import javax.swing.JTextPane;

import java.awt.GridLayout;
import java.awt.Font;

public class GUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	protected Logica mijuego;
	protected JLabel[][] matrizPrincipal;
	protected JPanel panelJuego;
	protected JLabel sigTetrimino_label;
	protected JTextPane puntaje_text;
	protected JTextPane tiempo_text; 
	protected VentanaInicio ventana;
	protected JPanel panelFinDeJuego;
	protected JPanel panel_grilla;
	protected JLabel fondoJuego;
	
	public GUI() {
		//Configuracion de la Ventana GUI
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/images/icon_tetris.png")));
		setTitle("TETRIS");
		setSize(new Dimension(900, 759));
		setResizable(false);

		//Panel Juego
		panelJuego = new JPanel();
		panelJuego.setBounds(0, 0, 900, 759);
		getContentPane().add(panelJuego);
		panelJuego.setVisible(true);
		panelJuego.setLayout(null);
	
		//Panel Grilla
		panel_grilla = new JPanel();
		panel_grilla.setBackground(Color.DARK_GRAY);
		panel_grilla.setBounds(207, 24, 488, 665);
		panelJuego.add(panel_grilla);
		panel_grilla.setLayout(new GridLayout(21, 10, 0, 0));

		matrizPrincipal = new JLabel[21][10];
		
		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 10; j++) {
				matrizPrincipal[i][j] = new JLabel();
				matrizPrincipal[i][j].setBounds(i, j, 60, 32);
				ponerImagen(matrizPrincipal[i][j], "/images/bloque_libre.jpg");
				panel_grilla.add(matrizPrincipal[i][j]);
			}
		}
		
		//Inicia Juego
		mijuego = new Logica(this);
		mijuego.iniciarJuego();
		
		//Oyentes de teclados
		KeyListener listener = new MyKeyListener();
		addKeyListener(listener);
		setFocusable(true);
		getContentPane().setLayout(null);
	
		//Label siguiente Tetrimino
		sigTetrimino_label = new JLabel("");
		sigTetrimino_label.setBounds(20, 61, 145, 145);
		ponerImagen(sigTetrimino_label, mijuego.getUrlSiguiente());
		panelJuego.add(sigTetrimino_label);
		
		//JTextPane puntaje
		puntaje_text = new JTextPane();
		puntaje_text.setForeground(Color.WHITE);
		puntaje_text.setText("0");
		puntaje_text.setBackground(Color.DARK_GRAY);
		puntaje_text.setFont(new Font("Consolas", Font.BOLD, 22));
		puntaje_text.setBounds(722, 461, 151, 50);
		panelJuego.add(puntaje_text);
		puntaje_text.setEditable(false);

		//JTextPane tiempo
		tiempo_text = new JTextPane();
		tiempo_text.setFont(new Font("Consolas", Font.BOLD, 22));
		tiempo_text.setForeground(Color.WHITE);
		tiempo_text.setBackground(Color.DARK_GRAY);
		tiempo_text.setBounds(722, 250, 151, 44);
		panelJuego.add(tiempo_text);
		tiempo_text.setEditable(false);

		//Imagen del fondo del juego
		String rutaFotoJuego = this.getClass().getResource("/images/gaming_fondo.png").toString();
		ImageIcon fotoJuego = null;
		try {
			fotoJuego = new ImageIcon(new URL(rutaFotoJuego));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		Image medidaJuego = fotoJuego.getImage().getScaledInstance(900, 759, Image.SCALE_DEFAULT);

		fondoJuego = new JLabel("");
		fondoJuego.setBounds(0, 0, 900, 745);
		panelJuego.add(fondoJuego);
		fondoJuego.setIcon(new ImageIcon(medidaJuego));
		panelJuego.add(fondoJuego, BorderLayout.CENTER);		
	}
	
	
	public void cambioPuntaje()  {
		puntaje_text.setText( "" + mijuego.getPuntaje());
	}
	
	public void cambioReloj(int seg, int min)  {
		tiempo_text.setText( min + ":" + seg);
	}
	
	public void cambioSiguiente(String url) {
		ponerImagen(sigTetrimino_label, url);
	}
	
	private class MyKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			if(mijuego.sigueEnJuego()) {
				 switch(e.getKeyCode()) {
	             case KeyEvent.VK_LEFT: { mijuego.operar(Logica.moverIzquierda); break; }
	             case KeyEvent.VK_RIGHT: { mijuego.operar(Logica.moverDerecha); break; }
	             case KeyEvent.VK_UP: { mijuego.operar(Logica.rotar); break; }
	             case KeyEvent.VK_DOWN: { mijuego.operar(Logica.moverAbajo); break; }
	         	}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
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
		int c=b.getColumna(); 
		ponerImagen(matrizPrincipal[f][c],ruta);		
	}
	
	public void finDeJuego(){
		
		ventana= new VentanaInicio(); 
		panelFinDeJuego = new GameOver(ventana,this);
		getContentPane().add(panelFinDeJuego);
		getContentPane().setComponentZOrder(panelFinDeJuego, 0);
		setFocusable(true);
		repaint();
	
	}
	

}