
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
	    
	}

	@Override
	public boolean moverAbajo() {
		boolean movimientoPosible = poscentral.getFila() != (miGrilla.getFilas()-1); //Si estamos en la última fila no es posible mover hacia abajo.
		if(movimientoPosible) {
			Bloque newP1 = miGrilla.getBloque(pos1.getFila()+1, pos1.getColumna());
			Bloque newP2 = miGrilla.getBloque(pos2.getFila()+1, pos2.getColumna());
			Bloque newP3 = miGrilla.getBloque(pos3.getFila()+1, pos3.getColumna());
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila()+1, poscentral.getColumna());
			
			movimientoPosible = ((newPosCentral.estaLibre()) && (newP2.estaLibre()) && (newP3.estaLibre()));
			
			if(movimientoPosible) {
				//Debemos tener cuidado con el orden en el que movemos los bloques. Debemos mover primero los tres de abajo y por último el de arriba.
				newP2.ocupar(pos2.getBloqueGrafico());
				pos2.desocupar();
				pos2 = newP2;
				
				newPosCentral.ocupar(poscentral.getBloqueGrafico());
				poscentral.desocupar();
				poscentral = newPosCentral;
				
				newP3.ocupar(pos3.getBloqueGrafico());
				pos3.desocupar();
				pos3 = newP3;
				
				newP1.ocupar(pos1.getBloqueGrafico());
				pos1.desocupar();
				pos1 = newP1;
				
			}
		}
		return movimientoPosible;
	}

	@Override
	public void moverIzquierda() {
		if(pos2.getColumna() != 0) { //Si el tetrimino no se encuentra en la primera columna
			Bloque newP1 = miGrilla.getBloque(pos1.getFila(), pos1.getColumna()-1);
			Bloque newP2 = miGrilla.getBloque(pos2.getFila(), pos2.getColumna()-1);
			Bloque newP3 = miGrilla.getBloque(pos3.getFila(), pos3.getColumna()-1);
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila(), poscentral.getColumna()-1);
			
			if(newP1.estaLibre() && newP2.estaLibre()) {//Si los bloques adyacentes a izquierda de P1 y P2 están libres entonces procedo a mover los bloques.
				//Teniendo cuidado con el orden en que se mueven. Primero muevo los dos de la izquierda (p1 y p2), continuando con pc y por ultimo muevo a p3.
				newP1.ocupar(pos1.getBloqueGrafico());
				pos1.desocupar();
				pos1 = newP1;
				
				newP2.ocupar(pos2.getBloqueGrafico());
				pos2.desocupar();
				pos2 = newP2;
				
				newPosCentral.ocupar(poscentral.getBloqueGrafico());
				poscentral.desocupar();
				poscentral = newPosCentral;
				
				newP3.ocupar(pos3.getBloqueGrafico());
				pos3.desocupar();
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
				newP3.ocupar(pos3.getBloqueGrafico());
				pos3.desocupar();
				pos3 = newP3;
				
				newP1.ocupar(pos1.getBloqueGrafico());
				pos1.desocupar();
				pos1 = newP1;
				
				newPosCentral.ocupar(poscentral.getBloqueGrafico());
				poscentral.desocupar();
				poscentral = newPosCentral;
				
				newP2.ocupar(pos2.getBloqueGrafico());
				pos2.desocupar();
				pos2 = newP2;
				
			}
		}
	}

	@Override
	public void rotar() {
		// TODO Auto-generated method stub
		
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
