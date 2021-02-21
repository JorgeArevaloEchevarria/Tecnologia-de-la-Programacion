package es.ucm.fdi.TP.P3.logica;

import java.io.FileWriter;
import java.util.Scanner;

/**
 *Interfaz de Celula
 */
public interface Celula {
	
	/**
	 * Ejecuta el movimiento de una celula
	 * @param f
	 * @param c
	 * @param superficie
	 * @return
	 */
	public abstract Casilla ejecutaMovimiento(int f, int c, Superficie superficie);
	
	/**
	 * devuelve si es comestible una celula
	 * @return boolean
	 */
	public abstract boolean esComestible();
	
	/**
	 * guarda una celula
	 * @param fw
	 */
	public abstract void guardar(FileWriter fw);
	
	/**
	 * carga una celula
	 * @param sc
	 */
	public abstract void cargar(Scanner  sc);
	
}
