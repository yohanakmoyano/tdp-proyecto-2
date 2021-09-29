package logica;

public class Reloj extends Thread {
	protected Logica miJuego;
	protected boolean activo;
	protected int step;
	protected long milisegundos; 
	protected long tiempoActualEnSegundos; 

	public Reloj(Logica juego, int step) {
		miJuego = juego;
		activo = true;
		this.step = step;
		milisegundos=System.currentTimeMillis(); 
		tiempoActualEnSegundos = System.currentTimeMillis() / 1000;
	}

	public void detener() {
		activo = false;
	}
	
	public long getTiempoActualEnSegundos() {
		return tiempoActualEnSegundos; 
	}

	public void setStep(int step) {
		this.step = step;
	}
	
	public int getStep() {
		return step; 
	}
	
	public void run() {
		while(activo) {
			try {
				Thread.sleep(step);
				miJuego.operar(Logica.moverAbajo);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
