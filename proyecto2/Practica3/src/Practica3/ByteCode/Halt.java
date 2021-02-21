package Practica3.ByteCode;

import Practica3.CPU;
/**
 * 
 * @author jorge
 *
 */
public class Halt extends ByteCode {

	@Override
	public boolean execute(CPU cpu) {
		// TODO Auto-generated method stub
		cpu.halt();
		return true;
	}

	@Override
	public ByteCode parse(String[] words) {
		// TODO Auto-generated method stub
		ByteCode bc = null;
		if (words.length==1)
			if(words[0].equalsIgnoreCase("HALT")){
				bc = new Halt();
		}
		return bc;
	}
	/**
	 * 
	 */
	public String toString(){
		 return "HALT"; 
	} 
}
