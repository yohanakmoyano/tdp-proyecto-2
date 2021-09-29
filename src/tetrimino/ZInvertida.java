package tetrimino;

import bloque.Bloque;
import gui.BloqueGrafico;
import gui.TetriminoGrafico;
import logica.Grilla;

public class ZInvertida extends Tetrimino {

	public ZInvertida(Grilla mg) {
		super();
		pos1 = mg.getBloque(1, 3);
		pos2 = mg.getBloque(0, 4);
		pos3 = mg.getBloque(0, 5);
		poscentral = mg.getBloque(1, 4);
		miGrilla = mg;
		String rutaZInvertida_0 ="/images/ZInvertida_Tetrimino_0.png";
		String rutaZInvertida_90 ="/images/ZInvertida_Tetrimino_90.png";
		String rutaZInvertida_180 ="/images/ZInvertida_Tetrimino_180.png";
		String rutaZInvertida_270 ="/images/ZInvertida_Tetrimino_270.png";
		BloqueGrafico bloqueGrafico1 = new BloqueGrafico(0, rutaZInvertida_0, rutaZInvertida_90, rutaZInvertida_180,
				rutaZInvertida_270);
		BloqueGrafico bloqueGrafico2 = new BloqueGrafico(0, rutaZInvertida_0, rutaZInvertida_90, rutaZInvertida_180,
				rutaZInvertida_270);
		BloqueGrafico bloqueGrafico3 = new BloqueGrafico(0, rutaZInvertida_0, rutaZInvertida_90, rutaZInvertida_180,
				rutaZInvertida_270);
		BloqueGrafico bloqueGrafico4 = new BloqueGrafico(0, rutaZInvertida_0, rutaZInvertida_90, rutaZInvertida_180,
				rutaZInvertida_270);
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
				// mover primero los dos de abajo (p1 y pc) y luego los otros dos de arriba (p2
				// y p3).
				newPosCentral.ocupar(poscentral.getBloqueGrafico());
				poscentral.desocupar();
				poscentral = newPosCentral;

				newP1.ocupar(pos1.getBloqueGrafico());
				pos1.desocupar();
				pos1 = newP1;

				newP3.ocupar(pos3.getBloqueGrafico());
				pos3.desocupar();
				pos3 = newP3;

				newP2.ocupar(pos2.getBloqueGrafico());
				pos2.desocupar();
				pos2 = newP2;

			}
		}
		return movimientoPosible;
	}

	@Override
	public void moverIzquierda() {
		if (pos1.getColumna() != 0) { // Si no me encuentro en la primer columna.
			Bloque newP1 = miGrilla.getBloque(pos1.getFila(), pos1.getColumna() - 1);
			Bloque newP2 = miGrilla.getBloque(pos2.getFila(), pos2.getColumna() - 1);
			Bloque newP3 = miGrilla.getBloque(pos3.getFila(), pos3.getColumna() - 1);
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila(), poscentral.getColumna() - 1);

			if (newP1.estaLibre() && newPosCentral.estaLibre()) {
				// Respetando que al mover este tetrimino, debemos tener cuidado con que antes
				// de mover el bloque p3 debemos mover el bloque p2 y antes del bloque pc debo
				// mover el bloque p1.
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
		if (pos3.getColumna() != (miGrilla.getColumnas() - 1)) { // Si no me encuentro en la última columna procedo a
																	// mover los bloques.
			Bloque newP1 = miGrilla.getBloque(pos1.getFila(), pos1.getColumna() + 1);
			Bloque newP2 = miGrilla.getBloque(pos2.getFila(), pos2.getColumna() + 1);
			Bloque newP3 = miGrilla.getBloque(pos3.getFila(), pos3.getColumna() + 1);
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila(), poscentral.getColumna() + 1);

			if (newP3.estaLibre() && newPosCentral.estaLibre()) { // Si hay lugar libre para mover
				// Considerando que debemos mover primero a pc y p3 y por último p1 y p2.
				newPosCentral.ocupar(poscentral.getBloqueGrafico());
				poscentral.desocupar();
				poscentral = newPosCentral;

				newP3.ocupar(pos3.getBloqueGrafico());
				pos3.desocupar();
				pos3 = newP3;

				newP2.ocupar(pos2.getBloqueGrafico());
				pos2.desocupar();
				pos2 = newP2;

				newP1.ocupar(pos1.getBloqueGrafico());
				pos1.desocupar();
				pos1 = newP1;

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
		case 0: {
			newP1 = miGrilla.getBloque(poscentral.getFila() , poscentral.getColumna());
			newP2 = miGrilla.getBloque(pos2.getFila(), pos2.getColumna());
			newP3 = miGrilla.getBloque(pos2.getFila() +1, pos2.getColumna()+1);
			newPosCentral = miGrilla.getBloque(pos2.getFila(), pos2.getColumna());
			if (newPosCentral.estaLibre() && newP3.estaLibre()) {
				ocuparBloques(newP1, newP2, newP3, newPosCentral);
			}
			break;
		}
		case 90: {
			newP1 = miGrilla.getBloque(pos1.getFila() + 1, pos1.getColumna());
			newP2 = miGrilla.getBloque(pos1.getFila(), pos1.getColumna());
			newP3 = miGrilla.getBloque(pos2.getFila() - 1, pos3.getColumna());
			newPosCentral = miGrilla.getBloque(pos2.getFila(), pos3.getColumna());
			if (newP1.estaLibre() && newP3.estaLibre()) {
				ocuparBloques(newP1, newP2, newP3, newPosCentral);
			}
			break;
		}
		case 180: {
			newP1 = miGrilla.getBloque(pos1.getFila(), pos1.getColumna()+2);
			newP2 = miGrilla.getBloque(pos1.getFila(), poscentral.getColumna());
			newP3 = miGrilla.getBloque(pos2.getFila(), pos2.getColumna());
			newPosCentral = miGrilla.getBloque(poscentral.getFila(), poscentral.getColumna());
			if (newP1.estaLibre() && newP2.estaLibre())
				ocuparBloques(newP1, newP2, newP3, newPosCentral);
			break;
		}
		case 270: {
			newP1 = miGrilla.getBloque(poscentral.getFila() - 1, poscentral.getColumna());
			newP2 = miGrilla.getBloque(poscentral.getFila(), poscentral.getColumna());
			newP3 = miGrilla.getBloque(pos3.getFila() +1, pos3.getColumna());
			newPosCentral = miGrilla.getBloque(pos3.getFila(), pos3.getColumna());
			if (newP3.estaLibre() && newP1.estaLibre())
				ocuparBloques(newP1, newP2, newP3, newPosCentral);
			break;
		}
		}
	}

	private void ocuparBloques(Bloque newP1, Bloque newP2, Bloque newP3, Bloque newPosCentral) {
		newP1.ocupar(pos1.getBloqueGrafico());
		pos1.desocupar();
		pos1 = newP1;
		newP2.ocupar(pos2.getBloqueGrafico());
		pos2.desocupar();
		pos2 = newP2;
		newP3.ocupar(pos3.getBloqueGrafico());
		pos3.desocupar();
		pos3 = newP3;
		newPosCentral.ocupar(poscentral.getBloqueGrafico());
		poscentral.desocupar();
		poscentral = newPosCentral;

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
