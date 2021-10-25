package logica;

import java.util.Random;
import bloque.Bloque;
import gui.GUI;
import tetrimino.*;

public class Logica {
	protected GUI miGui;
	protected Reloj miReloj;
	protected Grilla miGrillaPrincipal;
	protected Tetrimino tetriActual;
	protected int sigT;
	protected String urlSiguiente;
	protected int puntaje;
	public static final int moverIzquierda = 1;
	public static final int moverDerecha = 2;
	public static final int moverAbajo = 3;
	public static final int rotar = 4;
	private static final int incrementLineaUnicaCompleta = 100;
	private static final int incrementLineaDobleCompleta = 200;
	private static final int incrementLineaTripleCompleta = 500;
	private static final int incrementLineaCuadrupleCompleta = 800;
	

	
	public Logica(GUI mg) {
		miGui = mg;
		miGrillaPrincipal = new Grilla(this, 21, 10);
		Random rn = new Random();
		tetriActual = generarTetris(rn.nextInt(7));
		tetriActual.imprimirtetri();
		sigT = rn.nextInt(7);
		urlSiguiente = mostrarTetriminoSiguiente(sigT);
	}

	
	//Se encarga de ver si la fila pasada por parametro se encuentra ocupada por completo. En caso afirmativo, se encarga de desplazar
	//una fila hacia abajo todas las filas de más arriba a f.
	private int procesarFila(int f) {
		int compl = 0;
		boolean fcompleta = miGrillaPrincipal.comprobarFila(f);
		if(fcompleta) {
			miGrillaPrincipal.desplazar(f-1, f);
			compl = 1;
		}
		return compl;
	}
	
	
	//Método que se encarga de desplazar una fila que se encuentra en el índice x (y todo lo que le antecede) a una fila cuyo indice es y.
	//Se asume que el índice x antecede al indice y. 
	private void desplazar(int x, int y) {
		miGrillaPrincipal.desplazar(x, y);
	}
	
	private void finJuego() {
		miReloj.detener();
		miGui.finDeJuego();
	}
	
	private void actualizarTetriActual() {
		tetriActual = generarTetris(sigT);
		boolean continuar = comprobarPosicionesEnGrilla(tetriActual.getPos1(), tetriActual.getPos2(), tetriActual.getPos3(), tetriActual.getPosCentral(), tetriActual);
		if(continuar) {
			tetriActual.imprimirtetri();
			Random rn = new Random();
			sigT = rn.nextInt(7);
			urlSiguiente = mostrarTetriminoSiguiente(sigT);
			miGui.cambioSiguiente(urlSiguiente);
		} else {
			finJuego();
		}
	}
	
