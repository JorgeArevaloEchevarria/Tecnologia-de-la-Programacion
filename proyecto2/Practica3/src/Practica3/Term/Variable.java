package Practica3.Term;

import Practica3.ByteCode.ByteCode;
import Practica3.ByteCode.OneParameter.Load;
import Practica3.Command.Compile;
/**
 * 
 * @author jorge
 *
 */
public class Variable implements Term {

	private String varName;
	/**
	 * 
	 * @param term
	 */
	public Variable(String term){
		this.varName = term;
	}
	@Override
	public Term parse(String term) {
		// TODO Auto-generated method stub
		if(term.length()!=1)
			return null;
		else{
			
			char name = term.charAt(0);
			if('a' <= name && name <= 'z')
				return new Variable(term);
			else 
				return null;	
		}
	}

	@Override
	public ByteCode compile(Compile compiler) {
		// TODO Auto-generated method stub
		int index = compiler.getIndice(this.varName);
		return new Load(index);
	}
	/**
	 * 
	 */
	public String toString(){
		return this.varName;
	}


}
