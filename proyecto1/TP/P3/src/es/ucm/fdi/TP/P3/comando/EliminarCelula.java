package es.ucm.fdi.TP.P3.comando;

import es.ucm.fdi.TP.P3.control.Controlador;
import es.ucm.fdi.TP.P3.excepciones.CelulaExistente;
import es.ucm.fdi.TP.P3.excepciones.FormatoNumericoIncorrecto;
import es.ucm.fdi.TP.P3.excepciones.IndicesFueraDeRango;

/**
 * clase del comando EliminarCelula
 *
 */

public class EliminarCelula implements Comando{
	
	private int fila;
	private int columna;
	
	/**
	 * asigna la fila y la columna
	 * @param f
	 * @param c
	 */
	public EliminarCelula(int f,int c){
		fila = f;
		columna = c;
	}
	
	/**
	 * ejecuta crearcelulasimple del controlador
	 * @param con
	 * @throws IndicesFueraDeRango 
	 * @throws CelulaExistente 
	 */
	public void ejecuta(Controlador con) throws CelulaExistente, IndicesFueraDeRango{
		con.eliminarCelula(fila, columna);
	}
	
	/**
	 * parsea el comando
	 * @param cadenaComando
	 * @return cadenaComando
	 * @throws FormatoNumericoIncorrecto 
	 */
	public Comando parsea(String[] cadenaComando) throws FormatoNumericoIncorrecto{
		
		Comando com = null;
		try{
			if(cadenaComando.length == 3)
				if(cadenaComando[0].equals("ELIMINARCELULA"))
					com = new EliminarCelula(Integer.parseInt(cadenaComando[1]),Integer.parseInt(cadenaComando[2]));
				
		}catch(NumberFormatException e){
			throw new FormatoNumericoIncorrecto("");
		}
		return com;

	}

	/**
	 * muestra la ayuda del comando
	 * @return "ELIMINARCELULA: Elimina una celula de la posicion dicha"
	 */
	public String textoAyuda(){
		return "ELIMINARCELULA: Elimina una celula de la posicion dicha";
	}

}
