package es.ucm.fdi.TP.P2.logica;

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
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

}
