package es.ucm.fdi.TP.P3.excepciones;

@SuppressWarnings("serial")
/**
 *Excepcion que salta cuando una superficie no puede soportar las celulas que le indican
 */
public class ErrorDeInicializacion extends Exception{
	
	/**
	 * Constructor de ErrorDeInicializacion
	 * @param str
	 */
	public ErrorDeInicializacion(String str){
		super(str);
	}
}
