package es.ucm.fdi.TP.P1.logica;

public class Superficie {

	private static final int MAX_LIBRES = 8;
	private Celula[][] superficie;
	private boolean[][] array;
	private int filas;
	private int columnas;
	
	public  Superficie (int nf,int nc){//constructor de superficie
		
		filas = nf;
		columnas = nc;
		
		superficie = new Celula[filas][columnas];
		array = new boolean[filas][columnas];
	}

	public boolean ponerCelula(int fila, int columna,Celula celula){//colocamos una celula en los parametros dados
		  
		  boolean creado = false;
		  
		  if(posicionLibre(fila,columna)){//miramos si hay una celula en su posicion
		   creado = true;
		   superficie[fila][columna] = celula;
		  }
		  
		  return creado;
		 }
	
	
	public boolean morir (int fila, int columna) {//comprueba si la celula tiene que morir
		  return superficie[fila][columna].morir();
	}
	
	public boolean reproducir(int fila,int columna){//comprueba si la celula se tiene que reproducir
		return superficie[fila][columna].reproducir();
	}
	
	public boolean quitarCelula(int fila, int columna){//quitamos la celula en los parametros dados
		  
		  boolean eliminar = false;
		  
		  if(!posicionLibre(fila,columna)){
			   superficie[fila][columna] = null;//si la posicion no esta libre, la quitamos
			   eliminar=true;
		  }
		  
		  return eliminar;
	}
	
	public void reset(){//resetea la superficie
		for(int i = 0;i<filas;i++)
			for(int j = 0;j<columnas;j++)
				superficie[i][j] = null;
	}
	
	public void reproducirCelula(int fila,int columna, int filaM, int columnaM){//reproduccion de una celula
		
		superficie[filaM][columnaM] = new Celula();//creamos una celula nueva (padre)
		superficie[fila][columna] = new Celula();//creamos una celula nueva  (hija)
		System.out.println("Movimiento de (" + fila +","+ columna + ")" + " a " + "(" + filaM +","+columnaM + ")");
		System.out.println("Nace una nueva celula en (" + fila +","+ columna + ")" + " cuyo padre ha sido (" + filaM +","+ columnaM + ")");
	
	}
	
	public void mueveCelula(int fila,int columna,int filaM,int columnaM){//movimiento de una celula
		
		incrementar(fila,columna,true);//incrementamos los pasos dados
		superficie[fila][columna].setPasosQuieto(0);//reseteamos los pasos quitos
		superficie[filaM][columnaM] = superficie[fila][columna];//movemos la celula
		superficie[fila][columna] = null;// y eliminamos la otra
		System.out.println("Movimiento de (" + fila +","+ columna + ")" + " a " + "(" + filaM +","+ columnaM + ")");
	}
	
	public boolean posValida(int fila,int columna){//te devuelve si la posicion es valida
		boolean valido = false;
		
		if((fila>=0 && fila < filas) && (columna>=0 && columna < columnas))
			valido = true;
		
		return valido;
	}
	
	public void incrementar(int fila,int columna,boolean mover){//incrementamos los pasos realizados por la celula
		
		if(mover)
			superficie[fila][columna].contadorPasos();
		else
			superficie[fila][columna].contadorQuieto();
	}
	
	
	public boolean posicionLibre(int fila, int columna){//devuelve si la posicion esta libre
		return (superficie[fila][columna] == null);
	}
	
	public Casilla getCasillasLibres(int fila, int columna){
		
		boolean valida,libre = false;
		Casilla[] arrayCasilla = new Casilla[MAX_LIBRES];//creamos el array de casillas
		int contador = 0;
		
		for(int i = fila-1;i<=fila+1;i++)
			for(int j = columna-1;j<=columna+1;j++){//recorremos la superficie
				valida = posValida(i,j);//miramos si nuestra celula tiene posiciones libres alrededor
				
				if(valida)
					libre = posicionLibre(i,j);//si la posicion es valida, mira si esta libre
				
				if(valida && libre){
					arrayCasilla[contador] = new Casilla(i,j);//si la posicion es valida y esta libre
					contador++;//incrementamos una casilla en el array y su contador
				}	
			}
			
		return arrayCasilla[(int)(Math.random()*contador)];//devolvemos una casilla aleatoria
		
	}
	
	public void arrayBooleanos(){//creamos una array de boolean
		
		for(int i = 0;i<filas;i++)
			for(int j = 0;j<columnas;j++){//recorremos la superficie
				if(!posicionLibre(i,j))//miramos si esta libre
					array[i][j] = true;//ponemos true si esta libre
				else 
					array[i][j] = false;//y false en caso contrario
			}
	}
	
	public boolean posicionMovida(int fila, int columna){
		return (array[fila][columna]);//devuelve a que esta el valor
	}
	
	

	public String toString(){//muestra la superficie 
	    
	    String cadena="",cadena2 = "";
	    
	    for(int i = 0;i<filas;i++){
	    	for(int j = 0;j<columnas;j++){
			      if(superficie[i][j]==null)
			        cadena=cadena+"  -  ";
			      else{
			        cadena2 = " " + superficie[i][j].toString()+" "; 
			        cadena=cadena+cadena2;
			      }
		     }
	     cadena=cadena+"\n";
	    }
	    cadena=cadena+"\n";
	    
	    return cadena;
	 }
}