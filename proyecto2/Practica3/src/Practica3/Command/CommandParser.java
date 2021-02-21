package Practica3.Command;

/**
 * 
 * @author jorge
 *
 */
public class CommandParser {
	/**
	 * 
	 */
	private final static Command[] commands = {new Help(), new Quit(), new LoadFich(""),
			 new Replace(0), new Run(), new Compile()};
	/**
	 * 
	 * @param line
	 * @return
	 */
	public static Command parse(String line) {
		
		// elimina los caracteres en blanco iniciales    
		line = line.trim();
		// admite varios blancos separando las palabras.
		String []words = line.split(" +");
		boolean found = false;
		int i = 0;
		Command com = null;
		
		while(i < commands.length && !found){
			com = commands[i].parse(words);
			if(com!=null)
				found=true;
			else
				i++;
		}
		return com;
	}
	/**
	 * 
	 */
	public static void showHelp(){
		for(int i=0;i<CommandParser.commands.length;i++)
			System.out.println(CommandParser.commands[i].textHelp());
	}

}
