package es.ucm.fdi.TP.P3.logica;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

import es.ucm.fdi.TP.P3.comando.ParserComandos;
import es.ucm.fdi.TP.P3.excepciones.CelulaExistente;
import es.ucm.fdi.TP.P3.excepciones.FaltaElemento;
import es.ucm.fdi.TP.P3.excepciones.FormatoNumericoIncorrecto;
import es.ucm.fdi.TP.P3.excepciones.IndicesFueraDeRango;

/**
 *Clase que implementa el mundo
 */
public abstract class Mundo {
	//mundo reinicia los pasos despues de reproducirse una celula
	protected int ncolumnas;//para utitlizarlo en otra clase
	protected int nfilas;//pongo el nombre de su clase.CONSTANTE
	protected Superficie sup;

	
	/**
	 * constructor de mundo por defecto
	 */
	public Mundo(){
		sup = null;
		ncolumnas = 0;
		nfilas = 0;
	}
	
	/**
	 * constructor de mundo
	 * @param filas
	 * @param columnas
	 */
	public Mundo(int filas,int columnas){
		
		ncolumnas = columnas;
		nfilas = filas;
		
		inicializaMundo();
		
	}
	
	/**
	 * reinicia el tablero
	 */
	public void vaciar(){
		sup.reset(nfilas,ncolumnas);
	}
	
	/**
	 * inicia la simulacion
	 */
	abstract public void inicializaMundo();
	
	/**
	 * crea una celula
	 * @param fila
	 * @param columna
	 * @throws IndicesFueraDeRango
	 * @throws CelulaExistente
	 * @throws FormatoNumericoIncorrecto
	 */
	abstract public void crearCelula(int fila, int columna) 
			throws IndicesFueraDeRango, CelulaExistente, FormatoNumericoIncorrecto;
	 
	/**
	 * te devuelve un string con el tipo del mundo
	 * @return string
	 */
	abstract public String tipoMundo();
	 
	/**
	 * Carga el mundo
	 * @param s
	 * @throws FaltaElemento
	 * @throws IndicesFueraDeRango
	 */
	public void cargar(Scanner s) throws FaltaElemento, IndicesFueraDeRango{
		
		try{
			int filas = s.nextInt();
			int columnas = s.nextInt();
		
			nfilas = filas;
			ncolumnas = columnas;
		
			sup = new Superficie(filas,columnas);
			sup.cargar(s);
			
		}catch(InputMismatchException e){
			throw new FaltaElemento("");
		}

	 }
	 
	/**
	 * guarda el mundo
	 * @param fw
	 */
	 public void guardar(FileWriter fw){
		 
			PrintWriter pw = new PrintWriter(fw);
			
			pw.println(tipoMundo());
			pw.println(nfilas);
			pw.print(ncolumnas);
	
			sup.guardar(fw);
	 }
	  /**
	  * elimina una celula en los parametros dados
	  * @param fila
	  * @param columna
	  * @throws CelulaExistente 
	  * @throws IndicesFueraDeRango 
	  */
	 public void eliminarCelula(int fila, int columna) throws CelulaExistente, IndicesFueraDeRango{
		  
		 try{
			 if(sup.quitarCelula(fila, columna))
				 System.out.print("Eliminamos la celula de la posicion: (" + fila +","+ columna + ")" + "\n");
			 else
				 throw new CelulaExistente("");
			 
		}catch(ArrayIndexOutOfBoundsException e){
				throw new IndicesFueraDeRango("");
		}
	 }
	
	 /**
	  * realiza el movimiento del tablero
	  */
	public void evoluciona(){
		
		sup.arrayBooleanos();
		
		for(int i = 0;i<nfilas;i++)
			for(int j = 0;j < ncolumnas;j++)
				if(sup.posicionMovida(i, j))
					this.sup.ejecutaMovimiento(i, j);
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
		
		String str = "";
		
		if(sup != null)
		  str = sup.toString();
		else
			str = "No hay superficie" + '\n';
		
		return str;
	}
	
}
