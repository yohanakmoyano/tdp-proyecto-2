package tetrimino;

import bloque.Bloque;
import gui.BloqueGrafico;
import gui.TetriminoGrafico;
import logica.Grilla;

public class Cuadrado extends Tetrimino{
	
	public Cuadrado(Grilla mg) {
		super();
		pos1=miGrilla.getBloque(0,5);
	    pos2=miGrilla.getBloque(0,6);
	    pos3=miGrilla.getBloque(1,6);
	    poscentral=miGrilla.getBloque(1,5);
	    miGrilla=mg;
	    String rutaCuadrado_0= this.getClass().getResource("/images/Cuadrado_Tetrimino_0.png").toString();
	    String rutaCuadrado_90= this.getClass().getResource("/images/Cuadrado_Tetrimino_90.png").toString();
	    String rutaCuadrado_180= this.getClass().getResource("/images/Cuadrado_Tetrimino_180.png").toString();
	    String rutaCuadrado_270= this.getClass().getResource("/images/Cuadrado_Tetrimino_270.png").toString();
	    BloqueGrafico bloqueGrafico1=new BloqueGrafico(0,rutaCuadrado_0,rutaCuadrado_90,rutaCuadrado_180,rutaCuadrado_270);
	    BloqueGrafico bloqueGrafico2=new BloqueGrafico(0,rutaCuadrado_0,rutaCuadrado_90,rutaCuadrado_180,rutaCuadrado_270);
	    BloqueGrafico bloqueGrafico3=new BloqueGrafico(0,rutaCuadrado_0,rutaCuadrado_90,rutaCuadrado_180,rutaCuadrado_270);
	    BloqueGrafico bloqueGrafico4=new BloqueGrafico(0,rutaCuadrado_0,rutaCuadrado_90,rutaCuadrado_180,rutaCuadrado_270);
	    miTetris=new TetriminoGrafico(bloqueGrafico1,bloqueGrafico2,bloqueGrafico3,bloqueGrafico4);
	    
	}
	
	@Override
	public boolean moverAbajo() {
		boolean movimientoPosible = poscentral.getFila() != (miGrilla.getFilas()-1); //si est� en la �ltima fila no podr� mover hacia abajo.
		if(movimientoPosible) {
			Bloque newP1 = miGrilla.getBloque(pos1.getFila()+1, pos1.getColumna());
			Bloque newP2 = miGrilla.getBloque(pos2.getFila()+1, pos2.getColumna());
			Bloque newP3 = miGrilla.getBloque(pos3.getFila()+1, pos3.getColumna());
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila()+1, poscentral.getColumna());
			movimientoPosible = ((newPosCentral.estaLibre()) && (newP3.estaLibre()));
			if(movimientoPosible) {
				//Cuidado con el orden en el que se reemplazan/ocupan los bloques, debemos mover primero los dos de abajo y luego los otros dos de arriba.
				newP3.ocupar(pos3.getBloqueGrafico());
				pos3.desocupar();
				pos3 = newP3;
				
				newPosCentral.ocupar(poscentral.getBloqueGrafico());
				poscentral.desocupar();
				poscentral = newPosCentral;
				
				newP1.ocupar(pos1.getBloqueGrafico());
				pos1.desocupar();
				pos1 = newP1;
				
				newP2.ocupar(pos2.getBloqueGrafico());
				pos2.desocupar();
				pos2 = newP2;
				
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
			
			if(newPosCentral.estaLibre() && newP1.estaLibre()) { //Si los bloques adyacentes a izquierda de P1 y PC est�n libres entonces procedo a mover los bloques.
				//Teniendo cuidado con el orden en que se mueven. Primero muevo los dos de la izquierda (p1 y pc) y luego los dos de la derecha (p2 y p3).
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
		if(pos2.getColumna() != (miGrilla.getColumnas()-1)) { //Si no me encuentro en la �ltima columna procedo a mover los bloques.
			Bloque newP1 = miGrilla.getBloque(pos1.getFila(), pos1.getColumna()+1);
			Bloque newP2 = miGrilla.getBloque(pos2.getFila(), pos2.getColumna()+1);
			Bloque newP3 = miGrilla.getBloque(pos3.getFila(), pos3.getColumna()+1);
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila(), poscentral.getColumna()+1);
			
			if(newP2.estaLibre() && newP3.estaLibre()) { //Si hay lugar libre para mover
				//Considerando que debemos mover primero a p2 y p3 y luego a p1 y pc.
				newP2.ocupar(pos2.getBloqueGrafico());
				pos2.desocupar();
				pos2 = newP2;
				
				newP3.ocupar(pos3.getBloqueGrafico());
				pos3.desocupar();
				pos3 = newP3;
				
				newP1.ocupar(pos1.getBloqueGrafico());
				pos1.desocupar();
				pos1 = newP1;
				
				newPosCentral.ocupar(poscentral.getBloqueGrafico());
				poscentral.desocupar();
				poscentral = newPosCentral;
				
			}
		}
	}

	@Override
	public void rotar() {
		//Debo ver si es posible rotar
	}

}
