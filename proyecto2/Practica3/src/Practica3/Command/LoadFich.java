package Practica3.Command;

import java.io.FileNotFoundException;
import Practica3.Engine;
import Practica3.Excepciones.ArrayException;
import Practica3.Excepciones.LexicalAnalysisException;
/**
 * 
 * @author jorge
 *
 */
public class LoadFich extends Command{
	/**
	 * 
	 * @param i
	 */
	public LoadFich(String i){
		this.fich=i;
	}
	private String fich;

	@Override
	public boolean execute(Engine engine)throws LexicalAnalysisException, ArrayException, FileNotFoundException{
		// TODO Auto-generated method stub
		engine.load(this.fich);
		return true;
	}

	@Override
	public Command parse(String[] s) {
		// TODO Auto-generated method stub
		if(s.length==2 && s[0].equalsIgnoreCase("LOAD")){
			this.fich = s[1];
			return new LoadFich(this.fich);
		}else
			return null;

	}

	@Override
	public String textHelp() {
		// TODO Auto-generated method stub
		return  "  LOADFICH: Carga el fichero de un archivo "; 
	}

}
