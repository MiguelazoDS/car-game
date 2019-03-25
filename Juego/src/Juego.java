import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;

public class Juego extends JDesktopPane implements Runnable, KeyListener{
	
	private Image Background;
	private Image Auto_Blanco1;
	private Image Auto_Blanco2;
	private Image Auto_Blanco3;
    private Image Auto_Azul1;
    private Image Auto_Azul2;
    private Image Auto_Azul3;
    private Image Auto_Rojo;
    private Auto_Azul AA1;
    private Auto_Azul AA2;
    private Auto_Azul AA3;
    private Auto_Blanco AB1;
    private Auto_Blanco AB2;
    private Auto_Blanco AB3;
    private Auto_Rojo AR;
    private Thread hilo;
    private final int DELAY=10;  
    private Sound sonido;
    Sound sonido1;
    
    
    public Juego(){
        //setBackground(Color.WHITE);
        //setDoubleBuffered(true);
        Background = new ImageIcon("Data/Background2.0.png").getImage();
        Auto_Rojo=new ImageIcon("Data/Auto_RLV.png").getImage();
        AA1=new Auto_Azul(360,725);
        AA2=new Auto_Azul(360,825);
        AA3=new Auto_Azul(360,625);
        AB1=new Auto_Blanco(900,225);
        AB2=new Auto_Blanco(900,125);
        AB3=new Auto_Blanco(875,60);
        AR=new Auto_Rojo(150,900);
        sonido = new Sound("Data/Emerald_Hill.wav", 1);
        sonido.start();
        setFocusable(true);
        this.addKeyListener(this);              
    }
    
