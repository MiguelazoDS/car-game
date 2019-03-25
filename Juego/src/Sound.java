import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound extends Thread{
	
	private String ruta;
	private int valor;
	private boolean vivo;
	public Sound(String ruta, int a){
		this.ruta=ruta;	
		valor=a;
		vivo=true;
		
}
	public boolean get()
	{
		return vivo;
	}
	public void matar()
	{
		vivo=false;
	}
	public void run()
	{
		
		try{	
			
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(ruta));
			Clip clip = AudioSystem.getClip();
			clip.open(inputStream);
			if(valor==1&&vivo==true)
				{
				if(vivo==false)
					clip.close();
				clip.loop(clip.LOOP_CONTINUOUSLY);
				}
			else if(valor==2)
				clip.start();
			
		
		
		}
					catch (Exception e) {
						e.printStackTrace();
					}					
			}		
	}	


