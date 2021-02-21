package Practica3.Command;

import Practica3.Engine;
/**
 * 
 * @author jorge
 *
 */
public class AddByteCodeProgram extends Command {

	@Override
	public boolean execute(Engine engine) {
		// TODO Auto-generated method stub
		return engine.readByteCodeProgram();
	}

	@Override
	public Command parse(String[] s) {
		// TODO Auto-generated method stub
		if(s.length==1 && s[0].equalsIgnoreCase("BYTECODE"))
			return new AddByteCodeProgram();
		else
			return null;
	}
	
	@Override
	public String textHelp(){
		return "  BYTECODE: Permite introducir un programa " ;//+ System.getProperty("line.Separator");
	}
	/**
	 * 
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return "BYTECODE"+ "\n" +"Introduce el bytecode. Una instruccion por línea ";
	}

}
