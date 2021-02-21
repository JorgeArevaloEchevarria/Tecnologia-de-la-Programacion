package es.ucm.fdi.tp.practica5.ttt;

import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.ttt.TicTacToeFactory;
import es.ucm.fdi.tp.practica5.connectN.ConnectNSwingView;

public class TicTacToeFactoryExt extends TicTacToeFactory{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void createSwingView(final Observable<GameObserver> g, final Controller c, final Piece viewPiece,
			Player random, Player ai){
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ConnectNSwingView(g, c, viewPiece,random,ai);
			}
		});
	}

}
