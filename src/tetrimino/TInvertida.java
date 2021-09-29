package tetrimino;

import bloque.Bloque;
import gui.BloqueGrafico;
import gui.TetriminoGrafico;
import logica.Grilla;

public class TInvertida extends Tetrimino{
	
	public TInvertida(Grilla mg) {
		super();
		miGrilla=mg;
		pos1=mg.getBloque(0,4);
	    pos2=mg.getBloque(1,3);
	    pos3=mg.getBloque(1,5);
	    poscentral=mg.getBloque(1,4);
	    
	    String rutaTInvertida_0="/images/TInvertida_Tetrimino_0.png";
	    String rutaTInvertida_90="/images/TInvertida_Tetrimino_90.png";
	    String rutaTInvertida_180="/images/TInvertida_Tetrimino_180.png";
	    String rutaTInvertida_270="/images/TInvertida_Tetrimino_270.png";
	    BloqueGrafico bloqueGrafico1=new BloqueGrafico(0,rutaTInvertida_0,rutaTInvertida_90,rutaTInvertida_180,rutaTInvertida_270);
	    BloqueGrafico bloqueGrafico2=new BloqueGrafico(0,rutaTInvertida_0,rutaTInvertida_90,rutaTInvertida_180,rutaTInvertida_270);
	    BloqueGrafico bloqueGrafico3=new BloqueGrafico(0,rutaTInvertida_0,rutaTInvertida_90,rutaTInvertida_180,rutaTInvertida_270);
	    BloqueGrafico bloqueGrafico4=new BloqueGrafico(0,rutaTInvertida_0,rutaTInvertida_90,rutaTInvertida_180,rutaTInvertida_270);
	    miTetris=new TetriminoGrafico(bloqueGrafico1,bloqueGrafico2,bloqueGrafico3,bloqueGrafico4);
	    
	}

	@Override
	public boolean moverAbajo() {
		boolean movimientoPosible = poscentral.getFila() != (miGrilla.getFilas()-1); //si está en la última fila no podrá mover hacia abajo.
		if(movimientoPosible) {
			Bloque newP1 = miGrilla.getBloque(pos1.getFila()+1, pos1.getColumna());
			Bloque newP2 = miGrilla.getBloque(pos2.getFila()+1, pos2.getColumna());
			Bloque newP3 = miGrilla.getBloque(pos3.getFila()+1, pos3.getColumna());
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila()+1, poscentral.getColumna());
			
			movimientoPosible = newP2.estaLibre() && newP3.estaLibre() && newPosCentral.estaLibre();
			
			if(movimientoPosible) {
				//Primero mover p2, pcentral y p3 (los tres de abajo) y luego p1 (el de arriba)
				
				ocuparDesocuparCuatroBloquesEnOrden(newP2, pos2, newPosCentral, poscentral, newP3, pos3, newP1, pos1);
				
				pos2 = newP2;
				
				poscentral = newPosCentral;
				
				pos3 = newP3;
				
				pos1 = newP1;
				
			}
		}
		return movimientoPosible;
	}

	@Override
	public void moverIzquierda() {
		if(pos2.getColumna() != 0) { //Si no me encuentro en la primer columna
			Bloque newP1 = miGrilla.getBloque(pos1.getFila(), pos1.getColumna()-1);
			Bloque newP2 = miGrilla.getBloque(pos2.getFila(), pos2.getColumna()-1);
			Bloque newP3 = miGrilla.getBloque(pos3.getFila(), pos3.getColumna()-1);
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila(), poscentral.getColumna()-1);
			
			if(newP1.estaLibre() && newP2.estaLibre()) { //Si los bloques adyacentes a p1 y a p2 están libres entonces procedo a mover los bloques.
				//Y teniendo cuidado con el orden en el que se van realizando los movimientos (siempre, en este caso, respetando que primero debería moverse p2, luego pc y a continuación p3, en cuanto a p1, es indistinto en qué momento inmediato se mueva).
				
				ocuparDesocuparCuatroBloquesEnOrden(newP1, pos1, newP2, pos2, newPosCentral, poscentral, newP3, pos3);
				
				pos1 = newP1;
				
				pos2 = newP2;
				
				poscentral = newPosCentral;
				
				pos3 = newP3;
				
			}
		}
	}

	@Override
	public void moverDerecha() {
		if(pos3.getColumna() != (miGrilla.getColumnas()-1)) { //Si no me encuentro en la última columna procedo a mover los bloques.
			Bloque newP1 = miGrilla.getBloque(pos1.getFila(), pos1.getColumna()+1);
			Bloque newP2 = miGrilla.getBloque(pos2.getFila(), pos2.getColumna()+1);
			Bloque newP3 = miGrilla.getBloque(pos3.getFila(), pos3.getColumna()+1);
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila(), poscentral.getColumna()+1);
			
			if(newP3.estaLibre() && newP1.estaLibre()) { //Si hay lugar libre para mover
				//Considerando que debemos mover primero a p3, luego pc y por último p2. Con respecto a p1, puede moverse en cualquier momento inmediato.
				
				ocuparDesocuparCuatroBloquesEnOrden(newP3, pos3, newP1, pos1, newPosCentral, poscentral, newP2, pos2);
				
				pos3 = newP3;
				
				pos1 = newP1;
				
				poscentral = newPosCentral;
				
				pos2 = newP2;
				
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
				newP1 = miGrilla.getBloque(filaCentral, columnaCentral - 1); 
				newP2 = miGrilla.getBloque(filaCentral + 1 , columnaCentral);  
				newP3 = miGrilla.getBloque(filaCentral - 1 , columnaCentral); 
				
			}
			break;
		}
		case(90): { //Rotar de noventa a 180
			seguir = poscentral.getColumna() != (miGrilla.getColumnas() - 1); //Si no se encuentra en la última columna.
			if(seguir) {
				int filaCentral = poscentral.getFila(), columnaCentral = poscentral.getColumna();
				newP1 = miGrilla.getBloque(filaCentral + 1, columnaCentral); 
				newP2 = miGrilla.getBloque(filaCentral, columnaCentral + 1);  
				newP3 = miGrilla.getBloque(filaCentral, columnaCentral - 1);  
				
			}
			break;
		}
		case(180): {//Rotar de 180 a 270
			seguir = poscentral.getFila() != 0; //Si no se encuentra en la primer fila.
			if(seguir) {
				int filaCentral = poscentral.getFila(), columnaCentral = poscentral.getColumna();
				newP1 = miGrilla.getBloque(filaCentral, columnaCentral + 1);
				newP2 = miGrilla.getBloque(filaCentral - 1 , columnaCentral);
				newP3 = miGrilla.getBloque(filaCentral + 1 , columnaCentral);
				
			}
			break;
		}
		case(270): { //Rotar de 270 a 0
			seguir = poscentral.getColumna() != 0; //Si no se encuentra en la primera columna.
			if(seguir) {
				int filaCentral = poscentral.getFila(), columnaCentral = poscentral.getColumna();
				newP1 = miGrilla.getBloque(filaCentral - 1, columnaCentral);
				newP2 = miGrilla.getBloque(filaCentral, columnaCentral - 1);
				newP3 = miGrilla.getBloque(filaCentral, columnaCentral + 1);
				
			}
			break;
		}
		}
		
		seguir = newP2.estaLibre();
		if(seguir) {
			ocuparDesocuparTresBloquesEnOrden(newP2, pos2, newP1, pos1, newP3, pos3);
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
