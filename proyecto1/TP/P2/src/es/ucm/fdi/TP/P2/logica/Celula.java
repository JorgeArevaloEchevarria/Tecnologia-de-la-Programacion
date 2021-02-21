package es.ucm.fdi.TP.P2.logica;

public abstract class Celula {
	
	protected boolean esComestible;
	
	public abstract Casilla ejecutaMovimiento(int f, int c, Superficie superficie);
	public abstract boolean esComestible();
	
}
