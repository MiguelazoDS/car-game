;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound extends Thread{
	
	private String ruta;
	private int valor;
	private Clip clip;
	public Sound(String ruta, int a){
		this.ruta=ruta;	
		valor=a;
}
	@SuppressWarnings("static-access")
	public void run()
	{
		try{	
			
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(ruta));
			clip = AudioSystem.getClip();
			clip.open(inputStream);
			if(valor==1)
				clip.loop(clip.LOOP_CONTINUOUSLY);			
			else if(valor==2)
				clip.start();
					}
					catch (Exception e) {
						e.printStackTrace();
					}					
	}
	
	public void matarHilo()
	{
		clip.stop();
	}
	
}
