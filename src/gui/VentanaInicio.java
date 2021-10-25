package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class VentanaInicio extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton btnInstrucciones;
	private JLabel inicioJuego;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio ventana = new VentanaInicio();
					ventana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public VentanaInicio() {
		
		//Configuracion de la Ventana Inicio
		setTitle("TETRIS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/images/icon_tetris.png")));
		setSize(new Dimension(900, 600));
		setResizable(false);
		
		//Imagen del fondo de la Ventana Inicio
		String rutaFotoJuego = this.getClass().getResource("/images/inicio_fondo.jpg").toString();
		ImageIcon fotoJuego = null;
		try {
			fotoJuego = new ImageIcon(new URL(rutaFotoJuego));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		Image medidaJuego = fotoJuego.getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT);
		
		//Boton ingresar
		JButton btnIngresa = new JButton("");
		btnIngresa.setVerticalAlignment(SwingConstants.BOTTOM);
		btnIngresa.setBounds(300, 379, 227, 77);
		btnIngresa.setBackground(Color.black);
		String rutaBotonJuego = this.getClass().getResource("/images/boton_inicio.png").toString();
		ImageIcon fotoBoton = null;
		try {
			fotoBoton = new ImageIcon(new URL(rutaBotonJuego));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		btnIngresa.setIcon(fotoBoton); 
		btnIngresa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btnIngresa.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
					GUI ingresoJuego= new GUI();
					ingresoJuego.setVisible(true);
					setVisible(false); 
					}
					
				});
		getContentPane().setLayout(null);
		
		//Boton Instrucciones
		btnInstrucciones = new JButton("");
		btnInstrucciones.setVerticalAlignment(SwingConstants.BOTTOM);
		btnInstrucciones.setBounds(260, 467, 317, 77);
		btnInstrucciones.setBackground(Color.black);
		
		String rutaBotonInstrucciones = this.getClass().getResource("/images/boton_instrucciones.png").toString();
		ImageIcon fotoBotonInstrucciones = null;
		try {
			fotoBotonInstrucciones = new ImageIcon(new URL(rutaBotonInstrucciones));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		getContentPane().setLayout(null);
		
		btnInstrucciones.setIcon(fotoBotonInstrucciones); 
		btnInstrucciones.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btnInstrucciones.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				
				VentanaInstrucciones instrucciones= new VentanaInstrucciones();
				instrucciones.setVisible(true);
				setVisible(false); 
				}
					
			});
		
		getContentPane().add(btnInstrucciones);
		getContentPane().add(btnIngresa);
				
		//Label que contiene la imagen del fondo de la Ventana Inicio
		inicioJuego = new JLabel("");
		inicioJuego.setBounds(0, 0, 894, 571);
		getContentPane().add(inicioJuego);
		inicioJuego.setIcon(new ImageIcon(medidaJuego));
		getContentPane().add(inicioJuego, BorderLayout.CENTER);
		
	}
}

