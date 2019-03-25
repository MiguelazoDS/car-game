package TPFV8;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Panel extends JDesktopPane implements Runnable, KeyListener{
	
	private Image Background;
	private Auto_Azul Autos_Azules[];
	private Auto_Blanco Autos_Blancos[];
	private Auto_Rojo AR;
	private Thread hilo;	
	private Sound sonido;
	private Timer timer, tiempo;
	private Monitor []esq;
	private Image explosion;
	private int x, y, contador, duracion, descargas;
	private boolean dibujar, vivo;
	private Deposito deposito;
	
	//DISPAROS:
	private int []t0 = {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; 
	private int []t1 = {0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	private int []t2 = {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0}; 
	private int []t3 = {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0};
	private int []t4 = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0};
	private int []t5 = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0};
	private int []t6 = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0};
	private int []t7 = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0};
	private int []t8 = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0};
	private int []t9 = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1};
	private int []t10 = {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	private int []t11 = {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	private int []t12 = {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	private int []t13 = {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	private int []t14 = {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0};
	private int []t15 = {0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0};
	private int []t16 = {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0};
	private int []t17 = {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0};
	private int []t18 = {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0};
	private int []t19 = {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0};
	
	public Panel()
	{
		contador = 0;
		duracion = 0;
		descargas = 0;
		deposito=new Deposito();
		vivo = true;
		
		setFocusable(true);
		this.addKeyListener(this);
		Background=new ImageIcon("Data/Background3.png").getImage();
		explosion = new ImageIcon("Data/Explosion.gif").getImage();
		dibujar = false;
		sonido = new Sound("Data/Emerald_Hill.wav", 1);
		sonido.start();
		esq = new Monitor[16];
		
		for(int i=0;i<16;i++)
		{
			esq[i]= new Monitor();
		}
		
		AR=new Auto_Rojo(40,375);
		Autos_Blancos=new Auto_Blanco[8];
		Autos_Azules= new Auto_Azul[8];
		
		Autos_Azules[0]= new Auto_Azul(630,150,esq,"Azul_1", deposito,Autos_Azules,Autos_Blancos,AR);
		Autos_Azules[1]= new Auto_Azul(750,675,esq,"Azul_2", deposito,Autos_Azules,Autos_Blancos,AR);
		Autos_Azules[2]= new Auto_Azul(975,675,esq,"Azul_3", deposito,Autos_Azules,Autos_Blancos,AR);
		
		Autos_Azules[3]= new Auto_Azul(630,90,esq,"Azul_4", deposito, Autos_Azules,Autos_Blancos,AR);
		Autos_Azules[4]= new Auto_Azul(750,675,esq,"Azul_5", deposito, Autos_Azules,Autos_Blancos,AR);
		Autos_Azules[5]= new Auto_Azul(975,675,esq,"Azul_6", deposito, Autos_Azules,Autos_Blancos,AR);
		Autos_Azules[6]= new Auto_Azul(750,675,esq,"Azul_7", deposito, Autos_Azules,Autos_Blancos,AR);
		Autos_Azules[7]= new Auto_Azul(975,675,esq,"Azul_8", deposito, Autos_Azules,Autos_Blancos,AR);
		
		
		Autos_Blancos[0]= new Auto_Blanco(940,420,esq, "Blanco_1", deposito, Autos_Blancos,Autos_Azules,AR);
		Autos_Blancos[1]= new Auto_Blanco(800,420,esq, "Blanco_2", deposito,Autos_Blancos,Autos_Azules,AR);
		Autos_Blancos[2]= new Auto_Blanco(730,420,esq, "Blanco_3", deposito,Autos_Blancos,Autos_Azules,AR);
		
		Autos_Blancos[3]= new Auto_Blanco(940,420,esq, "Blanco_4", deposito,Autos_Blancos,Autos_Azules,AR);
		Autos_Blancos[4]= new Auto_Blanco(800,420,esq, "Blanco_5", deposito,Autos_Blancos,Autos_Azules,AR);
		Autos_Blancos[5]= new Auto_Blanco(730,420,esq, "Blanco_6", deposito,Autos_Blancos,Autos_Azules,AR);
		Autos_Blancos[6]= new Auto_Blanco(800,420,esq, "Blanco_7", deposito,Autos_Blancos,Autos_Azules,AR);
		Autos_Blancos[7]= new Auto_Blanco(730,420,esq, "Blanco_8", deposito,Autos_Blancos,Autos_Azules,AR);
		
		for(int i=0;i<Autos_Blancos.length;i++)
		{
			new Thread(Autos_Azules[i]).start();
			new Thread(Autos_Blancos[i]).start();
		}
	}
	
	public void addNotify()
	{
		super.addNotify();
		hilo=new Thread(this);
		hilo.start();
	}
		
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2= (Graphics2D)g;
		g2.drawImage(Background, 0,0,null);
		g2.drawImage(AR.getImagen(), AR.getX(), AR.getY(), null);
		if (dibujar) 
		{
			g.drawImage(explosion, x, y, null);
		}
		for(int i=0;i<Autos_Blancos.length;i++)
		{
			g2.drawImage(Autos_Blancos[i].getImagen(), Autos_Blancos[i].getX(),Autos_Blancos[i].getY() , null);
			g2.drawImage(Autos_Azules[i].getImagen(), Autos_Azules[i].getX(),Autos_Azules[i].getY() , null);
		}
		
		if(duracion!=0&&!vivo)
		{
			g2.setFont(new Font("digital display tfb", Font.ITALIC, 100));
			g2.setColor(Color.blue.darker());
			g2.drawString("Puntuación: "+1000*descargas/duracion, 150, 280);
		}
		else
		{
			g2.setColor(Color.black);
			g2.setFont(new Font("digital display tfb", Font.PLAIN, 32));
			g2.drawString(""+deposito.getCantidad(), 933, 112);
			g2.setFont(new Font("digital display tfb", Font.PLAIN, 26));
			g2.setColor(Color.red);
			g2.drawString(""+descargas, 98, 322);
		}
	}
	
	public void run()
	{
		
		tiempo = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				duracion++;
			}
		});
		tiempo.start();

		while (vivo)
		{
			repaint();
			if (AR.getX()>=35&&AR.getX()<=45&&AR.getY()>=370&&AR.getY()<=380&&AR.getCargado()==true) 
			{
				descargas++;
				AR.setCargado(false);
				AR.setRuta("C", "V");
				new Sound("Data/Cargar.wav", 2).start();
			}
			if (contador == 10 && AR.getCargado() == false) 
			{
				terminarJuego();
				sonido.matarHilo();
				new Sound("Data/Completar.wav", 2).start();
				tiempo.stop();
			}
			
			//COLISIONES:
			for (int i = 0; i < Autos_Blancos.length; i++) 
			{
				colision(Autos_Azules[i]);
				colision(Autos_Blancos[i]);
			}
		}
	}
	
	
	public void print(String cadena)
	{
		System.out.println(cadena);
	}

	
	public void terminarJuego() {
		vivo = false;
	}
	
	
	public void colision(Auto auto) 
	{
		if(AR.getChoque()==true)
		{
			if (AR.getCargado() == true) 
			{
				AR.setRuta("C", "V");
				AR.setCargado(false);
			}
			AR.setChoque(false);
		}
		else
		{
			if ((AR.getX()>=auto.getX()&&AR.getX()<=(auto.getX()+20)&&AR.getY()>=auto.getY()&&AR.getY()<=(auto.getY()+20)|| 
					AR.getX()+20>=auto.getX()&&AR.getX()+20<=(auto.getX()+20)&&AR.getY()>=auto.getY()&&AR.getY()<=(auto.getY()+20)|| 
					AR.getX()>=auto.getX()&&AR.getX()<=(auto.getX()+20)&&AR.getY()+20>=auto.getY()&&AR.getY()+20<=(auto.getY()+20)|| 
					AR.getX()+20>=auto.getX()&&AR.getX()+20<=(auto.getX()+20)&&AR.getY()+20>=auto.getY()&& AR.getY()+20<=(auto.getY()+20))
					&& ((AR.getX()>700&&AR.getX()<840)||(AR.getX()>910&&AR.getX()<1050)||(AR.getX()>490&&AR.getX()<630)||(AR.getX()>280&&AR.getX()<420)
					||(AR.getY()>70&&AR.getY()<210)||(AR.getY()>280&&AR.getY()<420)||(AR.getY()>490&&AR.getY()<630)||(AR.getY()>700&&AR.getY()<840)))	
			{
				if (auto.getCargado() == true && AR.getCargado() == false) 
				{
					AR.setRuta("V", "C");
					AR.setCargado(true);
				} 
					
					contador++;
					x = auto.getX();
					y = auto.getY();			
					auto.matarHilo();
					dibujar = true;
					new Sound("Data/Robar.wav", 2).start();
					timer = new Timer(500, new ActionListener() 
					{
						public void actionPerformed(ActionEvent e) 
						{
							dibujar = false;
							new Sound("Data/Cargar.wav", 2).start();
						}
					});
					timer.setRepeats(false);
					timer.start();
				}
		}
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int c=e.getKeyCode();
		String aux="";
	
		//SUBIR:
		//Condiciones en donde Auto Rojo puede subir
		if(c==KeyEvent.VK_UP&&AR.getOr()!='d'&&
		((AR.getX()>=1085&&AR.getX()<=1095&&AR.getY()>=3&&AR.getY()<=675)||
		 (AR.getX()>=875&&AR.getX()<=885&&AR.getY()>=3&&AR.getY()<=675)||
		 (AR.getX()>=665&&AR.getX()<=675&&AR.getY()>=3&&AR.getY()<=675)||
		 (AR.getX()>=455&&AR.getX()<=465&&AR.getY()>=3&&AR.getY()<=675)||
		 (AR.getX()>=245&&AR.getX()<=255&&AR.getY()>=3&&AR.getY()<=675)||
		 (AR.getX()>=35&&AR.getX()<=45&&AR.getY()>=3&&AR.getY()<=640)))
		{						
			AR.setY(-5);
			AR.setOr('u');
			aux=AR.getRuta().replace("U","U");
			AR.setImagen(aux);
			
			
			
			//Ocupa cruce D7:
			verificarRecurso(esq[6],'S', 240, 295, 505, 510, t5, "T5","AutoRojo","7");
			
			//Devuelvo D7 y ocupo y devuelvo B7:
			verificarRecurso(esq[6],'S', 240, 295, 446, 451, t4, "T4","AutoRojo","7");
			verificarRecurso(esq[6],'S', 240, 295, 382, 387, t3,"T3","AutoRojo","7");
			
			//Devuelvo D12 y ocupo y devuelvo B12:
			verificarRecurso(esq[11],'S', 240, 295, 656, 661, t4, "T4","AutoRojo","12");
			verificarRecurso(esq[11],'S', 240, 295, 592, 597, t3, "T3","AutoRojo","12");
			
			//---------------------------------------------------------------------
			
			//Ocupa cruce D8.
			verificarRecurso(esq[7],'S', 450, 505, 505, 510, t5, "T5","AutoRojo","8");
			
			//Devuelvo D8 y ocupo y devuelvo B8
			verificarRecurso(esq[7],'S', 450, 505, 446, 451, t4, "T4","AutoRojo","8");
			verificarRecurso(esq[7],'S', 450, 505, 382, 387, t3,"T3","AutoRojo","8");
			
			//Devuelvo D13 y ocupo y devuelvo B13:
			verificarRecurso(esq[12],'S', 450, 505, 656, 661, t4, "T4","AutoRojo","13");
			verificarRecurso(esq[12],'S', 450, 505, 592, 597, t3,"T3","AutoRojo","13");
			
			//---------------------------------------------------------------------
			
			//Ocupa cruce D1.
			verificarRecurso(esq[0],'S', 660, 715, 85, 90, t5, "T5","AutoRojo","1");
			verificarRecurso(esq[0],'S', 660, 715, 26, 31, t4, "T4","AutoRojo","1");
			
			//Ocupa cruce D4.
			verificarRecurso(esq[3],'S',660,715, 295 ,300, t5, "T5","AutoRojo","4");
			
			//Devuelvo D4 y ocupo y devuelvo B4
			verificarRecurso(esq[3],'S', 660, 715, 236, 241, t4, "T4","AutoRojo","4");
			verificarRecurso(esq[3],'S', 660, 715, 172, 177, t3, "T3","AutoRojo","4");
			
			//Ocupa cruce D9.
			verificarRecurso(esq[8],'S',660,715, 505, 510, t5, "T5","AutoRojo","9");
			
			//Devuelvo D9 y ocupo y devuelvo B9
			verificarRecurso(esq[8],'S', 660, 715, 446, 451, t4, "T4","AutoRojo","9");
			verificarRecurso(esq[8],'S', 660, 715, 382, 387, t3, "T3","AutoRojo","9");
			
			//Devuelvo D14 y ocupo y devuelvo B14:
			verificarRecurso(esq[13],'S', 660, 715, 656, 661, t4, "T4","AutoRojo","14");
			verificarRecurso(esq[13],'S', 660, 715, 592, 597, t3,"T3","AutoRojo","14");
			
			//-----------------------------------------------------------------------
			
			//Ocupa cruce D2:
			verificarRecurso(esq[1],'S', 870, 925, 85, 90, t5, "T5","AutoRojo","2");
			verificarRecurso(esq[1],'S', 870, 925, 26, 31, t4, "T4","AutoRojo","2");
			
			//Ocupa cruce D5:
			verificarRecurso(esq[4],'S',870, 925, 295 ,300, t5, "T5","AutoRojo","5");
			
			//Devuelvo D5 y ocupo y devuelvo B5:
			verificarRecurso(esq[4],'S',870, 925, 236, 241, t4, "T4","AutoRojo","5");
			verificarRecurso(esq[4],'S',870, 925, 172, 177, t3, "T3","AutoRojo","5");
			
			//Ocupa cruce D10:
			verificarRecurso(esq[9],'S',870, 925, 505, 510, t5, "T5","AutoRojo","10");
			
			//Devuelvo D10 y ocupo y devuelvo B10
			verificarRecurso(esq[9],'S', 870, 925, 446, 451, t4, "T4","AutoRojo","10");
			verificarRecurso(esq[9],'S', 870, 925, 382, 387, t3,"T3","AutoRojo","10");
			
			//Devuelvo D15 y ocupo y devuelvo B15:
			verificarRecurso(esq[14],'S', 870, 925, 656, 661, t4, "T4","AutoRojo","15");
			verificarRecurso(esq[14],'S', 870, 925, 592, 597, t3,"T3","AutoRojo","15");
			
			//----------------------------------------------------------------------------
			//Ocupa cruce D3:
			verificarRecurso(esq[2],'S', 1080, 1126, 85, 90, t5, "T5","AutoRojo","3");
			verificarRecurso(esq[2],'S', 1080, 1126, 26, 31, t4, "T4","AutoRojo","3");
			
			//Ocupa cruce D6:
			verificarRecurso(esq[5],'S', 1080, 1126, 295 ,300, t5, "T5","AutoRojo","6");
			
			//Devuelvo D6 y ocupo y devuelvo B6:
			verificarRecurso(esq[5],'S', 1080, 1126, 236, 241, t4, "T4","AutoRojo","6");
			verificarRecurso(esq[5],'S', 1080, 1126, 172, 177, t3, "T3","AutoRojo","6");
			
			//Ocupa cruce D11:
			verificarRecurso(esq[10],'S', 1080, 1126, 505, 510, t5, "T5","AutoRojo","11");
			
			//Devuelvo D11 y ocupo y devuelvo B11:
			verificarRecurso(esq[10],'S', 1080, 1126, 446, 451, t4, "T4","AutoRojo","11");
			verificarRecurso(esq[10],'S', 1080, 1126, 382, 387, t3, "T3","AutoRojo","11");
			
			//Devuelvo D16 y ocupo y devuelvo B16:
			verificarRecurso(esq[15],'S', 1080, 1126, 656, 661, t4, "T4","AutoRojo","16");
			verificarRecurso(esq[15],'S', 1080, 1126, 592, 597, t3,"T3","AutoRojo","16");
			
			
			
		}
		
		//BAJAR
		if(c==KeyEvent.VK_DOWN&&AR.getOr()!='u'&&
		((AR.getX()>=0&&AR.getX()<=10&&AR.getY()>=0&&AR.getY()<673)||
		 (AR.getX()>=210&&AR.getX()<=220&&AR.getY()>=0&&AR.getY()<673)||
		 (AR.getX()>=420&&AR.getX()<=430&&AR.getY()>=0&&AR.getY()<673)||
		 (AR.getX()>=630&&AR.getX()<=640&&AR.getY()>=0&&AR.getY()<673)||
		 (AR.getX()>=840&&AR.getX()<=850&&AR.getY()>=0&&AR.getY()<673)||
		 (AR.getX()>=1050&&AR.getX()<=1060&&AR.getY()>=0&&AR.getY()<673)))
		{
			AR.setY(5);
			AR.setOr('d');
			aux=AR.getRuta().replace("U","D");
			AR.setImagen(aux);
			
			
			//Ocupar y desocupar cruce A7 y ocupar cruce C7:
			verificarRecurso(esq[6],'B', 202, 246, 380, 385, t0, "T0","AutoRojo","7");
			verificarRecurso(esq[6],'B', 202, 246, 431, 436, t1, "T1","AutoRojo","7");
			
			//Desocupar cruce C7:
			verificarRecurso(esq[6],'B', 202, 246, 498, 503, t2,"T2","AutoRojo","7");
			
			//Ocupar y desocupar cruce A12 y ocupar cruce C12:
			verificarRecurso(esq[11],'B', 202, 246, 590, 595, t0, "T0","AutoRojo","12");
			verificarRecurso(esq[11],'B', 202, 246, 641, 646, t1, "T1","AutoRojo","12");
			
			//------------------------------------------------------------------------
			
			//Ocupar y desocupar cruce A8 y ocupar cruce C8:
			verificarRecurso(esq[7],'B', 412, 456, 380, 385, t0, "T0","AutoRojo","8");
			verificarRecurso(esq[7],'B', 412, 456, 431, 436, t1, "T1","AutoRojo","8");
			
			//Desocupar cruce C8:
			verificarRecurso(esq[7],'B', 412, 456, 498, 503, t2,"T2","AutoRojo","8");
			
			//Ocupar y desocupar cruce A13 y ocupar cruce C13:
			verificarRecurso(esq[12],'B', 412, 456, 590, 595, t0, "T0","AutoRojo","13");
			verificarRecurso(esq[12],'B', 412, 456, 641, 646, t1, "T1","AutoRojo","13");
			
			//------------------------------------------------------------------------
			
			//Desocupar cruce A1 y ocupar cruce C1:
			verificarRecurso(esq[0],'B', 622, 666, 11, 16, t1, "T1","AutoRojo","1");
			
			//Desocupar cruce C1:
			verificarRecurso(esq[0],'B', 622, 666, 78, 83, t2,"T2","AutoRojo","1");
			
			//Ocupar y desocupar cruce A4 y ocupar cruce C4:
			verificarRecurso(esq[3],'B', 622, 666, 170, 175, t0, "T0","AutoRojo","4");
			verificarRecurso(esq[3],'B', 622, 666, 221, 226, t1, "T1","AutoRojo","4");
			
			//Desocupar cruce C4:
			verificarRecurso(esq[3],'B', 622, 666, 288, 293, t2,"T2","AutoRojo","4");
			
			//Ocupar y desocupar cruce A9 y ocupar cruce C9:
			verificarRecurso(esq[8],'B', 622, 666, 380, 385, t0, "T0","AutoRojo","9");
			verificarRecurso(esq[8],'B', 622, 666, 431, 436, t1, "T1","AutoRojo","9");
			
			//Desocupar cruce C9:
			verificarRecurso(esq[8],'B', 622, 666, 498, 503, t2,"T2","AutoRojo","9");
			
			//Ocupar y desocupar cruce A14 y ocupar cruce C14:
			verificarRecurso(esq[13],'B', 622, 666, 590, 595, t0, "T0","AutoRojo","14");
			verificarRecurso(esq[13],'B', 622, 666, 641, 646, t1, "T1","AutoRojo","14");
			
			//-------------------------------------------------------------------------
			
			//Desocupar cruce A2 y ocupar cruce C2:
			verificarRecurso(esq[1],'B', 832, 876, 11, 16, t1, "T1","AutoRojo","2");
			
			//Desocupar cruce C2:
			verificarRecurso(esq[1],'B', 832, 876, 78, 83, t2,"T2","AutoRojo","2");
			
			//Ocupar y desocupar cruce A5 y ocupar cruce C5:
			verificarRecurso(esq[4],'B', 832, 876, 170, 175, t0, "T0","AutoRojo","5");
			verificarRecurso(esq[4],'B', 832, 876, 221, 226, t1, "T1","AutoRojo","5");
			
			//Desocupar cruce C5:
			verificarRecurso(esq[4],'B', 832, 876, 288, 293, t2,"T2","AutoRojo","5");
			
			//Ocupar y desocupar cruce A10 y ocupar cruce C10:
			verificarRecurso(esq[9],'B', 832, 876, 380, 385, t0, "T0","AutoRojo","10");
			verificarRecurso(esq[9],'B', 832, 876, 431, 436, t1, "T1","AutoRojo","10");
			
			//Desocupar cruce C10:
			verificarRecurso(esq[9],'B', 832, 876, 498, 503, t2,"T2","AutoRojo","10");
			
			//Ocupar y desocupar cruce A15 y ocupar cruce C15:
			verificarRecurso(esq[14],'B', 832, 876, 590, 595, t0, "T0","AutoRojo","15");
			verificarRecurso(esq[14],'B', 832, 876, 641, 646, t1, "T1","AutoRojo","15");
			
			//---------------------------------------------------------------------------
			
			//Desocupar cruce A3 y ocupar cruce C3:
			verificarRecurso(esq[2],'B', 1042, 1086, 11, 16, t1, "T1","AutoRojo","3");
			
			//Desocupar cruce C3:
			verificarRecurso(esq[2],'B', 1042, 1086, 78, 83, t2,"T2","AutoRojo","3");
			
			//Ocupar y desocupar cruce A6 y ocupar cruce C6:
			verificarRecurso(esq[5],'B', 1042, 1086, 170, 175, t0, "T0","AutoRojo","6");
			verificarRecurso(esq[5],'B', 1042, 1086, 221, 226, t1, "T1","AutoRojo","6");
			
			//Desocupar cruce C6:
			verificarRecurso(esq[5],'B', 1042, 1086, 288, 293, t2,"T2","AutoRojo","6");
			
			//Ocupar y desocupar cruce A11 y ocupar cruce C11:
			verificarRecurso(esq[10],'B', 1042, 1086, 380, 385, t0, "T0","AutoRojo","11");
			verificarRecurso(esq[10],'B', 1042, 1086, 431, 436, t1, "T1","AutoRojo","11");
			
			//Desocupar cruce C11:
			verificarRecurso(esq[10],'B', 1042, 1086, 498, 503, t2,"T2","AutoRojo","11");
			
			//Ocupar y desocupar cruce A16 y ocupar cruce C16:
			verificarRecurso(esq[15],'B', 1042, 1086, 590, 595, t0, "T0","AutoRojo","16");
			verificarRecurso(esq[15],'B', 1042, 1086, 641, 646, t1, "T1","AutoRojo","16");
		}
		
		//IZQUIERDA
		if(c==KeyEvent.VK_LEFT&&AR.getOr()!='r'&&
		((AR.getX()>=3&&AR.getX()<=1095&&AR.getY()>=0&&AR.getY()<=10)||
		 (AR.getX()>=3&&AR.getX()<=1095&&AR.getY()>=210&&AR.getY()<=220)||
		 (AR.getX()>=3&&AR.getX()<=1095&&AR.getY()>=420&&AR.getY()<=430)||
		 (AR.getX()>38&&AR.getX()<=1060&&AR.getY()>=630&&AR.getY()<=640)))
		{
			AR.setX(-5);
			AR.setOr('l');
			aux=AR.getRuta().replace("U","L");
			AR.setImagen(aux);
			
			//Ocupar y desocupar cruce B1 y ocupar cruce A1:
			verificarRecurso(esq[0],'I', 715, 720, 0, 37, t10, "T10","AutoRojo","1");
			verificarRecurso(esq[0],'I', 657, 662, 0, 37, t8,"T8","AutoRojo","1");
			
			//Desocupar cruce A1:
			verificarRecurso(esq[0],'I', 592, 597, 0, 37, t6,"T6","AutoRojo","1");
			
			//Ocupar y desocupar cruce B2 y ocupar cruce A2:
			verificarRecurso(esq[1],'I', 925, 930, 0, 37, t10, "T10","AutoRojo","2");
			verificarRecurso(esq[1],'I', 867, 872, 0, 37, t8,"T8","AutoRojo","2");
			
			//Desocupar cruce A2:
			verificarRecurso(esq[1],'I', 802, 807, 0, 37, t6,"T6","AutoRojo","2");
			
			//Desocupar cruce B3 y ocupar cruce A3:
			verificarRecurso(esq[2],'I', 1077, 1082, 0, 37, t8,"T8","AutoRojo","3");
			
			//Desocupar cruce A3:
			verificarRecurso(esq[2],'I', 1012, 1017, 0, 37, t6,"T6","AutoRojo","3");
			
			//-------------------------------------------------------------------------
			
			//Ocupar y desocupar cruce B4 y ocupar cruce A4:
			verificarRecurso(esq[3],'I', 715, 720, 204, 247, t10, "T10","AutoRojo","4");
			verificarRecurso(esq[3],'I', 657, 662, 204, 247, t8,"T8","AutoRojo","4");
			
			//Desocupar cruce A4:
			verificarRecurso(esq[3],'I', 592, 597, 204, 247, t6,"T6","AutoRojo","4");
			
			//Ocupar y desocupar cruce B5 y ocupar cruce A5:
			verificarRecurso(esq[4],'I', 925, 930, 204, 247, t10, "T10","AutoRojo","5");
			verificarRecurso(esq[4],'I', 867, 872, 204, 247, t8,"T8","AutoRojo","5");
			
			//Desocupar cruce A5:
			verificarRecurso(esq[4],'I', 802, 807, 204, 247, t6,"T6","AutoRojo","5");
			
			//Desocupar cruce B6 y ocupar cruce A6:
			verificarRecurso(esq[5],'I', 1077, 1082, 204, 247, t8,"T8","AutoRojo","6");
			
			//Desocupar cruce A6:
			verificarRecurso(esq[5],'I', 1012, 1017, 204, 247, t6,"T6","AutoRojo","6");
			
			//--------------------------------------------------------------------------
			
			//Ocupar y desocupar cruce B7 y ocupar cruce A7:
			verificarRecurso(esq[6],'I', 295, 300, 414, 457, t10, "T10","AutoRojo","7");
			verificarRecurso(esq[6],'I', 237, 242, 414, 457, t8,"T8","AutoRojo","7");
			
			//Desocupar cruce A7:
			verificarRecurso(esq[6],'I', 172, 177, 414, 457, t6,"T6","AutoRojo","7");
			
			//Ocupar y desocupar cruce B8 y ocupar cruce A8:
			verificarRecurso(esq[7],'I', 505, 510, 414, 457, t10, "T10","AutoRojo","8");
			verificarRecurso(esq[7],'I', 447, 452, 414, 457, t8,"T8","AutoRojo","8");
			
			//Desocupar cruce A8:
			verificarRecurso(esq[7],'I', 382, 387, 414, 457, t6,"T6","AutoRojo","8");
			
			//Ocupar y desocupar cruce B9 y ocupar cruce A9:
			verificarRecurso(esq[8],'I', 715, 720, 414, 457, t10, "T10","AutoRojo","9");
			verificarRecurso(esq[8],'I', 657, 662, 414, 457, t8,"T8","AutoRojo","9");
			
			//Desocupar cruce A9:
			verificarRecurso(esq[8],'I', 592, 597, 414, 457, t6,"T6","AutoRojo","9");
			
			//Ocupar y desocupar cruce B10 y ocupar cruce A10:
			verificarRecurso(esq[9],'I', 925, 930, 414, 457, t10, "T10","AutoRojo","10");
			verificarRecurso(esq[9],'I', 867, 872, 414, 457, t8,"T8","AutoRojo","10");
			
			//Desocupar cruce A10:
			verificarRecurso(esq[9],'I', 802, 807, 414, 457, t6,"T6","AutoRojo","10");
			
			//Desocupar cruce B11 y ocupar cruce A11:
			verificarRecurso(esq[10],'I', 1077, 1082, 414, 457, t8,"T8","AutoRojo","11");
			
			//Desocupar cruce A11:
			verificarRecurso(esq[10],'I', 1012, 1017, 414, 457, t6,"T6","AutoRojo","11");
			
			//----------------------------------------------------------------------------
			
			//Ocupar y desocupar cruce B12 y ocupar cruce A12:
			verificarRecurso(esq[11],'I', 295, 300, 624, 667, t10, "T10","AutoRojo","12");
			verificarRecurso(esq[11],'I', 237, 242, 624, 667, t8,"T8","AutoRojo","12");
			
			//Desocupar cruce A12:
			verificarRecurso(esq[11],'I', 172, 177, 624, 667, t6,"T6","AutoRojo","12");
			
			//Ocupar y desocupar cruce B13 y ocupar cruce A13:
			verificarRecurso(esq[12],'I', 505, 510, 624, 667, t10, "T10","AutoRojo","13");
			verificarRecurso(esq[12],'I', 447, 452, 624, 667, t8,"T8","AutoRojo","13");
			
			//Desocupar cruce A13:
			verificarRecurso(esq[12],'I', 382, 387, 624, 667, t6,"T6","AutoRojo","13");
			
			//Ocupar y desocupar cruce B14 y ocupar cruce A14:
			verificarRecurso(esq[13],'I', 715, 720, 624, 667, t10, "T10","AutoRojo","14");
			verificarRecurso(esq[13],'I', 657, 662, 624, 667, t8,"T8","AutoRojo","14");
			
			//Desocupar cruce A14:
			verificarRecurso(esq[13],'I', 592, 597, 624, 667, t6,"T6","AutoRojo","14");
			
			//Ocupar y desocupar cruce B15 y ocupar cruce A15:
			verificarRecurso(esq[14],'I', 925, 930, 624, 667, t10, "T10","AutoRojo","15");
			verificarRecurso(esq[14],'I', 867, 872, 624, 667, t8,"T8","AutoRojo","15");
			
			//Desocupar cruce A15:
			verificarRecurso(esq[14],'I', 802, 807, 624, 667, t6,"T6","AutoRojo","15");
			
			//Desocupar cruce B16 y ocupar cruce A16:
			verificarRecurso(esq[15],'I', 1077, 1082, 624, 667, t8,"T8","AutoRojo","16");
			
			//Desocupar cruce A16:
			verificarRecurso(esq[15],'I', 1012, 1017, 624, 667, t6,"T6","AutoRojo","16");
		}
		
		//DERECHA
		if(c==KeyEvent.VK_RIGHT&&AR.getOr()!='l'&&
		((AR.getX()>=0&&AR.getX()<=1092&&AR.getY()>=665&&AR.getY()<=675)||
		 (AR.getX()>=0&&AR.getX()<=1092&&AR.getY()>=455&&AR.getY()<=465)||
		 (AR.getX()>=0&&AR.getX()<=1092&&AR.getY()>=245&&AR.getY()<=255)||
		 (AR.getX()>=35&&AR.getX()<1092&&AR.getY()>=35&&AR.getY()<=45)))
		{
			
			
			AR.setX(5);
			AR.setOr('r');		
			aux=AR.getRuta().replace("U","R");
			AR.setImagen(aux);
			
			//Ocupar y desocupar cruce C1 y ocupar cruce D1:
			verificarRecurso(esq[0],'D', 585, 590, 32, 76, t7, "T7","AutoRojo","1");
			verificarRecurso(esq[0],'D', 643, 648, 32, 76, t9,"T9","AutoRojo","1");
			
			//Desocupar cruce D1:
			verificarRecurso(esq[0],'D', 709, 714, 32, 76, t11,"T11","AutoRojo","1");
			
			//Ocupar y desocupar cruce C2 y ocupar cruce D2:
			verificarRecurso(esq[1],'D', 795, 800, 32, 76, t7, "T7","AutoRojo","2");
			verificarRecurso(esq[1],'D', 853, 858, 32, 76, t9,"T9","AutoRojo","2");
			
			//Desocupar cruce D2:
			verificarRecurso(esq[1],'D', 919, 924, 32, 76, t11,"T11","AutoRojo","2");
			
			//Ocupar y desocupar cruce C3 y ocupar cruce D3:
			verificarRecurso(esq[2],'D', 1005, 1010, 32, 76, t7, "T7","AutoRojo","3");
			verificarRecurso(esq[2],'D', 1063, 1068, 32, 76, t9,"T9","AutoRojo","3");
			
			//------------------------------------------------------------------------
			
			//Ocupar y desocupar cruce C4 y ocupar cruce D4:
			verificarRecurso(esq[3],'D', 585, 590, 242, 286, t7, "T7","AutoRojo","4");
			verificarRecurso(esq[3],'D', 643, 648, 242, 286, t9,"T9","AutoRojo","4");
			
			//Desocupar cruce D4:
			verificarRecurso(esq[3],'D', 709, 714, 242, 286, t11,"T11","AutoRojo","4");
			
			//Ocupar y desocupar cruce C5 y ocupar cruce D5:
			verificarRecurso(esq[4],'D', 795, 800, 242, 286, t7, "T7","AutoRojo","5");
			verificarRecurso(esq[4],'D', 853, 858, 242, 286, t9,"T9","AutoRojo","5");
			
			//Desocupar cruce D5:
			verificarRecurso(esq[4],'D', 919, 924, 242, 286, t11,"T11","AutoRojo","5");
			
			//Ocupar y desocupar cruce C6 y ocupar cruce D6:
			verificarRecurso(esq[5],'D', 1005, 1010, 242, 286, t7, "T7","AutoRojo","6");
			verificarRecurso(esq[5],'D', 1063, 1068, 242, 286, t9,"T9","AutoRojo","6");
			
			//------------------------------------------------------------------------
			
			//Ocupar y desocupar cruce C7 y ocupar cruce D7:
			verificarRecurso(esq[6],'D', 165, 170, 452, 496, t7, "T7","AutoRojo","7");
			verificarRecurso(esq[6],'D', 223, 228, 452, 496, t9,"T9","AutoRojo","7");
			
			//Desocupar cruce D7:
			verificarRecurso(esq[6],'D', 289, 294, 452, 496, t11,"T11","AutoRojo","7");
			
			//Ocupar y desocupar cruce C8 y ocupar cruce D8:
			verificarRecurso(esq[7],'D', 375, 380, 452, 496, t7, "T7","AutoRojo","8");
			verificarRecurso(esq[7],'D', 433, 438, 452, 496, t9,"T9","AutoRojo","8");
			
			//Desocupar cruce D8:
			verificarRecurso(esq[7],'D', 499, 504, 452, 496, t11,"T11","AutoRojo","8");
			
			//Ocupar y desocupar cruce C9 y ocupar cruce D9:
			verificarRecurso(esq[8],'D', 585, 590, 452, 496, t7, "T7","AutoRojo","9");
			verificarRecurso(esq[8],'D', 643, 648, 452, 496, t9,"T9","AutoRojo","9");
			
			//Desocupar cruce D9:
			verificarRecurso(esq[8],'D', 709, 714, 452, 496, t11,"T11","AutoRojo","9");
			
			//Ocupar y desocupar cruce C10 y ocupar cruce D10:
			verificarRecurso(esq[9],'D', 795, 800, 452, 496, t7, "T7","AutoRojo","10");
			verificarRecurso(esq[9],'D', 853, 858, 452, 496, t9,"T9","AutoRojo","10");
			
			//Desocupar cruce D10:
			verificarRecurso(esq[9],'D', 919, 924, 452, 496, t11,"T11","AutoRojo","10");
			
			//Ocupar y desocupar cruce C11 y ocupar cruce D11:
			verificarRecurso(esq[10],'D', 1005, 1010, 452, 496, t7, "T7","AutoRojo","11");
			verificarRecurso(esq[10],'D', 1063, 1068, 452, 496, t9,"T9","AutoRojo","11");
			
			//------------------------------------------------------------------------
			
			//Ocupar y desocupar cruce C12 y ocupar cruce D12:
			verificarRecurso(esq[11],'D', 165, 170, 662, 706, t7, "T7","AutoRojo","12");
			verificarRecurso(esq[11],'D', 223, 228, 662, 706, t9,"T9","AutoRojo","12");
			
			//Desocupar cruce D12:
			verificarRecurso(esq[11],'D', 289, 294, 662, 706, t11,"T11","AutoRojo","12");
			
			//Ocupar y desocupar cruce C13 y ocupar cruce D13:
			verificarRecurso(esq[12],'D', 375, 380, 662, 706, t7, "T7","AutoRojo","13");
			verificarRecurso(esq[12],'D', 433, 438, 662, 706, t9,"T9","AutoRojo","13");
			
			//Desocupar cruce D13:
			verificarRecurso(esq[12],'D', 499, 504, 662, 706, t11,"T11","AutoRojo","13");
			
			//Ocupar y desocupar cruce C14 y ocupar cruce D14:
			verificarRecurso(esq[13],'D', 585, 590, 662, 706, t7, "T7","AutoRojo","14");
			verificarRecurso(esq[13],'D', 643, 648, 662, 706, t9,"T9","AutoRojo","14");
			
			//Desocupar cruce D14:
			verificarRecurso(esq[13],'D', 709, 714, 662, 706, t11,"T11","AutoRojo","14");
			
			//Ocupar y desocupar cruce C15 y ocupar cruce D15:
			verificarRecurso(esq[14],'D', 795, 800, 662, 706, t7, "T7","AutoRojo","15");
			verificarRecurso(esq[14],'D', 853, 858, 662, 706, t9,"T9","AutoRojo","15");
			
			//Desocupar cruce D15:
			verificarRecurso(esq[14],'D', 919, 924, 662, 706, t11,"T11","AutoRojo","15");
			
			//Ocupar y desocupar cruce C16 y ocupar cruce D16:
			verificarRecurso(esq[15],'D', 1005, 1010, 662, 706, t7, "T7","AutoRojo","16");
			verificarRecurso(esq[15],'D', 1063, 1068, 662, 706, t9,"T9","AutoRojo","16");
			
		}
	}
	
	//FUNCION
	public void verificarRecurso(Monitor esq, char direc,int xi, int xf, int yi, int yf,
		                         int []t,String nt,String na,String cruce)
	{
	//---------------------SI AUTO SUBE(o baja o va por izquierda o derecha)-----------------
		if((AR.getY()<=yf&&AR.getY()>=yi &&AR.getX()>=xi&&AR.getX()<=xf)
				&&(nt=="T0"||nt=="T5"||nt=="T7"||nt=="T10"|| nt=="T12"||nt=="T14"||nt=="T16"||nt=="T18"))
		{
			esq.extraerRecurso(t,nt,na,cruce);
		}
		
		if((AR.getY()<=yf&&AR.getY()>=yi &&AR.getX()>=xi&&AR.getX()<=xf)
				&&(nt!="T0"&&nt!="T5"&&nt!="T7"&&nt!="T10"&&nt!="T12"&&nt!="T14"&&nt!="T16"&&nt!="T18"))
		{
			esq.devolverRecurso(t,nt,na,cruce);
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
