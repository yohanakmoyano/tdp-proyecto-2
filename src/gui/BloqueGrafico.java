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
		
		switch (rotacion) {
			case 90: ruta=imagenes[1]; 
			break; 
			
			case 180: ruta=imagenes[2]; 
			break; 
			
			case 270: ruta=imagenes[3];
			break; 
		}
		
		return ruta;  
	}

}