package es.ucm.fdi.TP.P3.comando;

import java.io.IOException;

import es.ucm.fdi.TP.P3.control.Controlador;

/**
 * Clase del comando guardar
 *
 */
public class Guardar implements Comando {

	private String nombreFichero;
	/**
	 * Constructor de guardar
	 * @param nombre
	 */
	public Guardar(String nombre){
		nombreFichero=nombre;	
	}
	
	/**
	 * ejecuta el guardar del controlador
	 * @param cont
	 * @throws IOException
	 */
	public void ejecuta(Controlador cont) throws IOException{
		cont.guardar(this.nombreFichero);
		
	}

	/**
	 * parsea el comando
	 * @param cadenaComando
	 * @return com
	 */
	public Comando parsea(String[] cadenaComando) {
		Comando com = null;
		
		if(cadenaComando.length == 2)
			if(cadenaComando[0].equals("GUARDAR")) //comprueba si es guardar
				com = new Guardar(cadenaComando[1]);//
		
		return com;
		
	}

	/**
	 * devuelve un string con la opcion para el menu
	 * @return "GUARDAR: Guarda todos los datos de la partida en un fichero"
	 */
	public String textoAyuda() {
		return "GUARDAR: Guarda todos los datos de la partida en un fichero";
	}
	

}