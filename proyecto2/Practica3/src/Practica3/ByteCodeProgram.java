package Practica3;

import Practica3.ByteCode.ByteCode;

/**
 * 
 * @author jorge
 *
 */
public class ByteCodeProgram {
	
	private static final int MAX_INSTR = 100;
	private ByteCode[] bcProgram;
	private int numBc;
	/**
	 * 
	 */
	public ByteCodeProgram(){//constructor del programa
		this.bcProgram = new ByteCode[ByteCodeProgram.MAX_INSTR];
		this.numBc = 0;
	}  
	/**
	 * 
	 * @param instr
	 * @return boolean
	 */
	public boolean addBCInstruction(ByteCode instr){//añade una instruccion al programa
		
		if(numBc<MAX_INSTR){
			bcProgram[numBc]=instr;
			numBc++;
			return true;
		}else
			return false;
	}
	/**
	 * 
	 * @return devuelve el numero de programas
	 */
	public int getNumberOfByteCodes(){//devuelve el numero de programas
		return numBc;
	}  
	/**
	 * 
	 * @param i
	 * @return devuelve un programa
	 */
	public ByteCode getByteCode(int i){//devuelve un programa
		return this.bcProgram[i];
	}  
	/**
	 * 
	 * @return devuelve el string de programa
	 */
	public String toString(){//se encarga de mostrar por pantalla
		
		String s = System.getProperty("line.separator") + "Programa bytecode almacenado: ";
		
		for(int i=0;i<numBc;i++)
			s = s + System.getProperty("line.separator") + i + ": " + this.bcProgram[i];
			
		return s;
	}  
	/**
	 * 
	 * @param replace
	 * @param bc
	 */
	public void replace(int replace, ByteCode bc){//reemplaza una instruccion por otra
		bcProgram[replace] = bc;
	} 
	/**
	 * 
	 * @param replace
	 * @return
	 */
	public boolean replaceCorrecto(int replace){
		if(replace>=0 && replace<this.numBc){
			return true;
		}else
			return false;
	} 
		
	
	
}
