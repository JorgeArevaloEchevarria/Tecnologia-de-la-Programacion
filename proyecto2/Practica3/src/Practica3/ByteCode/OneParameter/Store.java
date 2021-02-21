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
public class Store extends ByteCodesOneParameter{
	/**
	 * 
	 */
	public Store(){
		super();
	}
	/**
	 * 
	 * @param n
	 */
	public Store(int n){
		super(n);
	}
	
	@Override
	protected ByteCode parseAux(String string1, String string2) {
		// TODO Auto-generated method stub
		ByteCode bc = null;
		if(string1.equalsIgnoreCase("STORE")){
			int p = Integer.parseUnsignedInt(string2);
			bc = new Store(p);
		}
		return bc;
	}

	@Override
	protected String toStringAux() {
		// TODO Auto-generated method stub
		return "STORE";
	}

	@Override
	public boolean execute(CPU cpu)throws DivisionByZero,StackException,ExecutionError  {
		// TODO Auto-generated method stub
		if(cpu.getSizeStack()>0){
			cpu.write(this.param,cpu.pop());
			cpu.increaseProgramCounter();
			return true;
		}else
			throw new StackException("Tamaño de pila insuficiente"); 
	}

}
