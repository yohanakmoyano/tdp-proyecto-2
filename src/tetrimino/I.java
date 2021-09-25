package tetrimino;

import gui.BloqueGrafico;
import gui.TetriminoGrafico;
import logica.Grilla;

public class I extends Tetrimino {
	public I(Grilla mg) {
		super();
		pos1=miGrilla.getBloque(0,3);
		pos2=miGrilla.getBloque(0,5);
		pos3=miGrilla.getBloque(0,6);
	    poscentral=miGrilla.getBloque(0,4);
	    miGrilla=mg;
	    String rutaI_0= this.getClass().getResource("/images/I_Tetrimino_0.png").toString();
	    String rutaI_90= this.getClass().getResource("/images/I_Tetrimino_90.png").toString();
	    String rutaI_180= this.getClass().getResource("/images/I_Tetrimino_180.png").toString();
	    String rutaI_270= this.getClass().getResource("/images/I_Tetrimino_270.png").toString();
	    BloqueGrafico bloqueGrafico1=new BloqueGrafico(0,rutaI_0,rutaI_90,rutaI_180,rutaI_270);
	    BloqueGrafico bloqueGrafico2=new BloqueGrafico(0,rutaI_0,rutaI_90,rutaI_180,rutaI_270);
	    BloqueGrafico bloqueGrafico3=new BloqueGrafico(0,rutaI_0,rutaI_90,rutaI_180,rutaI_270);
	    BloqueGrafico bloqueGrafico4=new BloqueGrafico(0,rutaI_0,rutaI_90,rutaI_180,rutaI_270);
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
