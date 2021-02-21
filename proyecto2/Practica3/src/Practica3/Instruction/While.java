package Practica3.Instruction;

import Practica3.LexicalParser;
import Practica3.ParsedProgram;
import Practica3.ByteCode.OneParameter.Goto;
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
public class While implements Instruction{
	
	 private Condition condition;
	 private ParsedProgram whileBody;

	 /**
	  * 
	  * @param cond
	  * @param body
	  */
	 public While(Condition cond, ParsedProgram body){
		 this.condition=cond;
		 this.whileBody=body;
	 }

	@Override
	public Instruction lexParse(String[] words, LexicalParser lexParser) {
		// TODO Auto-generated method stub
		if(!words[0].equalsIgnoreCase("WHILE"))
			return null;
		else{
			Condition cond = ConditionParser.parse(words[1],words[2],words[3],lexParser);
			ParsedProgram wBody = new ParsedProgram();
			try {
				lexParser.lexicalParser(wBody, "ENDWHILE");
			} catch (LexicalAnalysisException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			this.whileBody = wBody; 
			this.condition = cond ;
			lexParser.decremProgramCounter();
			return new While(this.condition,this.whileBody);
		}
	}

	@Override
	public void compile(Compile compiler) throws ArrayException {
		// TODO Auto-generated method stub
		int i = compiler.getProgramCounter();
		this.condition.compile(compiler);
		 compiler.compile(this.whileBody);
		 this.condition.setJump(compiler.getProgramCounter()+1);
		 compiler.addByteCode(new Goto(i));
	}

}
