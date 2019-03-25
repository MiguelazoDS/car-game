;

public class CVSemaforo {
	
	private SemaforoBinario mutex;
	private SemaforoBinario miCondicion;
	//private semFIFO mutex;
	//private semFIFO miCondicion;
	private int bloqueados;
	
	public CVSemaforo(SemaforoBinario _mutex)
	{
		mutex= _mutex;
		miCondicion= new SemaforoBinario(0);
		bloqueados=0;
	}
	
	public void delay()
	{
		bloqueados++;
		mutex.SIGNAL();
		miCondicion.WAIT();		
		mutex.WAIT();
	}
	
	public void resume()
	{
		if(bloqueados>0){
			bloqueados--;
		miCondicion.SIGNAL();}
		//else mutex.SIGNAL();
	}
	
	public boolean empty()
	{
		if(bloqueados>0) return false;
		else return true;
	}

}
