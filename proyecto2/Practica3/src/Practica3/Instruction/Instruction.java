package Practica3.Instruction;

import Practica3.LexicalParser;
import Practica3.Command.Compile;
import Practica3.Excepciones.ArrayException;
/**
 * 
 * @author jorge
 *
 */
public interface Instruction {
	/**
	 * 
	 * @param words
	 * @param lexParser
	 * @return
	 */
	 Instruction lexParse(String[] words, LexicalParser lexParser);
	 /**
	  * 
	  * @param compile
	  * @throws ArrayException
	  */
	 void compile(Compile compile) throws ArrayException;

}
