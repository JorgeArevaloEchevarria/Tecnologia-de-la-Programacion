package Practica3.Term;
/**
 * 
 * @author jorge
 *
 */
public class TermParser {
	
	private final static Term[] terms = {new Variable(""), new Number(0)};
	/**
	 * 
	 * @param line
	 * @return
	 */
	public static Term parse(String line) {
		
		// elimina los caracteres en blanco iniciales    
		//line = line.trim();
		// admite varios blancos separando las palabras.
		//String []words = line.split(" +");
		boolean found = false;
		int i = 0;
		Term term = null;
		
		while(i < terms.length && !found){
			term = terms[i].parse(line);
			if(term!=null)
				found=true;
			else
				i++;
		}
		return term;
	}

}
