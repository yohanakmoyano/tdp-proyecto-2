package logica;


public class Reloj extends Thread {
	protected Logica miJuego;
	protected boolean activo;
	protected int step;
	protected long segundos;
	
	public Reloj(Logica juego, int step) {
		miJuego = juego;
		activo = true;
		this.step = step;
		segundos=System.currentTimeMillis()/1000; 
	}

	public void detener() {
		activo = false;
	}
	
	public float getMinutos() {
		return (segundos/60); 
	}
	
	public long getSegundos() {
		return segundos;
	}
	

	public void setStep(int step) {
		this.step = step;
	}
	
	public String getTiempo() {
		return "00:"+this.getMinutos()+":"+segundos;  
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
