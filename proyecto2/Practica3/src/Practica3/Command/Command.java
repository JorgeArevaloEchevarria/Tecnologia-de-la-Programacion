package Practica3.Command;

import java.io.FileNotFoundException;

import Practica3.Engine;
import Practica3.Excepciones.ArrayException;
import Practica3.Excepciones.BadFormatByteCode;
import Practica3.Excepciones.DivisionByZero;
import Practica3.Excepciones.ExecutionError;
import Practica3.Excepciones.LexicalAnalysisException;
import Practica3.Excepciones.StackException;
/**
 * 
 * @author jorge
 *
 */
abstract public class Command {
	/**
	 * 
	 * @param engine
	 * @return
	 * @throws ArrayException 
	 * @throws LexicalAnalysisException 
	 * @throws FileNotFoundException 
	 * @throws DivisionByZero 
	 * @throws StackException 
	 * @throws ExecutionError 
	 * @throws BadFormatByteCode 
	 */
	abstract public boolean execute(Engine engine) throws LexicalAnalysisException, ArrayException,
		FileNotFoundException, BadFormatByteCode, ExecutionError, StackException, DivisionByZero;//ejecuta los comandos
	/**
	 * 
	 * @param s
	 * @return
	 */
	abstract public Command parse(String[] s);
	/**
	 * 
	 * @return
	 */
	abstract public String textHelp();
	
}
