package es.ucm.fdi.TP.P3.logica;

/**
 *Clase casilla
 */
public class Casilla {
	
	private int x;
	private int y;
	
	/**
	 * constructor de casilla
	 * @param pos_x
	 * @param pos_y
	 */
	public Casilla(int pos_x,int pos_y){
		x=pos_x;
		y=pos_y;
	}
	
	/**
	 * devuelve el valor del atributo x
	 * @return x
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * devuelve el valor del atributo y
	 * @return y
	 */
	public int getY(){
		return y;
	}

}
