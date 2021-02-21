package Practica3.Term;

import Practica3.ByteCode.ByteCode;
import Practica3.Command.Compile;
/**
 * 
 * @author jorge
 *
 */
public interface Term {
	/**
	 * 
	 * @param term
	 * @return
	 */
	Term parse(String term);
	/**
	 * 
	 * @param compiler
	 * @return
	 */
	ByteCode compile(Compile compiler);
}
