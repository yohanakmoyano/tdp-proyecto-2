package logica;

import bloque.Bloque;
import gui.BloqueGrafico;

public class Grilla {
	protected Logica miJuego;
	protected Bloque[][] matriz;
	protected int cantFilas;
	protected int cantColum;
	protected BloqueGrafico bloqueLibre;
	
	
	public Grilla(Logica juego,int f,int c) {
		String rutaBloqueLibre="/images/bloque_libre.jpg";
		miJuego=juego;
		cantFilas=f;
		cantColum=c;
		bloqueLibre=new BloqueGrafico(0,rutaBloqueLibre,rutaBloqueLibre,rutaBloqueLibre,rutaBloqueLibre);
		matriz=new Bloque[cantFilas][cantColum];
		for (int i=0;i<cantFilas;i++) {
			for (int j=0;j<cantColum;j++) {
				matriz[i][j]=new Bloque(i,j,this);
			}
		}		
				
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

	public BloqueGrafico getBloqueGraficoLibre() {
		return bloqueLibre;
	}
	
	public void cambioBloque(Bloque b) {
		miJuego.cambioBloque(b);
	}	
		
	//Método que se encarga de copiar la fila f1 en la fila f2. 
	public void copiar(int f1, int f2) {
		for(int j = 0; j<cantColum; j++) {
			matriz[f2][j].ocupar(matriz[f1][j].getBloqueGrafico());
		}
	}
	
	//Metodo que se encarga de desplazar todo lo que se encuentra arriba de fx (incluyendo a fx) hacia abajo en fy. 
	public void desplazar(int fx, int fy) {
		int f = fx; 
		int i = fy;
		while((i >= 0) && (f > 0)) {
			for(int j = 0; j<cantColum; j++) {
				Bloque bloq_aux = matriz[f][j];
				if(!bloq_aux.estaLibre())
					matriz[i][j].ocupar(bloq_aux.getBloqueGrafico());
				else { 
					if(!matriz[i][j].estaLibre())
						matriz[i][j].desocupar();
				}
			}
			i--;
			f--;
		}
	}
		
	//Comprueba si la fila F de la grilla se encuentra ocupada por completo. 
	public boolean comprobarFila(int f) {
		boolean completa = true;
		int c = 0;
		while(completa && (c < cantColum)) {
			completa = completa && !matriz[f][c].estaLibre();
			c++;
		}
		return completa;
	}
	
}
