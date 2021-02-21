package es.ucm.fdi.tp.practica5.ataxx;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.grafica.RectBoardSwingView;

public class AtaxxSwingView extends RectBoardSwingView {
	
	private static final long serialVersionUID = 1L;
	
	private AtaxxSwingPlayer player;
	private int oRow;
	private int oCol;
	private int dRow;
	private int dCol;
	private boolean activate;
	private boolean pulsada = true;


	public AtaxxSwingView(Observable<GameObserver> g, Controller c,
			Piece localPiece, Player randPlayer, Player aiPlayer) {	
		super(g, c, localPiece, randPlayer, aiPlayer);
		player = new AtaxxSwingPlayer();
	}


	protected void activateBoard() {
		activate=true;
	}

	protected void deActivateBoard(){
		activate=false;
	}


	protected void handleMouseClick(int row, int col, int clickCount,int mouseButton) {
		
		  if(pulsada && activate){
			   this.oRow = row;
			   this.oCol = col;
			   pulsada = false;
			   addContentToStatus("Choose one destination for your piece");
			   
		  }else{
				  
			  if(mouseButton == 3){
				  pulsada = true;
				  addContentToStatus("You've canceled your move");
				  return;
			  }

			  this.dRow=row;
			  this.dCol = col;
			  player.setMoveValue(oRow, oCol, dRow, dCol);
			  decideMakeManualMove(player); 
			  pulsada=true;
		  }
	}
}
	