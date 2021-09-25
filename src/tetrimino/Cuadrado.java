package tetrimino;

import bloque.Bloque;
import gui.TetriminoGrafico;
import logica.Grilla;

public class Cuadrado extends Tetrimino{
	/*protected Bloque pos1, pos2, pos3, poscentral;
	protected Grilla miGrilla;
	protected TetriminoGrafico miTetris;
	protected int rotacion;
*/
	public Cuadrado(Grilla mg) {
		super();
		pos1=miGrilla.getBloque(0,5);
	    pos2=miGrilla.getBloque(0,6);
	    pos3=miGrilla.getBloque(1,6);
	    poscentral=miGrilla.getBloque(1,5);
	    miGrilla=mg;
	}

	
	@Override
	public boolean moverAbajo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void moverIzquierda() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moverDerecha() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotar() {
		// TODO Auto-generated method stub
		
	}

}
