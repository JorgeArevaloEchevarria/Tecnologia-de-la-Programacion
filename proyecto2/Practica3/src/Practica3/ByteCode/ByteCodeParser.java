package Practica3.ByteCode;

import Practica3.ByteCode.Aritmetics.Add;
import Practica3.ByteCode.Aritmetics.Div;
import Practica3.ByteCode.Aritmetics.Mul;
import Practica3.ByteCode.Aritmetics.Sub;
import Practica3.ByteCode.OneParameter.Goto;
import Practica3.ByteCode.OneParameter.IfEq;
import Practica3.ByteCode.OneParameter.IfLe;
import Practica3.ByteCode.OneParameter.IfLeq;
import Practica3.ByteCode.OneParameter.IfNeq;
import Practica3.ByteCode.OneParameter.Load;
import Practica3.ByteCode.OneParameter.Push;
import Practica3.ByteCode.OneParameter.Store;

/**
 * 
 * @author jorge
 *
 */
public class ByteCodeParser {
	/**
	 * 
	 */
	private final static ByteCode[] bytes = {new Store(), new Load(), new Push(), new Goto(),
			new IfNeq(), new IfEq(), new IfLeq(), new IfLe(), new Sub(), new Add(), new Mul(),
			new Div(), new End(), new Halt(), new Out()};
	/**
	 * 
	 * @param s
	 * @return bit
	 */
	 public static ByteCode parse(String s){//devuelve el null si es incorrecto
		
		 
		// elimina los caracteres en blanco iniciales    
			s = s.trim();
			// admite varios blancos separando las palabras.
			String []words = s.split(" +");
			
			boolean found = false;
			int i = 0;
			ByteCode bit = null;
			
			while(i < bytes.length && !found){
				bit = bytes[i].parse(words);
				if(bit!=null)
					found=true;
				else
					i++;
			}
			return bit;
		}
		 	
}
