package bloque;

import gui.BloqueGrafico;
import gui.TetriminoGrafico;
import logica.Grilla;

public class Bloque {
	protected Grilla miGrilla;
	protected int fila;
	protected int columna;
	protected boolean estaLibre;

	public Bloque(int f, int c,Grilla mg) {
		fila = f;
		columna = c;
		estaLibre = true;
		miGrilla = mg;
	}

	public boolean estaLibre() {
		return estaLibre;
	}

	public void ocupar(TetriminoGrafico tg) {

	}

	public void desocupar() {

	}
	
	public BloqueGrafico getBloqueGrafico() {
		return null;
		
	}
}
