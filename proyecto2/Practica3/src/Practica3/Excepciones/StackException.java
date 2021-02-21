package Practica3.Excepciones;

@SuppressWarnings("serial")
/**
 * excepcion que salta cuando hay un error de ejecucion al superar el tamaño de pila
 * @author jorge
 *
 */
public class StackException extends ExecutionError{
	
	public StackException(String str){
		super(str);
	}

}
