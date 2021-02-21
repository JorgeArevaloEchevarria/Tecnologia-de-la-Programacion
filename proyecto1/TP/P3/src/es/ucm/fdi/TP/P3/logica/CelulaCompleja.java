package es.ucm.fdi.TP.P3.logica;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * clase donde ocurre el desarrollo y la informacion de la celula compleja
 *
 */
public class CelulaCompleja implements Celula{
	/**
	 * Numero de celulas maximo que puede comer una celula antes de explotar
	 */
	private static final int MAX_COMER = 3;
	
	/**
	 * Contador del numero de celulas que se ha comido
	 */
	private int comidas = 0;

	/**
	 * devuelve si se llegado al maximo de celulas comidas
	 * @return (comidas == MAX_COMER);
	 */

	public boolean explota(){
		return (comidas == MAX_COMER);
	}
	
	/**
	 * incrementa el numero de celulas comidas
	 */
	public void incrementarComidas(){
		comidas++;
	}
	
	/**
	 * realiza el movimiento de la celula
	 * @param f
	 * @param c
	 * @param superficie
	 * @return casilla
	 */
	public Casilla ejecutaMovimiento(int f, int c, Superficie superficie) {
		
		Casilla casilla = null;
		boolean comestible = false;
			
		int fila = (int) (Math.random()*superficie.getFilas());//devuelve una fila aleatoria
		int columna = (int) (Math.random()*superficie.getColumnas());//devuelve una columna aletoria
		
		if(!superficie.posicionLibre(fila, columna))//mira si la posicion esta libre
			comestible = superficie.comestible(fila, columna);//mira si esa celula se la puede comer
		
		if(superficie.posicionLibre(fila, columna) || comestible){
			
			System.out.println("Celula compleja en (" + f +","+ c + ")" + " se mueve a " + "(" + fila +","+ columna + ")");
			superficie.mueveCelula(f, c, fila, columna);//desplaza la celula
			casilla = new Casilla(fila,columna);//crea una casilla en la fila y columnas aleatorias
			
			if(comestible){//si es comestible
				incrementarComidas();//incrementa el numero de celulas comidas
				System.out.println("-- COME --");
			}else//si no ha podido comerse la celula
				System.out.println("-- NO COME --");
			
		}else//si no se ha podido mover
			System.out.println("La celula compleja de la posicion (" + f + "," + c +") no se ha podido mover");
		
		
		if(explota()){//si ha llegado al numero de MAX comidas
			superficie.quitarCelula(fila, columna);//elimina la celula
			System.out.println("La celula compleja de la posicion (" + fila + "," + columna +") ha explotado");
		}
		
		return casilla;
	}
	
	/**
	 * mira si la celula es comestible
	 * @return false
	 */
	public boolean esComestible() {
		return false;
	}
	
	/**
	 * @return "*"
	 */
	public String toString(){
		return "*";
	}
	
	/**
	 * guarda la celula
	 * @param fw
	 */
	public void guardar(FileWriter fw) {
		
		PrintWriter pw = new PrintWriter(fw);
		pw.print("compleja ");
		pw.print(comidas);

		
	}

	/**
	 * carga una celula
	 * @param sc
	 */
	public void cargar(Scanner sc) {
		comidas = sc.nextInt();
	}

}