	private void comprobarFilas(Tetrimino ultimo_tetri) {
		int puntajeAnterior = puntaje;
		//Notar que dados los tetriminos posibles, el máximos de filas que puede ocupar es 4 y el mínimo es 1.
		int cantFilOcup = ultimo_tetri.cantFilasOcupa();
		Integer[] filasOcupa = ultimo_tetri.filasOcupadas();
		int cantFilasCompletas = 0;
		switch(cantFilOcup) {
			case(1): {
				int f1 = filasOcupa[0];
				cantFilasCompletas = procesarFila(f1);
				break;
			}
			case(2): {
				int f1 = filasOcupa[0];
				int f2 = filasOcupa[1];
				boolean f1compl = miGrillaPrincipal.comprobarFila(f1);
				boolean f2compl = miGrillaPrincipal.comprobarFila(f2);
				if(f1compl || f2compl) {
					if(f1compl && f2compl) {
						cantFilasCompletas = 2;
						desplazar(f1-1, f2);
					} else {
						if(f1compl) { //F1F2'
							cantFilasCompletas = 1;
							desplazar(f1-1, f1);
						} else { //F1'F2
							cantFilasCompletas = 1;
							desplazar(f1, f2);
						}
					}
				}
				break;
			}
			case(3): {
				int f1 = filasOcupa[0];
				int f2 = filasOcupa[1];
				int f3 = filasOcupa[2];
				boolean f1compl = miGrillaPrincipal.comprobarFila(f1);
				boolean f2compl = miGrillaPrincipal.comprobarFila(f2);
				boolean f3compl = miGrillaPrincipal.comprobarFila(f3);
				if((f1compl && !f2compl) || (!f2compl && f3compl) || (f2compl)) {
					if(f1compl && !f2compl) {
						if(f3compl) { //F1F2'F3
							cantFilasCompletas = 2;
							miGrillaPrincipal.copiar(f2, f3);
							desplazar(f1-1, f2);
						} else { //!f3compl => F1F2'F3'
							cantFilasCompletas = 1;
							desplazar(f1-1, f1);
						}
					} else {
						if(!f2compl && f3compl) { //F1'F2'F3
							if(!f1compl) {
								cantFilasCompletas = 1;
								desplazar(f2, f3);
							}
						} else {
							if(f2compl) {
								if(f1compl) {
									if(f3compl) { //F1F2F3
										cantFilasCompletas = 3;
										desplazar(f1-1, f3);
									} else {//!f3compl => F1F2F3'
										cantFilasCompletas = 2;
										desplazar(f1-1, f2);
									}
								} else { //!f1compl => F1'
									if(f3compl) {//F1'F2F3
										cantFilasCompletas = 2;
										desplazar(f1, f3);
									} else { //F1'F2F3'
										cantFilasCompletas = 1;
										desplazar(f1, f2);
									}
								}
							}
						}
					}
				}
				break;
			}
			case(4): {
				int f1 = filasOcupa[0];
				int f2 = filasOcupa[1];
				int f3 = filasOcupa[2];
				int f4 = filasOcupa[3];
				boolean f1compl = miGrillaPrincipal.comprobarFila(f1);
				boolean f2compl = miGrillaPrincipal.comprobarFila(f2);
				boolean f3compl = miGrillaPrincipal.comprobarFila(f3);
				boolean f4compl = miGrillaPrincipal.comprobarFila(f4);
				if((!f1compl && f2compl && !f3compl) || (!f1compl && !f3compl && f4compl) || (!f1compl && f3compl) || f1compl) {
					if(!f1compl && f2compl && !f3compl) {
						if(f4compl) { //F1'F2F3'F4
							cantFilasCompletas = 2;
							miGrillaPrincipal.copiar(f3, f4);
							miGrillaPrincipal.copiar(f1, f3);
							desplazar(f1-1, f2);
						} else { //!f4compl => F1'F2F3'F4'
							cantFilasCompletas = 1;
							miGrillaPrincipal.copiar(f1, f2);
							desplazar(f1-1, f1);
						}
					} else {
						if(!f1compl && !f3compl && f4compl) {
							if(!f2compl) { //F1'F2'F3'F4
								cantFilasCompletas = 1;
								desplazar(f3, f4);
							}
						} else {
							if(!f1compl && f3compl) {
								if(f2compl) {
									if(f4compl) { //F1'F2F3F4
										cantFilasCompletas = 3;
										desplazar(f1, f4);
									} else { //F1'F2F3F4'
										cantFilasCompletas = 2;
										desplazar(f1, f3);
									}
								} else { //!f2compl => F2'
									if(f4compl) { //F1'F2'F3F4
										cantFilasCompletas = 2;
										desplazar(f2, f4);
									} else { // !f4compl => F4' => F1'F2'F3F4'
										cantFilasCompletas = 1;
										desplazar(f2, f3);
									}
								}
							} else {
								if(f1compl) { //F1
									if(f2compl) { //F2
										if(f3compl) { //F3
											if(f4compl) { //F4 => F1F2F3F4
												cantFilasCompletas = 4;
												desplazar(f1-1, f4);	
											} else { //!f4compl => F4' => F1F2F3F4'
												cantFilasCompletas = 3;
												desplazar(f1-1, f3);
											}		
										} else { //!f3compl => F3'
											if(f4compl) { //F4 => F1F2F3'F4
												cantFilasCompletas = 3;
												miGrillaPrincipal.copiar(f3, f4);
												desplazar(f1-1, f3);		
											} else { //!f4compl => F4' => F1F2F3'F4'
												cantFilasCompletas = 2;
												desplazar(f1-1, f2);
											}
										}
									} else { //!f2compl => F2'
										if(f3compl) { //F3
											if(f4compl) { //F4 => F1F2'F3F4
												cantFilasCompletas = 3;
												miGrillaPrincipal.copiar(f2, f4);
												desplazar(f1-1, f3);
											} else { //!f4compl => F4' => F1F2'F3F4'
												cantFilasCompletas = 2;
												miGrillaPrincipal.copiar(f2, f3);											
												desplazar(f1-1, f2);
											}
										} else { //!f3compl => F3'
											if(f4compl) { // => F4 => F1F2'F3'F4
												cantFilasCompletas = 2;
												miGrillaPrincipal.copiar(f3, f4);
												miGrillaPrincipal.copiar(f2, f3);												
												desplazar(f1-1, f2);
											} else { //!f4compl => F4' => F1F2'F3'F4'
												cantFilasCompletas = 1;												
												desplazar(f1-1, f1);
											}
										}
									}
								}
							}
						}
					}
				}
				break;
			}
		}
		switch(cantFilasCompletas) {
			case(1): {
				puntaje += incrementLineaUnicaCompleta;
				break;
			}
			case(2): {
				puntaje += incrementLineaDobleCompleta;
				break;
			}
			case(3): {
				puntaje += incrementLineaTripleCompleta;
				break;
			}
			case(4): {
				puntaje += incrementLineaCuadrupleCompleta;
				break;
			}
		}
		if(puntajeAnterior!=puntaje) {
			actualizarVelocidad();
		}
		miGui.cambioPuntaje();
	}
	
