package es.ucm.fdi.TP.P1.main;

import java.util.Scanner;

import es.ucm.fdi.TP.P1.control.Controlador;
import es.ucm.fdi.TP.P1.logica.Mundo;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		Mundo m = new Mundo();
		Controlador c = new Controlador(m,s);
		c.realizaSimulacion();
	}
}
