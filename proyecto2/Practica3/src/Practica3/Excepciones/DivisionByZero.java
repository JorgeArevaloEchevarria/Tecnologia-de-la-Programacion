package Practica3.Excepciones;

@SuppressWarnings("serial")
/**
 * excepcion que salta cuando hay un error de ejecucion en el que se divide por 0
 * @author jorge
 *
 */
public class DivisionByZero extends ExecutionError{

	public DivisionByZero(String str){
		super(str);
	}
	

}
