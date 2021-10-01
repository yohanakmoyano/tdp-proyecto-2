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
		bloqueLibre=new BloqueGrafico(0,rutaBloqueLibre,rutaBloqueLibre,rutaBloqueLibre,rutaBloqueLibre);//aca van las rutas de las fotos de lo que representar�a un bloque libre.
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
	
	public void llegoAFinGrilla() {
		miJuego.tetriLLegoaFin();
	}
	
	public int[] filasCompletas() { //retorno un arreglo con el numero de filas que se completaron y adem�s voy a saber la cantidad de filas comepltas
		
		int [] filas=new int[4];
		
		for(int k=0;k<filas.length;k++) { //inicializo los valores em -1, para no confundir el valor 0, de la fila 0 . 
			filas[k]=-1; 
		}
		
		int cant=0; 
		for(int i=0 ; i<cantFilas ; i++) {
			if( filaCompleta(i) ) {
				filas[cant]=i; 
				cant++;
			}
		}
		
		return filas; 
	}
	
	private boolean filaCompleta(int f) {
		boolean completa=true; 
		boolean seguir=true; 
		for(int i=0; i<cantColum & seguir; i++) {
			if(matriz[f][i].estaLibre()==true) {
				seguir=false;
				completa=false; 
			}
		}
		
		return completa; 
	}
	
	public void desocuparFila(int f) {
		for(int i=0;i<cantColum;i++) {
			matriz[f][i].desocupar();
		}
		
		//mover una posicion abajo en la grilla. 
		
		actualizarGrilla(f); 
	}
	
	public void actualizarGrilla(int fila) { 
		
		for(int i=fila-1;i>=0;i--) {
			for(int j=0;j<cantColum;j++) {
				Bloque aux=matriz[i][j]; 
				matriz[i+1][j].ocupar(aux.getBloqueGrafico());
				matriz[i][j].desocupar();
			}
		}
		
		miJuego.actualizarGrilla(fila); 
	}
	
	
}
