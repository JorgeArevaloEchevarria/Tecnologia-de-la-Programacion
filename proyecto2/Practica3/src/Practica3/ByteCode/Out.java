package Practica3.ByteCode;

import Practica3.CPU;
/**
 * 
 * @author jorge
 *
 */
public class Out extends ByteCode {
	/**
	 * 
	 */
	@Override
	public boolean execute(CPU cpu) {
		// TODO Auto-generated method stub
		cpu.out();
		cpu.increaseProgramCounter();
		return true;
	}
	/**
	 * 
	 */
	@Override
	public ByteCode parse(String[] words) {
		// TODO Auto-generated method stub
		ByteCode bc = null;
		if (words.length==1)
			if(words[0].equalsIgnoreCase("OUT")){
				bc = new Out();
		}
		return bc;
	}
	/**
	 * 
	 */
	public String toString(){
		 return "OUT"; 
	} 
}
