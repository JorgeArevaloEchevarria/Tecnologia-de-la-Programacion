package es.ucm.fdi.TP.P2.comando;

import es.ucm.fdi.TP.P2.logica.Mundo;

public class Paso extends Comando{

	public void ejecuta(Mundo mundo){/**ejecuta paso de mundo**/
		mundo.evoluciona();
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
