package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
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

public class GUI extends JFrame{
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
		setSize(new Dimension(900, 800));
		setResizable(false);
		
		mijuego = new Logica(this);
		getContentPane().setLayout(null);
		
		panelJuego = new JPanel();
		panelJuego.setBounds(0, 0, 900, 600);
		getContentPane().add(panelJuego);
		panelJuego.setVisible(true);
		panelJuego.setLayout(null);
		
		
		JLabel puntaje_Label = new JLabel("");
		puntaje_Label.setForeground(Color.WHITE);
		puntaje_Label.setBackground(Color.WHITE);
		puntaje_Label.setBounds(721, 301, 151, 50);
		panelJuego.add(puntaje_Label);
		
		
		JPanel panel_grilla = new JPanel();
		panel_grilla.setBounds(207, 24, 488, 372);
		panelJuego.add(panel_grilla);
		panel_grilla.setLayout(new GridLayout(21, 10, 0, 0));
		
		JLabel TetriNext_label = new JLabel("");
		TetriNext_label.setBounds(20, 47, 167, 181);
		panelJuego.add(TetriNext_label);
		
		JTextPane puntaje_text = new JTextPane();
		puntaje_text.setBounds(732, 283, 119, 26);
		panelJuego.add(puntaje_text);
		puntaje_text.setEditable(false);
		puntaje_text.setText("Puntaje: " + String.valueOf(mijuego.getPuntaje()));
		
		/*
		JTextPane tiempo_text = new JTextPane();
		tiempo_text.setBounds(732, 239, 119, 26);
		panelJuego.add(tiempo_text);
		tiempo_text.setText("Tiempo: " + String.valueOf(mijuego.getTiempo()));
		*/
		
		String rutaFotoJuego= this.getClass().getResource("/images/gaming_fondo.png").toString();
		ImageIcon fotoJuego=null;
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
		for(int i=0; i<21; i++) {
			for(int j=0; j<10; j++) {
				matrizPrincipal[i][j] = new JLabel("H");
				matrizPrincipal[i][j].setBounds(i, j, 50, 50);
				//matrizPrincipal[i][j].setIcon(new ImageIcon(mijuego.obtenerRutaBloqueGrafico()));
				ponerImagen(matrizPrincipal[i][j],"/images/bloque_libre.jpg");
				panel_grilla.add(matrizPrincipal[i][j]); 
			}
		}
		
	 
		
		
		
		
	}
	
	private void ponerImagen(JLabel lbl, String ruta) {
		ImageIcon img = new ImageIcon(GUI.class.getResource(ruta));
		Icon imgLbl = new ImageIcon(img.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT));
		lbl.setIcon(imgLbl);
	}
	
	public void graficarBloque(BloqueGrafico b){
		
	}
	
	public void cambioBloque(Bloque b) {}
	
	public void desocuparFila(int f) {}
	
	public void actualizarGrilla(int f) {}
}