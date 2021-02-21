package Practica3;
 /**
  * 
  * @author jorge
  *
  */
 public class Memory{
	 
	private Integer[] memory;
	private int size;
	private final static int MAX_MEM = 200;
	private boolean empty;
	/**
	 * 
	 */
	public Memory(){
	 
	this.empty = true;
	this.memory = new Integer[Memory.MAX_MEM];
	this.size = Memory.MAX_MEM;
	
	for (int i=0; i< this.size; i++)
		this.memory[i]=null;
	}
	/**
	 * 
	 * @param pos
	 */
	private void resize(int pos){
	// pone como nueva capacidad del array pos*2, en caso de que pos >= this.size
		if(pos >=this.size)
			this.size=pos*2;	
	}
	/**
	 * 
	 * @param pos
	 * @return boolean
	 */
	public boolean accesoCorrecto(int pos){//mira si hay contenido en esa posicion
		
		if(this.memory[pos]==null)
			return false;
		else 
			return true;
	}
	/**
	 * 
	 * @param pos
	 * @param value
	 * @return boolean
	 */
	public boolean write(int pos, int value){//escribe en memoria
		
		resize(pos);
		
		if(pos>=0){
			memory[pos]= value;
			empty = false;//al escribir ya no esta vacia la memoria
			return true;
		}
		else
			return false;
	}
	/**
	 * 
	 * @param pos
	 * @return memory[pos]
	 */
	public int read(int pos){//lee de memoria
		return memory[pos];	
	}
	/**
	 * 
	 */
	public String toString(){
		String s = "";
		
		if (this.empty)
			return "<vacia>";
		
		else {
			
			for (int i = 0; i < this.size; i++)
				if (this.memory[i]!=null)
					s = s + " [" + i +"]:" + this.memory[i];
		 }
		 return s;
	}
		
}
