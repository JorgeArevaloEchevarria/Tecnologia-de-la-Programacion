package es.ucm.fdi.TP.P2.comando;

import es.ucm.fdi.TP.P2.logica.Mundo;

public class CrearCelulaSimple extends Comando{
	
	private int fila;
	private int columna;
	
	/**
	 * asigna el valor de la fila y columna
	 * @param f
	 * @param c
	 */
	public CrearCelulaSimple(int f,int c){
		fila = f;
		columna = c;
	}
	
	/**
	 * ejecuta crearcelulasimple de mundo
	 * @param mundo
	 */
	public void ejecuta(Mundo mundo){
		mundo.crearCelulaSimple(fila,columna);
	}

	/**
	 * comprueba si el comando escrito es CrearcelulaSimple
	 * @param cadenaComando
	 * @return com
	 */
	public Comando parsea(String[] cadenaComando){
		
		Comando com = null;
		
		if(cadenaComando.length == 3)
			if(cadenaComando[0].equals("CREARCELULASIMPLE"))
				com = new CrearCelulaSimple(Integer.parseInt(cadenaComando[1]),Integer.parseInt(cadenaComando[2]));
				//crea el comando pasando a enteros la fila y columna introducidos
				
		
		return com;
	}

	/**
	 * muestra la ayuda del comando
	 * @return "CREARCELULASIMPLE: Crea una celula simple";
	 */
	public String textoAyuda(){

		return "CREARCELULASIMPLE: Crea una celula simple";
	}

}
