package es.ucm.fdi.TP.P1.control;

import java.util.Scanner;

import es.ucm.fdi.TP.P1.logica.Mundo;


public class Controlador {


	private Scanner scanner;
	private Mundo mundo;

	
	public Controlador(Mundo m, Scanner in){
		mundo = m;
		scanner = in;
	}
	
	public void realizaSimulacion(){
		
		mundo.iniciar();
		String cadena = mundo.toString();
		System.out.print(cadena);
		System.out.println("Introduce un comando: ");
		String str = scanner.nextLine();
		str= str.toUpperCase();//PASA DE MAYUSCULAS A MINUSCULAS LA CADENA
		String[] CmdArray = str.split(" ");

		
		while(!CmdArray[0].equals("SALIR")){// ES IGUAL A =!
			
			//SWITCH CON LOS COMANDOS EJECUTANDOSE
			if(CmdArray[0].equals("PASO")){
				//ejecuta el comando Paso
				System.out.println("Comando paso");
				mundo.evoluciona();
				cadena="";
			    cadena = mundo.toString();
			    System.out.print(cadena);
			}
			else if(CmdArray[0].equals("INICIAR")){
				//ejecuta el comando Iniciar
				System.out.println("Comando iniciar");
				//mundo.inicializar();
				cadena="";
			    mundo.iniciar();
			    cadena = mundo.toString();
			    System.out.print(cadena);
				
				
			}
			else if(CmdArray[0].equals("CREARCELULA")){
				//ejecuta el comando Crearcelula
				int fila = Integer.parseInt(CmdArray[1]);
				int columna = Integer.parseInt(CmdArray[2]);
				System.out.println("Comando crearcelula");
				if(mundo.crearCelula(fila, columna))
					System.out.println("Creamos nueva celula en la posicion: (" + fila +","+ columna + ")");
				else
					System.out.println("No se ha podido crear la celula en la posicion: (" + fila +","+ columna + ")");
				
				cadena ="";
			    cadena = mundo.toString();
			    System.out.print(cadena);
			}
			else if(CmdArray[0].equals("ELIMINARCELULA")){
				//ejecuta el comando eliminarcelula
				int fila = Integer.parseInt(CmdArray[1]);
				int columna = Integer.parseInt(CmdArray[2]);
				System.out.println("Comando eliminarcelula");
				if(mundo.eliminarCelula(fila, columna))
					System.out.println("Eliminamos la celula de la posicion: (" + fila +","+ columna + ")");
				else
					System.out.println("No se ha podido eliminar la celula de la posicion: (" + fila +","+ columna + ")");
				
				cadena = "";
			    cadena = mundo.toString();
			    System.out.print(cadena);
			}
			else if(CmdArray[0].equals ("AYUDA")){
				//ejecuta el comando AYUDA
				System.out.println("Comando ayuda");
				ayuda();
				cadena = "";
			    cadena = mundo.toString();
			    System.out.print(cadena);
			    
			}
			else if(CmdArray[0].equals ("VACIAR")){
				//ejecuta el comando VACIAR
				mundo.vaciar();
				System.out.println("Vaciando la superficie.... ");
				cadena = "";
			    cadena = mundo.toString();
			    System.out.print(cadena);
				
			}else
				System.out.println("Introduce un comando correcto");
				
			System.out.println("Introduce un comando: ");
			str = scanner.nextLine();
			str = str.toUpperCase();//PASA DE MAYUSCULAS A MINUSCULAS LA CADENA
			CmdArray = str.split(" ");

		}
		
		System.out.println("Saliendo...");
		
	}
	
	public void ayuda(){
		
		System.out.println("POSIBLES COMANDOS: ");
		System.out.println("   PASO: realiza un paso en la simulacion");
		System.out.println("   SALIR: cierra la aplicacion");
		System.out.println("   INICIAR: inicia una nueva simulacion");
		System.out.println("   VACIAR: crea un mundo vacio");
		System.out.println("   CREARCELULA F C: crea una nueva celula en la posicion (f,c) si es posible");
		System.out.println("   ELIMINARCELULA F C: elimina una nueva celula en la posicion (f,c) si es posible");
	
	}
	
	

}
