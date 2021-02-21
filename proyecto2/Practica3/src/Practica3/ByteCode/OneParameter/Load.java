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
public class Load extends ByteCodesOneParameter{
	/**
	 * 
	 */
	public Load(){
		super();
	}
	/**
	 * 
	 * @param n
	 */
	public Load(int n){
		super(n);
	}
	
	@Override
	protected ByteCode parseAux(String string1, String string2) {
		// TODO Auto-generated method stub
		ByteCode bc = null;
		if(string1.equalsIgnoreCase("LOAD")){
			int p = Integer.parseUnsignedInt(string2);
			bc = new Load(p);
		}
		return bc;
	}

	@Override
	protected String toStringAux() {
		// TODO Auto-generated method stub
		return "LOAD";
	}

	@Override
	public boolean execute(CPU cpu)throws DivisionByZero,StackException,ExecutionError {
		// TODO Auto-generated method stub
		if(cpu.accesoCorrecto(this.param)){
			if(cpu.push(cpu.read(this.param))){
				cpu.increaseProgramCounter();
				return true;
			}else
				throw new StackException("Tamaño de pila insuficiente"); 
		}else
			throw new StackException("Tamaño de pila insuficiente"); 
	}
}
