package tetrimino;

import bloque.Bloque;
import gui.BloqueGrafico;
import gui.TetriminoGrafico;
import logica.Grilla;

public class I extends Tetrimino {
	
	
	public I(Grilla mg) {
		
		super();	
		miGrilla=mg;
		pos1=mg.getBloque(0,3);
		pos2=mg.getBloque(0,5);
		pos3=mg.getBloque(0,6);
	    poscentral=mg.getBloque(0,4);
	    
	    String rutaI_0="/images/I_Tetrimino_0.png";
	    String rutaI_90="/images/I_Tetrimino_90.png";
	    String rutaI_180= "/images/I_Tetrimino_180.png";
	    String rutaI_270="/images/I_Tetrimino_270.png";
	    BloqueGrafico bloqueGrafico1=new BloqueGrafico(0,rutaI_0,rutaI_90,rutaI_180,rutaI_270);
	    BloqueGrafico bloqueGrafico2=new BloqueGrafico(0,rutaI_0,rutaI_90,rutaI_180,rutaI_270);
	    BloqueGrafico bloqueGrafico3=new BloqueGrafico(0,rutaI_0,rutaI_90,rutaI_180,rutaI_270);
	    BloqueGrafico bloqueGrafico4=new BloqueGrafico(0,rutaI_0,rutaI_90,rutaI_180,rutaI_270);
	    miTetris=new TetriminoGrafico(bloqueGrafico1,bloqueGrafico2,bloqueGrafico3,bloqueGrafico4);
	    
	    pos1.cambiarMiRepresentacion(bloqueGrafico1);
		pos2.cambiarMiRepresentacion(bloqueGrafico2);
		pos3.cambiarMiRepresentacion(bloqueGrafico3);
		poscentral.cambiarMiRepresentacion(bloqueGrafico4);
		
	}

