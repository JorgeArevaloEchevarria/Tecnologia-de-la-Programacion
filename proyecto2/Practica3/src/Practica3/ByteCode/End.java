package Practica3.ByteCode;

import Practica3.CPU;
/**
 * 
 * @author jorge
 *
 */
public class End extends ByteCode {
	@Override	
	public boolean execute(CPU cpu) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ByteCode parse(String[] words) {
		// TODO Auto-generated method stub
		ByteCode bc = null;
		if (words.length==1)
			if(words[0].equalsIgnoreCase("END")){
				bc = new End();
		}
		return bc;
	}
	/**
	 * 
	 */
	public String toString(){
		 return "END"; 
	} 
}
