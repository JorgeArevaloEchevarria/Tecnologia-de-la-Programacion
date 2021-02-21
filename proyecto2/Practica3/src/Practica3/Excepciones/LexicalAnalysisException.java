package Practica3.Excepciones;

@SuppressWarnings("serial")
/**
 * Excepcion que salta cuando se produce un error de fuente al parsear
 * @author jorge
 *
 */
public class LexicalAnalysisException extends Exception{
	public LexicalAnalysisException(String str){
		super(str);
	}

}
