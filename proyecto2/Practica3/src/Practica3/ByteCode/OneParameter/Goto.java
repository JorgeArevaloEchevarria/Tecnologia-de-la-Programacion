package Practica3.ByteCode.OneParameter;

import Practica3.CPU;
import Practica3.ByteCode.ByteCode;
/**
 * 
 * @author jorge
 *
 */
public class Goto extends ByteCodesOneParameter{
	/**
	 * 
	 */
	public Goto(){
		super();
	}
	/**
	 * 
	 * @param n
	 */
	public Goto(int n){
		super(n);
	}
	
	@Override
	protected ByteCode parseAux(String string1, String string2) {
		// TODO Auto-generated method stub
		ByteCode bc = null;
		if(string1.equalsIgnoreCase("GOTO")){
			int p = Integer.parseUnsignedInt(string2);
			bc = new Goto(p);
		}
		return bc;
	}


	@Override
	protected String toStringAux() {
		// TODO Auto-generated method stub
		return "GOTO";
	}

	@Override
	public boolean execute(CPU cpu) {
		// TODO Auto-generated method stub
		cpu.setProgramCounter(this.param);
		return true;
	}

}
