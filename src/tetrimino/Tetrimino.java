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
	
	//Métodos auxiliares protegidos
	protected void ocuparDesocuparTresBloquesEnOrden(Bloque newP1, Bloque oldP1, Bloque newP2, Bloque oldP2, Bloque newP3, Bloque oldP3) {
		newP1.ocupar(oldP1.getBloqueGrafico());
		oldP1.desocupar();
		
		newP2.ocupar(oldP2.getBloqueGrafico());
		oldP2.desocupar();
		
		newP3.ocupar(oldP3.getBloqueGrafico());
		oldP3.desocupar();
	}
	
	protected void ocuparDesocuparCuatroBloquesEnOrden(Bloque newP1, Bloque oldP1, Bloque newP2, Bloque oldP2, 
												Bloque newP3, Bloque oldP3, Bloque newP4, Bloque oldP4) {
		
		ocuparDesocuparTresBloquesEnOrden(newP1, oldP1, newP2, oldP2, newP3, oldP3);
		
		newP4.ocupar(oldP4.getBloqueGrafico());
		oldP4.desocupar();

	}
	
	protected void asignarTresBloquesNuevos(Bloque newP1, Bloque newP2, Bloque newP3) {
		pos1 = newP1;
		pos2 = newP2;
		pos3 = newP3;
	}
	
	protected void asignarBloquesNuevos(Bloque newP1, Bloque newP2, Bloque newP3, Bloque newposcentral) {
		asignarTresBloquesNuevos(newP1, newP2, newP3);
		poscentral = newposcentral;
	}
	
	
}
