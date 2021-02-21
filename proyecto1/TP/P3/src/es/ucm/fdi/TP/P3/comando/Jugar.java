package es.ucm.fdi.TP.P3.comando;

import es.ucm.fdi.TP.P3.control.Controlador;
import es.ucm.fdi.TP.P3.excepciones.ErrorDeInicializacion;
import es.ucm.fdi.TP.P3.logica.Mundo;
import es.ucm.fdi.TP.P3.logica.MundoComplejo;
import es.ucm.fdi.TP.P3.logica.MundoSimple;

/**
 *clase del comando jugar
 */
public class Jugar implements Comando {
	
	private Mundo mundo;

	/**
	 * constructor de jugar
	 * @param m
	 */
	public Jugar(Mundo m){
		mundo = m;
	}

	/**
	 * ejecuta jugar de controlador
	 * @param cont
	 * @throws ErrorDeInicializacion
	 */
	public void ejecuta(Controlador cont) throws ErrorDeInicializacion{
		cont.juega(this.mundo);
	}

	/**
	 * parsea el comando
	 * @param cadenaComando
	 * @throws ErrorDeInicializacion
	 * @return cadenaComando
	 */
	public Comando parsea(String[] cadenaComando) throws ErrorDeInicializacion {
		
		Comando com = null;
		int nfilas = Integer.parseInt(cadenaComando[2]);
		int ncolumnas = Integer.parseInt(cadenaComando[3]);
		int cSimples = Integer.parseInt(cadenaComando[4]);

		if(nfilas*ncolumnas < cSimples)
			throw new ErrorDeInicializacion("");

		if(cadenaComando.length == 5 || cadenaComando.length == 6)
			if(cadenaComando[0].equals("JUGAR")) //comprueba si es iniciar
				if(cadenaComando[1].equals("SIMPLE"))
					com = new Jugar(new MundoSimple(nfilas, ncolumnas, cSimples));

				else if(cadenaComando[1].equals("COMPLEJO")){
					int cComplejas = Integer.parseInt(cadenaComando[5]);
					if(nfilas*ncolumnas < cSimples + cComplejas)
						throw new ErrorDeInicializacion("");
					com = new Jugar(new MundoComplejo(nfilas, ncolumnas, cSimples, cComplejas));	
				}
		
		return com;
	}

	/**
	 * devuelve un string con la opcion para el menu
	 * @return "JUGAR: Crea el tablero con el tamaño de las filas y columnas, y con el numero celulas introducidas "
	 */
	public String textoAyuda() {
		return "JUGAR: Crea el tablero con el tamaño de las filas y columnas, y con el numero celulas introducidas ";
	}

}
