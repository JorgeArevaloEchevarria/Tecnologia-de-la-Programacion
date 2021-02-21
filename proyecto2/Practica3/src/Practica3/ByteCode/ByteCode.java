package Practica3.ByteCode;

import Practica3.CPU;
import Practica3.Excepciones.DivisionByZero;
import Practica3.Excepciones.ExecutionError;
import Practica3.Excepciones.StackException;
/**
 * 
 * @author jorge
 *
 */
abstract public class ByteCode{
	/**
	 * 
	 * @param cpu
	 * @return
	 * @throws DivisionByZero 
	 * @throws StackException 
	 */
	abstract public boolean execute(CPU cpu) throws DivisionByZero, StackException, ExecutionError;
	/**
	 * 
	 * @param words
	 * @return
	 */
	abstract public ByteCode parse(String[] words);
}
	
