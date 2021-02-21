package es.ucm.fdi.TP.P3.control;

import java.io.*;
import java.util.Scanner;

import es.ucm.fdi.TP.P3.comando.Comando;
import es.ucm.fdi.TP.P3.comando.ParserComandos;
import es.ucm.fdi.TP.P3.excepciones.CelulaExistente;
import es.ucm.fdi.TP.P3.excepciones.ComandoIncorrecto;
import es.ucm.fdi.TP.P3.excepciones.ErrorDeInicializacion;
import es.ucm.fdi.TP.P3.excepciones.FaltaElemento;
import es.ucm.fdi.TP.P3.excepciones.FormatoNumericoIncorrecto;
import es.ucm.fdi.TP.P3.excepciones.IndicesFueraDeRango;
import es.ucm.fdi.TP.P3.excepciones.PalabraIncorrecta;
import es.ucm.fdi.TP.P3.logica.Mundo;
import es.ucm.fdi.TP.P3.logica.MundoComplejo;
import es.ucm.fdi.TP.P3.logica.MundoSimple;

/**
 *Clase encargada de controlar el mundo
 */
public class Controlador {


	private Scanner scanner;
	private Mundo mundo;
	private boolean simulacionTerminada = false;
	/**
	 * constructor del controlador
	 * @param m
	 * @param in
	 */
	public Controlador(Mundo m, Scanner in){
		mundo = m;
		scanner = in;
	}
	
	/**
	 * realiza la simulacion del juego
	 * @throws IndicesFueraDeRango 
	 * @throws CelulaExistente 
	 * @throws FormatoNumericoIncorrecto 
	 * @throws FaltaElemento 
	 * @throws ComandoIncorrecto 
	 * @throws IOException 
	 * @throws ErrorDeInicializacion 
	 */
	public void realizaSimulacion() 
			throws IndicesFueraDeRango, CelulaExistente, FormatoNumericoIncorrecto, FaltaElemento, 
			ComandoIncorrecto, IOException, ErrorDeInicializacion{
		
		iniciar();//inicia el tablero
		
		while (!this.simulacionTerminada){//hasta que no salgas de la simulacion
			
			String cadena = mundo.toString();
			System.out.print(cadena);
			System.out.println("Introduce un comando: ");
			String str = scanner.nextLine();
			str= str.toUpperCase();//PASA A MAYUSCULAS
			String[] palabras = str.split(" ");
			
			try{
				Comando com = ParserComandos.parseaComando(palabras);

				if (com!=null)
					com.ejecuta(this);
			
			}catch(FileNotFoundException e){
				System.out.println("No se ha podido abrir el archivo.");
				
			}catch(IndicesFueraDeRango e){
				System.out.println("El rango que has elegido no concuerda con el indicado.");
			
			}catch(CelulaExistente e){
				System.out.println("Ya hay una celula en la posicion que has escogido.");
			
			}catch(FormatoNumericoIncorrecto e){
				System.out.println("Debes introducir un numero.");
			
			}catch(PalabraIncorrecta e){
				System.out.println("El archivo esta mal configurado y no se ha podido cargar.");
			
			}catch(FaltaElemento e){
				System.out.println("Falta un elemento en el archivo.");
			
			}catch(ComandoIncorrecto e){
				System.out.println("Comando Incorrecto.Escribe ayuda y veras las opciones!");
			
			}catch(ErrorDeInicializacion e){
				System.out.println("No caben las celulas en las dimensiones que has indicado.");
			}
		
			
		}
	}
	/**
	 * llama a ayuda de mundo
	 * @return mundo.ayuda()
	 */
	public String ayuda(){
		return mundo.ayuda();
	}
	
	/**
	 * llama a eliminarCelula  de mundo
	 * @param f
	 * @param c
	 * @throws CelulaExistente
	 * @throws IndicesFueraDeRango
	 */
	public void eliminarCelula(int f,int c) 
			throws CelulaExistente, IndicesFueraDeRango{
		mundo.eliminarCelula(f, c);
	}
	
	/**
	 * llama al iniciar de mundo
	 * @throws ErrorDeInicializacion
	 */
	public void iniciar() throws ErrorDeInicializacion{
		mundo.inicializaMundo();
	}
	
	/**
	 * llama al evolucionar de mundo
	 */
	public void paso(){
		mundo.evoluciona();
	}
	
	/**
	 * llama a crearCelula de mundo
	 * @param f
	 * @param c
	 * @throws IndicesFueraDeRango
	 * @throws CelulaExistente
	 * @throws FormatoNumericoIncorrecto
	 */
	public void crearCelula(int f,int c) 
			throws IndicesFueraDeRango, CelulaExistente, FormatoNumericoIncorrecto{
		mundo.crearCelula(f, c);
	}
	
	/**
	 * actualiza el atributo simulacionTerminada para acabar la simulacion
	 */
	public void salir(){
		simulacionTerminada = true;
	}
	
	/**
	 * llama a vaciar de mundo
	 */
	public void vaciar(){
		mundo.vaciar();
	}
	
	/**
	 * inicializa el mundo con el parametro
	 * @param m
	 * @throws ErrorDeInicializacion
	 */
	public void juega(Mundo m) throws ErrorDeInicializacion{
		mundo=m;
		mundo.inicializaMundo();
	}
	
	/**
	 * carga de un archivo, llamando a los cargar de otras clases
	 * @param nomFich
	 * @throws FileNotFoundException
	 * @throws PalabraIncorrecta
	 * @throws FaltaElemento
	 * @throws IndicesFueraDeRango
	 * @throws ErrorDeInicializacion
	 */
	@SuppressWarnings("resource")
	public void cargar(String nomFich) 
			throws FileNotFoundException, PalabraIncorrecta, FaltaElemento, IndicesFueraDeRango, ErrorDeInicializacion{
		
		File fich = null;
		Scanner sc = null;
		
		try{	
				fich = new File(nomFich);
				sc = new Scanner(fich);
				String linea = sc.nextLine();
				linea = linea.toUpperCase();
				
				if(linea.equals("SIMPLE"))
					mundo = new MundoSimple(0,0,0);
				else if(linea.equals("COMPLEJO"))
					mundo = new MundoComplejo(0,0,0,0);
				else
					throw new PalabraIncorrecta("");
				
				mundo.cargar(sc);
				sc.close();
				
		}catch(FileNotFoundException ex){
			throw new FileNotFoundException();
		}
	}
	
	/**
	 * guarda la partida,llamando a los guardar de otras clases
	 * @param nomFich
	 * @throws IOException
	 */
	public void guardar(String nomFich) throws IOException{
		
		FileWriter fich = null;
		
		try {
			fich = new FileWriter(nomFich);
			mundo.guardar(fich);
			fich.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	
	}	
}
