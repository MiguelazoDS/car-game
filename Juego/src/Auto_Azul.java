import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;


public class Auto_Azul extends Auto {

	private int x;
	private int y;
	private boolean cargado;
	private String ruta;
	private char orientacion;
	
	public Auto_Azul(int x, int y)
	{
		this.x=x;
		this.y=y;
		cargado=false;
		ruta="Data/Auto_A*V.png";
		orientacion=' ';
	}
	
	public String ciclo()
	{
		String aux="";
		
		
		if((y==600&&x<=360&x>=0)||(y==900&&x>=360&&x<=900&&(orientacion=='d'||orientacion=='l')))
		{												
			x-=1;
			orientacion='l';
			aux=ruta.replace("*","L");
			if(x<150||x>810)
			{
				aux=ruta.replace("*V", "LC");
				cargado=true;
			}
			else
				cargado=false;
		}
		if((x==0&&y>=600&&y<=960)||(x==900&&y>=650&&y<=900))
		{
			y+=1;
			orientacion='d';
			aux=ruta.replace("*","D");
			if(x<150||y<=900)
			{
				aux=ruta.replace("*V", "DC");
				cargado=true;
			}
			else
				cargado=false;
		}
		if((y==960&&x>=0&&x<=660)||(y==660&&x>=660&&x<=900))
		{
			x+=1;
			orientacion='r';
			aux=ruta.replace("*","R");
			if(x>0)
			{
				aux=ruta.replace("*V", "RC");
				cargado=true;
			}
			else
				cargado=false;
		}
		if((x==660&&y>=650&&y<=960&&(orientacion=='r'||orientacion=='u'))||(x==360&&y<=900&&y>=600))
		{
			y-=1;
			orientacion='u';
			aux=ruta.replace("*","U");
			if(x<150||x==660)
			{
				aux=ruta.replace("*V", "UC");
				cargado=true;
			}
			else
				cargado=false;
		}
		
		return aux;
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
		x=a;
	}
	
	public void setY(int a)
	{
		y=a;
	}
	
	public boolean getCargado()
	{
		return cargado;
	}
	public void setRuta(String a)
	{
		ruta=a;
	}
	
	public String getRuta()
	{
		return ruta;
	}

}
