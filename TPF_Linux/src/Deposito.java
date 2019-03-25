;


public class Deposito {
	
	private int deposito;
	
	public Deposito()
	{
		deposito=10;
	}
	
	public void disminuir()
	{
		deposito-=1;
	}
	
	public void aumentar()
	{
		deposito+=1;
	}
	
	public int getCantidad()
	{
		return deposito;
	}
}
