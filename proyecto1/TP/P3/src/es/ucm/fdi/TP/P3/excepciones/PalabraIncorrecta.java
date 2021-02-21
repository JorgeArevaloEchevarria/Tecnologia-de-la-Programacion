package es.ucm.fdi.TP.P3.excepciones;

@SuppressWarnings("serial")
/**
 *Excepcion que salta cuando hay una palabra incorrecta en un archivo
 */
public class PalabraIncorrecta extends Exception{
	
	/**
	 * Constructor de PalabraIncorrecta
	 * @param str
	 */
	public PalabraIncorrecta(String str){
		super(str);
	}

}
