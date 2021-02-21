package es.ucm.fdi.TP.P3.excepciones;

@SuppressWarnings("serial")
/**
 *Excepcion que salta cuando falta un elemento en el archivo del que se carga
 */
public class FaltaElemento extends Exception{
	
	/**
	 * Constructor de FaltaElemento
	 * @param str
	 */
	public FaltaElemento(String str){
		super(str);
	}

}
