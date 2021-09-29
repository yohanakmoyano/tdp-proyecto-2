package tetrimino;

import bloque.Bloque;
import gui.TetriminoGrafico;
import logica.Grilla;

public abstract class Tetrimino {
	protected Bloque pos1, pos2, pos3, poscentral;
	protected Grilla miGrilla;
	protected TetriminoGrafico miTetris;
	protected int rotacion;

	public Tetrimino() {
		rotacion=0;
	}
	public abstract boolean moverAbajo();

	public abstract void moverIzquierda();

	public abstract void moverDerecha();

	public abstract void rotar();
	
	public abstract Bloque getPos1(); 
	
	public abstract Bloque getPos2();
	
	public abstract Bloque getPos3();
	
	public abstract Bloque getPosCentral(); 
	
	public abstract TetriminoGrafico getTetriminoGrafico(); 
	
	
}
