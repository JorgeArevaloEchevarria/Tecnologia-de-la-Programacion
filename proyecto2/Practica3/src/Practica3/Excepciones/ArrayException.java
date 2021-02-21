package Practica3.Excepciones;

/**
 * Excepcion que salta cuando quieres introducir una posicion incorrecta en el array
 */
@SuppressWarnings("serial")
public class ArrayException extends Exception{
	public ArrayException(String str){
		super(str);
	}

}
