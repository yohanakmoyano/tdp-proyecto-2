package gui;

public class TetriminoGrafico {
	protected BloqueGrafico poscentral, pos1, pos2, pos3;

	public TetriminoGrafico(BloqueGrafico pc, BloqueGrafico p1, BloqueGrafico p2, BloqueGrafico p3) {
		poscentral = pc;
		pos1 = p1;
		pos2 = p2;
		pos3 = p3;
	}

	public void rotar() {
		poscentral.rotar();
		pos1.rotar();
		pos2.rotar();
		pos3.rotar();
	}
}
