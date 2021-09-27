package tetrimino;

import bloque.Bloque;
import gui.BloqueGrafico;
import gui.TetriminoGrafico;
import logica.Grilla;

public class Z extends Tetrimino{
	
	public Z(Grilla mg) {
		super();
		pos1=miGrilla.getBloque(0,3);
	    pos2=miGrilla.getBloque(0,4);
	    pos3=miGrilla.getBloque(1,5);
	    poscentral=miGrilla.getBloque(1,4);
	    miGrilla=mg;
	    String rutaZ_0= this.getClass().getResource("/images/Z_Tetrimino_0.png").toString();
	    String rutaZ_90= this.getClass().getResource("/images/Z_Tetrimino_90.png").toString();
	    String rutaZ_180= this.getClass().getResource("/images/Z_Tetrimino_180.png").toString();
	    String rutaZ_270= this.getClass().getResource("/images/Z_Tetrimino_270.png").toString();
	    BloqueGrafico bloqueGrafico1=new BloqueGrafico(0,rutaZ_0,rutaZ_90,rutaZ_180,rutaZ_270);
	    BloqueGrafico bloqueGrafico2=new BloqueGrafico(0,rutaZ_0,rutaZ_90,rutaZ_180,rutaZ_270);
	    BloqueGrafico bloqueGrafico3=new BloqueGrafico(0,rutaZ_0,rutaZ_90,rutaZ_180,rutaZ_270);
	    BloqueGrafico bloqueGrafico4=new BloqueGrafico(0,rutaZ_0,rutaZ_90,rutaZ_180,rutaZ_270);
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
			movimientoPosible = ((newPosCentral.estaLibre()) && (newP3.estaLibre()));
			if(movimientoPosible) {
				//Cuidado con el orden en el que se reemplazan/ocupan los bloques, debemos mover primero los dos de abajo (pc y p3) y luego los otros dos de arriba (p1 y p2).
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
		if(pos1.getColumna() != 0) { //Si no me encuentro en la primer columna.
			Bloque newP1 = miGrilla.getBloque(pos1.getFila(), pos1.getColumna()-1);
			Bloque newP2 = miGrilla.getBloque(pos2.getFila(), pos2.getColumna()-1);
			Bloque newP3 = miGrilla.getBloque(pos3.getFila(), pos3.getColumna()-1);
			Bloque newPosCentral = miGrilla.getBloque(poscentral.getFila(), poscentral.getColumna()-1);
			
			if(newP1.estaLibre() && newPosCentral.estaLibre()) {
				//Respetando que al mover este tetrimino, debemos tener cuidado con que antes de mover el bloque p2 debemos mover el bloque p1 y antes del bloque p3 debo mover el bloque pc.
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
				//Considerando que debemos mover primero a p2 y p3 y por último p1 y pc.
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
