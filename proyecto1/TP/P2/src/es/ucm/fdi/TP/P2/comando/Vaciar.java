package es.ucm.fdi.TP.P2.comando;

import es.ucm.fdi.TP.P2.logica.Mundo;

public class Vaciar extends Comando{

	/**
	 * ejecuta vaciar de mundo 
	 * @param mundo
	 */
	public void ejecuta(Mundo mundo){
		mundo.vaciar();
	}

	/**
	 * comprueba si el comando escrito es vaciar
	 * @param cadenaComando
	 * @return com
	 */
	public Comando parsea(String[] cadenaComando){
		
		Comando com = null;
		
		if(cadenaComando.length == 1)
			if(cadenaComando[0].equals("VACIAR"))//comprueba si es salir
				com = new Vaciar();
		
		return com;
	}

	/**
	 * muestra la ayuda del comando
	 * @return "VACIAR: Vacia el mundo de celulas"
	 */
	public String textoAyuda(){
		return "VACIAR: Vacia el mundo de celulas";
	}

}
