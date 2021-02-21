package Practica3.Term;

import Practica3.ByteCode.ByteCode;
import Practica3.ByteCode.OneParameter.Push;
import Practica3.Command.Compile;
/**
 * 
 * @author jorge
 *
 */
public class Number implements Term{
	
	private int numName;
	/**
	 * 
	 * @param term
	 */
	public Number(int term){
		this.numName=term;
	}
	
	@Override
	public Term parse(String term) {
		// TODO Auto-generated method stub
		try {
			int i = Integer.parseInt(term);
			return new Number(i);
		} catch (NumberFormatException nfe){
			return null;
		}
		//int num = Integer.parseInt(term);
	}

	@Override
	public ByteCode compile(Compile compiler) {
		// TODO Auto-generated method stub
		return new Push(this.numName);
	}
	
	public String toString(){
		return Integer.toString(numName);
	}

}
