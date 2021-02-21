package Practica3.Command;

import java.io.FileNotFoundException;

import Practica3.ByteCodeProgram;
import Practica3.Engine;
import Practica3.ParsedProgram;
import Practica3.ByteCode.ByteCode;
import Practica3.Excepciones.ArrayException;
import Practica3.Excepciones.BadFormatByteCode;
import Practica3.Excepciones.DivisionByZero;
import Practica3.Excepciones.ExecutionError;
import Practica3.Excepciones.LexicalAnalysisException;
import Practica3.Excepciones.StackException;
import Practica3.Instruction.Instruction;
/**
 * 
 * @author jorge
 *
 */
public class Compile extends Command {

	 private ByteCodeProgram bcProgram;
	 private String[] varTable;
	 private int numVars;
	 private static final int MAX_VAR = 100;
	 /**
	  * 
	  */
	 public Compile(){
		 this.bcProgram = new ByteCodeProgram();
		 this.varTable = new String[MAX_VAR];
		 this.numVars=0; 
	 }
	 /**
	  * 
	  * @param pProgram
	  * @throws ArrayException
	  */
	 public void compile(ParsedProgram pProgram) throws ArrayException{
		 int i = 0;
		 try {
			 
			 while (i < pProgram.getNumeroInstrucciones()){
				Instruction inst = pProgram.getInstruccion(i);
				inst.compile(this);
				i++;
			 }
		 }
		 catch(ArrayIndexOutOfBoundsException e){
			 throw new ArrayException("");
		 }
	 }
	 /**
	  * 
	  * @param b
	  * @throws ArrayException
	  */
	public void addByteCode(ByteCode b) throws ArrayException{
		
		try {
			bcProgram.addBCInstruction(b); 
		 }
		 catch(ArrayIndexOutOfBoundsException e){
			 throw new ArrayException("");
		 }
	}
	
	/**
	 * 
	 * @param varName
	 * @return
	 */
	 public int getIndice(String varName) {
		int cont = 0;
		boolean encontrado = false;
		 while(cont<this.numVars && !encontrado){
			 if(this.varTable[cont].equals(varName))
				 encontrado=true;
			 else
				 cont++;
		 }
			return cont; 
	 }
	 /**
	  * 
	  * @return
	  */
	 public int getNumVariables(){
		 return this.numVars;
	 }
	 /**
	  * 
	  * @return
	  */
	 public  ByteCodeProgram CodeProgram(){
		 return this.bcProgram;
	 }
	 /**
	  * 
	  * @return
	  */
	 public int getProgramCounter(){
		 return bcProgram.getNumberOfByteCodes();
	 }
	 /**
	  * 
	  * @param var
	  */
	 public void addVar(String var){
		 this.varTable[this.numVars]=var;
		 this.numVars++;
	 }
	 /**
	  * 
	  */
	@Override
	public boolean execute(Engine engine) throws LexicalAnalysisException, ArrayException, FileNotFoundException,
	BadFormatByteCode, ExecutionError, StackException, DivisionByZero{
		// TODO Auto-generated method stub
		engine.compile();
		return true;
	}
	/**
	 * 
	 */
	@Override
	public Command parse(String[] s) {
		// TODO Auto-generated method stub
		if(s.length==1 && s[0].equalsIgnoreCase("COMPILE"))
			return new Compile();
		else
			return null;
	}
	/**
	 * 
	 */
	@Override
	public String textHelp() {
		// TODO Auto-generated method stub
		return "  COMPILE : compila el codigo almacenado";
	}
}


