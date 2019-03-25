;

public class Monitor {
	
	/*---------------------- VARIABLES DEL MONITOR---------------------*/
	private SemaforoBinario mutex;//Semaforo para q solo alla un hilo en sl monitor
	private CVSemaforo cond1;//semaforo para armar cola de trans con mayor prioridad
	private CVSemaforo cond2;//semaforo para armar cola de trans con menor prioridad
	private int cola1;// n de elementos en cola de transiciones con mayor prioridad
	private int cola2;// n de elementos en cola de transiciones con menor prioridad
	
	
	/*--------------------------- RED DE PETRI -------------------------*/
	//Matriz de incidencia:
	private int [][] I ={{-1,1,0,0,-1,1,0,0,0,0,0,0,0,0,0,0,1,0,-1,0},
						  {1,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,1,0},
						  {0,0,0,0,1,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						  {0,0,1,0,0,0,0,0,0,0,0,0,0,-1,1,0,0,0,-1,0},
						  {0,0,0,0,0,0,0,0,1,-1,0,0,0,0,0,0,0,0,0,0},
						  {0,1,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,1,0,-1},
						  {0,0,0,0,0,0,1,-1,0,0,0,0,0,0,0,0,0,0,0,0},
						  {0,0,0,-1,0,0,0,0,0,0,0,0,0,0,-1,1,0,0,0,1},
						  {0,0,0,0,0,0,0,0,0,0,1,-1,0,0,0,0,0,0,0,0},
						  {0,0,-1,0,0,0,0,0,-1,1,0,0,0,1,-1,0,0,0,1,0},
						  {0,-1,0,0,0,0,-1,1,0,0,0,0,1,0,0,0,0,-1,0,1},
						  {-1,0,-1,1,0,0,0,0,0,0,0,0,1,1,0,-1,1,-1,0,0},
						  {0,0,0,1,0,0,0,0,0,0,-1,1,0,0,1,-1,0,0,0,-1}}; // Matriz de Incidencia
	//Marcado Inicial:
	private int [] MA = {1,0,0,0,0,0,0,0,0,1,1,2,1};
	private int [] MN = {0,0,0,0,0,0,0,0,0,0,0,0,0};// Marcado Nuevo
	private String []NMA ={"A","AZA","AZA2","AZB","AZB2","AZC","AZC2","AZD","AZD2","B","C","C Paso","D"}; //Nombres de plazas del marcado Actual
	private String []NMA2 ={"A     ","AZA     ","AZA2     ","AZB     ","AZB2     ","AZC     ","AZC2     "
							,"AZD     ","AZD2     ","B     ","C     ","C Paso     ","D     "};
	
	//Nombres de transiciones:
	private String []T={"T0","T1","T10","T11","T12","T13","T14","T15","T16","T17","T18","T19","T2","T3","T4","T5","T6","T7","T8","T9"};
	
	public Monitor()
	{
		mutex = new SemaforoBinario(1);
		cond1=new CVSemaforo(mutex);
		cond2=new CVSemaforo(mutex);
		cola1=cola2=0;
				
		eout("------------------------------PROGRAMACION CONCURRENTE---------------------------------\n");
		eout("                                   TRABAJO FINAL \n\n\n");
		//Matriz de incidencia:
		eout("La matriz de incidencia de la Red de Petri es la siguiente:");
		eout(null); eout(null); // ---> dejo un renglon
		
		for(int k=0; k<I[0].length; k++)
		{if(k==0){eout("         ");}eout(T[k]+"  ");}
		eout(null);
		
		for(int i= 0; i< I.length ; i++)
		{
			for(int j=0; j < I[i].length; j++)
			{
				if(j==0){eout(NMA2[i]+"   ");eout(I[i][j]+ "   ");}
				else{eout(I[i][j]+ "   ");}
			}
			eout(null);
		}
		eout(null);
		
		//Marcado Inicial:
		eout("Marcado inicial:   -->   [");
		for(int k=0; k < MA.length; k++)
		{
			eout(MA[k] + " ");
			if(MA.length-1==k){eout(MA[k]);}
		}
		eout("]");
		eout(null);eout(null);
		eout("Se utilizara el siguiente formato, para asociar el nombre de cada plaza de la RdP \ncon sus respectivos tokens:");
		eout(null);
		for(int ii= 0; ii<MA.length ; ii++)
		{
			eout(NMA[ii]+"("+MA[ii]+")"+ "  ");
		}
		eout(null);eout(null);eout(null);
		
		
	}
	
	public int getCola2(){return cola2;}
	
	/*Metodo 2:
	post: posicion de transicion a disparar
	t[]: transicion a disparar
	e: nombre de transicion a disparar
	*/
	public void extraerRecurso(int []t, String e, String na, String cruce)
	{
		
		mutex.WAIT();//idem mutex.Wait de insertar
		
		while((puedodisparar(t)==false && na!="AutoRojo")){

				eout(na+ " esperando para ocupar cruce "+cruce+"...");eout(null);
				cola2+= 1;
				eout("Autos en cola de entrada al cruce "+cruce+": "+cola2);eout(null);eout(null);
				cond2.delay();
		}
		
		disparar(t, e, na, cruce);
		eout(na+" ocupo esquina...");
		eout(null);
		eout("Autos en cola de entrada al cruce "+ cruce+": "+cola2);eout(null);eout(null);
		
		if(cond1.empty()==false)
		{
			cond1.resume();
			cola1-=1;
		}
		
		mutex.SIGNAL();
	}
	
	public void devolverRecurso(int []t, String e, String na, String cruce)
	{
		//hacer condicion verdadera
		
		mutex.WAIT();
		
		while(puedodisparar(t)==false && na!="AutoRojo")
		{
			
				eout(na+ " esperando para ocupar cruce "+cruce+"...");eout(null);
				cola1+= 1;
				eout("Autos en cola de salida intermedia o final al cruce "+cruce+": "+cola1);eout(null);eout(null);
				cond1.delay();
		}
		
		disparar(t, e, na, cruce);
		eout(na+" ocupo esquina...");
		eout(null);
		eout("Autos en cola de salida intermedia o final al cruce "+cruce+": "+cola1);eout(null);eout(null);
		
		if(cond1.empty()==false)
		{
			cond1.resume();
			cola1-=1;
		}
		else
		{
			if((cond2.empty()==false))
			{
				cond2.resume();
				cola2-=1;
			}

		}
		
		mutex.SIGNAL();
	}
	
	public void eout(Object mensaje)
	{
		if(mensaje != null)
			System.out.print(mensaje);
		
		else
			System.out.println();
	}

	
	public synchronized boolean puedodisparar(int []t)
	{
		int ke =0;// cantidad de entradas a la transicion
		int []pe= new int[2];// posiciones de entrada de la transicion
		pe[0]= pe[1]= -1;
		boolean flag=false;
		int i=-1;
		
		//Busco la posicion de la transicion a disparar:
		for(int k=0; k<I[0].length; k++)
		{
			if(t[k]==1){i=k; break;}
		}
		
		for(int j=0; j< I.length; j++)
		{
			if(I[j][i] == -1 && ke>=0){pe[ke]=j; ke++;}
			
			if(j==(12)){flag = true;}
			
			if(flag == true && pe[0] != -1 && MA[pe[0]]>0)
			{
				if(pe[1] ==-1){return true;}
				
				if(pe[1]!= -1 && MA[pe[1]]>0){return true;}
				
				return false;	
			}
		}
		
		return false;
	}
	
	// eos: nombre de la transicion de entrda o salida
	public synchronized void disparar(int t[], String eos, String na, String cruce)
	{
		boolean flag = false;
		
		//Mostrar marcado actual:
		eout("Estado actual --> MA =   ");
		for(int ii= 0; ii<MA.length ; ii++)
		{
			eout(NMA[ii]+"("+MA[ii]+")"+ "  ");
		}
		eout(null);
		
		
		//Disparar:
		for(int i=0; i<I[0].length; i++)
		{
			if(t[i]==1 && puedodisparar(t) == true)
			{
				for(int j=0; j<I.length; j++)
				{
					MN[j] = MA[j] + I[j][i]*t[i];
				}
				
				//Clono vector MA a MN:
				for(int ii= 0; ii<I.length ; ii++)
				{
							MA[ii] = MN[ii];
				}
				flag=true;
			}
			
			if(flag==true)
			{
				eout(na+" disparo "+eos+" en el cruce "+cruce+"...");eout(null);
				break;
			}
			
			if(flag==false && t[i]==1)
			{eout(na+ " no pudo disparar "+eos+" en cruce "+cruce+"...");eout(null);}
		}
		
		//Mostrar marcado nuevo:
		
		eout("El marcado nuevo de la RdP es el siguiente:   \n");
		eout("--> MN =   ");
		for(int ii= 0; ii<MN.length ; ii++)
		{
			eout(NMA[ii]+"("+MN[ii]+")"+ "  ");
		}
		eout(null);
	}	
}
