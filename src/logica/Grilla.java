package logica;

import bloque.Bloque;
import gui.BloqueGrafico;

public class Grilla {
	protected Logica miJuego;
	protected Bloque[][] matriz;
	protected int cantFilas;
	protected int cantColum;
	protected BloqueGrafico bloqueLibre;
	public Grilla(Logica j,int f,int c) {
		miJuego=j;
		cantFilas=f;
		cantColum=c;
		bloqueLibre=new BloqueGrafico(0,"","","","");//aca van las rutas de las fotos.
		matriz=new Bloque[f][c];
		for (int i=0;i<f;i++)
			for (int k=0;k<c;k++)
				matriz[i][k]=new Bloque(i,k,this);
	}
	public int getFilas() {
		return cantFilas;
	}
	public int getColumnas() {
		return cantColum;
	}
	public Bloque getBloque(int i, int j) {
		return matriz[i][j];
	}

	public BloqueGrafico getBloqueGrafico() {
		return bloqueLibre;
	}
}
