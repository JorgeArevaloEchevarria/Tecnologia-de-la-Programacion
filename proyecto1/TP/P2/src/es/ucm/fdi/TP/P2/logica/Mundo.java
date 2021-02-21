package es.ucm.fdi.TP.P2.logica;

import es.ucm.fdi.TP.P2.comando.ParserComandos;

public class Mundo {
	//mundo reinicia los pasos despues de reproducirse una celula
	private static final int NUM_CELULAS_S = 3;
	private static final int NUM_CELULAS_C = 2;
	private static final int NCOLUMNAS = 4;//para utitlizarlo en otra clase
	private static final int NFILAS = 5;//pongo el nombre de su clase.CONSTANTE
	private Superficie sup;
	private boolean simulacionTerminada = false;
	
	/**
	 * constructor de mundo
	 */
	public Mundo(){
		
		sup = new Superficie(NFILAS,NCOLUMNAS);
		iniciar();
	} 
	
	/**
	 * sale de la simulacion
	 */
	public void salir(){
		simulacionTerminada = true;
	}
	
	/**
	 * reinicia el tablero
	 */
	public void vaciar(){
		sup.reset();
	}
	
	/**
	 * inicia la simulacion
	 */
	public void iniciar(){
		  
		  vaciar();
		  
		  boolean creado=false;
		  int nc, nf;
		  int i = 0, j = 0;
		  
		  while(i<NUM_CELULAS_S){
		   
		   nc = (int) (Math.random()*NCOLUMNAS);//aleatorio para las columnas
		   nf = (int) (Math.random()*NFILAS);//aleatorio para las filas
		   creado = sup.ponerCelula(nf, nc,new CelulaSimple());
		    
		   if(creado)
		    i++;
		  
		  }
		  
		  while(j<NUM_CELULAS_C){
		   
		   nc = (int) (Math.random()*NCOLUMNAS);//aleatorio para las columnas
		   nf = (int) (Math.random()*NFILAS);//aleatorio para las filas
		   creado = sup.ponerCelula(nf, nc,new CelulaCompleja());
		    
		   if(creado)
		    j++;
		   
		  }   
	 }
	
	/**
	 * crea una celula simple en los parametros dados
	 * @param fila
	 * @param columna
	 * @return crear
	 */
	 public boolean crearCelulaSimple(int fila, int columna){
    
		 boolean crear = false;
		 
		 if(sup.ponerCelula(fila, columna, new CelulaSimple())){
			crear = true;
			System.out.print("Creamos nueva celula en la posicion: (" + fila +","+ columna + ")"+  "\n");
		   
		 }else
			System.out.print("No se ha podido crear la celula en la posicion: (" + fila +","+ columna + ")"+  "\n");
		   
		  return crear;
	}
	 
	 /**
	  * crea una celula compleja en los parametros dados
	  * @param fila
	  * @param columna
	  * @return crear
	  */
	 public boolean crearCelulaCompleja(int fila, int columna){
		    
		 boolean crear = false;
		 
		 if(sup.ponerCelula(fila, columna, new CelulaCompleja())){
			 crear = true;
			 System.out.print("Creamos nueva celula compleja en la posicion: (" + fila +","+ columna + ")"+  "\n");
		 }else
			 System.out.print("No se ha podido crear la celula compleja en la posicion: (" + fila +","+ columna + ")"+  "\n");
		 
		  return crear;
	}
	 
	 /**
	  * elimina una celula en los parametros dados
	  * @param fila
	  * @param columna
	  * @return eliminar
	  */
	 public boolean eliminarCelula(int fila, int columna){
		  
		  boolean eliminar = false;
		  
		  if(sup.quitarCelula(fila, columna)){
			  eliminar = true;
			  System.out.print("Eliminamos la celula de la posicion: (" + fila +","+ columna + ")" + "\n");
		  }else
			  System.out.print("No se ha podido eliminar la celula de la posicion: (" + fila +","+ columna + ")"+  "\n");
		
		  return eliminar;
	}
	
	 /**
	  * realiza el movimiento del tablero
	  */
	public void evoluciona(){
		
		sup.arrayBooleanos();
		
		for(int i = 0;i<NFILAS;i++)
			for(int j = 0;j < NCOLUMNAS;j++)
				if(sup.posicionMovida(i, j))
					this.sup.ejecutaMovimiento(i, j);
	}
	
	/**
	 * Termina la simulacion del programa
	 * @return salir
	 */
	public boolean esSimulacionTerminada(){
		boolean salir;
		
		if(simulacionTerminada)
			salir = true;
		else
			salir = false;
		
		return salir;
	}
	
	/**
	 * muestra la ayuda de todos los comandos
	 * @return ParserComandos.AyudaComandos()
	 */
	public String ayuda(){
		return ParserComandos.AyudaComandos();
	}
	
	/**
	 * muestra el tablero
	 * @return sup.toString()
	 */
	public String toString(){
		return sup.toString();
	}
	
}
