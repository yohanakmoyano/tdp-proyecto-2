package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameOver extends JPanel {
	private static final long serialVersionUID = 1L; 
	private JButton btnReiniciar;
	private JLabel inicioJuego; 
	
	public GameOver(VentanaInicio ventana,GUI miGui) {
		
		//Configuracion del panel GameOver
		
		setBounds(1, 70, 900, 569);
		setLayout(null);
		
		//Imagen del fondo de la ventana GameOver
		String rutaFotoJuego = this.getClass().getResource("/images/game_over.png").toString();
		ImageIcon fotoJuego = null;
		try {
			fotoJuego = new ImageIcon(new URL(rutaFotoJuego));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		Image medidaJuego = fotoJuego.getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT);
		String rutaBotonJuego = this.getClass().getResource("/images/boton_reiniciar.png").toString();
		ImageIcon fotoBoton = null;
		try {
				fotoBoton = new ImageIcon(new URL(rutaBotonJuego));
		} catch (MalformedURLException e) {
					e.printStackTrace();
		}
		
		
		//Boton reinciar juego
		btnReiniciar = new JButton("");
	    btnReiniciar.setVerticalAlignment(SwingConstants.BOTTOM);
	    btnReiniciar.setBounds(319, 454, 227, 77);
	    btnReiniciar.setBackground(Color.black);
	    btnReiniciar.setIcon(fotoBoton); 
	    btnReiniciar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
	    btnReiniciar.addActionListener(new ActionListener(){
	    				
	    		public void actionPerformed(ActionEvent e) {
	    			
	    			ventana.setVisible(true); 
	    			miGui.setVisible(false); 
	    		}						
	    	});
	    
	    add(btnReiniciar); 
		
		//Label que contiene la imagen del fondo de la ventana GameOver
	    inicioJuego = new JLabel("");
		inicioJuego.setBounds(0, 0, 894, 571);
		inicioJuego.setIcon(new ImageIcon(medidaJuego));
		add(inicioJuego); 
	}
}
