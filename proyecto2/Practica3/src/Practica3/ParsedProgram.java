package Practica3;

import Practica3.Instruction.Instruction;
/**
 * 
 * @author jorge
 *
 */
public class ParsedProgram {
	
	private Instruction[] pProgram;
	private static final int MAX_INSTR = 100;
	private int numI;
	/**
	 * 
	 */
	public ParsedProgram(){
		this.pProgram = new Instruction[MAX_INSTR];
		this.numI=0;
	}
	/**
	 * 
	 * @param inst
	 * @return
	 */
	public boolean addInstruccion(Instruction inst){//añade una instruccion al programa
		
		if(numI<MAX_INSTR){
			this.pProgram[this.numI] = inst;
			this.numI++;
			return true;
		}else
			return false;
	}
	/**
	 * 
	 * @return
	 */
	public int getNumeroInstrucciones(){//devuelve el numero de instrucciones
		return this.numI;
	}
	/**
	 * 
	 * @param i
	 * @return
	 */
	public Instruction getInstruccion(int i){//devuelve una instruccion
		return this.pProgram[i];
	}
	/**
	 * 
	 */
	public String toString(){//se encarga de mostrar por pantalla
		
		String s = System.getProperty("line.separator") + "Programa almacenado: ";
		
		for(int i=0;i<this.numI;i++)
			s = s + System.getProperty("line.separator") + i + ": " + this.pProgram[i];
			
		return s;
	} 

}
