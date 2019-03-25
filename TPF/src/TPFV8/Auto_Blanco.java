package TPFV8;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Auto_Blanco extends Auto implements Runnable{
	
	private String id;
	private int vel;
	private boolean dormir;
	private Auto_Blanco []b;//Autos blancos
	private Auto_Azul []a;//Autos azules
	private int x, y, delta;
	private boolean cargado;
	private String ruta, aux;
	private char orientacion;
	private boolean vivo, avanzar, descargar;
	private Deposito deposito;
	private Auto_Rojo r;
	private Monitor [] e;
	
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
	
	
	
	public Auto_Blanco(int x, int y, Monitor []esq, String id, Deposito d, Auto_Blanco []ab, Auto_Azul []aa, Auto_Rojo ar)
	{
		avanzar=true;
		delta=1;
		this.id=id;
		this.x=x;
		this.y=y;
		deposito=d;
		e = esq;
		cargado=true;
		ruta="Data/Auto_B*C.png";
		vivo=true;
		dormir=false;//autos se duermen cuando se dispara una transicion(con false no se duermen)
		vel=5;
		b=ab;
		a=aa;
		r=ar;
	}
	
	public void Ciclo()
	{
		//Auto baja
		if((x==1050&&y>=45&&y<=420)||(x==420&&y>=420&&y<=630&&orientacion!='r'))
		{
			
			y+=delta;
			orientacion= 'd';
			aux=ruta.replace("*","D");
			if((x==420&&y>=420&&y<=630||x==1050&&y>=45&&y<=420)&&descargar)
			{
				aux=ruta.replace("*C", "DV");
				cargado=false;
			}
			else
				cargado=true;
			
			
			
			//Verifica si hay cola en el cruce 6:
			verificarCola(e[5], 1042, 1086, 30, 244);
			//Verifica si hay cola en el cruce 11:
			verificarCola(e[10], 1042, 1086, 240, 454);
			//Verifica si hay cola en el cruce 13:
			verificarCola(e[12], 415, 456, 450, 664);
			
			
			
			//Desocupar cruce C3:
			verificarRecurso(e[2],'B', 1042, 1087, 103, 103, t15, "T15",id, "3");
			
			//Ocupar y desocupar cruce A6 y ocupar cruce C6:
			verificarRecurso(e[5],'B', 1042, 1086, 168, 168, t0, "T0", id, "6");
			dormir('B', 1042, 1086, 168+40, 168+40);
			verificarRecurso(e[5],'B', 1042, 1086, 223, 223, t1, "T1", id, "6");
			dormir('B', 1042, 1086, 223+30, 223+30);
			
			//Desocupar cruce C6:
			verificarRecurso(e[5],'B', 1042, 1086, 288, 288, t2, "T2", id, "6");
			
			//Desocupar cruce A8 y ocupar y desocupar cruce C8:
			verificarRecurso(e[7],'B', 418, 456, 432, 432, t1, "T1", id, "8");
			dormir('B', 418, 456, 432+30, 432+30);
			verificarRecurso(e[7],'B', 418, 456, 498, 498, t2, "T2", id, "8");
			
			//Ocupar cruce A11:
			verificarRecurso(e[10],'B', 1042, 1086, 379, 379, t12, "T12", id, "11");
			dormir('B', 1042, 1086, 379+40, 379+40);
			
			//Ocupar cruce A13:
			verificarRecurso(e[12],'B', 415, 456, 588, 588, t12, "T12", id, "13");
			dormir('B', 415, 456, 588+40, 588+40);
			


		}
		
		// Izquierda
		if((y==630&&x>=255&&x<=420)||(y==420&&x<=1050&x>=420&&orientacion!='u'))
		{
			
			x-=delta;
			orientacion = 'l';
			aux=ruta.replace("*","L");
			if((y==630&&x>=335&&x<=420||y==420&&1050>=x&&x>=420)&&descargar)
			{
				aux=ruta.replace("*C", "LV");
				cargado=false;
			}
			else
				cargado=true;
			
			
			
			//Verifica si hay cola en el cruce 8:
			verificarCola(e[7], 454, 670, 415, 458);
			//Verifica si hay cola en el cruce 9:
			verificarCola(e[8], 664, 880, 415, 458);
			//Verifica si hay cola en el cruce 10:
			verificarCola(e[9], 874, 1090, 415, 458);
			//Verifica si hay cola en el cruce 12:
			verificarCola(e[11], 244, 480, 625, 666);
			
			
			
			//Desocupar cruce A11:
			verificarRecurso(e[10],'I', 1016, 1016, 414, 457, t13, "T13", id, "11");
			
			//Ocupar y desocupar cruce B10 y ocupar cruce A10:
			verificarRecurso(e[9],'I', 926, 926, 415, 458, t10, "T10", id, "10");
			dormir('I', 926-43, 926-43, 415, 458);
			verificarRecurso(e[9],'I', 871, 871, 415, 458, t8, "T8", id, "10");
			dormir('I', 871-30, 871-30, 415,458);
			
			//Devolver cruce A10:
			verificarRecurso(e[9],'I', 804, 804, 415, 458, t6, "T6", id, "10");
			
			//Ocupar y desocupar cruce B9 y ocupar cruce A9:
			verificarRecurso(e[8],'I', 716, 716, 415, 458, t10, "T10", id, "9");
			dormir('I', 716-43, 716-43, 415, 458);
			verificarRecurso(e[8],'I', 661, 661, 415, 458, t8, "T8", id, "9");
			dormir('I', 661-30, 661-30, 415,458);
			
			//Devolver cruce A9:
			verificarRecurso(e[8],'I', 594,594, 415, 458, t6, "T6", id, "9");
			
			//Ocupar y desocupar cruce B8 y ocupar cruce A8:
			verificarRecurso(e[7],'I', 506, 506, 415, 458, t10, "T10", id, "8");
			dormir('I', 506-43, 506-43, 415, 458);
			verificarRecurso(e[7],'I', 452, 452, 415, 458, t8, "T8", id, "8");
			dormir('I', 452-30, 452-30, 415, 458);
			
			//Desocupar cruce A13:
			verificarRecurso(e[12],'I', 385, 385, 626, 667, t13, "T13", id, "13");
			
			//Ocupar y cruce B12:
			verificarRecurso(e[11],'I', 296, 296, 625, 666, t16, "T16", id, "12");
			dormir('I', 296-40, 296-40, 625, 666);

		}
		
		//Sube
		if((x==255&&y<=630&&y>=465)||(x==675&&y<=465&&y>=255&&orientacion!='l')||(x==885&&y<=255&&y>=45))
		{
			y-=delta;
			orientacion = 'u';
			aux=ruta.replace("*","U");
			if(x==885&&y<=90&&y>=45)
			{					
				if(x==885&&y==90)
				{
					if(deposito.getCantidad()<12)
					{
						deposito.aumentar();
						descargar=true;
					}
					else
						descargar=false;
				}	
				if(descargar==true)
				{
					aux=ruta.replace("*C", "UV");
					cargado=false;	
				}
				if(descargar==false)
				{
					cargado=true;
				}
			}
			
			
			//Verifica si hay cola en el cruce 2:
			verificarCola(e[1], 872, 914, 36, 250);
			//Verifica si hay cola en el cruce 4:
			verificarCola(e[3], 663, 700, 246, 460);
			//Verifica si hay cola en el cruce 7:
			verificarCola(e[6], 244, 284, 456, 670);
			
			
			
			
			
			//Ocupar cruce D2
			verificarRecurso(e[1],'S', 872, 914, 86, 86, t18, "T18", id, "2");
			dormir('S', 872, 914, 86-40, 86-40);
			
			
			//Ocupar cruce D4
			verificarRecurso(e[3],'S',663, 700, 296, 296, t18, "T18", id, "4");
			dormir('S',665, 700, 296-40, 296-40);
			
			//Desocupar D5 y ocupar y desocupar B5:
			verificarRecurso(e[4],'S', 875, 915, 242, 242, t4, "T4", id, "5");
			dormir('S', 875, 915, 241-30, 241-30);
			verificarRecurso(e[4],'S', 875, 915, 176, 176, t3, "T3", id, "5");
			
			//Ocupar y desocupar cruce B9:
			verificarRecurso(e[8],'S',663, 705, 452, 452, t4, "T4", id, "9");
			dormir('S',663, 705, 452-30, 452-30);
			verificarRecurso(e[8],'S',663, 705, 384, 383, t3, "T3",id, "9");
			
			//Desocupar cruce B12:
			verificarRecurso(e[11],'S', 242, 284, 597, 597, t17, "T17", id, "12");
			
			//Ocupar cruce D7:
			verificarRecurso(e[6],'S', 244, 284, 506, 506, t18, "T18", id, "7");
			dormir('S', 244, 284, 506-40, 506-40);
			
		}
		
		//Derecha
		if((y==465&&x>=255&&x<=675&&orientacion!='d')||(x>=675&&x<=885&&y==255)||(y==45&&x>=885&&x<=1075))
		{
			
			x+=delta;
			orientacion = 'r';
			aux=ruta.replace("*","R");
			if(y==45&&x>=885&&x<=1050&&descargar)
			{					
				aux=ruta.replace("*C", "RV");
				cargado=false;				
			}
			else
				cargado=true;
			
			
			
			//Verifica si hay cola en el cruce 3:
			verificarCola(e[2], 870, 1084, 33, 77);
			//Verifica si hay cola en el cruce 5:
			verificarCola(e[4], 660, 874, 243, 290);
			//Verifica si hay cola en el cruce 8:
			verificarCola(e[7], 240, 454, 453, 496);
			//Verifica si hay cola en el cruce 9:
			verificarCola(e[8], 450, 664, 453, 496);
			
			
			//Desocupar cruce D2:
			verificarRecurso(e[1],'D' ,918, 918, 31, 77, t19, "T19",id, "2");
			
			//Ocupar cruce C3:
			verificarRecurso(e[2],'D', 1008, 1008, 33, 75, t14, "T14",id, "3");
			dormir('D', 1008+40, 1008+40, 33, 75);
			
			//Desocupar cruce D4:
			verificarRecurso(e[3],'D',708, 708, 242, 285, t19, "T19", id, "4");
			
			//Ocupar y desocupar cruce C5 y ocupar cruce D5:
			verificarRecurso(e[4],'D', 798, 798, 243, 290, t7, "T7",id, "5");
			dormir('D', 798+43, 798+43, 243, 290);
			verificarRecurso(e[4],'D', 851, 851, 243, 290, t9, "T9",id, "5");
			dormir('D', 852+30, 852+30, 243, 290);
			
			//Desocupar cruce D7:
			verificarRecurso(e[6],'D', 288, 288, 452,495, t19, "T19",id, "7");
			
			//Ocupar y desocupar cruce C8 y ocupar D8:
			verificarRecurso(e[7],'D', 378, 378, 453, 496, t7, "T7",id, "8");
			dormir('D', 378+43, 378+43, 453, 496);
			verificarRecurso(e[7],'D', 432, 432, 453, 496, t9, "T9",id, "8");
			dormir('D', 432+30, 432+30, 453, 496);
			
			//Ocupar y desocupar cruce C9:
			verificarRecurso(e[8],'D', 588, 588, 453, 496, t7, "T7",id, "9");
			dormir('D', 588+43, 588+43, 453, 496);
			verificarRecurso(e[8],'D', 643, 643, 453, 496, t9, "T9",id, "9");
			dormir('D', 643+30, 643+30, 453, 496);
			
			//Desocupar cruce D8:
			verificarRecurso(e[7],'D', 500, 500, 453, 496, t11, "T11",id, "8");
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
		int ixy=32;//superposicion sobre eje x o y (si baja o va por derecha)
		int ixyr=26;//superposicion sobre eje x o y (si baja o va por derecha)
		if(getOr()=='u' || getOr()=='l'){ixy=-32; ixyr=-26;} // idem (si sube o va por izquierda)
		
		//----------------------CUANDO AUTOS SUBEN O BAJAN----------------------------
		if(y>=yi && y<=yf && x>=xi && x<=xf &&(getOr()=='u'||getOr()=='d'))
		{
			for(int i =0; i<b.length; i++)
			{
				while(((b[i].getY()==(y+ixy) && b[i].getX()==x) && b[i].getID()!=getID() && b[i].getAvanzar()==false)
						|| ((a[i].getY()==(y+ixy) && a[i].getX()==x) && a[i].getID()!=getID()&&a[i].getAvanzar()==false) || (r.getY()==(y+ixyr) && r.getX()>=xi && r.getX()<=xf))
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
			for(int i =0; i<b.length; i++)
			{
				while(((b[i].getX()==(x+ixy)&& b[i].getY()==y) && b[i].getID()!=getID() && b[i].getAvanzar()==false)
						|| ((a[i].getX()==(x+ixy)&& a[i].getY()==y) && a[i].getID()!=getID() && a[i].getAvanzar()==false) || (r.getX()==(x+ixyr) && r.getY()>=yi && r.getY()<=yf))
				{
								
					if(r.getX()==(x+ixyr) && r.getY()>=yi && r.getY()<=yf)
					{r.setChoque(true);}
					avanzar(false);
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
		return new ImageIcon(aux).getImage();
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public char getOr()
	{
		return orientacion;
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
			Thread.sleep(10);
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
	
	public void setAvanzar(int a) {
		// TODO Auto-generated method stub
		delta=a;
	}

}
