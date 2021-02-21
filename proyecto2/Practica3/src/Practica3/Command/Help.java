package Practica3.Command;

import Practica3.Engine;
/**
 * 
 * @author jorge
 *
 */
public class Help extends Command {

	@Override
	public boolean execute(Engine engine) {
		// TODO Auto-generated method stub
		CommandParser.showHelp();
		return true;
	}

	@Override
	public Command parse(String[] s) {
		// TODO Auto-generated method stub
		if(s.length==1 && s[0].equalsIgnoreCase("HELP"))
			return new Help();
		else
			return null;
	}

	@Override
	public String textHelp() {
		// TODO Auto-generated method stub
		return "  HELP: Muestra la ayuda para el uso del programa ";// + System.getProperty("line.Separator");
	}
	/**
	 * 
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return "HELP";
	}

}
