package es.ucm.fdi.TP.P3.logica;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *Clase donde se desarrolla la celulaSimple
 */
public class CelulaSimple implements Celula{
	
	/**
	 * Numero maximo de pasos que puede estar una celula sin moverse
	 */
	static final int MAX_PASOS_SIN_MOVER=1;
	
	/**
	 * Numero de pasos que tiene que hacer para reproducirse una celula
	 */
	static final int PASOS_REPRODUCCION=2;

	private int numPasos;
	private int numPasosQuieto;
	
	/**
	 * Constructor de celula
	 */
	public CelulaSimple(){
		numPasos = 0;
		numPasosQuieto = 0;
	}
	
	/**
	 * reinicia los pasos de la celula
	 */
	public void reiniciarPasos(){
		numPasosQuieto = 0;
	}
	
	/**
	 * incrementa los pasos dados
	 */
	public void contadorPasos(){
		numPasos++;
	}
	
	/**
	 * incrementa los pasos que ha estado quieto
	 */
	public void contadorQuieto(){
		numPasosQuieto++;
	}
	
	/**
	 * devuelve si la celula tiene que morir
	 * @return (numPasosQuieto == MAX_PASOS_SIN_MOVER);
	 */
	public boolean morir(){ 
		return (numPasosQuieto == MAX_PASOS_SIN_MOVER); 
	 }
	
	/**
	 * devuelve si la celula tiene que reproducirse
	 * @return (numPasos == PASOS_REPRODUCCION)
	 */
	public boolean reproducir(){
		return (numPasos == PASOS_REPRODUCCION);
	}
	
	/**
	 * muestra los valores de las celulas
	 * @return  "X"
	 */
	public String toString(){   
		return "X";
	 }
	
	/**
	 * incrementamos los pasos realizados por la celula
	 * @param fila
	 * @param columna
	 * @param mover
	 */
	public void incrementar(int fila,int columna,boolean mover){
		
		if(mover)
			contadorPasos();
		else
			contadorQuieto();
	}
	
	/**
	 * realiza el movimiento de la celula
	 * @param f
	 * @param c
	 * @param sup
	 * @return cas
	 */
	public Casilla ejecutaMovimiento(int f, int c, Superficie sup) {
		
		boolean libres = true;
		Casilla cas = null;
		
		cas = sup.getCasillasLibres(f,c);//mira que casillas hay libres en el tablero 
		
		if(cas == null)
			libres = false;
		
		if(libres){					
			if(reproducir())
				sup.reproducirCelula(f, c, cas.getX(), cas.getY());//reproduce la celula en esa fila y columna
			else{
				System.out.println("Movimiento de (" + f +","+ c + ")" + " a " + "(" + cas.getX() +","+ cas.getY() + ")");
				sup.mueveCelula(f, c, cas.getX(), cas.getY());//mueve la celula
				reiniciarPasos();//Reinicia los pasos despues de moverse
				incrementar(f,c,libres);//incrementa los pasos
			}
			
		}else if(morir() || reproducir()){

			if(morir())//muere por no moverse
				 System.out.println("Muere la celula de la casilla (" + f +","+ c + ") por inactividad");
			else if(reproducir())//muere por no reproducirse
				 System.out.println("Muere la celula de la casilla (" + f +","+ c + ") por no poder reproducirse");
			
			sup.quitarCelula(f, c);//quitas la celula
		}
		else
			incrementar(f, c,libres);//si no muere incrementas pasos quietos
	

		return cas;
	}
	

	/**
	 * devuelve si la celula se puede comer
	 * @return true
	 */
	public boolean esComestible() {
		return true;
	}

	/**
	 * guarda una celula
	 * @param fw
	 */
	public void guardar(FileWriter fw) {
		
		PrintWriter pw = new PrintWriter(fw);
		pw.print("simple ");
		pw.print(numPasos + " ");
		pw.print(numPasosQuieto);
	}

	/**
	 * carga una celula
	 * @param sc
	 */
	public void cargar(Scanner sc) {
		numPasos = sc.nextInt();
		numPasosQuieto = sc.nextInt();
	}

}
