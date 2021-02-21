package Practica3.Command;

import Practica3.Engine;
/**
 * 
 * @author jorge
 *
 */
public class Reset extends Command {

	@Override
	public boolean execute(Engine engine) {
		// TODO Auto-generated method stub
		return engine.resetProgram();
	}

	@Override
	public Command parse(String[] s) {
		// TODO Auto-generated method stub
		if(s.length==1 && s[0].equalsIgnoreCase("RESET"))
			return new Reset();
		else
			return null;
			
	}

	@Override
	public String textHelp() {
		// TODO Auto-generated method stub
		return "  RESET: Restea el programa " ;//+ System.getProperty("line.Separator");
	}
	/**
	 * 
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return "RESET";
	}

}
