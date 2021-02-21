package Practica3.Instruction;

import Practica3.LexicalParser;
import Practica3.ParsedProgram;
import Practica3.Command.Compile;
import Practica3.Condition.Condition;
import Practica3.Condition.ConditionParser;
import Practica3.Excepciones.ArrayException;
import Practica3.Excepciones.LexicalAnalysisException;
/**
 * 
 * @author jorge
 *
 */
public class IfThen implements Instruction{
	
	private Condition condition;
	private ParsedProgram ifBody;
	/**
	 * 
	 * @param cond
	 * @param body
	 */
	public IfThen(Condition cond,ParsedProgram body){
		this.condition=cond;
		this.ifBody = body;
	}
	
	@Override
	public Instruction lexParse(String[] words, LexicalParser lexParser) {
		// TODO Auto-generated method stub
		if(!words[0].equalsIgnoreCase("IF"))
			return null;
		else{
			Condition cond = ConditionParser.parse(words[1],words[2],words[3],lexParser);
			this.condition = cond ;
			ParsedProgram iBody = new ParsedProgram();
			try {
				lexParser.lexicalParser(iBody, "ENDIF");
			} catch (LexicalAnalysisException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.ifBody = iBody; 
			lexParser.decremProgramCounter();
			return new IfThen(this.condition,this.ifBody);
		}
	}

	@Override
	public void compile(Compile compiler) throws ArrayException{
		// TODO Auto-generated method stub

		 this.condition.compile(compiler);
		 compiler.compile(this.ifBody);
		 this.condition.setJump(compiler.getProgramCounter());
	 }


}
