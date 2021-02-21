package Practica3.ByteCode.Aritmetics;

import Practica3.ByteCode.ByteCode;
/**
 * 
 * @author jorge
 *
 */
public class Mul extends Aritmetics  {

	@Override
	protected int Operacion(int i, int j) {
		// TODO Auto-generated method stub
		return j*i;
	}

	@Override
	protected ByteCode parseAux(String string1) {
		// TODO Auto-generated method stub
		ByteCode bc = null;
		if(string1.equalsIgnoreCase("*"))
			bc = new Mul();
		if(string1.equalsIgnoreCase("MUL"))
			bc = new Mul();
		return bc;
	}

	@Override
	protected String toStringAux() {
		// TODO Auto-generated method stub
		return "MUL";
	}

}
