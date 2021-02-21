package Practica3.ByteCode.Aritmetics;

import Practica3.ByteCode.ByteCode;
import Practica3.Excepciones.DivisionByZero;

/**
 * 
 * @author jorge
 *
 */
public class Div extends Aritmetics {

	@Override
	protected int Operacion(int i, int j)throws DivisionByZero{
		// TODO Auto-generated method stub
		if(i==0)
			throw new DivisionByZero("Error al dividir por 0");
		return j/i;
	}

	@Override
	protected ByteCode parseAux(String string1) {
		// TODO Auto-generated method stub
		ByteCode bc = null;
		if(string1.equalsIgnoreCase("/"))
			bc = new Div();
		else if(string1.equalsIgnoreCase("DIV"))
			bc = new Div();
		return bc;
	}

	@Override
	protected String toStringAux() {
		// TODO Auto-generated method stub
		return "DIV";
	}

}
