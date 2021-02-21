package Practica3.Instruction;

import Practica3.LexicalParser;
import Practica3.ByteCode.ByteCode;
import Practica3.ByteCode.ByteCodeParser;
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
public class CompoundAssignment implements Instruction {
	
	private String varName;
	private Term t1;
	private ByteCode operator;
	private Term t2;
	/**
	 * 
	 * @param var
	 * @param term1
	 * @param ope
	 * @param term2
	 */
	public CompoundAssignment(String var, Term term1, ByteCode ope,Term term2){
		this.varName = var;
		this.t1 = term1;
		this.operator = ope;
		this.t2 = term2;	
	}
	
	
	@Override
	public Instruction lexParse(String[] words, LexicalParser lexParser) {
		if(words.length != 5)
			return null;
		else if(!words[1].equals("="))
			return null;
		else{
			this.varName = words[0];
			this.t1 = TermParser.parse(words[2]);
			this.operator = ByteCodeParser.parse(words[3]);
			this.t2 = TermParser.parse(words[4]);
			return new CompoundAssignment(this.varName,this.t1,this.operator,this.t2);
		}
	}

	@Override
	public void compile(Compile compiler) throws ArrayException {
		// TODO Auto-generated method stub
		int index = compiler.getIndice(this.varName);
		if(index == compiler.getNumVariables())
			compiler.addVar(this.varName);
		compiler.addByteCode(this.t1.compile(compiler));
		compiler.addByteCode(this.t2.compile(compiler));
		compiler.addByteCode(operator);
		compiler.addByteCode(new Store(index));
		
	}

}
