 package es.ucm.fdi.tp.practica5.connectN;


import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.grafica.RectBoardSwingView;

public class ConnectNSwingView extends RectBoardSwingView{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConnectNSwingPlayer player;
	private boolean activate;
	
	
	public ConnectNSwingView(Observable<GameObserver> g, Controller c, Piece localPiece, Player randPlayer,
			Player aiPlayer) {
		super(g, c, localPiece, randPlayer, aiPlayer);
		player = new ConnectNSwingPlayer();
	}


	protected void activateBoard() {
		activate = true;
		
	}

	protected void deActivateBoard() {
		activate = false;
		
	}


	protected void handleMouseClick(int row, int col, int clickCount, int mouseButton) {
		if(activate){
			player.setRow(row);
			player.setCol(col);
		}
		
		decideMakeManualMove(player);
		
	}


}