	@Override
	public boolean moverAbajo() {
		boolean posiblebajar_0_180 = ((rotacion == 0) || (rotacion == 180)) && (poscentral.getFila() != (miGrilla.getFilas()-1)); //Si el tetrimino no se encuentra en la última fila.
		boolean posiblebajar_90 = (rotacion == 90) && (pos1.getFila() != (miGrilla.getFilas()-1));
		boolean posiblebajar_270 = (rotacion == 270) && (pos3.getFila() != (miGrilla.getFilas()-1));

		boolean movimientoPosible = posiblebajar_0_180 || posiblebajar_90 || posiblebajar_270; 
		
		if(movimientoPosible) {
			Bloque newP1 = miGrilla.getBloque(pos1.getFila()+1, pos1.getColumna());
			Bloque newP2 = miGrilla.getBloque(pos2.getFila()+1, pos2.getColumna());
			Bloque newP3 = miGrilla.getBloque(pos3.getFila()+1, pos3.getColumna());
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila()+1, poscentral.getColumna());
			
			switch(rotacion) {
				case(0): case(180): {
					movimientoPosible = ( newP1.estaLibre() && newP2.estaLibre() && newP3.estaLibre() && newPosCentral.estaLibre() );
					if(movimientoPosible) {
						ocuparDesocuparCuatroBloquesEnOrden(newP1, pos1, newP2, pos2, newP3, pos3, newPosCentral, poscentral);
					}
					break;
				}
				case(90): {
					movimientoPosible = newP1.estaLibre();
					if(movimientoPosible) {
						ocuparDesocuparCuatroBloquesEnOrden(newP1, pos1, newPosCentral, poscentral, newP2, pos2, newP3, pos3);
					}
					break;
				}
				case(270): {
					movimientoPosible = newP3.estaLibre();
					if(movimientoPosible) {
						ocuparDesocuparCuatroBloquesEnOrden(newP3, pos3, newP2, pos2, newPosCentral, poscentral, newP1, pos1);
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
		boolean movizquiposible_0 = (rotacion == 0) && (pos1.getColumna() != 0);
		boolean movizquiposible_90_180_270 = ((rotacion == 90) || (rotacion == 180) || (rotacion == 270)) && ((pos3.getColumna() != 0));
		
		if(movizquiposible_0 || movizquiposible_90_180_270) {
			Bloque newP1 = miGrilla.getBloque(pos1.getFila(), pos1.getColumna()-1);
			Bloque newP2 = miGrilla.getBloque(pos2.getFila(), pos2.getColumna()-1);
			Bloque newP3 = miGrilla.getBloque(pos3.getFila(), pos3.getColumna()-1);
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila(), poscentral.getColumna()-1);
			
			boolean mover = false; //Indica si el tetrimino se movio.
			
			switch(rotacion) {
				case(0): {
					mover = newP1.estaLibre();
					if(mover) { 
						ocuparDesocuparCuatroBloquesEnOrden(newP1, pos1, newPosCentral, poscentral, newP2, pos2, newP3, pos3);
					}
					break;
				}
				case(90): case(270): {
					mover = newP1.estaLibre() && newPosCentral.estaLibre() && newP2.estaLibre() && newP3.estaLibre();
					if(mover) { 
						ocuparDesocuparCuatroBloquesEnOrden(newP1, pos1, newPosCentral, poscentral, newP2, pos2, newP3, pos3);	
					}
					break;
				}
				case(180): {
					mover = newP3.estaLibre();
					if(mover) { 
						ocuparDesocuparCuatroBloquesEnOrden(newP3, pos3, newP2, pos2, newPosCentral, poscentral, newP1, pos1);	
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
		boolean movderposible_0 = (rotacion == 0) && (pos3.getColumna() != miGrilla.getColumnas()-1);
		boolean movderposible_90_180_270 = ((rotacion == 90) || (rotacion == 180) || (rotacion == 270)) && (pos1.getColumna() != miGrilla.getColumnas()-1);
		
		if(movderposible_0 || movderposible_90_180_270) {
			
			Bloque newP1 = miGrilla.getBloque(pos1.getFila(), pos1.getColumna()+1);
			Bloque newP2 = miGrilla.getBloque(pos2.getFila(), pos2.getColumna()+1);
			Bloque newP3 = miGrilla.getBloque(pos3.getFila(), pos3.getColumna()+1);
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila(), poscentral.getColumna()+1);
			
			boolean mover = false; //Indica si el tetrimino se movio.
			
			switch(rotacion) {
				case(0): {
					mover = newP3.estaLibre();
					if( mover ) { 
						ocuparDesocuparCuatroBloquesEnOrden(newP3, pos3, newP2, pos2, newPosCentral, poscentral, newP1, pos1);
					}
					break;
				}
				case(90): case(270): {
					mover = newP1.estaLibre() &&  newP2.estaLibre() &&  newP3.estaLibre() &&  newPosCentral.estaLibre();
					if( mover ) {
						ocuparDesocuparCuatroBloquesEnOrden(newP1, pos1, newPosCentral, poscentral, newP2, pos2, newP3, pos3);
					}
					break;
				}
				case(180): {
					mover = newP1.estaLibre();
					if( mover ) {
						ocuparDesocuparCuatroBloquesEnOrden(newP1, pos1, newPosCentral, poscentral, newP2, pos2, newP3, pos3);
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
				seguir = (poscentral.getFila()>=2) && (poscentral.getFila() <= miGrilla.getFilas()-2); //Si no se encuentra en las primeras dos filas ni en la última.
				if(seguir) {
					int filaCentral = poscentral.getFila(), columnaCentral = poscentral.getColumna();
					newP1 = miGrilla.getBloque(filaCentral + 1, columnaCentral);
					newP2 = miGrilla.getBloque(filaCentral - 1 , columnaCentral);
					newP3 = miGrilla.getBloque(filaCentral - 2 , columnaCentral);
				}
				break;
			}
			case(90): { //Rotar de noventa a 180
				seguir = (poscentral.getColumna()>=2) && (poscentral.getColumna() <= miGrilla.getColumnas()-2); //Si no se encuentra en las primeras dos columnas ni en la última.
				if(seguir) {
					int filaCentral = poscentral.getFila(), columnaCentral = poscentral.getColumna();
					newP1 = miGrilla.getBloque(filaCentral, columnaCentral + 1);
					newP2 = miGrilla.getBloque(filaCentral, columnaCentral - 1);
					newP3 = miGrilla.getBloque(filaCentral, columnaCentral - 2);
				}
				break;
			}
			case(180): {//Rotar de 180 a 270
				seguir = (poscentral.getFila()>=1) && (poscentral.getFila() <= miGrilla.getFilas()-3); //Si no se encuentra en las primera fila ni en las últimas dos.
				if(seguir) {
					int filaCentral = poscentral.getFila(), columnaCentral = poscentral.getColumna();
					newP1 = miGrilla.getBloque(filaCentral - 1, columnaCentral);
					newP2 = miGrilla.getBloque(filaCentral + 1 , columnaCentral);
					newP3 = miGrilla.getBloque(filaCentral + 2 , columnaCentral);
				}
				break;
			}
			case(270): { //Rotar de 270 a 0
				seguir = (poscentral.getColumna()>=1) && (poscentral.getColumna() <= miGrilla.getColumnas()-3); //Si no se encuentra en las primera columna ni en las últimas dos.
				if(seguir) {
					int filaCentral = poscentral.getFila(), columnaCentral = poscentral.getColumna();
					newP1 = miGrilla.getBloque(filaCentral, columnaCentral - 1);
					newP2 = miGrilla.getBloque(filaCentral, columnaCentral + 1);
					newP3 = miGrilla.getBloque(filaCentral, columnaCentral + 2);
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
		int cantFilas = ((rotacion == 0) || (rotacion == 180)) ? 1 : 4;
		return cantFilas;
	}
	
	@Override
	public Integer[] filasOcupadas() {
		Integer[] filas = new Integer[cantFilasOcupa()];
		switch(rotacion) {
			case(0): case(180): {
				filas[0] = poscentral.getFila();
				break;
			}
			case(90): {
				filas[0] = pos3.getFila();
				filas[1] = pos2.getFila();
				filas[2] = poscentral.getFila();
				filas[3] = pos1.getFila();
				break;
			}		
			case(270): {
				filas[0] = pos1.getFila();
				filas[1] = poscentral.getFila();
				filas[2] = pos2.getFila();
				filas[3] = pos3.getFila();
				break;
			}
		}
		return filas;
	}
	
}
