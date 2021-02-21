package es.ucm.fdi.TP.P3.comando;

import es.ucm.fdi.TP.P3.control.Controlador;
import es.ucm.fdi.TP.P3.excepciones.CelulaExistente;
import es.ucm.fdi.TP.P3.excepciones.FormatoNumericoIncorrecto;
import es.ucm.fdi.TP.P3.excepciones.IndicesFueraDeRango;

public class CrearCelula implements Comando {

	private int fila;
	private int columna;
	/**
	 * constructor de crearCelula
	 * @param f
	 * @param c
	 */
	public CrearCelula(int f,int c){
		fila = f;
		columna = c;
	}
	
	/**
	 * ejecuta el crearCelula del controlador
	 * @param con
	 * @throws IndicesFueraDeRango
	 * @throws CelulaExistente
	 * @throws FormatoNumericoIncorrecto
	 */
	public void ejecuta(Controlador con) throws IndicesFueraDeRango, CelulaExistente, FormatoNumericoIncorrecto{
		con.crearCelula(fila, columna);		
	}
	
	/**
	 * parsea el comando
	 * @param cadenaComando
	 * @throws FormatoNumericoIncorrecto
	 * @return com
	 */
	public Comando parsea(String[] cadenaComando) throws FormatoNumericoIncorrecto {
		
		Comando com = null;
		
		try{
			if(cadenaComando.length == 3)
				if(cadenaComando[0].equals("CREARCELULA"))//comprueba si es paso
					com = new CrearCelula(Integer.parseInt(cadenaComando[1]),Integer.parseInt(cadenaComando[2]));
					
		}catch(NumberFormatException e){
			throw new FormatoNumericoIncorrecto("");
		}
		
		return com;
	}
	
	/**
	 * devuelve un string con la opcion para el menu
	 * @return "CREARCELULA: Te crea una celula en la posicion f,c";
	 */
	public String textoAyuda() {
		return "CREARCELULA: Te crea una celula en la posicion f,c";
	}

}
