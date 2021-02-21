package es.ucm.fdi.TP.P2.control;

import java.util.Scanner;

import es.ucm.fdi.TP.P2.comando.Comando;
import es.ucm.fdi.TP.P2.comando.ParserComandos;
import es.ucm.fdi.TP.P2.logica.Mundo;

public class Controlador {


	private Scanner scanner;
	private Mundo mundo;

	
	public Controlador(Mundo m, Scanner in){
		mundo = m;
		scanner = in;
	}
	
	/**
	 * realiza la simulacion del juego
	 */
	public void realizaSimulacion(){
		
		mundo.iniciar();//inicia el tablero
		
		while (!mundo.esSimulacionTerminada()){//hasta que no salgas de la simulacion
			
			String cadena = mundo.toString();
			System.out.print(cadena);
			System.out.println("Introduce un comando: ");
			String str = scanner.nextLine();
			str= str.toUpperCase();//PASA A MAYUSCULAS
			String[] palabras = str.split(" ");
			
			Comando com = ParserComandos.parseaComando(palabras);

			 if (com!=null) com.ejecuta(this.mundo);

			 else System.out.println("Comando incorrecto");
		}
	

	}
}
