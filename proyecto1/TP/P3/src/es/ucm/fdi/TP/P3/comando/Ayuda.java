package es.ucm.fdi.TP.P3.comando;

import es.ucm.fdi.TP.P3.control.Controlador;

/**
 *clase que muestra un menu de ayuda
 */
public class Ayuda implements Comando{
	
	/**
	 * Llama al controlador para printar el menu de ayuda
	 * @param con
	 */
	public void ejecuta(Controlador con){
		System.out.println(con.ayuda());
	}
	
	/**
	 * Parsea el comando
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
	 * devuelve un string con la opcion para el menu
	 * @return "AYUDA: Muestra un menu de ayuda";
	 */
	public String textoAyuda(){
		return "AYUDA: Muestra un menu de ayuda";
	}
}
