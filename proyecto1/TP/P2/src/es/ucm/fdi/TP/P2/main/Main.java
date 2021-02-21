package es.ucm.fdi.TP.P2.main;

import java.util.Scanner;

import es.ucm.fdi.TP.P2.control.Controlador;
import es.ucm.fdi.TP.P2.logica.Mundo;

public class Main {
	
	/**
	 * ejecuta el main
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		Mundo m = new Mundo();
		Controlador c = new Controlador(m,s);
		c.realizaSimulacion();
	}
}
