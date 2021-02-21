package es.ucm.fdi.TP.P3.main;

import java.io.IOException;
import java.util.Scanner;

import es.ucm.fdi.TP.P3.control.Controlador;
import es.ucm.fdi.TP.P3.excepciones.CelulaExistente;
import es.ucm.fdi.TP.P3.excepciones.ComandoIncorrecto;
import es.ucm.fdi.TP.P3.excepciones.ErrorDeInicializacion;
import es.ucm.fdi.TP.P3.excepciones.FaltaElemento;
import es.ucm.fdi.TP.P3.excepciones.FormatoNumericoIncorrecto;
import es.ucm.fdi.TP.P3.excepciones.IndicesFueraDeRango;
import es.ucm.fdi.TP.P3.logica.Mundo;
import es.ucm.fdi.TP.P3.logica.MundoSimple;

/**
 * Clase main
 */
public class Main {
	
	/**
	 * ejecuta el main
	 * @param args
	 * @throws IndicesFueraDeRango 
	 * @throws CelulaExistente 
	 * @throws FormatoNumericoIncorrecto 
	 * @throws FaltaElemento 
	 * @throws ComandoIncorrecto 
	 * @throws IOException 
	 * @throws ErrorDeInicializacion 
	 */
	public static void main(String[] args) 
			throws IndicesFueraDeRango, CelulaExistente, FormatoNumericoIncorrecto, FaltaElemento, 
			ComandoIncorrecto,IOException, ErrorDeInicializacion {

		Scanner s = new Scanner(System.in);
		Mundo m = new MundoSimple(0,0,0);
		Controlador c = new Controlador(m,s);
		c.realizaSimulacion();
	}
}