	private void tetriLLegoaDestino() {
		comprobarFilas(tetriActual);
		actualizarTetriActual();
	}
	
	private boolean comprobarPosicionesEnGrilla(Bloque p1, Bloque p2, Bloque p3, Bloque p4, Tetrimino t) {
		boolean pos1Libre = miGrillaPrincipal.getBloque(t.getPos1().getFila(), t.getPos1().getColumna()).estaLibre();
		boolean pos2Libre = miGrillaPrincipal.getBloque(t.getPos2().getFila(), t.getPos2().getColumna()).estaLibre();
		boolean pos3Libre = miGrillaPrincipal.getBloque(t.getPos3().getFila(), t.getPos3().getColumna()).estaLibre();
		boolean pos4Libre = miGrillaPrincipal.getBloque(t.getPosCentral().getFila(), t.getPosCentral().getColumna()).estaLibre();
		return pos1Libre && pos2Libre && pos3Libre && pos4Libre;
	}	
	
	private Tetrimino generarTetris(int i) {
		Tetrimino t = null;
		switch (i) {
		case 0: {
			t = new Cuadrado(miGrillaPrincipal);
			break;
		}
		case 1: {
			t = new I(miGrillaPrincipal);
			break;
		}
		case 2: {
			t = new L(miGrillaPrincipal);
			break;
		}
		case 3: {
			t = new LInvertida(miGrillaPrincipal);
			break;
		}
		case 4: {
			t = new TInvertida(miGrillaPrincipal);
			break;
		}
		case 5: {
			t = new Z(miGrillaPrincipal);
			break;
		}
		case 6: {
			t = new ZInvertida(miGrillaPrincipal);
			break;
		}
		}
		return t;
	}

	public String mostrarTetriminoSiguiente(int sigTetri) {
		String t = null;
		switch (sigTetri) {
		case(0): {
			t = "/images/Cuadrado_Tetrimino.png";
			break;
		}
		case(1): {
			t = "/images/I_Tetrimino.png";
			break;
		}
		case(2): {
			t = "/images/L_Tetrimino.png";
			break;
		}
		case(3): {
			t = "/images/LInvertida_Tetrimino.png";
			break;
		}
		case(4): {
			t = "/images/TInvertida_Tetrimino.png";
			break;
		}
		case(5): {
			t = "/images/Z_Tetrimino.png";
			break;
		}
		case(6): {
			t = "/images/ZInvertida_Tetrimino.png";
			break;
		}
		}
		return t;
	}
	
	public void iniciarJuego() {
		miReloj = new Reloj(this, 1000);
		puntaje = 0;
		miReloj.start();
	}

	public void actualizarReloj(long tiempo) {
		int minutos = (int) (tiempo/60);
		int segundos = (int) (tiempo - (minutos * 60));
	
		miGui.cambioReloj(segundos, minutos);
	}
	
	public boolean sigueEnJuego() {
		return miReloj.getEstado();
	}

	private void moverAbajo() {
		boolean seMovio = tetriActual.moverAbajo(); 
		if(!seMovio) {
			tetriLLegoaDestino();
		}
	}

	private void moverIzquierda() {
		tetriActual.moverIzquierda();
	}

	private void moverDerecha() {
		tetriActual.moverDerecha();
	}

	private void rotar() {
		tetriActual.rotar();
	}

	public synchronized void operar(int op) {
		switch (op) {
		case moverAbajo: {
			moverAbajo();
			break;
		}
		case moverIzquierda: {
			moverIzquierda();
			break;
		}
		case moverDerecha: {
			moverDerecha();
			break;
		}
		case rotar: {
			rotar();
			break;
		}
		}
	}
	
	public void cambioBloque(Bloque b) {
		miGui.cambioBloque(b);
	}

	public void actualizarVelocidad() {
		if((puntaje%500 == 0) && miReloj.getStep()>=400) {
			miReloj.setStep(miReloj.getStep()-200);	
		}
		
		
	}
	
	public int getPuntaje() {
		return puntaje;
	}
	
	public String obtenerRutaBloqueLibre() {
		return miGrillaPrincipal.getBloqueGraficoLibre().obtenerRutaImagen();
	}
	
	public Tetrimino getTetriActual() {
		return tetriActual; 
	}
	
	public String getUrlSiguiente() {
		return urlSiguiente;
	}
}
