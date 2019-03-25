package TPFV8;
import java.awt.Image;

import javax.swing.ImageIcon;


public class Auto_Rojo {

	private int x, y;
	private char orientacion;
	private String ruta;
	private boolean cargado;
	private Image imagen;
	private boolean choque;

	
	public Auto_Rojo(int x, int y)
	{
		this.x=x;
		this.y=y;
		orientacion='u';
		cargado=false;
		ruta="Data/Auto_RUV.png";
		imagen=new ImageIcon(ruta).getImage();
		choque = false;
	}
	
	    public int getX()
	    {
	    	return x;
	    }
	    public int getY()
	    {
	    	return y;
	    }
	    
	    public void setX(int a)
	    {
	    	x+=a;
	    }
	
	    public void setY(int a)
	    {
	    	y+=a;
	    }
	    
	    public char getOr()
	    {
	    	return orientacion;
	    }
	    
	    public void setOr(char a)
	    {
	    	orientacion=a;
	    }
	    
	    public String getRuta()
	    {
	    	return ruta;
	    }
	    
	    public void setRuta(String a, String b)
	    {
	    	ruta=ruta.replace(a, b);
	    }
	    
	    public void setCargado(boolean a)
	    {
	    	cargado=a;
	    }
	    
	    public boolean getCargado()
	    {
	    	return cargado;
	    }
	    
	    public Image getImagen()
	    {
	    	return imagen;
	    }
	    
	    public void setImagen(String a)
	    {
	    	imagen=new ImageIcon(a).getImage();
	    }
	    
	    public void setChoque(boolean c)
	    {
	    	choque = c;
	    }
	    
	    public boolean getChoque(){return choque;}
}
