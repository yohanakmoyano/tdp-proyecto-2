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
	
	public void imprimirtetri() {
		pos1.ocupar(pos1.getBloqueGrafico());
		pos2.ocupar(pos2.getBloqueGrafico());
		pos3.ocupar(pos3.getBloqueGrafico());
		poscentral.ocupar(poscentral.getBloqueGrafico());
	}
	
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
	
	protected void concretarRotacion() {
		pos1.getBloqueGrafico().rotar();
		pos1.ocupar(pos1.getBloqueGrafico());
		pos2.getBloqueGrafico().rotar();
		pos2.ocupar(pos2.getBloqueGrafico());
		pos3.getBloqueGrafico().rotar();
		pos3.ocupar(pos3.getBloqueGrafico());
		poscentral.getBloqueGrafico().rotar();
		poscentral.ocupar(poscentral.getBloqueGrafico());
		
		if (rotacion<270)
			  rotacion=rotacion+90;
			else
				rotacion=0;
	}
	
	//Consulta en cuantas filas se encuentra el tetrimino. 
	public abstract int cantFilasOcupa();
	
	//Devuelve un arreglo de enteros con los índices que indican las filas que ocupan. 
	//Considerando que la fila inicial es la fila 0, la última fila es la fila n-1, siendo n igual a la cantidad de filas que ocupa el tetrimino.
	//Considerando además que el orden de los elementos del arreglo retornado será de menor a mayor.
	public abstract Integer[] filasOcupadas();
	
}
