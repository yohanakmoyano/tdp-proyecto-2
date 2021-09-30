package logica;

import java.util.Random;
import bloque.Bloque;
import gui.GUI;
import tetrimino.*;

public class Logica {
	protected GUI miGui;
	protected Reloj miReloj;
	protected Grilla miGrillaPrincipal;
	protected Tetrimino tetriActual;
	protected Tetrimino tetriSiguiente;
	protected int sigT;
	protected String urlSiguiente;
	protected int puntaje;
	public static final int moverIzquierda = 1;
	public static final int moverDerecha = 2;
	public static final int moverAbajo = 3;
	public static final int rotar = 4;

	public Logica(GUI mg) {
		miGrillaPrincipal = new Grilla(this, 21, 10);
		Random rn = new Random();
		tetriActual = generarTetris(rn.nextInt(7));
		
		sigT = rn.nextInt(7);
		tetriSiguiente = generarTetris(sigT);
		urlSiguiente = mostrarTetriminoSiguiente(sigT);
		miGui = mg;
	}

	private Tetrimino generarTetris(int i) {
		Tetrimino t = null;
		switch (i) {
		case 0: {
			t = new Cuadrado(miGrillaPrincipal);
			break;
		}
		case 1: {
			t = new I(miGrillaPrincipal);
			break;
		}
		case 2: {
			t = new L(miGrillaPrincipal);
			break;
		}
		case 3: {
			t = new LInvertida(miGrillaPrincipal);
			break;
		}
		case 4: {
			t = new TInvertida(miGrillaPrincipal);
			break;
		}
		case 5: {
			t = new Z(miGrillaPrincipal);
			break;
		}
		case 6: {
			t = new ZInvertida(miGrillaPrincipal);
			break;
		}
		}
		return t;
	}

	public String mostrarTetriminoSiguiente(int sigT) {
		String t = null;
		switch (sigT) {
		case 0: {
			t = "/images/Cuadrado_Tetrimino.png";
			break;
		}
		case 1: {
			t = "/images/I_Tetrimino.png";
			break;
		}
		case 2: {
			t = "/images/L_Tetrimino.png";
			break;
		}
		case 3: {
			t = "/images/LInvertido_Tetrimino.png";
			break;
		}
		case 4: {
			t = "/images/TInvertida_Tetrimino.png";;
			break;
		}
		case 5: {
			t = "/images/Z_Tetrimino.png";
			break;
		}
		case 6: {
			t = "/images/ZInvertido_Tetrimino.png";
			break;
		}
		}
		return t;
	}
	
	
	public void iniciarJuego() {
		miReloj = new Reloj(this, 1000);
		puntaje = 0;
		miReloj.start();
	}

	private int cantLineasCompletas() {
		int cant=0; 
		int filasComp[]=miGrillaPrincipal.filasCompletas();
		
		for(int i=0;i<filasComp.length & filasComp[i]!=-1 ;i++) {
			cant++;
		}
		
		return cant;
	}
	
	public void actualizarPuntaje() {
		int cant=cantLineasCompletas(); 
	    if(cant==1) {
	    	puntaje=puntaje+100;
	    }
	    
	    if(cant==2) {
	    	puntaje=puntaje+200;
	    }
	    
	    if(cant==3) {
	    	puntaje=puntaje+500;
	    }
	    
	    if(cant==4) {
	    	puntaje=puntaje+800;
	    }
	}
	
	public void borrarFila() {
		//deberia comunicarle a la grilla y gui- 
		int filasComp[]=miGrillaPrincipal.filasCompletas();
		int cant=cantLineasCompletas(); 
		int fila=0;
		for(int i=0; i<cant; i++) {
			fila=filasComp[i];
			miGrillaPrincipal.desocuparFila(fila);
			miGui.desocuparFila(fila); //creo que esto es necesario
		}
	}
	

	public void moverAbajo() {
		tetriActual.moverAbajo(); 
	}

	public void moverIzquierda() {
		tetriActual.moverIzquierda();
	}

	public void moverDerecha() {
		tetriActual.moverDerecha();
	}

	public void rotar() {
		tetriActual.rotar();
	}

	public synchronized void operar(int op) {
		switch (op) {
		case moverAbajo: {
			moverAbajo();
			break;
		}
		case moverIzquierda: {
			moverIzquierda();
			break;
		}
		case moverDerecha: {
			moverDerecha();
			break;
		}
		case rotar: {
			rotar();
			break;
		}
		}
	}
	
	public void cambioBloque(Bloque b) {
		miGui.cambioBloque(b);
	}

	public void actualizarGrilla(int fila) {
		miGui.actualizarGrilla(fila);
		
	}
	
	public void actualizarVelocidad() {
		if(miReloj.getSegundos()%100==0) {
			miReloj.setStep(miReloj.getStep()/2);
		}
	}
	
	
	public int getPuntaje() {
		return puntaje;
	
	}
	
	public String getTiempo() {
		return miReloj.getTiempo();
	}
	
	public String obtenerRutaBloqueLibre() {
		return miGrillaPrincipal.getBloqueGraficoLibre().obtenerRutaImagen();
	}
	
	public Tetrimino getTetriActual() {
		return tetriActual; 
	}
	
	public String getUrlSiguiente() {
		return urlSiguiente;
	}
}
