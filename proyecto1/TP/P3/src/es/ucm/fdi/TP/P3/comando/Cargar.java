package es.ucm.fdi.TP.P3.comando;

import java.io.FileNotFoundException;

import es.ucm.fdi.TP.P3.control.Controlador;
import es.ucm.fdi.TP.P3.excepciones.ErrorDeInicializacion;
import es.ucm.fdi.TP.P3.excepciones.FaltaElemento;
import es.ucm.fdi.TP.P3.excepciones.IndicesFueraDeRango;
import es.ucm.fdi.TP.P3.excepciones.PalabraIncorrecta;
/**
 * clase del comando cargar
 */
public class Cargar implements Comando {

	private String nombreFichero;
	/**
	 * Constructor de cargar
	 * @param nombre
	 */
	public Cargar(String nombre){
		nombreFichero=nombre;
	}
	
	
	/**
	 * ejecuta el cargar de controlador
	 * @param cont
	 * @throws FileNotFoundException
	 * @throws PalabraIncorrecta
	 * @throws FaltaElemento
	 * @throws IndicesFueraDeRango
	 * @throws ErrorDeInicializacion
	 */
	public void ejecuta(Controlador cont) 
			throws FileNotFoundException, PalabraIncorrecta, FaltaElemento, IndicesFueraDeRango, ErrorDeInicializacion{
		cont.cargar(this.nombreFichero);
	}
		
	
	/**
	 * parsea el comando
	 * @param cadenaComando
	 * @return com
	 */

	public Comando parsea(String[] cadenaComando) {
		Comando com = null;
		
		if(cadenaComando.length == 2)
			if(cadenaComando[0].equals("CARGAR")) //comprueba si es cargar
				com = new Cargar(cadenaComando[1]);
				
		return com;
		
	}

	/**
	 * devuelve un string con la opcion para el menu
	 * @return "CARGAR: Carga todos los datos de un fichero";	
	 */
	public String textoAyuda() {
		return "CARGAR: Carga todos los datos de un fichero";
	}
	

}
