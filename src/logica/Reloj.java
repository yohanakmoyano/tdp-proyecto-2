package logica;

public class Reloj extends Thread {
	protected Logica miJuego;
	protected boolean activo;
	protected int step;

	public Reloj(Logica juego, int step) {
		miJuego = juego;
		activo = true;
		this.step = step;
	}

	public void detener() {
		activo = false;
	}

	public void setStep(int step) {
		this.step = step;
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
