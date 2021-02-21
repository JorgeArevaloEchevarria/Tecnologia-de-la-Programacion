package es.ucm.fdi.tp.practica5.connectN;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.connectn.ConnectNMove;

public class ConnectNSwingPlayer extends Player{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int row;
	private int col;
	@Override
	
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {
		return createMove(row,col,p);
	}
	
	private GameMove createMove(int row,int col,Piece p){
		return new ConnectNMove(row,col,p);
	}
	
	public void setRow(int r){
		this.row = r;
	}
	
	public void setCol(int c){
		this.col = c;
	}
}
