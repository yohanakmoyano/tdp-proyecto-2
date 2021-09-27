package bloque;

import gui.BloqueGrafico;
import gui.TetriminoGrafico;
import logica.Grilla;

public class Bloque {
	protected Grilla miGrilla;
	protected BloqueGrafico miRepresentacion;
	protected int fila;
	protected int columna;
	protected boolean estaLibre;

	public Bloque(int f, int c, Grilla mg) {
		fila = f;
		columna = c;
		estaLibre = true;
		miGrilla = mg;
		miRepresentacion = mg.getBloqueGraficoLibre();
	}

	public int getFila() {
		return fila;
	}
	
	public int getColumna() {
		return columna;
	}
	
	public boolean estaLibre() {
		return estaLibre;
	}

	public void ocupar(BloqueGrafico miBlqGrf) {
		estaLibre = false;
		miRepresentacion = miBlqGrf;
		miGrilla.cambioBloque(this);
	}

	public void desocupar() {
		estaLibre = true;
		miRepresentacion = miGrilla.getBloqueGraficoLibre();
		miGrilla.cambioBloque(this);
	}
	
	public BloqueGrafico getBloqueGrafico() {
		return miRepresentacion;
		
	}
}
