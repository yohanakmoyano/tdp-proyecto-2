		
package tetrimino;

import bloque.Bloque;
import gui.BloqueGrafico;
import gui.TetriminoGrafico;
import logica.Grilla;

public class L extends Tetrimino{
	
	
	public L(Grilla mg) {
		
		super();
		miGrilla=mg;
		pos1=mg.getBloque(0,3);
		pos2=mg.getBloque(1,3);
		pos3=mg.getBloque(1,5);
	    poscentral=mg.getBloque(1,4);
	    
	    String rutaL_0="/Images/L_Tetrimino_0.png";
	    String rutaL_90="/Images/L_Tetrimino_90.png";
	    String rutaL_180="/Images/L_Tetrimino_180.png";
	    String rutaL_270="/Images/L_Tetrimino_270.png";
	    BloqueGrafico bloqueGrafico1=new BloqueGrafico(0,rutaL_0,rutaL_90,rutaL_180,rutaL_270);
	    BloqueGrafico bloqueGrafico2=new BloqueGrafico(0,rutaL_0,rutaL_90,rutaL_180,rutaL_270);
	    BloqueGrafico bloqueGrafico3=new BloqueGrafico(0,rutaL_0,rutaL_90,rutaL_180,rutaL_270);
	    BloqueGrafico bloqueGrafico4=new BloqueGrafico(0,rutaL_0,rutaL_90,rutaL_180,rutaL_270);
	    miTetris=new TetriminoGrafico(bloqueGrafico1,bloqueGrafico2,bloqueGrafico3,bloqueGrafico4);
	    
	    pos1.cambiarMiRepresentacion(bloqueGrafico1);
		pos2.cambiarMiRepresentacion(bloqueGrafico2);
		pos3.cambiarMiRepresentacion(bloqueGrafico3);
		poscentral.cambiarMiRepresentacion(bloqueGrafico4);
	}

