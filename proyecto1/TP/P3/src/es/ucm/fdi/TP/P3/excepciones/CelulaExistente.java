package es.ucm.fdi.TP.P3.excepciones;

@SuppressWarnings("serial")
/**
 * Excepcion que salta cuando quieres introducir una celula donde hay una
 */
public class CelulaExistente extends Exception{
	/**
	 * constructor de CelulaExistente
	 * @param str
	 */
	public CelulaExistente(String str){
		super(str);
	}
}
