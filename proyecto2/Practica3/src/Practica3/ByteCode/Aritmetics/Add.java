package Practica3.ByteCode.Aritmetics;
/**
 * 
 */
import Practica3.ByteCode.ByteCode;
/**
 * 
 * @author jorge
 *
 */
public class Add extends Aritmetics {

	@Override
	protected int Operacion(int i, int j) {//cima y subcima de la pila
		// TODO Auto-generated method stub
		return j + i;
	}

	@Override
	protected ByteCode parseAux(String string1) {
		ByteCode bc = null;
		if(string1.equalsIgnoreCase("+"))
			bc = new Add();
		else if(string1.equalsIgnoreCase("ADD"))
			bc = new Add();
		return bc;
	}

	@Override
	protected String toStringAux() {
		// TODO Auto-generated method stub
		return "ADD";
	}

}
