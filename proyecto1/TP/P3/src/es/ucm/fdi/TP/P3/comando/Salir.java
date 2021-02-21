package es.ucm.fdi.TP.P3.comando;

import es.ucm.fdi.TP.P3.control.Controlador;

/**
 *clase del comando salir
 */
public class Salir implements Comando{

	/**
	 * ejecuta salir de controlador
	 * @param con
	 */
	public void ejecuta(Controlador con){
		con.salir();
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
