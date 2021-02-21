package Practica3.ByteCode.OneParameter;

import Practica3.ByteCode.ByteCode;
/**
 * 
 * @author jorge
 *
 */
public class IfLeq extends ConditionalJumps{
	/**
	 * 
	 */
	public IfLeq(){
		super();
	}
	/**
	 * 
	 * @param n
	 */
	public IfLeq(int n){
		super(n);
	}
	
	@Override
	protected boolean compare(int n1, int n2) {//cima y subcima de la pila
		// TODO Auto-generated method stub
		return n2<=n1;
	}

	@Override
	protected ByteCode parseAux(String string1, String string2) {
		// TODO Auto-generated method stub
		ByteCode bc = null;
		if(string1.equalsIgnoreCase("IFLEQ")){
			int p = Integer.parseUnsignedInt(string2);
			bc = new IfLeq(p);
		}
		return bc;
	}

	@Override
	protected String toStringAux() {
		// TODO Auto-generated method stub
		return "IFLEQ";
	}

}
