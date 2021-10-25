package logica;


public class Reloj extends Thread {
	protected Logica miJuego;
	protected boolean activo;
	protected int step;
	protected long tiempoInicio;
	
	public Reloj(Logica juego, int step) {
		miJuego = juego;
		activo = true;
		this.step = step;
		tiempoInicio = System.currentTimeMillis();
	}

	public void detener() {
		activo = false;
	}
	
	public boolean getEstado() {
		return activo;
	}
	
	public int getStep() {
		return step; 
	}
	
	public void setStep(int step) {
		this.step = step;
	}
	
	public void run() {
		while(activo) {
			try {
				Thread.sleep(step);
				long tiempoActual = System.currentTimeMillis(); 
				long tiempoTranscurrido = tiempoActual - tiempoInicio;
				long tiempoActualEnSegundos = tiempoTranscurrido/1000;
				miJuego.actualizarReloj(tiempoActualEnSegundos);
				miJuego.operar(Logica.moverAbajo);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

}
