package es.ucm.fdi.TP.P3.logica;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

import es.ucm.fdi.TP.P3.excepciones.FaltaElemento;
import es.ucm.fdi.TP.P3.excepciones.IndicesFueraDeRango;

/**
 *Clase de superficie
 */
public class Superficie {

	private static final int MAX_LIBRES = 8;
	private Celula[][] superficie;
	private boolean[][] array;
	private int filas;
	private int columnas;
	
	/**
	 * constructor de superficie
	 * @param nf
	 * @param nc
	 */
	public Superficie (int nf,int nc){
		
		filas = nf;
		columnas = nc;
		
		superficie = new Celula[filas][columnas];
		array = new boolean[filas][columnas];
	}
	
	/**
	 * devuelve el atributo filas
	 * @return filas
	 */
	public int getFilas(){
		return filas;
	}
	
	/**
	 * devuelve el atributo columnas
	 * @return columnas
	 */
	public int getColumnas(){
		return columnas;
	}
	
	/**
	 * pone una celula en los parametros dados y devuelve si ha habido exito
	 * @param fila
	 * @param columna
	 * @param celula
	 * @return creado
	 */
	public boolean ponerCelula(int fila, int columna,Celula celula){
		  
		  boolean creado = false;
		  
		  if(posicionLibre(fila,columna)){//miramos si hay una celula en su posicion
		   creado = true;
		   superficie[fila][columna] = celula;
		  }
		  
		  return creado;
		 }
	
	/**
	 * quitamos la celula en los parametros dados
	 * @param fila
	 * @param columna
	 * @return eliminar
	 */
	public boolean quitarCelula(int fila, int columna){
		  
		  boolean eliminar = false;
		  
		  if(!posicionLibre(fila,columna)){
			   superficie[fila][columna] = null;//si la posicion no esta libre, la quitamos
			   eliminar=true;
		  }
		  
		  return eliminar;
	}
	
	/**
	 * resetea la superficie
	 * @param f
	 * @param c
	 */
	public void reset(int f, int c){
		for(int i = 0;i<f;i++)
			for(int j = 0;j<c;j++)
				superficie[i][j] = null;
	}
	
	/**
	 * reproduccion de una celula
	 * @param fila
	 * @param columna
	 * @param filaM
	 * @param columnaM
	 */
	public void reproducirCelula(int fila,int columna, int filaM, int columnaM){
		
		superficie[filaM][columnaM] = new CelulaSimple();//creamos una celula nueva (padre)
		superficie[fila][columna] = new CelulaSimple();//creamos una celula nueva  (hija)
		System.out.println("Movimiento de (" + fila +","+ columna + ")" + " a " + "(" + filaM +","+columnaM + ")");
		System.out.println("Nace una nueva celula en (" + fila +","+ columna + ")" + " cuyo padre ha sido (" + filaM +","+ columnaM + ")");
	
	}
	
	/**
	 * movimiento de una celula
	 * @param fila
	 * @param columna
	 * @param filaM
	 * @param columnaM
	 */
	public void mueveCelula(int fila,int columna,int filaM,int columnaM){
		
		superficie[filaM][columnaM] = superficie[fila][columna];//movemos la celula
		array[filaM][columnaM] = false;
		superficie[fila][columna] = null;// y eliminamos la otra
		
	}
	
	/**
	 * te devuelve si la posicion es valida
	 * @param fila
	 * @param columna
	 * @return valido
	 */
	public boolean posValida(int fila,int columna){
		boolean valido = false;
		
		if((fila>=0 && fila < filas) && (columna>=0 && columna < columnas))
			valido = true;
		
		return valido;
	}
	
	/**
	 * devuelve si la posicion esta libre
	 * @param fila
	 * @param columna
	 * @return (superficie[fila][columna] == null);
	 */
	public boolean posicionLibre(int fila, int columna){
		return (superficie[fila][columna] == null);
	}
	
	/**
	 * devuelve si la celula es comestible
	 * @param fila
	 * @param columna
	 * @return superficie[fila][columna].esComestible()
	 */
	public boolean comestible(int fila,int columna){
		return this.superficie[fila][columna].esComestible();
	}
	
	/**
	 * realiza el movimiento del tablero
	 * @param fila
	 * @param columna
	 * @return superficie[fila][columna].ejecutaMovimiento(fila, columna,this)
	 */
	public Casilla ejecutaMovimiento(int fila,int columna){
		return this.superficie[fila][columna].ejecutaMovimiento(fila, columna,this);
	}
	
	/**
	 * devuelve una casilla aleatoria que pertenezca a las libres
	 * @param fila
	 * @param columna
	 * @return arrayCasilla[(int)(Math.random()*contador)]
	 */
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
	
	/**
	 * creamos una array de boolean
	 */
	public void arrayBooleanos(){
		
		for(int i = 0;i<filas;i++)
			for(int j = 0;j<columnas;j++){//recorremos la superficie
				if(!posicionLibre(i,j))//miramos si esta libre
					array[i][j] = true;//ponemos true si esta libre
				else 
					array[i][j] = false;//y false en caso contrario
			}
	}
	
	/**
	 * devuelve a que esta el valor en el array de boolean
	 * @param fila
	 * @param columna
	 * @return (array[fila][columna])
	 */
	public boolean posicionMovida(int fila, int columna){
		return (array[fila][columna]);
	}
	
	/**
	 * muestra la superficie
	 * @return cadena
	 */
	public String toString(){
	     
	     String cadena="",cadena2 = "";
	     
	     for(int i = 0;i<filas;i++){
	    	 for(int j = 0;j<columnas;j++){
	    		 if(superficie[i][j]==null)
	    			 cadena=cadena+" - ";
	    		 else{
	    			 cadena2 = " " + superficie[i][j].toString()+" "; 
	    			 cadena=cadena+cadena2;
	    		 }
	    	 }
	    	 cadena=cadena+"\n";
	     }
	     return cadena;
	  }
	
	/**
	 * carga la superficie
	 * @param s
	 * @throws IndicesFueraDeRango
	 * @throws FaltaElemento 
	 */
	public void cargar(Scanner s) throws IndicesFueraDeRango,FaltaElemento{
		
		try{
			while(s.hasNextLine()){
				
				int x = s.nextInt();
				int y = s.nextInt();
				String tipo = s.next();
				tipo = tipo.toUpperCase();
	
				if(tipo.equals("SIMPLE"))
					ponerCelula(x,y,new CelulaSimple());
				else if (tipo.equals("COMPLEJA"))
					ponerCelula(x,y,new CelulaCompleja());
				
				superficie[x][y].cargar(s);
			}
		}catch(ArrayIndexOutOfBoundsException e){
			throw new IndicesFueraDeRango("");
		
		}catch(InputMismatchException e){
			throw new FaltaElemento("");
		}
	}
	
	/**
	 * guarda la superficie
	 * @param fw
	 */
	public void guardar(FileWriter fw){
		
		PrintWriter pw = new PrintWriter(fw);
		
		for(int i = 0;i<filas;i++)
			for(int j = 0;j<columnas;j++){
				if(!posicionLibre(i,j)){
					pw.println();
					pw.print(i + " ");
					pw.print(j + " ");
					superficie[i][j].guardar(fw);
				}
			}
	}
}