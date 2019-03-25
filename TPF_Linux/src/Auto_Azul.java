;
import java.awt.Image;

import javax.swing.ImageIcon;



public class Auto_Azul extends Auto implements Runnable{
	
	private String id;
	private int vel;
	private int x, y, delta;
	boolean dormir;
	private Auto_Azul a[];
	private Auto_Blanco []b;
	private boolean cargado;
	private String ruta, aux;
	private char orientacion;
	private boolean vivo, avanzar, cargar;
	private Image imagen;
	private Deposito deposito;
	private Auto_Rojo r;
	private boolean choque;
	
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
	
	private Monitor []e;

	public Auto_Azul(int x, int y, Monitor []esq, String id, Deposito d, Auto_Azul []aa, Auto_Blanco []ab, Auto_Rojo ar)
	{
		this.id=id;
		this.x=x;
		this.y=y;
		e = esq; 
		ruta="Data/Auto_A*V.png";
		vivo=true;
		delta=1;
		avanzar=true;
		deposito=d;
		cargado=false;
		dormir=false;//autos se duermen cuando se dispara una transicion(con false no se duermen)
		vel=5;
		a =aa;
		b=ab;
		r=ar;
		choque=false;
	}
	
	public void Ciclo()
	{
	
		//IZQUIERDA
		if((y==210&&x<=1095&x>=885)||(y==0&&x<=885&&x>=630)||(y==420&&x<=840&&x>=630))
		{												
				
				x-=delta;
				orientacion='l';
				aux=ruta.replace("*","L");
				if((x>=630&&x<=885&&y==0||y==420&&x<=840&&x>=770)&&cargar)
				{
					aux=ruta.replace("*V", "LC");
					cargado=true;
				}
				else
					cargado=false;
			
			
			
			//Verifica si hay cola en el cruce 1:
			verificarCola(e[0], 664, 880, 0, 38);
			//Verifica si hay cola en el cruce 5:
			verificarCola(e[4], 874, 1090, 205, 248);
			//Verifica si hay cola en el cruce 9:
			verificarCola(e[8], 664, 880, 415, 456);
			
			
			
			
			//Ocupar y desocupar cruce B1 y ocupar A1:
			verificarRecurso(e[0],'I', 716, 716, 0, 38, t10, "T10", id, "1");
			dormir('I', 716-43, 716-43, 0, 38);
			verificarRecurso(e[0],'I', 663, 663, 0, 38, t8, "T8", id, "1");
			dormir('I', 663-30, 663-30, 0, 38);
			
			//Desocupar cruce B2 y ocupar y desocupar cruce A2:
			verificarRecurso(e[1],'I', 871, 871, 0, 38, t8, "T8", id, "2");
			dormir('I', 871-25, 871-25, 0, 38);
			verificarRecurso(e[1],'I', 805, 805, 0, 38, t6, "T6", id, "2");
			
			//Desocupar cruce B6 y ocupar y desocupar cruce A6:
			verificarRecurso(e[5],'I', 1082, 1082, 205, 248, t8, "T8", id, "6");
			dormir('I', 1082-30, 1082-30, 205, 248);
			verificarRecurso(e[5],'I', 1064, 1064, 205, 248, t6, "T6", id, "6");
			
			//Ocupar cruce B5:
			verificarRecurso(e[4],'I', 926, 926, 205, 248, t10, "T10", id, "5");
			dormir('I', 926-40, 926-40, 205, 248);
			
			//Ocupar y desocupar cruce B9 y ocupar A9:
			verificarRecurso(e[8],'I', 716, 716, 415, 456, t10, "T10", id, "9");
			dormir('I', 716-43, 716-43, 415, 456);
			verificarRecurso(e[8],'I', 666, 666, 415, 456, t8, "T8", id, "9");
			dormir('I', 666-30, 666-30, 415, 456);
			
			//Desocupar cruce A10:
			verificarRecurso(e[9],'I', 805, 805, 414, 457, t6, "T6", id, "10");
		}
		
		//BAJA
		if((x==630&&y>=0&&y<=255)||(x==840&&y>=255&&y<=420)||(x==630&&y>=420&&y<=675))
		{	
			
			y+=delta;
			orientacion='d';
			aux=ruta.replace("*","D");
			if((x==630&&y>=0&&y<=255||x==840&&y>=255&&y<=420)&&cargar)
			{
				aux=ruta.replace("*V", "DC");
				cargado=true;
			}
			else
				cargado=false;
			
			
			
			//Verifica si hay cola en el cruce 4:
			verificarCola(e[3], 614, 665, 30, 210);
			//Verifica si hay cola en el cruce 10:
			verificarCola(e[9], 833, 877, 240, 454);
			//Verifica si hay cola en el cruce 14:
			verificarCola(e[13], 625, 666, 450, 664);
			
			
			
			//Desocupar cruce A1 y ocupar y desocupar cruce C1:
			verificarRecurso(e[0],'B', 625, 666, 13, 13, t1, "T1", id, "1");
			dormir('B', 625, 666, 13+28, 13+28);
			verificarRecurso(e[0],'B', 625, 666, 78, 78, t2, "T2", id, "1");
			
			//Ocupar y desocupar cruce A4 y ocupar cruce C4:
			verificarRecurso(e[3],'B', 614, 665, 169, 169, t0, "T0", id, "4");
			dormir('B', 614, 665, 169+43, 169+43);
			verificarRecurso(e[3],'B', 614, 665, 222, 222, t1, "T1", id, "4");
			dormir('B', 614, 665, 222+30, 222+30);
			
			//Desocupar cruce C5:
			verificarRecurso(e[4],'B', 835, 876, 295, 295, t2, "T2", id,"5");
			
			//Desocupar cruce A9 y ocupar y desocupar cruce C9:
			verificarRecurso(e[8],'B', 622, 670, 429, 429, t1, "T1", id, "9");
			dormir('B', 622, 670, 429+37, 429+37);
			verificarRecurso(e[8],'B', 622, 670, 493, 493, t2, "T2", id, "9");
			
			//Ocupar cruce A10:
			verificarRecurso(e[9],'B', 833, 877, 377, 377, t0, "T0", id,"10");
			dormir('B',833, 877,377+43, 377+43);
			
			//Ocupar y desocupar cruce A14 y ocupar C14:
			verificarRecurso(e[13],'B', 625, 666, 587, 587, t0, "T0", id, "14");
			dormir('B', 625, 666, 587+43, 587+43);
			verificarRecurso(e[13],'B', 625, 666, 639, 639, t1, "T1", id, "14");
			dormir('B', 625, 666, 639+28, 639+28);
				
			
			
		}
		
		//DERECHA
		if((y==675&&x>=630&&x<=1095)||(y==255&&x>=630&&x<=840))
		{
			
			x+=delta;
			orientacion='r';
			aux=ruta.replace("*","R");
			if((y==255&&x>=630&&x<=840)&&cargar)
			{
				aux=ruta.replace("*V", "RC");
				cargado=true;
			}
			else
				cargado=false;
			
			
			
			//Verifica si hay cola en el cruce 5:
			verificarCola(e[4], 660, 874, 242, 286);
			//Verifica si hay cola en el cruce 15:
			verificarCola(e[14], 660, 874, 663, 700);
			//Verifica si hay cola en el cruce 16:
			verificarCola(e[15], 870, 1084, 663, 700);
			
			
			
			
			//Desocupar cruce C4 y ocupar y desocupar cruce D4:
			verificarRecurso(e[3],'D', 643, 643, 242, 286, t9, "T9", id, "4");
			dormir('D', 643+27, 643+27, 244, 270);
			verificarRecurso(e[3],'D', 708, 708, 242, 286, t11, "T11", id, "4");
			
			//Ocupar cruce C5:
			verificarRecurso(e[4],'D', 797, 797, 242, 286, t7, "T7", id,"5");
			dormir('D', 797+43, 797+43, 242, 286);
			
			//Desocupar cruce C14 y ocupar y desocupar cruce D14:
			verificarRecurso(e[13],'D', 640, 640, 663, 700, t9, "T9", id, "14");
			dormir('D', 640+33, 640+33, 663, 700);
			verificarRecurso(e[13],'D', 702, 702, 663, 700, t11, "T11", id, "14");
			
			//Ocupar y desocupar cruce C15 y ocupar cruce D15:
			verificarRecurso(e[14],'D', 797, 797, 663, 700, t7, "T7", id, "15");
			dormir('D', 797+43, 797+43, 663, 700);
			verificarRecurso(e[14],'D', 849, 849, 663, 700, t9, "T9", id, "15");
			dormir('D', 849+30, 849+30, 663, 700);
			
			//Desocupar cruce D15:
			verificarRecurso(e[14],'D', 918, 918, 663, 700, t11, "T11", id, "15");
			
			//Ocupar y desocupar cruce C16 y ocupar cruce D16:
			verificarRecurso(e[15],'D', 1007, 1007, 663, 700, t7, "T7", id, "16");
			dormir('D', 1007+43, 1007+43, 663, 700);
			verificarRecurso(e[15],'D', 1059, 1059, 663, 699, t9, "T9", id, "16");
			dormir('D', 1056+30, 1056+30, 663, 699);
			
			
		}
		
		//SUBE
		if((x==1095&&y>=210&&y<=675)||(x==885&&y<=210&&y>=0))
		{
			
			y-=delta;
			orientacion='u';
			aux=ruta.replace("*","U");
			
			if(x==885&&y<=90&&y>=0)
			{
				if(x==885&&y==90)
				{
					if(deposito.getCantidad()>0)
					{
						deposito.disminuir();	
						cargar=true;
					}
					else
						cargar=false;
				}
				if(cargar)
				{
					aux=ruta.replace("*V", "UC");
					cargar=true;
				}
				else
				{
					cargado=false;
				}					
			}
			
			
			
			//Verifica si hay cola en el cruce 2:
			verificarCola(e[1], 872, 915, 36, 250);
			//Verifica si hay cola en el cruce 6:
			verificarCola(e[5], 1082, 1119, 246, 460);
			//Verifica si hay cola en el cruce 11:
			verificarCola(e[10], 1082, 1119, 456, 670);
			
			
			
			//Ocupar y desocupar cruce D2 y ocupar y desocupar cruce B2:
			verificarRecurso(e[1],'S', 872, 915, 86, 86, t5, "T5", id, "2");
			dormir('S', 872, 915, 86-43, 86-43);
			verificarRecurso(e[1],'S', 872, 915, 32, 32, t4, "T4", id, "2");
			dormir('S', 872, 915, 32-23, 32-23);
			
			//Desocupar cruce B5:
			verificarRecurso(e[4],'S', 872, 915, 176, 176, t3, "T3", id, "5");
			
			//Ocupar y desocupar cruce D6 y ocupar cruce B6:
			verificarRecurso(e[5],'S', 1082, 1119, 296, 296, t5, "T5", id, "6");
			dormir('S', 1082, 1119, 296-43, 296-43);
			verificarRecurso(e[5],'S', 1082, 1119, 252, 252, t4, "T4", id, "6");
			dormir('S', 1082, 1119, 252-30, 252-30);
			
			//Ocupar y desocupar cruce D11 y ocupar cruce B11:
			verificarRecurso(e[10],'S', 1082, 1119, 506, 506, t5, "T5", id, "11");
			dormir('S', 1082, 1119, 506-43, 506-43);
			verificarRecurso(e[10],'S', 1082, 1119, 452, 452, t4, "T4", id, "11");
			dormir('S', 1082, 1119, 452-30, 452-30);
			
			//Desocupar cruce B11:
			verificarRecurso(e[10],'S', 1082, 1119, 437, 437, t3, "T3", id, "11");
			
			//Desocupar cruce D16 y ocupar y desocupar cruce B16:
			verificarRecurso(e[15],'S', 1082, 1119, 662, 662, t4, "T4", id, "16");
			dormir('S', 1082, 1119, 662-28, 662-28);
			verificarRecurso(e[15],'S', 1082, 1119, 595, 595, t3, "T3", id, "16");
			
			
		}
		
	}
	
	
	public String getID(){return id;}
	
	
	/*Funcion verificar cola en los cruces:
	Esta funcion verifica si en cualquiera de los cruces hay cola.
	Parametros:
	Monitor e: esquina en la cual se verifica si hay cola.
	yi e yf: coordenadas en y de la calle a verificar (limites en pixeles).
	xi e xf: coordenadas en x de la calle a verificar (limites en pixeles).
	
	*/
	public void verificarCola(Monitor e, int xi, int xf, int yi, int yf)
	{
		int ixy = 32;//superposicion sobre eje x o y (si baja o va por derecha)
		int ixyr = 26;
		if(getOr()=='u' || getOr()=='l'){ixy = -32; ixyr = -26;} // idem (si sube o va por izquierda)
		
		//----------------------CUANDO AUTOS SUBEN O BAJAN----------------------------
		if(y>=yi && y<=yf && x>=xi && x<=xf &&(getOr()=='u'||getOr()=='d'))
		{
			for(int i =0; i<a.length; i++)
			{
				while(((a[i].getY()==(y+ixy) && a[i].getX()==x) && a[i].getID()!=getID() && a[i].getAvanzar()==false)
						|| ((b[i].getY()==(y+ixy) && b[i].getX()==x) && b[i].getID()!=getID()&&b[i].getAvanzar()==false) || (r.getY()==(y+ixyr) && r.getX()>=xi && r.getX()<=xf))
				{
							
						avanzar(false);
						if(r.getY()==(y+ixyr) && r.getX()>=xi && r.getX()<=xf)
						{r.setChoque(true);}
						try {
								Thread.sleep(1000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}		
				}
				avanzar(true);
			}
		}
		
		//-----------------CUNADO AUTOS VAN POR DERECHO O IZQUIERDA---------------------
		if(x>=xi && x<=xf && y>=yi && y<=yf && (getOr()=='r'||getOr()=='l'))
		{
			for(int i =0; i<a.length; i++)
			{
				while(((a[i].getX()==(x+ixy)&& a[i].getY()==y) && a[i].getID()!=getID() && a[i].getAvanzar()==false)
						|| ((b[i].getX()==(x+ixy)&& b[i].getY()==y) && b[i].getID()!=getID()&& b[i].getAvanzar()==false) || (r.getX()==(x+ixyr) && r.getY()>=yi && r.getY()<=yf))
					{
								
								avanzar(false);
								if(r.getX()==(x+ixyr) && r.getY()>=yi && r.getY()<=yf)
								{r.setChoque(true);}
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}		
					}
				avanzar(true);
			}
		}
	}
	
	
	//Funcion dormir: duerme el auto para simular el disparo de una transicion.
	//con boolean dormir se puede desactivar o activar esta opcion 
	//(true:activar ; false:desactivar).
	public void dormir(char direc, int xi, int xf, int yi, int yf)
	{
		if(dormir==true&&(direc=='D'||direc=='I')&&x==xi && y<=yf && y>=yi)
		{
			try {
				Thread.sleep(250);
			} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		}
		
		if(dormir==true&&(direc=='S'||direc=='B')&&y==yi && x<=xf && x>=xi)
		{
			try {
				Thread.sleep(250);
			} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		}
	}
	
