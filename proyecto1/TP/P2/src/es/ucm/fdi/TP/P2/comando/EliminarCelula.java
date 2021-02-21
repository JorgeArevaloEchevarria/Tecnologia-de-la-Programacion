package es.ucm.fdi.TP.P2.comando;

import es.ucm.fdi.TP.P2.logica.Mundo;



public class EliminarCelula extends Comando{
	
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
	 * ejecuta crearcelulasimple de mundo
	 * @param mundo
	 */
	public void ejecuta(Mundo mundo){
		mundo.eliminarCelula(fila,columna);
	}
	
	/**
	 * comprueba si el comando escrito es EliminarCelula
	 * @param cadenaComando
	 * @return com
	 */
	public Comando parsea(String[] cadenaComando){
		
		Comando com = null;
		
		if(cadenaComando.length == 3)
			if(cadenaComando[0].equals("ELIMINARCELULA"))
				com = new EliminarCelula(Integer.parseInt(cadenaComando[1]),Integer.parseInt(cadenaComando[2]));
				//crea el comando pasando a enteros la fila y columna introducidos
		
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
