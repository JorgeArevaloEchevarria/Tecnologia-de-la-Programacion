package es.ucm.fdi.TP.P3.excepciones;

@SuppressWarnings("serial")
/**
 * Excepcion que salta cuando se sale del rango de un array
 */
public class IndicesFueraDeRango extends Exception{
	
	/**
	 * Constructor de IndicesFueraDeRango
	 * @param str
	 */
	public IndicesFueraDeRango(String str){
		super(str);
	}
	
}
