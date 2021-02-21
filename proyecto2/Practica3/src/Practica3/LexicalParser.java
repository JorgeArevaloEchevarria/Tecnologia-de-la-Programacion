package Practica3;

import Practica3.Excepciones.LexicalAnalysisException;
import Practica3.Instruction.Instruction;
import Practica3.Instruction.ParserInstruction;
/**
 * 
 * @author jorge
 *
 */
public class LexicalParser {
	
	private SourceProgram sProgram;
	private int programCounter;
	/**
	 * 
	 * @param soProgram
	 */
	public LexicalParser(SourceProgram soProgram){
		this.sProgram = soProgram;	
	}
	/**
	 * 
	 * @param pProgram
	 * @param stopKey
	 * @throws LexicalAnalysisException
	 */
	public void lexicalParser(ParsedProgram pProgram, String stopKey) throws LexicalAnalysisException {
		boolean stop = false;
		
		while (this.programCounter < sProgram.getNumeroLineas()&& !stop){
			String instr = sProgram.getLinea(this.programCounter);
			String key = instr.trim();
			if (key.equalsIgnoreCase(stopKey)){
				stop = true;
			}else {
				Instruction instruction = ParserInstruction.parse(instr,this);
				pProgram.addInstruccion(instruction);//añade una nueva instruccion
			}
			increaseProgramCounter();
		}

	}
	
	/**
	 * 
	 */
	public void increaseProgramCounter(){
	 this.programCounter++;
	}
	/**
	 * 
	 */
	public void decremProgramCounter(){
		this.programCounter--;
	}

}
