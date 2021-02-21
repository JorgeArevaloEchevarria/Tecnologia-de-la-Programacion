package es.ucm.fdi.TP.P2.comando;

import es.ucm.fdi.TP.P2.logica.Mundo;

public class CrearCelulaCompleja extends Comando {
	
	private int fila;
	private int columna;
	
	/**
	 * asigna el valor de la fila y columna
	 * @param f
	 * @param c
	 */
	public CrearCelulaCompleja(int f,int c){
		fila = f;
		columna = c;
	}
	
	/**
	 * ejecuta crearcelulacompleja de mundo
	 * @param mundo
	 */
	public void ejecuta(Mundo mundo) {
		mundo.crearCelulaCompleja(fila,columna);
	}

	/**
	 * comprueba si el comando escrito es Crearcelulacompleja
	 * @param cadenaComando
	 * @return com
	 */
	public Comando parsea(String[] cadenaComando) {
		
		Comando com = null;
		
		if(cadenaComando.length == 3)
			if(cadenaComando[0].equals("CREARCELULACOMPLEJA"))
				com = new CrearCelulaCompleja(Integer.parseInt(cadenaComando[1]),Integer.parseInt(cadenaComando[2]));
				//crea el comando pasando a enteros la fila y columna introducidos
		
		return com;
	}

	/**
	 * muestra la ayuda del comando
	 * @return "CREARCELULACOMPLEJA: Crea una celula compleja";
	 */
	public String textoAyuda() {

		return "CREARCELULACOMPLEJA: Crea una celula compleja";
	}

}
