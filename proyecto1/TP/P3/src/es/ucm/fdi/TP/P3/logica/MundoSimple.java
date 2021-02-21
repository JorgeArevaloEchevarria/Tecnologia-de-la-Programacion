package es.ucm.fdi.TP.P3.logica;

import es.ucm.fdi.TP.P3.excepciones.CelulaExistente;
import es.ucm.fdi.TP.P3.excepciones.IndicesFueraDeRango;

/**
 *Clase de MundoSimple
 */
public class MundoSimple extends Mundo{
	
	private int celulas;
	
	/**
	 * Constructor de MundoSimple
	 * @param f
	 * @param c
	 * @param cel
	 */
	public MundoSimple(int f, int c, int cel){
		super(f,c);
		celulas = cel;
	}
	
	/**
	 * Inicializa el mundo
	 */
	public void inicializaMundo(){

		if(sup != null)
			vaciar();

		sup = new Superficie(nfilas,ncolumnas);

		boolean creado=false;
		int nc, nf;
		int i = 0;

		while(i<celulas){

			nc = (int) (Math.random()*ncolumnas);//aleatorio para las columnas
			nf = (int) (Math.random()*nfilas);//aleatorio para las filas
			creado = sup.ponerCelula(nf, nc,new CelulaSimple());

			if(creado)
				i++;
		}
		
	}
	
	
	/**
	 * @param fila
	 * @param columna
	 * @throws IndicesFueraDeRango
	 * @throws CelulaExistente
	 */
	public void crearCelula(int fila, int columna) throws IndicesFueraDeRango, CelulaExistente{
		
		
		try{	
			
			if(sup.ponerCelula(fila, columna, new CelulaSimple()))
					System.out.print("Creamos nueva celula en la posicion: (" + fila +","+ columna + ")"+  "\n");
			else
				throw new CelulaExistente("");
			
		}catch(ArrayIndexOutOfBoundsException e){
			throw new IndicesFueraDeRango("");
		}
	 
	}
	
	/**
	 * devuelve un string indicando que el mundo es simple
	 * @return "simple"
	 */
	public String tipoMundo() {
		return "simple";
	}
}
