package Practica3.Command;

import Practica3.Engine;
/**
 * 
 * @author jorge
 *
 */
public class Quit extends Command {

	@Override
	public boolean execute(Engine engine) {
		return engine.endExecution();
	}

	@Override
	public Command parse(String[] s) {
		// TODO Auto-generated method stub
		if(s.length==1 && s[0].equalsIgnoreCase("QUIT"))
			return new Quit();
		else
			return null;
	}

	@Override
	public String textHelp() {
		// TODO Auto-generated method stub
		return "  QUIT: Cierra el programa " ;//+ System.getProperty("line.Separator");
	}
	/**
	 * 
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return "QUIT";
	}
}
