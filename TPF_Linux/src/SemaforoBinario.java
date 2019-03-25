;

public class SemaforoBinario {
	
	protected int contador = 0;
	
public SemaforoBinario(int vi){ // --> vi: valor inicial
	
		contador = vi;
	}
	
 public synchronized void WAIT(){
		while(contador == 0)
		{
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		contador--;
	}
	
 public synchronized void SIGNAL(){
		contador =1;
		notify();
	}

}
