import java.awt.event.KeyEvent;

public class Auto_Rojo {

	private int x, y;
	private char orientacion;
	private String ruta;
	private boolean cargado;

	
	public Auto_Rojo(int x, int y)
	{
		this.x=x;
		this.y=y;
		orientacion='u';
		cargado=false;
		ruta="Data/Auto_RLV.png";
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
	    
	    public void setRuta()
	    {
	    	ruta=ruta.replace("V", "C");
	    }
}
