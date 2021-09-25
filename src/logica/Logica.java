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
	protected int puntaje;
	public static final int moverIzquierda = 1;
	public static final int moverDerecha = 2;
	public static final int moverAbajo = 3;
	public static final int rotar = 4;

	public Logica(GUI mg) {
		Random rn = new Random(7);
		tetriActual=generarTetris(rn.nextInt());
		miGrillaPrincipal = new Grilla(this, 21, 10);
		miGui=mg;
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

	public void iniciarJuego() {
		miReloj = new Reloj(this, 1000);
		puntaje = 0;
		miReloj.start();
	}

	public void moverAbajo() {

	}

	private void verificarLineasSeguir() {

	}

	public void moverIzquierda() {

	}

	public void moverDerecha() {

	}

	public void rotar() {

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
		
	}
}
