package Practica3.ByteCode.OneParameter;

import Practica3.ByteCode.ByteCode;
/**
 * 
 * @author jorge
 *
 */
public class IfEq extends ConditionalJumps{
	/**
	 * 
	 */
	public IfEq(){
		super();
	}
	/**
	 * 
	 * @param n
	 */
	public IfEq(int n){
		super(n);
	}
	@Override
	protected boolean compare(int n1, int n2) {//cima y subcima de la pila
		// TODO Auto-generated method stub
		//si no es igual  salta, y devuelve un booleano
		return (n2==n1);
	}

	@Override
	protected ByteCode parseAux(String string1, String string2) {
		// TODO Auto-generated method stub
		ByteCode bc = null;
		if(string1.equalsIgnoreCase("IFEQ")){
			int p = Integer.parseUnsignedInt(string2);
			bc = new IfEq(p);
		}
		return bc;
	}

	@Override
	protected String toStringAux() {
		// TODO Auto-generated method stub
		return "IFEQ";
	}

}
