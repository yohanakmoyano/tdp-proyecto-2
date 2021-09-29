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
	    
	}

	@Override
	public boolean moverAbajo() {
		boolean movimientoPosible = poscentral.getFila() != (miGrilla.getFilas()-1); //Si estoy en la última fila no puedo mover hacia abajo.
		if(movimientoPosible) {
			Bloque newP1 = miGrilla.getBloque(pos1.getFila()+1, pos1.getColumna());
			Bloque newP2 = miGrilla.getBloque(pos2.getFila()+1, pos2.getColumna());
			Bloque newP3 = miGrilla.getBloque(pos3.getFila()+1, pos3.getColumna());
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila()+1, poscentral.getColumna());
			
			movimientoPosible = newP1.estaLibre() && newP2.estaLibre() && newP3.estaLibre() && newPosCentral.estaLibre();
			
			if(movimientoPosible) {
				//En este caso es indistinto cuál movemos primero, todos por igual deben moverse hacia abajo desde la misma fila.
				
				ocuparDesocuparCuatroBloquesEnOrden(newP1, pos1, newP2, pos2, newP3, pos3, newPosCentral, poscentral);
				
				pos1 = newP1;
				
				pos2 = newP2;
				
				pos3 = newP3;
				
				poscentral = newPosCentral;
				
			}
		}
		return movimientoPosible;
	}

	@Override
	public void moverIzquierda() {
		if(poscentral.getColumna() != 0) {//Si no me encuentro en la primer columna
			Bloque newP1 = miGrilla.getBloque(pos1.getFila(), pos1.getColumna()-1);
			Bloque newP2 = miGrilla.getBloque(pos2.getFila(), pos2.getColumna()-1);
			Bloque newP3 = miGrilla.getBloque(pos3.getFila(), pos3.getColumna()-1);
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila(), poscentral.getColumna()-1);
			
			if(newP1.estaLibre()) { //Si el bloque adyacente a izquierda de P1 está libre entonces procedo a mover los bloques a izquierda.
				//Teniendo cuidado con el orden en que se mueven. Primero muevo a p1, luego a PC, continuado con p2 y por último a p3. Iportante respetar el orden.
				
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
		if(pos3.getColumna() != miGrilla.getColumnas()-1) { //Si no me encuentro en la última columna
			Bloque newP1 = miGrilla.getBloque(pos1.getFila(), pos1.getColumna()+1);
			Bloque newP2 = miGrilla.getBloque(pos2.getFila(), pos2.getColumna()+1);
			Bloque newP3 = miGrilla.getBloque(pos3.getFila(), pos3.getColumna()+1);
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila(), poscentral.getColumna()+1);
			
			if(newP2.estaLibre() && newP3.estaLibre()) { //Si hay lugar libre para mover
				//Considerando que debemos mover primero a p3, luego a p2, siguiendo a este será el turno de pc y por último movemos a p1.
				
				ocuparDesocuparCuatroBloquesEnOrden(newP3, pos3, newP2, pos2, newPosCentral, poscentral, newP1, pos1);
				
				pos3 = newP3;
				
				
				pos2 = newP2;
				
				poscentral = newPosCentral;
				
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
			seguir = (poscentral.getColumna()>=1) && (poscentral.getColumna() <= miGrilla.getColumnas()-2); //Si no se encuentra en las primera columna ni en las últimas dos.
			if(seguir) {
				int filaCentral = poscentral.getFila(), columnaCentral = poscentral.getColumna();
				newP1 = miGrilla.getBloque(filaCentral, columnaCentral - 1);
				newP2 = miGrilla.getBloque(filaCentral, columnaCentral + 1);
				newP3 = miGrilla.getBloque(filaCentral, columnaCentral + 2);
			}
			break;
		}
		}
		
		seguir = newP1.estaLibre() && newP2.estaLibre() && newP3.estaLibre();
		if(seguir) {
			ocuparDesocuparTresBloquesEnOrden(newP1, pos1, newP2, pos2, newP3, pos3);
			asignarTresBloquesNuevos(newP1, newP2, newP3);
		}
		
		pos1.getBloqueGrafico().rotar();
		pos2.getBloqueGrafico().rotar();
		pos3.getBloqueGrafico().rotar();
		poscentral.getBloqueGrafico().rotar();
		
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
