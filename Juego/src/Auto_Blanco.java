import javax.swing.ImageIcon;


public class Auto_Blanco extends Auto{

	private int x;
	private int y;
	private boolean cargado;
	private String ruta;
	
	public Auto_Blanco(int x, int y)
	{
		this.x=x;
		this.y=y;
		cargado=false;
		ruta="Data/Auto_B*V.png";
	}

	public String ciclo()
	{
		String aux="";
		char orientacion=' ';
		if((x==900&&y>=50&&y<=300)||(x==300&&y>=300&&y<=600))
		{
			y+=1;
			orientacion= 'd';
			aux=ruta.replace("*","D");
			if(x<740&&x>150)
			{
				aux=ruta.replace("*V", "DC");
				cargado=true;
			}
			else
				cargado=false;

		}
		if((y==300&&x>=300&&x<=900)||y==600&&x<=300&&x>=50)
		{
			x-=1;
			orientacion = 'l';
			aux=ruta.replace("*","L");
			if(x<740&&x>150)
			{
				aux=ruta.replace("*V", "LC");
				cargado=true;
			}
			else
				cargado=false;
		}		
		if((x==60&&y<=300&&y>=60)||(x==60&&y<=600&&y>=60))
		{
			y-=1;
			orientacion = 'u';
			aux=ruta.replace("*","U");
		}
		if((y==60)&&(x>=50&&x<=900))
		{
			x+=1;
			orientacion = 'r';
			aux=ruta.replace("*","R");
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
	
	
	
}
