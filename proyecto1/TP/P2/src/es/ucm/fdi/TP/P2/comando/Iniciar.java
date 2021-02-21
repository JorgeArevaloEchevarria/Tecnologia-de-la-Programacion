package es.ucm.fdi.TP.P2.comando;

import es.ucm.fdi.TP.P2.logica.Mundo;

public class Iniciar extends Comando{

	/**
	 * ejecuta iniciar de mundo
	 * @param mundo
	 */
	public void ejecuta(Mundo mundo){
		mundo.iniciar();
	}

	/**
	 *comprueba si el comando escrito es iniciar
	 *@param cadenaComando
	 *@return com
	 */
	public Comando parsea(String[] cadenaComando){
		
		Comando com = null;
		
		if(cadenaComando.length == 1)
			if(cadenaComando[0].equals("INICIAR")) //comprueba si es iniciar
				com = new Iniciar();//
		
		return com;
	}

	/**
	 * muestra la ayuda del comando
	 * @return "INICIAR: Inicia el mundo de forma aleatoria"
	 */
	public String textoAyuda(){
		return "INICIAR: Inicia el mundo de forma aleatoria";
	}

}
