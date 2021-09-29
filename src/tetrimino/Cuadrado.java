package tetrimino;

import bloque.Bloque;
import gui.BloqueGrafico;
import gui.TetriminoGrafico;
import logica.Grilla;

public class Cuadrado extends Tetrimino {

	public Cuadrado(Grilla mg) {
		super();
		miGrilla = mg;
		pos1 = mg.getBloque(0, 5);
		pos2 = mg.getBloque(0, 6);
		pos3 = mg.getBloque(1, 6);
		poscentral = mg.getBloque(1, 5);
		
		String rutaCuadrado_0 ="/images/Cuadrado_Tetrimino_0.png";
		String rutaCuadrado_90 ="/images/Cuadrado_Tetrimino_90.png";
		String rutaCuadrado_180 ="/images/Cuadrado_Tetrimino_180.png";
		String rutaCuadrado_270 ="/images/Cuadrado_Tetrimino_270.png";
		BloqueGrafico bloqueGrafico1 = new BloqueGrafico(0, rutaCuadrado_0, rutaCuadrado_90, rutaCuadrado_180,
				rutaCuadrado_270);
		BloqueGrafico bloqueGrafico2 = new BloqueGrafico(0, rutaCuadrado_0, rutaCuadrado_90, rutaCuadrado_180,
				rutaCuadrado_270);
		BloqueGrafico bloqueGrafico3 = new BloqueGrafico(0, rutaCuadrado_0, rutaCuadrado_90, rutaCuadrado_180,
				rutaCuadrado_270);
		BloqueGrafico bloqueGrafico4 = new BloqueGrafico(0, rutaCuadrado_0, rutaCuadrado_90, rutaCuadrado_180,
				rutaCuadrado_270);
		miTetris = new TetriminoGrafico(bloqueGrafico1, bloqueGrafico2, bloqueGrafico3, bloqueGrafico4);

	}

	@Override
	public boolean moverAbajo() {
		boolean movimientoPosible = poscentral.getFila() != (miGrilla.getFilas() - 1); // si está en la última fila no
																						// podrá mover hacia abajo.
		if (movimientoPosible) {
			Bloque newP1 = miGrilla.getBloque(pos1.getFila() + 1, pos1.getColumna());
			Bloque newP2 = miGrilla.getBloque(pos2.getFila() + 1, pos2.getColumna());
			Bloque newP3 = miGrilla.getBloque(pos3.getFila() + 1, pos3.getColumna());
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila() + 1, poscentral.getColumna());
			movimientoPosible = ((newPosCentral.estaLibre()) && (newP3.estaLibre()));
			if (movimientoPosible) {
				// Cuidado con el orden en el que se reemplazan/ocupan los bloques, debemos
				// mover primero los dos de abajo y luego los otros dos de arriba.
				newP3.ocupar(pos3.getBloqueGrafico());
				pos3.desocupar();
				pos3 = newP3;

				newPosCentral.ocupar(poscentral.getBloqueGrafico());
				poscentral.desocupar();
				poscentral = newPosCentral;

				newP1.ocupar(pos1.getBloqueGrafico());
				pos1.desocupar();
				pos1 = newP1;

				newP2.ocupar(pos2.getBloqueGrafico());
				pos2.desocupar();
				pos2 = newP2;

			}
		}
		return movimientoPosible;
	}

	@Override
	public void moverIzquierda() {
		if (poscentral.getColumna() != 0) {// Si no me encuentro en la primer columna
			Bloque newP1 = miGrilla.getBloque(pos1.getFila(), pos1.getColumna() - 1);
			Bloque newP2 = miGrilla.getBloque(pos2.getFila(), pos2.getColumna() - 1);
			Bloque newP3 = miGrilla.getBloque(pos3.getFila(), pos3.getColumna() - 1);
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila(), poscentral.getColumna() - 1);

			if (newPosCentral.estaLibre() && newP1.estaLibre()) { // Si los bloques adyacentes a izquierda de P1 y PC
																	// están libres entonces procedo a mover los
																	// bloques.
				// Teniendo cuidado con el orden en que se mueven. Primero muevo los dos de la
				// izquierda (p1 y pc) y luego los dos de la derecha (p2 y p3).
				newP1.ocupar(pos1.getBloqueGrafico());
				pos1.desocupar();
				pos1 = newP1;

				newPosCentral.ocupar(poscentral.getBloqueGrafico());
				poscentral.desocupar();
				poscentral = newPosCentral;

				newP2.ocupar(pos2.getBloqueGrafico());
				pos2.desocupar();
				pos2 = newP2;

				newP3.ocupar(pos3.getBloqueGrafico());
				pos3.desocupar();
				pos3 = newP3;

			}
		}
	}

	@Override
	public void moverDerecha() {
		if (pos2.getColumna() != (miGrilla.getColumnas() - 1)) { // Si no me encuentro en la última columna procedo a
																	// mover los bloques.
			Bloque newP1 = miGrilla.getBloque(pos1.getFila(), pos1.getColumna() + 1);
			Bloque newP2 = miGrilla.getBloque(pos2.getFila(), pos2.getColumna() + 1);
			Bloque newP3 = miGrilla.getBloque(pos3.getFila(), pos3.getColumna() + 1);
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila(), poscentral.getColumna() + 1);

			if (newP2.estaLibre() && newP3.estaLibre()) { // Si hay lugar libre para mover
				// Considerando que debemos mover primero a p2 y p3 y luego a p1 y pc.
				newP2.ocupar(pos2.getBloqueGrafico());
				pos2.desocupar();
				pos2 = newP2;

				newP3.ocupar(pos3.getBloqueGrafico());
				pos3.desocupar();
				pos3 = newP3;

				newP1.ocupar(pos1.getBloqueGrafico());
				pos1.desocupar();
				pos1 = newP1;

				newPosCentral.ocupar(poscentral.getBloqueGrafico());
				poscentral.desocupar();
				poscentral = newPosCentral;

			}
		}
	}

	@Override
	public void rotar() {
		Bloque newP1 = null;
		Bloque newP2 = null;
		Bloque newP3 = null;
		Bloque newPosCentral = null;
		switch (rotacion) {
		case 0:{
			newP1 = pos1;
			newP2 = pos2;
			newP3 = pos3;
			newPosCentral = poscentral;
			break;
		}
		case 90: {
			newP1 = poscentral;
			newP2 = pos1;
			newP3 = pos2;
			newPosCentral = pos3;
			break;
		}
		case 180: {
			newP1 = pos3;
			newP2 = poscentral;
			newP3 = pos1;
			newPosCentral = pos2;
			break;
		}
		case 270:{
			newP1 = pos2;
			newP2 = pos3;
			newP3 = poscentral;
			newPosCentral = pos1;
			break;
		}
		}
		pos1=newP1;
		pos2=newP2;
		pos3=newP3;
		poscentral=newPosCentral;

	}
	
    public Bloque getPos1() {
    	return pos1; 
    }
	
	public Bloque getPos2() {
		return pos2;
	}
	
	public Bloque getPos3() {
		return pos3; 
	}
	
	public Bloque getPosCentral() {
		return poscentral; 
	} 
	
	public TetriminoGrafico getTetriminoGrafico() {
		return miTetris; 
	}
	
}
