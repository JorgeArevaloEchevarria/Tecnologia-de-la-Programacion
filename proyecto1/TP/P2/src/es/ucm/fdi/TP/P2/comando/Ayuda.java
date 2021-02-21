package es.ucm.fdi.TP.P2.comando;

import es.ucm.fdi.TP.P2.logica.Mundo;

public class Ayuda extends Comando{
	
	/**
	 * ejecuta el comando ayuda
	 * @param mundo
	 */
	public void ejecuta(Mundo mundo){
		System.out.println(mundo.ayuda());
	}
	
	/**
	 * comprueba si la cadena es el comando ayuda
	 * @param cadenaComando
	 * @return com
	 */
	public Comando parsea(String[] cadenaComando){

		Comando com = null;
		
		if(cadenaComando.length == 1)//comprueba si es una cadena
			if(cadenaComando[0].equals("AYUDA")){//comprueba si es ayuda
				com = new Ayuda();
				
			}
		return com;
	}
	
	/**
	 * muestra la ayuda del comando
	 */
	public String textoAyuda(){
		return "AYUDA: Muestra un menu de ayuda";
	}
	
	

}
