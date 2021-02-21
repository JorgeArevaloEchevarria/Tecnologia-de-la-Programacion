package Practica3.Command;

import Practica3.Engine;
import Practica3.Excepciones.BadFormatByteCode;
import Practica3.Excepciones.DivisionByZero;
import Practica3.Excepciones.ExecutionError;
import Practica3.Excepciones.StackException;
/**
 * 
 * @author jorge
 *
 */
public class Run extends Command{

	@Override
	public boolean execute(Engine engine) throws BadFormatByteCode, ExecutionError, StackException, DivisionByZero {
		// TODO Auto-generated method stub
		return engine.executeCommandRun();
	}

	@Override
	public Command parse(String[] s) {
		// TODO Auto-generated method stub
		if(s.length==1 && s[0].equalsIgnoreCase("RUN"))
			return new Run();
		else
			return null;
	}

	@Override
	public String textHelp() {
		// TODO Auto-generated method stub
		return "  RUN: Ejecuta el programa almacenado " ;//+ System.getProperty("line.Separator");
	}
	/**
	 * 
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return "RUN";
	}

}
