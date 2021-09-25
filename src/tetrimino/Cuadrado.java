package tetrimino;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void moverIzquierda() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moverDerecha() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotar() {
		// TODO Auto-generated method stub
		
	}

}
