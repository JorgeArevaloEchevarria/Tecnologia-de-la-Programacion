package Practica3.ByteCode.Aritmetics;

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
abstract public class Aritmetics extends ByteCode {
	/**
	 * 
	 */
	public Aritmetics(){};
	@Override
	public boolean execute(CPU cpu) throws DivisionByZero,StackException,ExecutionError{
		// TODO Auto-generated method stub
		if(cpu.getSizeStack()>1){
			int i = cpu.pop();
			int j = cpu.pop();
			cpu.increaseProgramCounter();
			return cpu.push(this.Operacion(i, j));
		}
		else{
			throw new StackException("Tamaño de pila insuficiente");
		}
	}
	/**
	 * 
	 * @param i
	 * @param j
	 * @return
	 * @throws DivisionByZero 
	 */
	abstract protected int Operacion (int i, int j) throws DivisionByZero;
	
	@Override
	public ByteCode parse(String[] words) {
		// TODO Auto-generated method stub
		if (words.length!=1)
			return null;
		else 
			return this.parseAux(words[0]);
	}
	/**
	 * 
	 * @param string1
	 * @return
	 */
	abstract protected ByteCode parseAux(String string1);
	/**
	 * 
	 */
	public String toString(){
		return this.toStringAux();
	}
	
	abstract protected String toStringAux(); 
}
