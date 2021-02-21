package Practica3.Excepciones;

@SuppressWarnings("serial")
/**
 * excepcion que salta cuando hay un error al ejecutar un bytecode
 * @author jorge
 *
 */
public class ExecutionError extends Exception {
	String mensaje;
	public ExecutionError(String str){
		this.mensaje = str;
	}
	
	public String toString(){
		return this.mensaje;
	}

}
