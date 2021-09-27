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

}