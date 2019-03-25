;
import java.awt.Image;


public abstract class Auto {
	
	public abstract int getX();
	public abstract int getY();
	public abstract char getOr();
	public abstract boolean getCargado();
	public abstract Image getImagen();
	public abstract void Ciclo();	
	public abstract void matarHilo();
	public abstract void setCoords(int x, int y);
	public abstract void esperar();
	public abstract void avanzar(boolean a);
	public abstract String getID();
	public abstract void setAvanzar(int a);
	public abstract void setRuta(String a, String b);
	public abstract void setCargado(boolean a);
}
