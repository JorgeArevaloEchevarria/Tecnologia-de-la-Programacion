package es.ucm.fdi.TP.P3.excepciones;

@SuppressWarnings("serial")
/**
 * Excepcion que salta cuando se espera un numero y no le entra un numero
 */
public class FormatoNumericoIncorrecto extends Exception{
	
	/**
	 * Constructor de FormatoNumericoIncorrecto
	 * @param str
	 */
	public FormatoNumericoIncorrecto(String str){
		super(str);
	}
}
