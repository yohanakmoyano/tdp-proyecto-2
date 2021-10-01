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
		
		pos1.cambiarMiRepresentacion(bloqueGrafico1);
		pos2.cambiarMiRepresentacion(bloqueGrafico2);
		pos3.cambiarMiRepresentacion(bloqueGrafico3);
		poscentral.cambiarMiRepresentacion(bloqueGrafico4);
		
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
				
				ocuparDesocuparCuatroBloquesEnOrden(newPosCentral, poscentral, newP1, pos1, newP3, pos3, newP2, pos2);
				
				poscentral = newPosCentral;

				pos1 = newP1;

				pos3 = newP3;

				pos2 = newP2;

			}
		}
		
		if(!movimientoPosible)
			miGrilla.llegoAFinGrilla();
		/**
		switch(rotacion) {
		case(0): {
			if(poscentral.getFila() == (miGrilla.getFilas()-1))
				miGrilla.llegoAFinGrilla();
			break;
		}
		case(90): {
			if(pos1.getFila() == (miGrilla.getFilas()-1))
				miGrilla.llegoAFinGrilla();
			break;
		}
		case(180): {
			if(pos2.getFila() == (miGrilla.getFilas() - 1))
				miGrilla.llegoAFinGrilla();
			break;
		}
		case(270): {
			if(pos3.getFila() == (miGrilla.getFilas()-1))
				miGrilla.llegoAFinGrilla();
			break;
		}
		}
		*/
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
				
				ocuparDesocuparCuatroBloquesEnOrden(newP1, pos1, newPosCentral, poscentral, newP2, pos2, newP3, pos3);
				
				pos1 = newP1;

				poscentral = newPosCentral;

				pos2 = newP2;

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
				ocuparDesocuparCuatroBloquesEnOrden(newPosCentral, poscentral, newP3, pos3, newP2, pos2, newP1, pos1);
				
				poscentral = newPosCentral;

				pos3 = newP3;

				pos2 = newP2;

				pos1 = newP1;

			}
		}
	}

	@Override
	public void rotar() {
		boolean seguir = true;
		Bloque newP1 = null, newP2 = null, newP3 = null;
		switch(rotacion) {
		case(0): { //Rotar de cero a noventa.
			seguir = poscentral.getFila() != (miGrilla.getFilas() - 1); //Si no estoy en la última fila
			if(seguir) {
				int filaCentral = poscentral.getFila(), columnaCentral = poscentral.getColumna();
				newP1 = miGrilla.getBloque(filaCentral + 1, columnaCentral); 
				newP2 = miGrilla.getBloque(filaCentral, columnaCentral - 1);  
				newP3 = miGrilla.getBloque(filaCentral - 1 , columnaCentral - 1); 
				
			}
			break;
		}
		case(90): { //Rotar de noventa a 180
			seguir = poscentral.getColumna() != (miGrilla.getColumnas() - 1); //Si no se encuentra en la última columna.
			if(seguir) {
				int filaCentral = poscentral.getFila(), columnaCentral = poscentral.getColumna();
				newP1 = miGrilla.getBloque(filaCentral, columnaCentral + 1); 
				newP2 = miGrilla.getBloque(filaCentral + 1, columnaCentral);  
				newP3 = miGrilla.getBloque(filaCentral + 1, columnaCentral - 1);  
				
			}
			break;
		}
		case(180): {//Rotar de 180 a 270
			seguir = poscentral.getFila() != 0; //Si no se encuentra en la primer fila.
			if(seguir) {
				int filaCentral = poscentral.getFila(), columnaCentral = poscentral.getColumna();
				newP1 = miGrilla.getBloque(filaCentral - 1, columnaCentral);
				newP2 = miGrilla.getBloque(filaCentral, columnaCentral + 1);
				newP3 = miGrilla.getBloque(filaCentral + 1 , columnaCentral + 1);
				
			}
			break;
		}
		case(270): { //Rotar de 270 a 0
			seguir = poscentral.getColumna() != 0; //Si no se encuentra en la primera columna.
			if(seguir) {
				int filaCentral = poscentral.getFila(), columnaCentral = poscentral.getColumna();
				newP1 = miGrilla.getBloque(filaCentral, columnaCentral - 1);
				newP2 = miGrilla.getBloque(filaCentral - 1, columnaCentral);
				newP3 = miGrilla.getBloque(filaCentral - 1, columnaCentral + 1);
				
			}
			break;
		}
		}
		
		seguir = newP3.estaLibre() && newP1.estaLibre();
		if(seguir) {
			ocuparDesocuparTresBloquesEnOrden(newP1, pos1, newP2, pos2, newP3, pos3);
			asignarTresBloquesNuevos(newP1, newP2, newP3);
		}
		
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