	@Override
	public boolean moverAbajo() {
		boolean bajarposible_0_270 = ((rotacion == 0) || (rotacion == 270))&& (pos3.getFila() != (miGrilla.getFilas()-1));
		boolean bajarposible_90_180 = ((rotacion == 90) || (rotacion == 180)) && (pos1.getFila() != (miGrilla.getFilas()-1));
		boolean movimientoPosible = bajarposible_0_270 || bajarposible_90_180; 
		
		if(movimientoPosible) {
			Bloque newP1 = miGrilla.getBloque(pos1.getFila()+1, pos1.getColumna());
			Bloque newP2 = miGrilla.getBloque(pos2.getFila()+1, pos2.getColumna());
			Bloque newP3 = miGrilla.getBloque(pos3.getFila()+1, pos3.getColumna());
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila()+1, poscentral.getColumna());
			
			switch(rotacion) {
				case(0): {
					movimientoPosible = ( newPosCentral.estaLibre() && newP2.estaLibre() && newP3.estaLibre() );
					if(movimientoPosible) {
						ocuparDesocuparCuatroBloquesEnOrden(newP2, pos2, newPosCentral, poscentral, newP3, pos3, newP1, pos1);
					}
					break;
				}
				case(90): {
					movimientoPosible = ( newP1.estaLibre() && newP2.estaLibre() );
					if(movimientoPosible) {
						ocuparDesocuparCuatroBloquesEnOrden(newP1, pos1, newP2, pos2, newPosCentral, poscentral, newP3, pos3);
					}
					break;
				}
				case(180): {
					movimientoPosible = ( newP1.estaLibre() && newP3.estaLibre() && newPosCentral.estaLibre() ) ;
					if(movimientoPosible) {
						ocuparDesocuparCuatroBloquesEnOrden(newP1, pos1, newP3, pos3, newPosCentral, poscentral, newP2, pos2);
					}
					break;
				}
				case(270): {
					movimientoPosible = ( newP3.estaLibre() && newP1.estaLibre() );
					if(movimientoPosible) {
						ocuparDesocuparCuatroBloquesEnOrden(newP3, pos3, newPosCentral, poscentral, newP2, pos2, newP1, pos1);
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
			Bloque newP1 = miGrilla.getBloque(pos1.getFila(), pos1.getColumna()-1);
			Bloque newP2 = miGrilla.getBloque(pos2.getFila(), pos2.getColumna()-1);
			Bloque newP3 = miGrilla.getBloque(pos3.getFila(), pos3.getColumna()-1);
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila(), poscentral.getColumna()-1);
			
			boolean mover = false; //Indica si se realizó o no el movimiento del tetrimino.
			
			switch(rotacion) {
				case(0): {
					mover = newP1.estaLibre() && newP2.estaLibre();
					if(mover) {
						ocuparDesocuparCuatroBloquesEnOrden(newP1, pos1, newP2, pos2, newPosCentral, poscentral, newP3, pos3);
					}
					break;
				}
				case(90): {
					mover = newP1.estaLibre() && newPosCentral.estaLibre() && newP3.estaLibre();
					if(mover) {
						ocuparDesocuparCuatroBloquesEnOrden(newP1, pos1, newP2, pos2, newPosCentral, poscentral, newP3, pos3);
					}
					break;
				}
				case(180): {
					mover = newP3.estaLibre() && newP1.estaLibre();
					if(mover) {
						ocuparDesocuparCuatroBloquesEnOrden(newP3, pos3, newPosCentral, poscentral, newP2, pos2, newP1, pos1);
					}
					break;
				}
				case(270): {
					mover = newP2.estaLibre() && newPosCentral.estaLibre() && newP3.estaLibre();
					if(mover) {
						ocuparDesocuparCuatroBloquesEnOrden(newP2, pos2, newPosCentral, poscentral, newP3, pos3, newP1, pos1);
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
		boolean movderposible_0_90 = ((rotacion == 0) || (rotacion == 90)) && (pos3.getColumna() != (miGrilla.getColumnas()-1));
		boolean movderposible_180_270 = ((rotacion == 180) || (rotacion == 270)) && (pos1.getColumna() != (miGrilla.getColumnas()-1));
		
		if(movderposible_0_90 || movderposible_180_270) {
			Bloque newP1 = miGrilla.getBloque(pos1.getFila(), pos1.getColumna()+1);
			Bloque newP2 = miGrilla.getBloque(pos2.getFila(), pos2.getColumna()+1);
			Bloque newP3 = miGrilla.getBloque(pos3.getFila(), pos3.getColumna()+1);
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila(), poscentral.getColumna()+1);
			
			boolean mover = false; //Indica si se realizó o no el movimiento.
			
			switch(rotacion) {
				case(0): {
					mover = newP3.estaLibre() && newP1.estaLibre();
					if(mover) {
						ocuparDesocuparCuatroBloquesEnOrden(newP3, pos3, newPosCentral, poscentral, newP1, pos1, newP2, pos2);
					}
					break;
				}
				case(90): {
					mover = newP2.estaLibre() && newPosCentral.estaLibre() && newP3.estaLibre();
					if(mover) {
						ocuparDesocuparCuatroBloquesEnOrden(newP2, pos2, newPosCentral, poscentral, newP3, pos3, newP1, pos1);
					}
					break;
				}
				case(180): {
					mover = newP1.estaLibre() && newP2.estaLibre();
					if(mover) {
						ocuparDesocuparCuatroBloquesEnOrden(newP1, pos1, newP2, pos2, newPosCentral, poscentral, newP3, pos3);
					}
					break;
				}
				case(270): {
					mover = newP1.estaLibre() && newPosCentral.estaLibre() && newP3.estaLibre();
					if(mover) {
						ocuparDesocuparCuatroBloquesEnOrden(newP1, pos1, newP2, pos2, newPosCentral, poscentral, newP3, pos3);
					}
					break;
				}
			}
			if(mover)
				asignarBloquesNuevos(newP1, newP2, newP3, newPosCentral);
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
					newP1 = miGrilla.getBloque(filaCentral + 1, columnaCentral - 1); //Diagonal-inferior-izquierda
					newP2 = miGrilla.getBloque(filaCentral + 1 , columnaCentral); //inmediato inferior
					newP3 = miGrilla.getBloque(filaCentral - 1 , columnaCentral); //inmediato superior
				}
				break;
			}
			case(90): { //Rotar de noventa a 180
				seguir = poscentral.getColumna() != (miGrilla.getColumnas() - 1); //Si no se encuentra en la última columna.
				if(seguir) {
					int filaCentral = poscentral.getFila(), columnaCentral = poscentral.getColumna();
					newP1 = miGrilla.getBloque(filaCentral + 1, columnaCentral + 1); //inmediata diagonal-derecha-inferior
					newP2 = miGrilla.getBloque(filaCentral, columnaCentral + 1); //inmediato-posterior
					newP3 = miGrilla.getBloque(filaCentral, columnaCentral - 1); //inmediato-anterior
				}
				break;
			}
			case(180): {//Rotar de 180 a 270
				seguir = poscentral.getFila() != 0; //Si no se encuentra en la primer fila.
				if(seguir) {
					int filaCentral = poscentral.getFila(), columnaCentral = poscentral.getColumna();
					newP1 = miGrilla.getBloque(filaCentral - 1, columnaCentral + 1);
					newP2 = miGrilla.getBloque(filaCentral - 1 , columnaCentral);
					newP3 = miGrilla.getBloque(filaCentral + 1 , columnaCentral);	
				}
				break;
			}
			case(270): { //Rotar de 270 a 0
				seguir = poscentral.getColumna() != 0; //Si no se encuentra en la primera columna.
				if(seguir) {
					int filaCentral = poscentral.getFila(), columnaCentral = poscentral.getColumna();
					newP1 = miGrilla.getBloque(filaCentral - 1, columnaCentral - 1);
					newP2 = miGrilla.getBloque(filaCentral, columnaCentral - 1);
					newP3 = miGrilla.getBloque(filaCentral, columnaCentral + 1);		
				}
				break;
			}
		}
		if((newP1 != null) && (newP2 != null) && (newP3 != null)) {
			seguir = newP1.estaLibre() && newP2.estaLibre() && newP3.estaLibre();
			if(seguir) {
				ocuparDesocuparTresBloquesEnOrden(newP1, pos1, newP2, pos2, newP3, pos3);
				asignarTresBloquesNuevos(newP1, newP2, newP3);

				concretarRotacion();
			}
		}
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
		int filas = ((rotacion == 0) || (rotacion == 180) ? 2 : 3);
		return filas;
	}
	
	@Override
	public Integer[] filasOcupadas() {
		Integer[] filas = new Integer[this.cantFilasOcupa()];
		switch(rotacion) {
			case(0): {
				filas[0] = pos1.getFila();
				filas[1] = pos2.getFila();
				break;
			} 
			case(90): {
				filas[0] = pos3.getFila();
				filas[1] = poscentral.getFila();
				filas[2] = pos2.getFila();
				break;
			}
			case(180): {
				filas[0] = pos2.getFila();
				filas[1] = pos1.getFila();
				break;
			}
			case(270): {
				filas[0] = pos2.getFila();
				filas[1] = poscentral.getFila();
				filas[2] = pos3.getFila();
				break;
			}
		}
		return filas;
	}
	
}
