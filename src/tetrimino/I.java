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
				newP1.ocupar(pos1.getBloqueGrafico());
				pos1.desocupar();
				pos1 = newP1;
				
				newP2.ocupar(pos2.getBloqueGrafico());
				pos2.desocupar();
				pos2 = newP2;
				
				newP3.ocupar(pos3.getBloqueGrafico());
				pos3.desocupar();
				pos2 = newP3;
				
				newPosCentral.ocupar(poscentral.getBloqueGrafico());
				poscentral.desocupar();
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
		if(pos3.getColumna() != miGrilla.getColumnas()-1) { //Si no me encuentro en la última columna
			Bloque newP1 = miGrilla.getBloque(pos1.getFila(), pos1.getColumna()+1);
			Bloque newP2 = miGrilla.getBloque(pos2.getFila(), pos2.getColumna()+1);
			Bloque newP3 = miGrilla.getBloque(pos3.getFila(), pos3.getColumna()+1);
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila(), poscentral.getColumna()+1);
			
			if(newP2.estaLibre() && newP3.estaLibre()) { //Si hay lugar libre para mover
				//Considerando que debemos mover primero a p3, luego a p2, siguiendo a este será el turno de pc y por último movemos a p1.
				newP3.ocupar(pos3.getBloqueGrafico());
				pos3.desocupar();
				pos3 = newP3;
				
				newP2.ocupar(pos2.getBloqueGrafico());
				pos2.desocupar();
				pos2 = newP2;
				
				newPosCentral.ocupar(poscentral.getBloqueGrafico());
				poscentral.desocupar();
				poscentral = newPosCentral;
				
				newP1.ocupar(pos1.getBloqueGrafico());
				pos1.desocupar();
				pos1 = newP1;
				
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
