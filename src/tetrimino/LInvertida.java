package tetrimino;

import bloque.Bloque;
import gui.BloqueGrafico;
import gui.TetriminoGrafico;
import logica.Grilla;

public class LInvertida extends Tetrimino {
	
	public LInvertida(Grilla mg) {
		super();
		pos1=miGrilla.getBloque(1,3);
	    pos2=miGrilla.getBloque(1,5);
	    pos3=miGrilla.getBloque(0,5);
	    poscentral=miGrilla.getBloque(1,4);
	    miGrilla=mg;
	    String rutaLInvertida_0= this.getClass().getResource("/images/LInvertida_Tetrimino_0.png").toString();
	    String rutaLInvertida_90= this.getClass().getResource("/images/LInvertida_Tetrimino_90.png").toString();
	    String rutaLInvertida_180= this.getClass().getResource("/images/LInvertida_Tetrimino_180.png").toString();
	    String rutaLInvertida_270= this.getClass().getResource("/images/LInvertida_Tetrimino_270.png").toString();
	    BloqueGrafico bloqueGrafico1=new BloqueGrafico(0,rutaLInvertida_0,rutaLInvertida_90,rutaLInvertida_180,rutaLInvertida_270);
	    BloqueGrafico bloqueGrafico2=new BloqueGrafico(0,rutaLInvertida_0,rutaLInvertida_90,rutaLInvertida_180,rutaLInvertida_270);
	    BloqueGrafico bloqueGrafico3=new BloqueGrafico(0,rutaLInvertida_0,rutaLInvertida_90,rutaLInvertida_180,rutaLInvertida_270);
	    BloqueGrafico bloqueGrafico4=new BloqueGrafico(0,rutaLInvertida_0,rutaLInvertida_90,rutaLInvertida_180,rutaLInvertida_270);
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
			
			movimientoPosible = ((newPosCentral.estaLibre()) && (newP1.estaLibre()) && (newP2.estaLibre()));
			
			if(movimientoPosible) {
				//Debemos tener cuidado con el orden en el que movemos los bloques. Debemos mover primero los tres de abajo y por último el de arriba.
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
				//Teniendo cuidado con el orden en que se mueven. Primero muevo a p1, luego a PC, y finalizo moviendo a p2 y p3 (estos dos ultimos pueden moverse en cualquier orden).
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
		if(pos3.getColumna() != (miGrilla.getColumnas()-1)) { //Si no me encuentro en la última columna procedo a mover los bloques.
			Bloque newP1 = miGrilla.getBloque(pos1.getFila(), pos1.getColumna()+1);
			Bloque newP2 = miGrilla.getBloque(pos2.getFila(), pos2.getColumna()+1);
			Bloque newP3 = miGrilla.getBloque(pos3.getFila(), pos3.getColumna()+1);
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila(), poscentral.getColumna()+1);
			
			if(newP3.estaLibre() && newP2.estaLibre()) { //Si hay lugar libre para mover
				//Considerando que debemos mover primero a p2, luego pc y por último p1. Con respecto a p3, puede moverse en cualquier momento inmediato.
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

}
