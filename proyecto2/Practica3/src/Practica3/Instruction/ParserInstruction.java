package Practica3.Instruction;

import Practica3.LexicalParser;
import Practica3.Excepciones.LexicalAnalysisException;
/**
 * 
 * @author jorge
 *
 */
public class ParserInstruction {
	
	private final static Instruction[] instructions = {new SimpleAssignement("",null), new Write(""),
			new Return(), new While(null,null), new IfThen(null,null), new CompoundAssignment("",null,null,null)};
	/**
	 * 
	 * @param line
	 * @return
	 */
	public static Instruction parse(String line,  LexicalParser lexParser)throws LexicalAnalysisException {
		
		// elimina los caracteres en blanco iniciales    
		line = line.trim();
		// admite varios blancos separando las palabras.
		String []words = line.split(" +");
		boolean found = false;
		int i = 0;
		Instruction inst = null;
		
		while(i < instructions.length && !found){
			
			inst = instructions[i].lexParse(words, lexParser);
			if(inst!=null){
				found=true;
			}else
				i++;
		}
		if(inst==null)
			throw new LexicalAnalysisException("");
		return inst;
	}

}
