package es.ucm.fdi.TP.P3.comando;

import java.io.FileNotFoundException;
import java.io.IOException;

import es.ucm.fdi.TP.P3.control.Controlador;
import es.ucm.fdi.TP.P3.excepciones.CelulaExistente;
import es.ucm.fdi.TP.P3.excepciones.ErrorDeInicializacion;
import es.ucm.fdi.TP.P3.excepciones.FaltaElemento;
import es.ucm.fdi.TP.P3.excepciones.FormatoNumericoIncorrecto;
import es.ucm.fdi.TP.P3.excepciones.IndicesFueraDeRango;
import es.ucm.fdi.TP.P3.excepciones.PalabraIncorrecta;

/**
 *Interfaz de Comando
 */
public interface Comando{
/**
 * ejecuta del controlador el metodo de cada comando
 * @param con
 * @throws FileNotFoundException
 * @throws IndicesFueraDeRango
 * @throws CelulaExistente
 * @throws FormatoNumericoIncorrecto
 * @throws PalabraIncorrecta
 * @throws FaltaElemento
 * @throws IOException
 * @throws ErrorDeInicializacion
 */
	public abstract void ejecuta(Controlador con) 
			throws FileNotFoundException, IndicesFueraDeRango, CelulaExistente, FormatoNumericoIncorrecto, 
			PalabraIncorrecta, FaltaElemento, IOException, ErrorDeInicializacion;
	/**
	 * parsea el comando
	 * @param cadenaComando
	 * @return
	 * @throws FormatoNumericoIncorrecto
	 * @throws ErrorDeInicializacion
	 */
	public abstract Comando parsea(String[] cadenaComando) throws FormatoNumericoIncorrecto, ErrorDeInicializacion;
	/**
	 * devuelve un string con la opcion para el menu
	 * @return string
	 */
	public abstract String textoAyuda();
}
