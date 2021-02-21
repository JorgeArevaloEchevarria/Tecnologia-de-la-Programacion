package Practica3.ByteCode.OneParameter;

import Practica3.ByteCode.ByteCode;
/**
 * 
 * @author jorge
 *
 */
public class IfNeq extends ConditionalJumps{
	/**
	 * 
	 */
	public IfNeq(){
		super();
	}
	/**
	 * 
	 * @param n
	 */
	public IfNeq(int n){
		super(n);
	}
	
	@Override
	protected boolean compare(int n1, int n2) {//cima y subcima de la pila
		// TODO Auto-generated method stub
		//si son distintos  salta y devuelve el booleano
		return n2!=n1;
	}

	@Override
	protected ByteCode parseAux(String string1, String string2) {
		// TODO Auto-generated method stub
		ByteCode bc = null;
		if(string1.equalsIgnoreCase("IFNEQ")){
			int p = Integer.parseUnsignedInt(string2);
			bc = new IfNeq(p);
		}
		return bc;
	}

	@Override
	protected String toStringAux() {
		// TODO Auto-generated method stub
		return "IFNEQ";
	}

}
