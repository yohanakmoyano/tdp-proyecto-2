package gui;

public class BloqueGrafico {
	protected int rotacion;
	protected String[] imagenes;//direccion de las 4 imagenes.
    public BloqueGrafico(int r,String im1,String im2,String im3,String im4) {
    	rotacion=r;
    	imagenes=new String[4];
    	imagenes[0]=im1;
    	imagenes[1]=im2;
    	imagenes[2]=im3;
    	imagenes[3]=im4;
    	
    }
	public void rotar() {
		if (rotacion<270)
		  rotacion=rotacion+90;
		else
			rotacion=0;
	}
	
	
	public String obtenerRutaImagen() {
		String ruta=imagenes[0]; 
		
		if (rotacion==0) {
			ruta=imagenes[0]; 
		}
		
		if (rotacion==90) {
			ruta=imagenes[1]; 
		}
		
		if (rotacion==180) {
			ruta=imagenes[2]; 
		}
		
		if (rotacion==270) {
			ruta=imagenes[3]; 
		}
		
		return ruta;  
	}
	

}