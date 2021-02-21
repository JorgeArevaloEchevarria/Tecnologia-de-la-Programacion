package Practica3.ByteCode.OneParameter;

import Practica3.CPU;
import Practica3.ByteCode.ByteCode;
import Practica3.Excepciones.DivisionByZero;
import Practica3.Excepciones.ExecutionError;
import Practica3.Excepciones.StackException;
/**
 * 
 * @author jorge
 *
 */
public class Push extends ByteCodesOneParameter {
	/**
	 * 
	 */
	public Push(){
		super();
	}
	/**
	 * 
	 * @param n
	 */
	public Push(int n){
		super(n);
	}
	
	@Override
	protected ByteCode parseAux(String string1, String string2) {
		// TODO Auto-generated method stub
		ByteCode bc = null;
		if(string1.equalsIgnoreCase("PUSH")){
			int p = Integer.parseUnsignedInt(string2);
			bc = new Push(p);
		}
		return bc;
	}


	@Override
	protected String toStringAux() {
		// TODO Auto-generated method stub
		return "PUSH";
	}

	@Override
	public boolean execute(CPU cpu) throws DivisionByZero, StackException, ExecutionError{
		// TODO Auto-generated method stub
		cpu.increaseProgramCounter();
		return cpu.push(this.param);
	}

}
