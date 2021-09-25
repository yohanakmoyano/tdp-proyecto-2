package tetrimino;

import gui.BloqueGrafico;
import gui.TetriminoGrafico;
import logica.Grilla;

public class TInvertida extends Tetrimino{
	
	public TInvertida(Grilla mg) {
		super();
		pos1=miGrilla.getBloque(0,4);
	    pos2=miGrilla.getBloque(1,3);
	    pos3=miGrilla.getBloque(1,5);
	    poscentral=miGrilla.getBloque(1,4);
	    miGrilla=mg;
	    String rutaTInvertida_0= this.getClass().getResource("/images/TInvertida_Tetrimino_0.png").toString();
	    String rutaTInvertida_90= this.getClass().getResource("/images/TInvertida_Tetrimino_90.png").toString();
	    String rutaTInvertida_180= this.getClass().getResource("/images/TInvertida_Tetrimino_180.png").toString();
	    String rutaTInvertida_270= this.getClass().getResource("/images/TInvertida_Tetrimino_270.png").toString();
	    BloqueGrafico bloqueGrafico1=new BloqueGrafico(0,rutaTInvertida_0,rutaTInvertida_90,rutaTInvertida_180,rutaTInvertida_270);
	    BloqueGrafico bloqueGrafico2=new BloqueGrafico(0,rutaTInvertida_0,rutaTInvertida_90,rutaTInvertida_180,rutaTInvertida_270);
	    BloqueGrafico bloqueGrafico3=new BloqueGrafico(0,rutaTInvertida_0,rutaTInvertida_90,rutaTInvertida_180,rutaTInvertida_270);
	    BloqueGrafico bloqueGrafico4=new BloqueGrafico(0,rutaTInvertida_0,rutaTInvertida_90,rutaTInvertida_180,rutaTInvertida_270);
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
