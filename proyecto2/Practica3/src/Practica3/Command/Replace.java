package Practica3.Command;

import Practica3.Engine;
import Practica3.Excepciones.BadFormatByteCode;
/**
 * 
 * @author jorge
 *
 */
public class Replace extends Command {
	
	/**
	 * 
	 * @param i
	 */
	public Replace(int i){
		this.rep=i;
	}
	private int rep;
	@Override
	public boolean execute(Engine engine) throws BadFormatByteCode {
		// TODO Auto-generated method stub
		return engine.replaceByteCode(this.rep);
	}

	@Override
	public Command parse(String[] s) {
		// TODO Auto-generated method stub
		if(s.length==2 && s[0].equalsIgnoreCase("REPLACEBC")){
			try{
				
			this.rep = Integer.parseUnsignedInt(s[1]);
			return new Replace(rep);
			
			}catch( NumberFormatException e){
				System.out.println("Error: Introduce la linea correcta");
			}
		}else
			return null;
		return null;
	}


	@Override
	public String textHelp() {
		// TODO Auto-generated method stub
		return "  REPLACEBC: Reemplaza la instruccion del numero introducido ";// + System.getProperty("line.Separator");
	}
	/**
	 * 
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return "REPLACEBC";
	}

}
