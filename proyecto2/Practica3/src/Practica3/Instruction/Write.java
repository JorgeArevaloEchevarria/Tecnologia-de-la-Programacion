package Practica3.Instruction;

import Practica3.LexicalParser;
import Practica3.ByteCode.Out;
import Practica3.ByteCode.OneParameter.Load;
import Practica3.Command.Compile;
import Practica3.Excepciones.ArrayException;

/**
 * 
 * @author jorge
 *
 */
public class Write implements Instruction{
	
	private String varName;
	/**
	 * 
	 * @param var
	 */
	public Write(String var){
		this.varName =var;
	}

	@Override
	public Instruction lexParse(String[] words, LexicalParser lexParser) {
		// TODO Auto-generated method stub
		if(words.length != 2)
			return null;
		else{
			varName = words[1];
			return new Write(this.varName);
		}
	}

	@Override
	public void compile(Compile compiler) throws ArrayException {
		// TODO Auto-generated method stub
		int index = compiler.getIndice(this.varName);
		compiler.addByteCode(new Load(index));
		compiler.addByteCode(new Out());
	}

}
