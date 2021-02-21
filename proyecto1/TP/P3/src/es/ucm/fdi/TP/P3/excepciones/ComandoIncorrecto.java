package es.ucm.fdi.TP.P3.excepciones;

@SuppressWarnings("serial")
/**
 *Excepcion que salta cuando el usuario mete un comando incorrecto
 */
public class ComandoIncorrecto extends Exception{
	
	/**
	 * constructor de ComandoIncorrecto
	 * @param str
	 */
	public ComandoIncorrecto(String str){
		super(str);
	}
}
