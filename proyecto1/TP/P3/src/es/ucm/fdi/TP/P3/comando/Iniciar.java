package es.ucm.fdi.TP.P3.comando;

import es.ucm.fdi.TP.P3.control.Controlador;
import es.ucm.fdi.TP.P3.excepciones.ErrorDeInicializacion;
/**
 * clase del comando iniciar
 */
public class Iniciar implements Comando{

	/**
	 * ejecuta iniciar del controlador
	 * @param con
	 * @throws ErrorDeInicializacion 
	 */
	public void ejecuta(Controlador con) throws ErrorDeInicializacion{
		con.iniciar();
	}

	/**
	 *parsea el comando
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
	 * devuelve un string con la opcion para el menu
	 * @return "INICIAR: Inicia el mundo de forma aleatoria"
	 */
	public String textoAyuda(){
		return "INICIAR: Inicia el mundo de forma aleatoria";
	}

}
