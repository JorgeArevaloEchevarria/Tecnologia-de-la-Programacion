package Practica3.Excepciones;

@SuppressWarnings("serial")
/**
 * excepcion que salta cuando la sintaxis del bytecode es incorrecta
 * @author jorge
 *
 */
public class BadFormatByteCode extends Exception{
	public  BadFormatByteCode(String str){
		super(str);
	}

}
