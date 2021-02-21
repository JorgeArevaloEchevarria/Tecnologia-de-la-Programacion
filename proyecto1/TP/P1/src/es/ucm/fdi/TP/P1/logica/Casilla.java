package es.ucm.fdi.TP.P1.logica;

public class Casilla {
	
	private int x;
	private int y;
	
	public Casilla(int pos_x,int pos_y){//constructor de casilla
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
