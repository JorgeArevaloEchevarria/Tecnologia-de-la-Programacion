package es.ucm.fdi.TP.P3.comando;

import es.ucm.fdi.TP.P3.control.Controlador;


public class Vaciar implements Comando{

	/**
	 * ejecuta vaciar del controlador
	 * @param con
	 */
	public void ejecuta(Controlador con){
		con.vaciar();
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
