package Practica3.Instruction;

import Practica3.LexicalParser;
import Practica3.ByteCode.OneParameter.Store;
import Practica3.Command.Compile;
import Practica3.Excepciones.ArrayException;
import Practica3.Term.Term;
import Practica3.Term.TermParser;
/**
 * 
 * @author jorge
 *
 */
public class SimpleAssignement implements Instruction {
	
	private String varName;
	private Term rhs;
	/**
	 * 
	 * @param var
	 * @param term
	 */
	public SimpleAssignement(String var,Term term){
		this.varName = var;
		this.rhs = term;
	}
	
	@Override
	public Instruction lexParse(String[] words, LexicalParser lexParser) {
		// TODO Auto-generated method stub
		if(words.length != 3)
			return null;
		else if(!words[1].equalsIgnoreCase("="))
			return null;
		else{
			this.varName = words[0];
			Term term = TermParser.parse(words[2]);
			this.rhs = term;
			return new SimpleAssignement(this.varName,this.rhs);
		}
	}

	@Override
	public void compile(Compile compiler) throws ArrayException {
		// TODO Auto-generated method stub
		int index = compiler.getIndice(this.varName);
		if(index == compiler.getNumVariables())
			compiler.addVar(this.varName.toString());
		compiler.addByteCode(this.rhs.compile(compiler));
		compiler.addByteCode(new Store(index));
	}

}
