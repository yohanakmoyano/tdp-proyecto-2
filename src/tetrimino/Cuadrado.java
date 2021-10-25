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
		
		pos1.cambiarMiRepresentacion(bloqueGrafico1);
		pos2.cambiarMiRepresentacion(bloqueGrafico2);
		pos3.cambiarMiRepresentacion(bloqueGrafico3);
		poscentral.cambiarMiRepresentacion(bloqueGrafico4);
		
	}
	
	@Override
	public boolean moverAbajo() {
		boolean bajarposible_0_90 = ((rotacion == 0) || (rotacion == 90)) && (poscentral.getFila() != (miGrilla.getFilas() - 1));
		boolean bajarposible_180_270 = ((rotacion == 180) || (rotacion == 270)) && (pos2.getFila() != (miGrilla.getFilas() - 1));
		
		boolean movimientoPosible = (bajarposible_0_90 || bajarposible_180_270);
		if(movimientoPosible) {
			Bloque newP1 = miGrilla.getBloque(pos1.getFila() + 1, pos1.getColumna());
			Bloque newP2 = miGrilla.getBloque(pos2.getFila() + 1, pos2.getColumna());
			Bloque newP3 = miGrilla.getBloque(pos3.getFila() + 1, pos3.getColumna());
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila() + 1, poscentral.getColumna());
			
			switch(rotacion) {
				case(0): {
					movimientoPosible = ( newPosCentral.estaLibre() && newP3.estaLibre() );
					if (movimientoPosible) {
						ocuparDesocuparCuatroBloquesEnOrden( newPosCentral, poscentral, newP3, pos3, newP1, pos1, newP2, pos2);
					} 
					break;
				}
				case(90): {
					movimientoPosible = ( newPosCentral.estaLibre() && newP1.estaLibre() );
					if (movimientoPosible) {
						ocuparDesocuparCuatroBloquesEnOrden( newP1, pos1, newPosCentral, poscentral, newP2, pos2, newP3, pos3);
					} 
					break;
				}
				case(180): {
					movimientoPosible = ( newP2.estaLibre() && newP1.estaLibre() );
					if (movimientoPosible) {
						ocuparDesocuparCuatroBloquesEnOrden ( newP2, pos2, newP1, pos1, newP3, pos3, newPosCentral, poscentral);
					} 
					break;
				}
				case(270): {
					movimientoPosible = ( newP2.estaLibre() && newP3.estaLibre() );
					if (movimientoPosible) {
						ocuparDesocuparCuatroBloquesEnOrden ( newP3, pos3, newP2, pos2, newPosCentral, poscentral, newP1, pos1);
					} 
					break;
				}
			}
			if(movimientoPosible)
				asignarBloquesNuevos(newP1, newP2, newP3, newPosCentral);
		}
		return movimientoPosible;
	}

	@Override
	public void moverIzquierda() {
		boolean movizqposible_0_90 = ((rotacion == 0) || (rotacion == 90)) && (pos1.getColumna() != 0);
		boolean movizqposible_180_270 = ((rotacion == 180) || (rotacion == 270)) && (pos3.getColumna() != 0);
		
		if(movizqposible_0_90 || movizqposible_180_270) {
			Bloque newP1 = miGrilla.getBloque(pos1.getFila(), pos1.getColumna() - 1);
			Bloque newP2 = miGrilla.getBloque(pos2.getFila(), pos2.getColumna() - 1);
			Bloque newP3 = miGrilla.getBloque(pos3.getFila(), pos3.getColumna() - 1);
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila(), poscentral.getColumna() - 1);
			
			boolean mover = false; //Indica si el tetrimino se movió.
			
			switch(rotacion) {
				case(0): {
					mover = newP1.estaLibre() && newPosCentral.estaLibre();
					if (mover) {
						ocuparDesocuparCuatroBloquesEnOrden(newP1, pos1, newPosCentral, poscentral, newP2, pos2, newP3, pos3);
					}
					break;
				}
				case(90): {
					mover = newP2.estaLibre() && newP1.estaLibre();
					if (mover) {
						ocuparDesocuparCuatroBloquesEnOrden(newP2, pos2, newP1, pos1, newP3, pos3, newPosCentral, poscentral);
					} 
					break;
				}
				case(180): {
					mover = newP3.estaLibre() && newP2.estaLibre();
					if (mover) {
						ocuparDesocuparCuatroBloquesEnOrden(newP3, pos3, newP2, pos2, newPosCentral, poscentral, newP1, pos1);
					} 
					break;
				}
				case(270): {
					mover = newPosCentral.estaLibre() && newP3.estaLibre();
					if (mover) {
						ocuparDesocuparCuatroBloquesEnOrden(newPosCentral, poscentral, newP3, pos3, newP1, pos1, newP2, pos2);
					} 
					break;
				}
			}
			if(mover)
				asignarBloquesNuevos(newP1, newP2, newP3, newPosCentral);
		}
	}

	@Override
	public void moverDerecha() {
		boolean movderposible_0_90 = ((rotacion == 0) || (rotacion == 90)) && (pos3.getColumna() != (miGrilla.getColumnas() - 1));
		boolean movderposible_180_270 = ((rotacion == 180) || (rotacion == 270)) && (pos1.getColumna() != (miGrilla.getColumnas() - 1));
		
		if(movderposible_0_90 || movderposible_180_270) { //Si no me encuentro en la última columna procedo a mover los bloques.
			Bloque newP1 = miGrilla.getBloque(pos1.getFila(), pos1.getColumna() + 1);
			Bloque newP2 = miGrilla.getBloque(pos2.getFila(), pos2.getColumna() + 1);
			Bloque newP3 = miGrilla.getBloque(pos3.getFila(), pos3.getColumna() + 1);
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila(), poscentral.getColumna() + 1);
			
			boolean mover = false; //Indica si el tetrimino se movió.
			
			switch(rotacion) {
				case(0): {
					mover = newP2.estaLibre() && newP3.estaLibre();
					if (mover) {
						ocuparDesocuparCuatroBloquesEnOrden(newP2, pos2, newP3, pos3, newP1, pos1, newPosCentral, poscentral);
					} 
					break;
				}
				case(90): {
					mover = newP3.estaLibre() && newPosCentral.estaLibre();
					if (mover) { 
						ocuparDesocuparCuatroBloquesEnOrden(newP3, pos3, newPosCentral, poscentral, newP2, pos2, newP1, pos1);
					} 
					break;
				}
				case(180): {
					mover = newPosCentral.estaLibre() && newP1.estaLibre();
					if (mover) { 
						ocuparDesocuparCuatroBloquesEnOrden(newPosCentral, poscentral, newP1, pos1, newP3, pos3, newP2, pos2);
					} 
					break;
				}
				case(270): {
					mover = newP1.estaLibre() && newP2.estaLibre();
					if (mover) { 
						ocuparDesocuparCuatroBloquesEnOrden(newP1, pos1, newP2, pos2, newPosCentral, poscentral, newP3, pos3);
					} 
					break;
				}
			}
			if(mover) //Si se movió.
				asignarBloquesNuevos(newP1, newP2, newP3, newPosCentral);
		}
		
	}

	@Override
	public void rotar() {
		Bloque newP1 = null, newP2 = null, newP3 = null, newPosCentral = null;
		BloqueGrafico bgcentral = poscentral.getBloqueGrafico();
		newP1 = poscentral;
		newP2 = pos1;
		newP3 = pos2;
		newPosCentral = pos3;
		
		ocuparDesocuparTresBloquesEnOrden(newP1, pos1, newP2, pos2, newP3, pos3);
		newPosCentral.ocupar(bgcentral);
		asignarBloquesNuevos(newP1, newP2, newP3, newPosCentral);
		
		concretarRotacion();

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
	
	@Override
	public int cantFilasOcupa() {
		return 2;
	}
	
	@Override
	public Integer[] filasOcupadas() {
		Integer[] toRet = new Integer[cantFilasOcupa()];
		switch(rotacion) {
			case(0): {
				toRet[0] = pos1.getFila();
				toRet[1] = poscentral.getFila();
				break;
			}
			case(90): {
				toRet[0] = pos2.getFila();
				toRet[1] = pos1.getFila();
				break;
			}
			case(180): {
				toRet[0] = pos3.getFila();
				toRet[1] = pos2.getFila();
				break;
			}
			case(270): {
				toRet[0] = poscentral.getFila();
				toRet[1] = pos3.getFila();
				break;
			}
		}
		return toRet;
	}
	
}
