package Practica3;

/**
 * 
 * @author jorge
 *
 */
public class SourceProgram {
	
	private String[] sProgram;
	private static final int MAX_LINEAS = 100;
	private int numL;
	/**
	 * 
	 */
	public SourceProgram(){
		this.sProgram = new String[MAX_LINEAS];
		this.numL = 0;
	}
	/**
	 * 
	 * @param str
	 * @return
	 */
	public boolean addLinea(String str){//añade una linea de codigo al programa
		
		if(numL<MAX_LINEAS){
			this.sProgram[this.numL]=str;
			this.numL++;
			return true;
		}else
			return false;
	}
	/**
	 * 
	 * @return
	 */
	public int getNumeroLineas(){//devuelve el numero de programas
		return this.numL;
	}  
	/**
	 * 
	 * @param i
	 * @return
	 */
	public String getLinea(int i){//devuelve una linea programa
		return this.sProgram[i];
	}  
	/**
	 * 
	 * @return
	 */
	public boolean llena(){
		if(this.numL<MAX_LINEAS)
			return true;
		else
			return false;
	}
	/**
	 * 
	 */
	public String toString(){//se encarga de mostrar por pantalla
		
		String s = System.getProperty("line.separator") + "Programa fuente almacenado: ";
		
		for(int i=0;i<this.numL;i++)
			s = s + System.getProperty("line.separator") + i + ": " + this.sProgram[i];
			
		return s;
	} 
}
