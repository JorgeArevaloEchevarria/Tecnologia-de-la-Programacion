package Practica3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import Practica3.ByteCode.ByteCode;
import Practica3.ByteCode.ByteCodeParser;
import Practica3.Command.Command;
import Practica3.Command.CommandParser;
import Practica3.Command.Compile;
import Practica3.Excepciones.ArrayException;
import Practica3.Excepciones.BadFormatByteCode;
import Practica3.Excepciones.DivisionByZero;
import Practica3.Excepciones.ExecutionError;
import Practica3.Excepciones.LexicalAnalysisException;
import Practica3.Excepciones.StackException;

/**
 * 
 * @author jorge
 *
 */
public class Engine {
	
	private boolean end; // controla que no se ha ejecutado el comando QUIT
	private ByteCodeProgram bcProgram;
	private static Scanner in = new Scanner(System.in);
	private CPU cpu;
	private SourceProgram sProgram;
	private ParsedProgram pProgram;
	private Scanner sc; 
	//tiene a parte el analizador lexico
	private LexicalParser lexical;
	private Compile compilador;
	/**
	 * 
	 */
	public Engine(){     
		this.compilador=new Compile();
		this.bcProgram = compilador.CodeProgram();
		this.cpu = new CPU(bcProgram);
		this.end = false;
		this.pProgram = new ParsedProgram();
		this.sProgram = new SourceProgram();
		this.sc= new java.util.Scanner(System.in);
		this.lexical= new LexicalParser(this.sProgram);	
	}

	/**
	 * @throws ExecutionError 
	 * @throws StackException 
	 * 
	 */
	public void start() {
		
		String line = "";
		while (!this.end){
			System.out.println("");
			System.out.print("> ");
			
			//help();//muestra todos los comandos al principio
			line = sc.nextLine();// lee una línea “line”
				Command com = CommandParser.parse(line);
			if (com == null)// mensaje de error
				System.out.println("Error : Comando incorrecto");
			
			else {
			// mensaje informativo del comando a ejecutar
				System.out.println("Comienza la ejecucion de "+ com);
				try{
					if (!com.execute(this))
					System.out.println("Error: Ejecucion incorrecta del comando");// mensaje de errorFileNotFoundException
			
				}	
				catch(ArrayException e){
					System.out.println("Error al acceder al array");
				}
				catch(LexicalAnalysisException e){
					System.out.println("Error en el analisis lexico");
				}
				catch(FileNotFoundException e){
					System.out.println("Error, no se ha encontrado el archivo introducido");
				}
				catch(BadFormatByteCode e){
					System.out.println("sintaxis incorrecta en bytecodes ");
				}
				catch(ExecutionError e){
					System.out.print(e.toString());
					
				}
			}
			if(this.sProgram.getNumeroLineas()>0){
				System.out.println(this.sProgram);
				System.out.println(this.bcProgram);
			}
		}	
		sc.close();	
		System.out.println("Fin de la ejecucion...");
	}

	/**
	 * 
	 * @return error
	 * @throws StackException 
	 * @throws ExecutionError 
	 * @throws BadFormatByteCode 
	 * @throws DivisionByZero 
	 */
	public boolean executeCommandRun() throws BadFormatByteCode, ExecutionError, StackException, DivisionByZero{
		// inicialización de variables
		return this.cpu.run();	
	}
	/**
	 * 
	 * @return end
	 */
	public boolean endExecution(){//acaba la ejecucion
		this.end = true;
		return end;
	}
	/**
	 * 
	 * @param bc
	 * @return this.cpu.addByteCodeInstruction(bc);
	 */
	public boolean addByteCodeInstruction(ByteCode bc){//añade una instruccion al programa
		return this.cpu.addByteCodeInstruction(bc);
	}
	/**
	 * 
	 * @return true
	 */
	public boolean resetProgram(){//resetea el programa
		return cpu.resetProgram();
	}   
	/**
	 * 
	 * @param replace
	 * @return
	 * @throws BadFormatByteCode 
	 */
	public boolean replaceByteCode(int replace) throws BadFormatByteCode{//remplaza una instruccion
		if(cpu.replaceCorrecto(replace)){
			System.out.println("Nueva instrucción: ");
			String s = in.nextLine();
			cpu.replaceByteCode(replace,s);
			return true; 
		}else{
			System.out.println("La poscion de la instuccion que desea variar es incorrecta");
			return false;
		}
			
	}
	/**
	 * 
	 * @return read;
	 */
	public boolean readByteCodeProgram(){
		boolean read = false;
		this.resetProgram();
		String line;
		line= in.nextLine();
		line= line.trim();
		String[] tokens;
		ByteCode bc;
		tokens = line.split(" +");
		
		while(!tokens[0].equalsIgnoreCase("END")){
			
			bc=ByteCodeParser.parse(line);
			if(bc!=null)
				this.cpu.addByteCodeInstruction(bc);
			else
				System.out.println("ByteCode incorrecto");
			
			line = in.nextLine();
			line = line.trim();
			tokens = line.split(" +");
			read = true;
		}
		return read;
	}
	
	public void compile() throws LexicalAnalysisException,ArrayException{
		
		this.lexicalAnalysis();
		this.generateBytecode();
		
	}
	
	private  ParsedProgram lexicalAnalysis() throws LexicalAnalysisException{
		//this.sProgram.ae
		this.lexical.lexicalParser(this.pProgram,"end");
		return this.pProgram;
	}
	
	private ParsedProgram generateBytecode() throws ArrayException{
		this.compilador.compile(this.pProgram);
		return this.pProgram;
	}
	/// load lanza y captura el desbordamiento
	@SuppressWarnings("resource")
	public void load(String nomArch ) throws LexicalAnalysisException, ArrayException,FileNotFoundException {
		//String arch ="arch.txt";
		File fich = null;
		Scanner sc = null;
			fich = new File(nomArch);
			sc = new Scanner(fich);
			//String linea = sc.nextLine();
			//linea = linea.toUpperCase();
			
			while(sc.hasNextLine()){
				if(!this.sProgram.llena())
					throw new ArrayException("Excepcion : limite capacidad programa fuente. ");
				else
					this.sProgram.addLinea(sc.nextLine());
			}
		sc.close();
	}
	/**
	 * 	
	 */
	public static void help(){//muestra la ayuda
		 
		 System.out.println("");
		 System.out.println("HELP: Muestra esta ayuda");
		 System.out.println("QUIT: Cierra la aplicacion");
		 System.out.println("RUN: Ejecuta el programa");
		 System.out.println("NEWINST BYTECODE: Introduce una nueva instrucción al programa");
		 System.out.println("RESET: Vacia el programa actual");
		 System.out.println("REPLACE N: Reemplaza la instruccion N por la solicitada al usuario");
		  
	 }
	
}
