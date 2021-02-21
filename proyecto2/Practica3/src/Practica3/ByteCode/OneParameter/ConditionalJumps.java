package Practica3.ByteCode.OneParameter;

import Practica3.CPU;
import Practica3.Excepciones.DivisionByZero;
import Practica3.Excepciones.ExecutionError;
import Practica3.Excepciones.StackException;
/**
 * 
 * @author jorge
 *
 */
abstract public class ConditionalJumps extends ByteCodesOneParameter{
	/**
	 * 
	 */
	public ConditionalJumps(){}
	/**
	 * 
	 * @param j
	 */
	public ConditionalJumps(int j){ 
		 super(j);
	}
	
	@Override
	public boolean execute(CPU cpu) throws DivisionByZero,StackException,ExecutionError{
		// TODO Auto-generated method stub
		if (cpu.getSizeStack()>=2){
			int n1 = cpu.pop();//cima
			int n2 = cpu.pop();//subcima
			if (!compare(n1,n2))
				cpu.setProgramCounter(this.param);
			else 
				cpu.increaseProgramCounter();
			return true;
			}
		else 
			throw new StackException("Tamaño de pila insuficiente");    
	}    
	
	public void setJump(int jump){
		this.param = jump;	
	}

	/**
	 * 
	 * @param n1
	 * @param n2
	 * @return
	 */
	abstract protected boolean compare(int n1, int n2);
}
