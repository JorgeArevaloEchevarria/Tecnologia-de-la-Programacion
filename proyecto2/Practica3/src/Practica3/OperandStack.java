package Practica3;

import Practica3.Excepciones.DivisionByZero;
import Practica3.Excepciones.ExecutionError;
import Practica3.Excepciones.StackException;

/**
 * 
 * @author jorge
 *
 */
public class OperandStack{
	
	private static final int MAX_STACK = 100;
	private int[] stack;
	private int numElems;
	/**
	 * 
	 */
	public OperandStack() {//constructor
		this.numElems=0;
		this.stack = new int[OperandStack.MAX_STACK];
	}
	/**
	 * 
	 * @return boolean
	 */
	public boolean isEmpty(){//mira si esta vacia la pila
		if(numElems==0)
			return true;
		else
			return false;	
	}
	/**
	 * 
	 * @return boolean
	 */
	public boolean aritmeticas(){//para las instrucciones aritmeticologicas
		if(numElems>1)
			return true;
		else
			return false;
	}
	/**
	 * 
	 * @param value
	 * @return boolean
	 */
	public boolean push(int value)throws DivisionByZero,StackException,ExecutionError {//mete el valor en pila
		if(numElems <= MAX_STACK){
			stack[numElems]=value;
			numElems++;
			return true;
		}else
			throw new StackException("Se ha intendado acceder en la pila de operandos y no habia operandos");    
	}
	/**
	 * 
	 * @return i
	 */
	public int pop(){//saca el valor de la pila
		int i = stack[numElems-1];
		numElems--;
		return i;
	}
	/**
	 * 
	 * @return numElems
	 */
	public int getNumElems(){//devuelve el numero de elementos que hay en la pila
		return numElems;
	}
	/**
	 * 
	 * @return s
	 */
	public String out(){// realiza la instruccion OUT
		String s = System.getProperty("line.separator") + "Consola : " + this.stack[numElems-1];
		return s;
	}  
	/**
	 * 
	 * @return s
	 */
	public String toString(){
		
		if (this.numElems==0)
			return "<vacia>";
		else {
			String s="";
			for (int i=0; i < this.numElems; i++)
				s = s + this.stack[i] + " ";
			return s;
		}
	 }
	

}