	//FUNCION
	public void verificarRecurso(Monitor e,char direc,int xi, int xf, int yi, int yf,
			                      int []t,String nt,String na, String cruce)
	{
		//---------------------------------SI AUTO SUBE O BAJA------------------------------------------

		if((direc=='S'||direc=='B')&&(nt=="T0"||nt=="T5"||nt=="T7"||nt=="T10" || nt=="T12"||nt=="T14"||nt=="T16"||nt=="T18")&& y==yi&& x<=xf && x>=xi)
		{
			avanzar(false);
			e.extraerRecurso(t,nt,na,cruce);
			avanzar(true);
		}

		if((direc=='S'||direc=='B')&&(nt!="T0"&&nt!="T5"&&nt!="T7"&&nt!="T10"&&nt!="T12"&&nt!="T14"&&nt!="T16"&&nt!="T18")&& y==yi&& x<=xf && x>=xi)
		{
			avanzar(false);
			e.devolverRecurso(t,nt,na,cruce);
			avanzar(true);
		}
		//---------------------SI AUTO VA POR DERECHA O IZQUIERDA---------------------------------
		
		if((direc=='D'||direc=='I')&&(nt!="T0"&&nt!="T5"&&nt!="T7"&&nt!="T10"&&nt!="T12"&&nt!="T14"&&nt!="T16"&&nt!="T18")&&x==xi && y<=yf && y>=yi)
		{
			avanzar(false);
			e.devolverRecurso(t,nt,na,cruce);
			avanzar(true);
		}
		
		if((direc=='D'||direc=='I')&&(nt=="T0"||nt=="T5"||nt=="T7"||nt=="T10" || nt=="T12"||nt=="T14"||nt=="T16"||nt=="T18")&&x==xi && y<=yf && y>=yi)
		{
			avanzar(false);
			e.extraerRecurso(t,nt,na,cruce);
			avanzar(true);
		}
	}
	
	
	public Image getImagen()
	{
		imagen=new ImageIcon(aux).getImage();
		return imagen;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	

	public void matarHilo()
	{
		vivo=false;
		x=-1000;
	}
	
	public void setCargado(boolean c)
	{
		cargado =c;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(vivo)
		{
			/*Ciclo();
			try{
                Thread.sleep(vel);
            }catch(InterruptedException err){
                System.out.println(err);
            }*/
			
			try{
				if(avanzar==true)
				Ciclo();
                Thread.sleep(vel);
            }catch(InterruptedException err){
                System.out.println(err);
            }
		}
	}
	
	@Override
	public char getOr() {
		// TODO Auto-generated method stub
		return orientacion;
	}
	
	
	@Override 
	public void setRuta(String a, String b)
	{ // TODO Auto-generated method stub
		aux=aux.replace(a, b);
	}

	@Override
	public void setCoords(int x, int y) {
		// TODO Auto-generated method stub
		this.x=x;
		this.y=y;
	}

	@Override
	public boolean getCargado() {
		// TODO Auto-generated method stub
		return cargado;
	}

	@Override
	public void esperar() {
		// TODO Auto-generated method stub
		try
		{
			Thread.sleep(1000);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean getAvanzar(){return avanzar;}

	@Override
	public void avanzar(boolean a) {
		// TODO Auto-generated method stub
		avanzar=a;
	}

	@Override
	public void setAvanzar(int a) {
		// TODO Auto-generated method stub
		delta=a;
	}

}
