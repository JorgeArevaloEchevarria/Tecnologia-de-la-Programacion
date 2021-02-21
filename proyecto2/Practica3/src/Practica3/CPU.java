package Practica3;
import Practica3.ByteCode.ByteCode;
import Practica3.ByteCode.ByteCodeParser;
import Practica3.Excepciones.BadFormatByteCode;
import Practica3.Excepciones.DivisionByZero;
import Practica3.Excepciones.ExecutionError;
import Practica3.Excepciones.StackException;
/**
 * 
 * @author jorge
 *
 */
public class CPU {

	private Memory memory = new Memory();
	private OperandStack stack = new OperandStack();
	private boolean halt = false;
	private int programCounter = 0;
	private ByteCodeProgram bcProgram = new ByteCodeProgram();
	/**
	 * 
	 * @param program
	 */
	public CPU(ByteCodeProgram program){
		this.bcProgram = program;
		this.memory = new Memory();
		this.stack = new OperandStack();
		this.halt = false;
		this.programCounter = 0;
	}
	/**
	 * 
	 */
	public void halt(){
		halt = true;
	}
	
	public void out(){
		System.out.print(stack.out());
	}
	/**
	 * 
	 * @return this.stack.getNumElems();
	 */
	
	public int getSizeStack(){
		return this.stack.getNumElems();
	}
	/**
	 * 
	 * @return
	 */
	public int pop(){
		return this.stack.pop();
	}
	/**
	 * 
	 * @param i
	 * @return this.stack.push(i);
	 * @throws ExecutionError 
	 * @throws StackException 
	 * @throws DivisionByZero 
	 */
	public boolean push(int i) throws DivisionByZero, StackException, ExecutionError{
		return this.stack.push(i);	
	}
	/**
	 * 
	 * @param bc
	 * @return this.bcProgram.addBCInstruction(bc);
	 */
	public boolean addByteCodeInstruction(ByteCode bc){//añade una instruccion al programa
		return this.bcProgram.addBCInstruction(bc);
	}
	/**
	 * 
	 * @return true
	 */
	public boolean resetProgram(){//resetea el programa
		bcProgram = new ByteCodeProgram();
		this.memory = new Memory();
		this. stack = new OperandStack();
		return true;
	}
	/**
	 * 
	 * @param replace
	 * @param s
	 */
	public void replaceByteCode(int replace,String s)throws BadFormatByteCode{//remplaza una instruccion
		if(ByteCodeParser.parse(s)!= null)
			bcProgram.replace(replace,ByteCodeParser.parse(s));//añade la nueva inatruccion
		else
			throw new BadFormatByteCode("");
	}
	/**
	 * 
	 * @param replace
	 * @return
	 */
	public boolean replaceCorrecto(int replace){
		return bcProgram.replaceCorrecto(replace);
	}
	/**
	 * 
	 */
	public void ProgramaAlmacenado(){
		System.out.println(this.bcProgram);
	}
	/**
	 * 
	 * @param param
	 * @return
	 */
	public boolean accesoCorrecto(int param){//mira si hay contenido en esa posicion
		
		if(this.memory.accesoCorrecto(param))
			return true;
		else 
			return false;
	}
	/**
	 * 
	 * @param param
	 * @return pos
	 */
	public int read(int param){
		return this.memory.read(param);
	}
	/**
	 * 
	 * @param param
	 * @param value
	 * @return this.memory.write(param, value);
	 */
	public boolean write(int param, int value){
		return this.memory.write(param, value);
	}
	/**
	 * 
	 * @param jump
	 */
	public void setProgramCounter(int jump){
		this.programCounter = jump;
	}
	/**
	 * 
	 */
	public void increaseProgramCounter(){
		this.programCounter++;
	}
	/**
	 * 
	 * @return error
	 * @throws DivisionByZero 
	 */
	public boolean run()throws BadFormatByteCode,ExecutionError,StackException, DivisionByZero{
		
		//new CPU(this.bcProgram);
		this.memory = new Memory();
		this. stack = new OperandStack();
		this.programCounter = 0;
		this.halt = false;
		
		boolean error = true;
	
		ByteCode bc;//tratar bien la exccepcion
		while(this.programCounter<bcProgram.getNumberOfByteCodes() && error){
			bc = bcProgram.getByteCode(this.programCounter);
				if(!halt){
					try{
						if(!bc.execute(this)){//salir del bucle
							error = false;
							throw new ExecutionError("Error de ejecucion del bytecode " + this.programCounter); 
						 }
					}catch(DivisionByZero e){
						throw new ExecutionError("Error de ejecucion del bytecode " + this.programCounter + "\n" + "Error al dividir por 0 en: " + bc.toString()); 
					}catch(StackException e){
						throw new ExecutionError("Error de ejecucion del bytecode " + this.programCounter + "\n" + "Excepcion-Bytecode " + bc.toString() + ": Tamaño de pila insuficiente"); 
					}
				}else
					this.programCounter++;
			}
		System.out.println(this);
		return error;
	}
	/**
	 * 
	 * @return String
	 */
	public String toString(){
		String s = System.getProperty("line.separator") + "Estado de la CPU: " + System.getProperty("line.separator") + "  Memoria: " + this.memory + System.getProperty("line.separator") + "  Pila: " + this.stack + System.getProperty("line.separator");
		return s;  
	}  

}
