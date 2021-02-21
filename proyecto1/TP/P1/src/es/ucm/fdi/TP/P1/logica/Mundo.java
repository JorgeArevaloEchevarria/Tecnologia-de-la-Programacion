package es.ucm.fdi.TP.P1.logica;

public class Mundo {

	private static final int NUM_CELULAS = 6;
	private static final int NCOLUMNAS = 4;
	private static final int NFILAS = 3;
	private Superficie sup;
	
	public Mundo(){//constructor de mundo
		
		sup = new Superficie(NFILAS,NCOLUMNAS);
		iniciar();
		
	} 
	
	public void vaciar(){//vaciamos llamando al reset de superficie
		sup.reset();
	}
	
	public void iniciar(){
		
		vaciar();//vaciamos la superficie
		
		boolean creado;
		int nc, nf;
		
		for(int i=0;i<NUM_CELULAS;i++){
			
			nc = (int) (Math.random()*NCOLUMNAS);//aleatorio para las columnas
			nf = (int) (Math.random()*NFILAS);//aleatorio para las filas
			
			creado = sup.ponerCelula(nf, nc,new Celula());
			
			while(!creado){
				
				nc = (int) (Math.random()*NCOLUMNAS);//aleatorio para las columnas
				nf = (int) (Math.random()*NFILAS);//aleatorio para las filas
				
				creado = sup.ponerCelula(nf, nc,new Celula());//comprueba si se puede poner una celula en la posicion
			}
			
		}
		
	}
		
	 public boolean crearCelula(int fila, int columna){//comprueba si se puede crear la celula
		    
		  boolean crear = false;
		  if(sup.ponerCelula(fila, columna, new Celula()))
		   crear = true;
		   
		  return crear;
		  
		 }
	
	 public boolean eliminarCelula(int fila, int columna){//comprueba si se puede eliminar la celula
		  
		  boolean eliminar = false;
		  if(sup.quitarCelula(fila, columna))
		   eliminar = true;
		  
		  return eliminar;
	}
	 
	 
	 
	public void evoluciona(){
		
		boolean libres;
		Casilla cas;
		sup.arrayBooleanos();
		
		for(int i = 0;i<NFILAS;i++)
			for(int j = 0;j < NCOLUMNAS;j++){
				libres = true;
				if(sup.posicionMovida(i, j)){//mira si tiene celula en la pos
					cas = sup.getCasillasLibres(i,j);//comprueba las poscicones cercanas a la casilla
					
					if(cas == null)//mira si hay alguna casilla
						libres = false;
					
					if(libres){//si hay casilla
						
						if(sup.reproducir(i, j))//comprueba si la celula se tiene que reproducir
							sup.reproducirCelula(i, j, cas.getX(), cas.getY());//reproduce la celula
						else
							sup.mueveCelula(i, j, cas.getX(), cas.getY());//mueve celula
						
					}else if(sup.morir(i, j) || sup.reproducir(i, j)){//muere la celula
		
						if(sup.morir(i, j))
							 System.out.println("Muere la celula de la casilla (" + i +","+ j + ") por inactividad");
						else if(sup.reproducir(i, j))
							 System.out.println("Muere la celula de la casilla (" + i +","+ j + ") por no poder reproducirse");
						
						sup.quitarCelula(i, j);
					}
					else
						sup.incrementar(i, j,libres);
				}
				
			}
	}
	
	
	public String toString(){
		  
		  String cadena="";
		  cadena = sup.toString();
		  
		  return cadena;
	}
	
}
