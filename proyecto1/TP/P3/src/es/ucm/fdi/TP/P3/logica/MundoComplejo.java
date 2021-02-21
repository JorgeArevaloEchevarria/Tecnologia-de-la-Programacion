package es.ucm.fdi.TP.P3.logica;


import java.util.InputMismatchException;
import java.util.Scanner;

import es.ucm.fdi.TP.P3.excepciones.CelulaExistente;
import es.ucm.fdi.TP.P3.excepciones.ErrorDeInicializacion;
import es.ucm.fdi.TP.P3.excepciones.FormatoNumericoIncorrecto;
import es.ucm.fdi.TP.P3.excepciones.IndicesFueraDeRango;

/**
 *Clase de MundoComplejo
 */
public class MundoComplejo extends Mundo{

	private int celulasSimples;
	private int celulasComplejas;
	private Scanner scanner;
	
	/**
	 * Constructor de MundoComplejo
	 * @param f
	 * @param c
	 * @param celS
	 * @param celC
	 * @throws ErrorDeInicializacion
	 */
	public MundoComplejo(int f,int c, int celS,int celC) throws ErrorDeInicializacion{
		
		super(f,c);
		celulasSimples = celS;
		celulasComplejas = celC;
		
	}
	
	/**
	 * Inicializa el mundo
	 */
	public void inicializaMundo() {
	
	  sup = new Superficie (nfilas, ncolumnas);

	  vaciar();
	  
	  boolean creado=false;
	  int nc, nf;
	  int i = 0, j = 0;
	  
	  while(i<celulasSimples){
	   
	   nc = (int) (Math.random()*ncolumnas);//aleatorio para las columnas
	   nf = (int) (Math.random()*nfilas);//aleatorio para las filas
	   creado = sup.ponerCelula(nf, nc,new CelulaSimple());
	    
	   if(creado)
	    i++;
	  
	  }
	  
	  while(j<celulasComplejas){
	   
	   nc = (int) (Math.random()*ncolumnas);//aleatorio para las columnas
	   nf = (int) (Math.random()*nfilas);//aleatorio para las filas
	   creado = sup.ponerCelula(nf, nc,new CelulaCompleja());
	    
	   if(creado)
	    j++;
	   
	  }
	
	}

	
	/**
	 * crea una celula segun te lo pida el usuario
	 * @param fila
	 * @param columna
	 * @throws CelulaExistente
	 * @throws IndicesFueraDeRango
	 * @throws FormatoNumericoIncorrecto
	 */
	public void crearCelula(int fila, int columna) 
			throws CelulaExistente, IndicesFueraDeRango, FormatoNumericoIncorrecto {
		
		try{	
			
			scanner = new Scanner(System.in);
			System.out.print("De que tipo: Compleja(1) o Simple(2): ");
			
			int scr = scanner.nextInt();
			
			while(scr != 1 && scr != 2){
				System.out.println("No es uno de los numeros que te hemos pedido!Elige otra vez:");
				scr = scanner.nextInt();
			}	
			
			if(scr==1){
				
				if(sup.ponerCelula(fila, columna, new CelulaCompleja()))
					 System.out.print("Creamos nueva celula Compleja en la posicion: (" + fila +","+ columna + ")"+  "\n");
				
				 else
					 throw new CelulaExistente("");
				
			}else{
				
				 if(sup.ponerCelula(fila, columna, new CelulaSimple()))
						System.out.print("Creamos nueva celula Simple en la posicion: (" + fila +","+ columna + ")"+  "\n");
					   
				 else
					 throw new CelulaExistente("");
				
			}	
		
		}catch(ArrayIndexOutOfBoundsException e){
			throw new IndicesFueraDeRango("");
			
		}catch(InputMismatchException e){
			throw new FormatoNumericoIncorrecto("");
		}
	}
	
	/**
	 * devuelve un string indicando que el mundo es complejo
	 * @return "complejo"
	 */
	public String tipoMundo() {
		return "complejo";
	}

}