    @Override
    public void addNotify(){
        super.addNotify();
        hilo = new Thread(this);
        hilo.start();
        
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(Background, 0,0, null);
        g2.drawImage(Auto_Rojo,AR.getX(),AR.getY(),null);
        g2.drawImage(Auto_Azul1,AA1.getX(),AA1.getY(), null);
        g2.drawImage(Auto_Azul2,AA2.getX(),AA2.getY(), null);
        g2.drawImage(Auto_Azul3,AA3.getX(),AA3.getY(), null);
        g2.drawImage(Auto_Blanco1,AB1.getX(),AB1.getY(), null);
        g2.drawImage(Auto_Blanco2,AB2.getX(),AB2.getY(), null);
        g2.drawImage(Auto_Blanco3,AB3.getX(),AB3.getY(), null);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    
   @Override
    public void run() {
        while(true){   
        	int a=0;
        	int b=0;
        	int c=0;
        	int d=0;
        	int e=0;
        	int f=0;
        	//System.out.println(sonido.get());
        	if(AR.getX()>500)
        	{
        		sonido.matar();
        		
        	}
        	if(AR.getX()>=AA1.getX()&&AR.getX()<=(AA1.getX()+40)&&AR.getY()>=AA1.getY()&&AR.getY()<=(AA1.getY()+40)||
        	   AR.getX()+40>=AA1.getX()&&AR.getX()+40<=(AA1.getX()+40)&&AR.getY()>=AA1.getY()&&AR.getY()<=(AA1.getY()+40)||
        	   AR.getX()>=AA1.getX()&&AR.getX()<=(AA1.getX()+40)&&AR.getY()+40>=AA1.getY()&&AR.getY()+40<=(AA1.getY()+40)||
        	   AR.getX()+40>=AA1.getX()&&AR.getX()+40<=(AA1.getX()+40)&&AR.getY()+40>=AA1.getY()&&AR.getY()+40<=(AA1.getY()+40))
        	{        		
        		a=1;
        		if(AA1.getCargado()==true)
        		{
        			AR.setRuta();
        		}
        		
        	}
        	if(a==1)
        	{    
        		AA1.setRuta("Data/Explosion.gif");
        		Auto_Azul1=new ImageIcon(AA1.getRuta()).getImage();
        		new Sound("Data/Robar.wav", 2).start();        		
        		try{
        			Thread.sleep(400);
        			
        			AA1.setX(2000);
        			new Sound("Data/Cargar.wav", 2).start(); 
        			
        			}catch(InterruptedException err){
        			System.out.println(err);
        			}
        	}
        	else
        	{
        		Auto_Azul1= new ImageIcon(AA1.ciclo()).getImage(); 
        	}
        	Auto_Azul2= new ImageIcon(AA2.ciclo()).getImage();
        	Auto_Azul3= new ImageIcon(AA3.ciclo()).getImage();
        	Auto_Blanco1= new ImageIcon(AB1.ciclo()).getImage();
        	Auto_Blanco2= new ImageIcon(AB2.ciclo()).getImage();
        	Auto_Blanco3= new ImageIcon(AB3.ciclo()).getImage(); 
            repaint();
            try{
                Thread.sleep(DELAY);
            }catch(InterruptedException err){
                System.out.println(err);
            }
        }
    }
   

	@Override
	public void keyPressed(KeyEvent e) {
																																																	// TODO Auto-generated method stub
		int c=e.getKeyCode();
		String aux="";
	
		if(c==KeyEvent.VK_UP&&AR.getOr()!='d'&&
		((AR.getX()>=50&&AR.getX()<=60&&AR.getY()>51&&AR.getY()<=910)||
		 (AR.getX()>=350&&AR.getX()<=360&&AR.getY()>1&&AR.getY()<=960)||
		 (AR.getX()>=650&&AR.getX()<=660&&AR.getY()>1&&AR.getY()<=960)||
		 (AR.getX()>=950&&AR.getX()<=960&&AR.getY()>1&&AR.getY()<=960)))
		{						
			AR.setY(-3);		
			AR.setOr('u');
			aux=AR.getRuta().replace("L","U");
			Auto_Rojo=new ImageIcon(aux).getImage();
			repaint();
		}
		if(c==KeyEvent.VK_DOWN&&AR.getOr()!='u'&&
		((AR.getX()>=0&&AR.getX()<=10&&AR.getY()>=0&&AR.getY()<958)||
		 (AR.getX()>=300&&AR.getX()<=310&&AR.getY()>=0&&AR.getY()<958)||
		 (AR.getX()>=600&&AR.getX()<=610&&AR.getY()>=0&&AR.getY()<958)||
		 (AR.getX()>=900&&AR.getX()<=910&&AR.getY()>=50&&AR.getY()<909)))
		{
			AR.setY(3);
			AR.setOr('d');
			aux=AR.getRuta().replace("L","D");
			Auto_Rojo=new ImageIcon(aux).getImage();
			repaint();
		}
		if(c==KeyEvent.VK_LEFT&&AR.getOr()!='r'&&
		((AR.getX()>51&&AR.getX()<=910&&AR.getY()>=900&&AR.getY()<=910)||
		 (AR.getX()>1&&AR.getX()<=960&&AR.getY()>=600&&AR.getY()<=610)||
		 (AR.getX()>1&&AR.getX()<=960&&AR.getY()>=300&&AR.getY()<=310)||
		 (AR.getX()>1&&AR.getX()<=960&&AR.getY()>=0&&AR.getY()<=10)))
		{
			AR.setX(-3);
			AR.setOr('l');
			aux=AR.getRuta().replace("*","L");
			Auto_Rojo=new ImageIcon(aux).getImage();
			repaint();
		}
		if(c==KeyEvent.VK_RIGHT&&AR.getOr()!='l'&&
		((AR.getX()>=0&&AR.getX()<958&&AR.getY()>=950&&AR.getY()<=960)||
		 (AR.getX()>=0&&AR.getX()<958&&AR.getY()>=650&&AR.getY()<=660)||
		 (AR.getX()>=0&&AR.getX()<958&&AR.getY()>=350&&AR.getY()<=360)||
		 (AR.getX()>=50&&AR.getX()<908&&AR.getY()>=50&&AR.getY()<=60)))
		{
			AR.setX(3);
			AR.setOr('r');		
			aux=AR.getRuta().replace("L","R");
			Auto_Rojo=new ImageIcon(aux).getImage();
			repaint();
		}
}
																	


@Override
public void keyReleased(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}
}
