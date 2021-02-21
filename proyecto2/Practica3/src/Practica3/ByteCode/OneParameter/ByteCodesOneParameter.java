package Practica3.ByteCode.OneParameter;

import Practica3.ByteCode.ByteCode;
/**
 * 
 * @author jorge
 *
 */
public abstract class ByteCodesOneParameter extends ByteCode {
	
	protected int param;
	/**
	 * 
	 */
	public ByteCodesOneParameter(){};
	/**
	 * 
	 * @param p
	 */
	public ByteCodesOneParameter(int p){
		this.param = p;
	}

	@Override
	public ByteCode parse(String[] words) {
		if (words.length!=2)
			return null;
		else 
			return this.parseAux(words[0],words[1]);
	}
	/**
	 * 
	 * @param string1
	 * @param string2
	 * @return
	 */
	abstract protected ByteCode parseAux(String string1, String string2);
	/**
	 * 
	 */
	public String toString(){
		return this.toStringAux() + " " + this.param;
	}
	/**
	 * 
	 * @return
	 */
	abstract protected String toStringAux(); 
}
