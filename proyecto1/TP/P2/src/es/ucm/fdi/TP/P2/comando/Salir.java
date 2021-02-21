package es.ucm.fdi.TP.P2.comando;

import es.ucm.fdi.TP.P2.logica.Mundo;

public class Salir extends Comando{

	/**
	 * ejecuta paso de mundo
	 * @param mundo
	 */
	public void ejecuta(Mundo mundo){
		mundo.salir();
	}

	/**
	 * comprueba si el comando escrito es salir
	 * @param cadenaComando
	 * @return com
	 */
	public Comando parsea(String[] cadenaComando){
		
		Comando com = null;
		
		if(cadenaComando.length == 1)
			if(cadenaComando[0].equals("SALIR"))//comprueba si es salir
				com = new Salir();
		
		return com;
	}
	
	/**
	 * muestra la ayuda del comando
	 * @return "SALIR: Cierra el programa"
	 */
	public String textoAyuda(){
		return "SALIR: Cierra el programa";
	}

}
