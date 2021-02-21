package Practica3.Instruction;

import Practica3.LexicalParser;
import Practica3.ByteCode.Halt;
import Practica3.Command.Compile;
import Practica3.Excepciones.ArrayException;
/**
 * 
 * @author jorge
 *
 */
public class Return implements Instruction{

	@Override
	public Instruction lexParse(String[] words, LexicalParser lexParser) {
		// TODO Auto-generated method stub
		if(words[0].equalsIgnoreCase("RETURN"))
			return new Return();
		else{
			return null;
		}
	}

	@Override
	public void compile(Compile compiler) throws ArrayException {
		// TODO Auto-generated method stub
		compiler.addByteCode(new Halt());
	}

}
