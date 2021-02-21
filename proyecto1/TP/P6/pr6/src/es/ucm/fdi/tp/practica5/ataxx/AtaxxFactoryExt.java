package es.ucm.fdi.tp.practica5.ataxx;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class AtaxxFactoryExt extends AtaxxFactory{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AtaxxFactoryExt(int obst){
		this(7,obst);
	}
	
	public AtaxxFactoryExt(int dim,int obs){
		super(dim,obs);
	}
	public void createSwingView(final Observable<GameObserver> g, final Controller c, final Piece viewPiece,
			Player random, Player ai){
		
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				   @Override
				   public void run() {
				    new AtaxxSwingView(g, c, viewPiece, random, ai);
				   }
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}