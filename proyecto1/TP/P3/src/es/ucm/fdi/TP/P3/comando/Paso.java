package es.ucm.fdi.TP.P3.comando;

import es.ucm.fdi.TP.P3.control.Controlador;

/**
 *clase de paso
 */
public class Paso implements Comando{
	
	/**
	 * ejecuta paso de controlador
	 * @param con
	 */
	public void ejecuta(Controlador con){
		con.paso();
	}
	
	/**
	 * comprueba si el comando escrito es paso
	 * @param cadenaComando
	 * @return com
	 */
	public Comando parsea(String[] cadenaComando){
		
		Comando com = null;
		
		if(cadenaComando.length == 1)
			if(cadenaComando[0].equals("PASO"))//comprueba si es paso
				com = new Paso();
				
		return com;
	}
	
	/**
	 * muestra la ayuda del comando 
	 * @return "PASO: Evolucion del mundo"
	 */
	public String textoAyuda(){
		return "PASO: Evolucion del mundo";
	}

}
